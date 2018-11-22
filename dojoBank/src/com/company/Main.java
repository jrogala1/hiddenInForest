package com.company;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String file = "G:/Users/Jakub/Documents/GitHub/wszibjava/dojoBank/src/com/company/bank_ocr_dojo_us4"; //Enter file location in this line
        String digitsTemplate = "G:/Users/Jakub/Documents/GitHub/wszibjava/dojoBank/src/com/company/digits"; //Enter file location in this line

        try
        {
            fileChecker filechecker = new fileChecker(file,digitsTemplate);

        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
