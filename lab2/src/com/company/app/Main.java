package com.company.app;

import com.company.app.logic.*;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {


        try
        {
            userInput employee = new userInput();

            Scanner input = new Scanner(System.in);

            System.out.println("Console program for Mex Company \nPlease enter data below to generate email address for new employee.");

            System.out.println("Enter user name:");
            employee.name = input.nextLine();

            if(employee.checkInput(employee.name) == -1 ) throw new Exception("Your input is invalid. You can only type a word consisting of the characters of the Polish alphabet");

            System.out.println("Enter user surname:");
            employee.surname = input.nextLine();

            if(employee.checkInput(employee.surname) == -1 ) throw new Exception("Your input is invalid. You can only type a word consisting of the characters of the Polish alphabet");

            String s = employee.generateEmail();
            System.out.println("Generated email: " + s);

        } catch (Exception e)
        {
            System.out.println(e);
        }




    }
}
