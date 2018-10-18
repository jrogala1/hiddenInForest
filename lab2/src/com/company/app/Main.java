package com.company.app;

import com.company.app.logic.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        userInput employee = new userInput();

        Scanner input = new Scanner(System.in);

        System.out.println("Console program for Mex Company \nPlease enter data below to generate email address for new employee.");

        System.out.println("Enter user name:");

        employee.name = input.nextLine();

        System.out.println(employee.checkInput(employee.name));

        System.out.println("Enter user surname:");

        employee.surname = input.nextLine();
        String s = employee.generateEmail();
        System.out.println("Generated email: " + s);



    }
}
