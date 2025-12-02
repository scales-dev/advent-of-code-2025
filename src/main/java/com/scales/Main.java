package com.scales;

import com.scales.days.Day1;
import com.scales.days.Day2;

import java.io.IOException;
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
            }

            System.out.println("Time taken: " + (System.currentTimeMillis() - time) + "ms");
        }
    }
}