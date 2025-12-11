package com.scales;

import com.scales.days.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hi, thank you for checking out my advent of code solutions!");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter which solution you would like to run: ");
            int solution = scanner.nextInt();
            long time = System.currentTimeMillis();
            switch (solution) {
                case 1 -> Day1.run();
                case 2 -> Day2.run();
                case 3 -> Day3.run();
                case 4 -> Day4.run();
                case 5 -> Day5.run();
                case 6 -> Day6.run();
                case 7 -> Day7.run();
                case 8 -> Day8.run();
            }

            System.out.println("Time taken: " + (System.currentTimeMillis() - time) + "ms");
        }
    }

    // "Puzzle inputs differ by user.  Please log in to get your puzzle input."
    private static BufferedReader parseDay(int day) {
        InputStream fileStream = Main.class.getResourceAsStream("/day" + day + ".txt");
        assert fileStream != null;

        return new BufferedReader(new InputStreamReader(fileStream));
    }

    public static String parseDayLine(int day) throws IOException {
        BufferedReader reader = parseDay(day);
        String line = reader.readLine();
        reader.close();

        return line;
    }

    public static ArrayList<String> parseDayLines(int day) throws IOException {
        BufferedReader reader = parseDay(day);
        ArrayList<String> lines = new ArrayList<>();
        while (reader.ready()) lines.add(reader.readLine());
        reader.close();

        return lines;
    }
}