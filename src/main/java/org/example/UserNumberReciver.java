package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserNumberReciver {

    ArrayList<Integer> userNumbers = new ArrayList<>();
    public ArrayList<Integer> inputNumbers() {
        Scanner s = new Scanner(System.in);
        for (int i = 0; i < 6; i++) {
            while (true) {
                System.out.println("Please enter number from range (1-49)");
                if (i >= 1) {
                    System.out.printf("Your current numbers are :%s%n", userNumbers);
                }
                try{
                    String numberString = s.nextLine();
                    int number = Integer.parseInt(numberString);
                    if (number >= 1 && number <= 49 && !userNumbers.contains(number)) {
                        userNumbers.add(number);
                        break;
                    } else {
                        System.out.printf("ERROR: %d is not in range (1-49)! or You have already entered the number to your list. Please try again.%n", number);
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("ERROR: This is not a number! Please try again.");
                }
            }
        }
        s.close();
        return userNumbers;
    }

}
