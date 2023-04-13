package org.example.console.ui;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import org.example.InputReceivable;

public class ScannerInputReceiver implements InputReceivable {

    Scanner scanner = new Scanner(System.in);

    @Override
    public Set<Integer> inputSixNumbers() {
        Set<Integer> numbersFromUser = new HashSet<>();
        while (numbersFromUser.size() != 6) {
            String numberString = scanner.nextLine();
            try {
                int number = Integer.parseInt(numberString);
                numbersFromUser.add(number);
            } catch (NumberFormatException nfe) {
                System.out.println("ERROR: This is not a number! Please try again.");
            }
        }
        scanner.close();
        return numbersFromUser;
    }
}
