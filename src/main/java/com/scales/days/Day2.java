package com.scales.days;

import com.scales.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Day2 {
    public static void run() throws IOException {
        InputStream fileStream = Main.class.getResourceAsStream("/day2.txt");
        assert fileStream != null;

        BufferedReader fileReader = new BufferedReader(new InputStreamReader(fileStream));

        String line = fileReader.readLine();
        String[] ranges = line.split(",");

        long invalidTotalP1 = 0;
        long invalidTotalP2 = 0;

        for (String range : ranges) {
            long lowerBound = Long.parseLong(range.split("-")[0]);
            long upperBound = Long.parseLong(range.split("-")[1]);

            for (long i = lowerBound; i <= upperBound; i++) {
                // "What do you get if you add up all of the invalid IDs?"
                if (!isValidPart1(i)) invalidTotalP1 += i;
                if (!isValidPart2(i)) invalidTotalP2 += i;
            }
        }

        System.out.println("Part one: " + invalidTotalP1);
        System.out.println("Part two: " + invalidTotalP2);
    }

    private static boolean isValidPart1(long num) {
        String str = String.valueOf(num);

        int half = str.length() / 2;
        String firstHalf = str.substring(0, half);
        String secondHalf = str.substring(half);

        // "any ID which is made only of some sequence of digits repeated twice. So, 55 (5 twice), 6464 (64 twice), and 123123 (123 twice) would all be invalid IDs."
        return !firstHalf.equals(secondHalf);
    }

    // not very fast, takes 2453ms seconds on my computer, however, I coded it very fast; it was my first idea.
    private static boolean isValidPart2(long num) {
        String str = String.valueOf(num);

        // yeah str.length() / 2, I'm kinda a god at optimising :pray:
        for (int i = 0; i <= str.length() / 2; i++) {
            String repeatedableString = str.substring(0, i);
            if (str.replaceAll(repeatedableString, "").isEmpty()) return false;
        }

        return true;
    }
}
