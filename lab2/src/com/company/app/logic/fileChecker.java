package com.company.app.logic;

import java.io.*;
import java.util.Scanner;

public class fileChecker extends userInput{

    File file = new File("G:/Users/Jakub/Documents/GitHub/wszibjava/lab2/src/emails.txt");

    public int read(String email) throws FileNotFoundException {

        Scanner scanner = new Scanner(file);

        while(scanner.hasNext()){

            String[] tokens = scanner.nextLine().split(";");
            String last = tokens[tokens.length - 1];
            //System.out.println(last);

            for ( int i = 0; i < tokens.length; i++) {
                if (tokens[i].equals(email)) {
                    return -1;
                }
            }

        }

        return 0;
    }


    public void write(String email) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.append("\n" + email + ";");
        writer.close();

    }




}
