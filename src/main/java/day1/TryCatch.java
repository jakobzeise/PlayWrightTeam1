package day1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatch {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please give me a number");

        try {
            int number = scanner.nextInt();
            System.out.println(number);
        } catch (InputMismatchException e) {
            System.out.println("Something went wrong!");
        }

        System.out.println("Continuation of your program");



    }
}
