package com.scales.days;

import com.scales.Main;

import java.io.IOException;
import java.util.ArrayList;

// "If only we hadn't switched to the new inventory management system right before Christmas!"
public class Day5 {
    public static void run() throws IOException {
        ArrayList<String> lines = Main.parseDayLines(5);
        ArrayList<long[]> ranges = new ArrayList<>();

        // "The Elves are trying to determine which of the available ingredient IDs are fresh"
        int fresh = 0;
        boolean addingRanges = true;

        for (String line : lines) {
            if (!addingRanges) {
                long value = Long.parseLong(line);
                for (long[] range : ranges) {
                    // "The fresh ID ranges are inclusive"
                    if (value >= range[0] && value <= range[1]) {
                        fresh++;
                        break;
                    }
                }
            }
            // "It consists of a list of fresh ingredient ID ranges, a blank line, and a list of available ingredient IDs."
            else if (line.isBlank()) {
                addingRanges = false;

                // removing overlapping ranges, not in a very smart way, my bad.
                for (int i = 0; i < ranges.size(); i++) {
                    long[] range1 = ranges.get(i);
                    for (int j = 0; j < ranges.size(); j++) {
                        if (j == i) continue;
                        long[] range2 = ranges.get(j);

                        // wow our ranges are overlapping, go away.
                        if (range1[0] >= range2[0] && range1[0] <= range2[1]) range1[0] = range2[1] + 1;
                        if (range1[1] >= range2[0] && range1[1] <= range2[1]) range1[1] = range2[0] - 1;
                    }

                    // min is more than max, please leave
                    if (range1[0] > range1[1]) {
                        ranges.remove(i--);
                        continue;
                    }

                    ranges.set(i, range1);
                }
            }
            else {
                String[] values = line.split("-");

                ranges.add(new long[]{Long.parseLong(values[0]), Long.parseLong(values[1])});
            }
        }

        // "the Elves would like to know all of the IDs that the fresh ingredient ID ranges consider to be fresh"
        long totalValid = ranges.stream().mapToLong(range -> range[1] - range[0] + 1).sum();

        System.out.println("Part one: " + fresh);
        // "Now, the second section of the database (the available ingredient IDs) is irrelevant" thanks aoc.
        System.out.println("Part two: " + totalValid);
    }
}
