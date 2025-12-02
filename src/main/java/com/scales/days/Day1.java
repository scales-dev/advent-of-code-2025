package com.scales.days;

import com.scales.Main;

import java.io.IOException;
import java.util.ArrayList;

// https://adventofcode.com/2025/day/1
public class Day1 {
    public static void run() throws IOException {
        // "The dial starts by pointing at 50."
        int pos = 50;

        int timesLandedOn0 = 0;
        int timesCrossed0 = 0;

        ArrayList<String> lines = Main.parseDayLines(1);
        for (String line : lines) {
            int prevPos = pos;

            // "A rotation starts with an L or R which indicates whether the rotation should be to the left (toward lower numbers) or to the right (toward higher numbers)."
            int dir = line.charAt(0) == 'R' ? 1 : -1;
            int dist = Integer.parseInt(line.substring(1));

            // more than a hundred, too big, please leave
            int fullCircles = (int) Math.floor(dist / 100d);
            timesCrossed0 += fullCircles;

            // add the remaining number
            int wholeIncrement = Math.floorMod(dist, 100) * dir;
            pos += wholeIncrement;

            // number still too big, wrap around
            if (pos >= 100 || pos <= 0) {
                // "Because the dial is a circle, turning the dial left from 0 one click makes it point at 99. Similarly, turning the dial right from 99 one click makes it point at 0."
                pos = Math.floorMod(pos, 100);

                // if the previous number is 0, we already incremented last time
                if (prevPos != 0) timesCrossed0++;
            }

            // "The actual password is the number of times the dial is left pointing at 0 after any rotation in the sequence."
            if (pos == 0) timesLandedOn0++;
        }

        System.out.println("Part one: " + timesLandedOn0);
        System.out.println("Part two: " + timesCrossed0);
    }
}
