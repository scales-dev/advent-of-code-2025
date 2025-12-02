package com.scales.days;

import com.scales.Main;

import java.io.IOException;

// https://adventofcode.com/2025/day/2
public class Day2 {
    public static void run() throws IOException {
        String line = Main.parseDayLine(2);

        String[] ranges = line.split(",");

        long invalidTotalP1 = 0;
        long invalidTotalP2 = 0;

        for (String range : ranges) {
            // "The ranges are separated by commas (,); each range gives its first ID and last ID separated by a dash (-)."
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

    // as optimised as I could think of, still takes ~170ms on my computer :(
    private static boolean isValidPart2(long num) {
        String str = String.valueOf(num);

        // yeah str.length() / 2, I'm kinda a god at optimising :pray:
        for (int i = 1; i <= str.length() / 2; i++) {
            // if the string length is not divisible by i, then it can't be the repeated string
            if (str.length() % i != 0) continue;

            // .substring(0, i) is somehow faster than adding to a string builder with .charAt or toCharArray
            String repeatedableString = str.substring(0, i);

            // we loop through the rest of the string and see if at any point stops matching the current repeated string
            for (int j = i; j < str.length(); j+=i) {
                // yay, it doesn't match; the silly elf has NOT faked this ID
                if (!str.substring(j, j + i).equals(repeatedableString)) break;

                // wow, we made it, go team!
                if (j + i == str.length()) return false;
            }
        }

        return true;
    }
}
