package com.scales.days;

import com.scales.Main;

import java.io.IOException;
import java.util.ArrayList;

// this runs in 1ms on my computer! i did it!!! im fast!!!!
public class Day3 {
    public static void run() throws IOException {
        ArrayList<String> lines = Main.parseDayLines(3);

        long totalOutputJoltageP1 = 0;
        long totalOutputJoltageP2 = 0;

        for (String line : lines) {
            // "The total output joltage is the sum of the maximum joltage from each bank"
            totalOutputJoltageP1 += parsePart1Joltage(line);
            totalOutputJoltageP2 += parsePart2Joltage(line);
        }

        System.out.println("Part one: " + totalOutputJoltageP1);
        System.out.println("Part two: " + totalOutputJoltageP2);
    }

    // "Within each bank, you need to turn on exactly two batteries; the joltage that the bank produces is equal to the number formed by the digits on the batteries you've turned on"
    private static int parsePart1Joltage(String batteryBank) {
        int highestInt = 0;
        int highestIntPos = 0;
        int loops = 0;
        for (int i = highestIntPos; i < batteryBank.length() - 1; i++) {
            int c = batteryBank.charAt(i) - '0';

            if (c > highestInt) {
                highestInt = c;
                highestIntPos = loops;
            }
            loops++;
        }

        int secondHighestInt = 0;

        for (int i = highestIntPos + 1; i < batteryBank.length(); i++) {
            int c = batteryBank.charAt(i) - '0';
            if (c > secondHighestInt) {
                secondHighestInt = c;
            }
        }

        return Integer.parseInt("" + highestInt + secondHighestInt);
    }

    // "the only difference is that now there will be 12 digits in each bank's joltage output instead of two"
    private final static int MAX_BATTERIES = 12;
    private static long parsePart2Joltage(String batteryBank) {
        StringBuilder joltage = new StringBuilder();

        int skipDigits = 0;
        int loops = MAX_BATTERIES;

        while (loops-- > 0) {
            int highest = 0;
            for (int i = skipDigits; i < batteryBank.length() - loops; i++) {
                int c = batteryBank.charAt(i) - '0';

                if (c > highest) {
                    highest = c;
                    skipDigits = i + 1;
                }
            }

            joltage.append(highest);
        }

        return Long.parseLong(joltage.toString());
    }
}
