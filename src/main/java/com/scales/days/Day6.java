package com.scales.days;

import com.scales.Main;

import java.io.IOException;
import java.util.ArrayList;

// "While you wait, they're curious if you can help the youngest cephalopod with her math homework"
public class Day6 {
    public static void run() throws IOException {
        ArrayList<String> lines = Main.parseDayLines(6);
        int numbers = lines.size() - 1;
        int lineLength = lines.getFirst().length();

        long sumP1 = 0;
        long sumP2 = 0;

        ArrayList<String> valuesP1 = new ArrayList<>();
        ArrayList<String> valuesP2 = new ArrayList<>();
        for (int i = 0; i < lineLength; i++) {
            // stores a whole column of numbers, how great.
            StringBuilder columnNumbers = new StringBuilder();

            // "they seem to be presented next to each other in a very long horizontal list"
            for (int j = 0; j < numbers; j++) {
                char c = lines.get(j).charAt(i);

                // adding the whole number in a line
                if (valuesP1.size() <= j) valuesP1.add(c+"");
                else valuesP1.set(j, valuesP1.get(j) + c);

                if (c == ' ') continue;

                columnNumbers.append(c);
            }

            if (columnNumbers.isEmpty()) {
                // "To check their work, cephalopod students are given the grand total of adding together all of the answers to the individual problems"
                sumP1 += sum(valuesP1, lines, i, numbers);
                sumP2 += sum(valuesP2, lines, i, numbers);
                continue;
            }

            // "Reading the problems right-to-left one column at a time, the problems are now quite different"
            valuesP2.add(columnNumbers.toString());
        }

        // add the remaining numbers boom chicka wow wow
        sumP1 += sum(valuesP1, lines, lineLength, numbers);
        sumP2 += sum(valuesP2, lines, lineLength, numbers);

        System.out.println("Part one: " + sumP1);
        System.out.println("Part two: " + sumP2);
    }

    private static long sum(ArrayList<String> values, ArrayList<String> lines, int pos, int operatorRow) {
        // "Each problem's numbers are arranged vertically; at the bottom of the problem is the symbol for the operation that needs to be performed"
        boolean addition = lines.get(operatorRow).substring(0, pos).trim().endsWith("+");

        long sum = addition ? 0 : 1;
        for (String value : values) {
            long number = Long.parseLong(value.trim());
            sum = addition ? sum + number : sum * number;
        }
        values.clear();

        return sum;
    }
}