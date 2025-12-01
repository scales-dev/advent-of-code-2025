package com.scales;

import com.scales.days.Day1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hi, thank you for checking out my advent of code solutions!");

        //Scanner scanner = new Scanner(System.in);
        //System.out.print("Enter which solution you would like to run: ");
        //int solution = scanner.nextInt();
        //scanner.close();
        int solution = 1;

        switch (solution) {
            case 1 -> Day1.run();
        }

    }
}