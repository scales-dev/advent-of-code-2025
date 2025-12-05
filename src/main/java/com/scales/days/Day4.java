package com.scales.days;

import com.scales.Main;

import java.io.IOException;
import java.util.ArrayList;

// "If you can optimize the work the forklifts are doing, maybe they would have time to spare to break through the wall."
public class Day4 {
    public static void run() throws IOException {
        ArrayList<String> lines = Main.parseDayLines(4);

        // "Consider your complete diagram of the paper roll locations. How many rolls of paper can be accessed by a forklift?"
        int accessibleRolls = 0;
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                // not a roll, skip
                if (lines.get(i).charAt(j) == '.') continue;

                // "The forklifts can only access a roll of paper if there are fewer than four rolls of paper in the eight adjacent positions"
                if (adjacentRolls(lines, i, j) >= 4) continue;

                accessibleRolls++;
            }
        }

        // "Once a roll of paper can be accessed by a forklift, it can be removed. Once a roll of paper is removed, the forklifts might be able to access more rolls of paper"
        int removedTotal = 0;
        Removed removedRolls = removeRolls(lines);

        // "Stop once no more rolls of paper are accessible by a forklift"
        while (removedRolls.removedTotal() > 0) {
            removedTotal += removedRolls.removedTotal();
            removedRolls = removeRolls(removedRolls.returnString());
        }

        System.out.println("Part one: " + accessibleRolls);
        System.out.println("Part two: " + removedTotal);
    }

    private static int adjacentRolls(ArrayList<String> lines, int row, int col) {
        int adjacentRolls = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;
                if (row + x < 0 || row + x >= lines.size() || col + y < 0 || col + y >= lines.get(row).length()) continue;

                if (lines.get(row + x).charAt(col + y) == '@') adjacentRolls++;
            }
        }

        return adjacentRolls;
    }

    // not my finest work, i kinda rushed, i gotta go to school now
    private static Removed removeRolls(ArrayList<String> lines) {
        ArrayList<String> newLines = new ArrayList<>(lines);
        int removedTotal = 0;

        for (int row = 0; row < lines.size(); row++) {
            StringBuilder line = new StringBuilder();
            for (int col = 0; col < lines.get(row).length(); col++) {
                if (lines.get(row).charAt(col) == '.') line.append('.');
                else if (adjacentRolls(lines, row, col) >= 4) line.append('@');
                else {
                    line.append('.');
                    removedTotal++;
                }
            }
            newLines.set(row, line.toString());
        }

        return new Removed(newLines, removedTotal);
    }

    private record Removed(ArrayList<String> returnString, int removedTotal) {}
}
