package com.scales.days;

import com.scales.Main;

import java.io.IOException;
import java.util.*;

// this prompt comes with several wikipedia links, how informative! https://wikipedia.org/wiki/Junction_box and https://wikipedia.org/wiki/Euclidean_distance
// the previous question also came with one, but I didn't add it as a comment because I am a hater

// "The Elves are trying to figure out which junction boxes to connect so that electricity can reach every junction box"
public class Day8 {
    public static void run() throws IOException {
        // "This list describes the position of 20 junction boxes, one per line"
        ArrayList<String> lines = Main.parseDayLines(8);
        List<Vec3> junctionBoxes = lines.stream().map(e -> {
            String[] values = e.split(",");
            // "Each position is given as X,Y,Z coordinates"
            return new Vec3(Long.parseLong(values[0]), Long.parseLong(values[1]), Long.parseLong(values[2]));
        }).toList();

        HashMap<Vec3, ArrayList<Value>> circuitsMap = new HashMap<>(junctionBoxes.size());

        for (Vec3 box : junctionBoxes) {
            ArrayList<Value> possibleLinks = new ArrayList<>(
                    junctionBoxes.stream().map(e -> new Value(e, Math.sqrt(Math.pow(box.x - e.x, 2) + Math.pow(box.y - e.y, 2) + Math.pow(box.z - e.z, 2)))).toList()
            );

            // "focus on connecting pairs of junction boxes that are as close together as possible according to straight-line distance"
            possibleLinks.sort(Comparator.comparingDouble(other -> other.distance));

            circuitsMap.put(box, possibleLinks);
        }

        HashMap<Vec3, ArrayList<Vec3>> circuits = new HashMap<>();
        for (Vec3 box : junctionBoxes) circuits.put(box, new ArrayList<>(List.of(box)));

        Vec3 lastBox = null;
        long p1 = 0;
        long p2 = 0;

        int i = 0;
        while (true) {
            Optional<Vec3> key = circuitsMap.keySet().stream().min(Comparator.comparingDouble(e -> {
                ArrayList<Value> possibleLinks = circuitsMap.get(e);
                if (possibleLinks.size() == 1) return Double.MAX_VALUE;
                return circuitsMap.get(e).get(1).distance;
            }));

            Vec3 box = key.get();
            ArrayList<Value> possibleLinks = circuitsMap.get(box);
            if (possibleLinks.size() == 1) break;

            Value value = circuitsMap.get(box).get(1);
            possibleLinks.remove(value);

            // go find a pair, the connecting box will have the same distance and therefore be paired next loop
            if (lastBox == null) {
                lastBox = box;
                continue;
            }
            else {
                // "Because these two junction boxes were already in the same circuit, nothing happens"
                if (!circuits.get(box).equals(circuits.get(lastBox))) {
                    // "By connecting these two junction boxes together, because electricity can flow between them, they become part of the same circuit"
                    circuits.get(box).addAll(circuits.get(lastBox));
                    // update the circuit for every other box in the circuit.
                    for (Vec3 circuit : circuits.get(box)) {
                        circuits.put(circuit, circuits.get(box));
                    }
                }
            }

            // "connect together the 1000 pairs of junction boxes which are closest together"
            if (i == 999) {
                List<ArrayList<Vec3>> sortedCircuits = circuits.values().stream().distinct().toList().stream().sorted(Comparator.comparingInt(e -> -e.size())).toList();
                // "what do you get if you multiply together the sizes of the three largest circuits"
                p1 = (long) sortedCircuits.get(0).size() * sortedCircuits.get(1).size() * sortedCircuits.get(2).size();
            }

            i++;

            if (circuits.values().stream().max(Comparator.comparingInt(ArrayList::size)).get().size() == junctionBoxes.size()) {
                p2 = lastBox.x * box.x;
                break;
            }

            lastBox = null;
        }

        System.out.println("Part one: " + p1);
        System.out.println("Part two: " + p2);
    }

    private record Vec3(long x, long y, long z) {}
    private record Value(Vec3 box, double distance) {}
}