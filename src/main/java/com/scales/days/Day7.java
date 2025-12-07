package com.scales.days;

import com.scales.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// "After connecting one of the diagnostic tools, it helpfully displays error code 0H-N0, which apparently means that there's an issue with one of the tachyon manifolds"
public class Day7 {
    public static void run() throws IOException {
        ArrayList<String> lines = Main.parseDayLines(7);

        int splitsP1 = countSplitters(lines);

        // this was an int, and i was confused why it was returning a negative number.
        long splitsP2 = 0;

        // finding the splitter pos
        String line = lines.getFirst();
        for (int j = 0; j < line.length(); j++) {
            if (line.charAt(j) == 'S') {
                splitsP2 = split(lines, 1, j) + 1;
                break;
            }
        }

        System.out.println("Part one: " + splitsP1);
        System.out.println("Part two: " + splitsP2);
    }

    // bad solution, sorry.
    private static int countSplitters(ArrayList<String> lines) {
        int width = lines.getFirst().length();
        int[] currentSplitters = new int[width];

        int splitters = 0;

        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                switch (c) {
                    // "A tachyon beam enters the manifold at the location marked S"
                    case 'S':
                        currentSplitters[i]++;
                        break;

                    // "if a tachyon beam encounters a splitter (^), the beam is stopped; instead, a new tachyon beam continues from the immediate left and from the immediate right of the splitter"
                    case '^':
                        if (currentSplitters[i] == 1) {
                            currentSplitters[i]--;
                            if (currentSplitters[i - 1] == 0) currentSplitters[i - 1]++;
                            if (currentSplitters[i + 1] == 0) currentSplitters[i + 1]++;
                            splitters++;
                        }
                        break;

                    // "Tachyon beams pass freely through empty space (.)"
                }
            }
        }

        return splitters;
    }

    // caching is NEEDED or else it will take FOREVER
    private static final HashMap<String, Long> cache = new HashMap<>();
    // pray for no stack overflow.
    private static long split(ArrayList<String> lines, int line, int pos) {
        for (int i = line; i < lines.size(); i++) {
            String cacheKey = "" + i + pos;

            // god bless hash map
            if (cache.containsKey(cacheKey)) return cache.get(cacheKey);

            // split, recurse, move on, up up and away.
            if (lines.get(i).charAt(pos) == '^') {
                long returnValue = 1 + split(lines, i+1, pos-1) + split(lines, i+1, pos+1);
                cache.put(cacheKey, returnValue);
                return returnValue;
            }
        }

        return 0;
    }
}
