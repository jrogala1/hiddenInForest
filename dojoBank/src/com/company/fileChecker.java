package com.company;

import java.io.*;
import java.util.*;

public class fileChecker {

    private final String file;
    private final String templateFile;

    fileChecker(String file, String digitTemplate)
    {
        this.file = file;
        this.templateFile = digitTemplate;
        getAccountNumbers(new File(this.file), new File(this.templateFile));
    }

    private String[] readLines(File sourceFile) {

        ArrayList<String> fileLines = new ArrayList<String>();

            try {
                Scanner scanner = new Scanner(sourceFile);
                String line = null;

                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    fileLines.add(line);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            return fileLines.toArray(new String[]{});
    }

    public List<String> getAccountNumbers(File sourceFile, File templateFile)  {

        List<String> accountNumbers = new LinkedList<String>();
        String[] fileContent = readLines(sourceFile);
        String[] templateFileContent = readLines(templateFile);
        String[] templateNumbers = parseTemplate(templateFileContent);

        for (int lineIndex = 0; lineIndex < fileContent.length; lineIndex += 4) {
            String[] accountEntry = new String[3];
            accountEntry[0] = fileContent[lineIndex];
            accountEntry[1] = fileContent[lineIndex + 1];
            accountEntry[2] = fileContent[lineIndex + 2];

            accountNumbers.add(getNumbersFromEntry(accountEntry,templateNumbers));
        }
        System.out.println(Arrays.toString(accountNumbers.toArray()));
        return accountNumbers;
    }

    private String[] parseTemplate(String[] templateFileContent) {

        String[] templateNumbers = new String[10];
        int j = 0;

        for (int i = 0; i < templateFileContent.length; i += 4 ) {
            String[] digitAsRows = new String[3];

            digitAsRows[0] = templateFileContent[i].substring(0, 3);
            digitAsRows[1] = templateFileContent[i+1].substring(0, 3);
            digitAsRows[2] = templateFileContent[i+2].substring(0, 3);

            templateNumbers[j] = (String.join(",",digitAsRows));
            System.out.println(templateNumbers[j]);
            j++;
        }
            return templateNumbers;
    }

    private static String getNumbersFromEntry(String[] accountEntry, String[] templateNumbers) {

        StringBuilder stringbuilder = new StringBuilder();
        String digit;
        for (int digitIndex = 0; digitIndex < 9; digitIndex++) {
            String[] digitAsRows = new String[3];

            int StartIndex = digitIndex * 3;
            digitAsRows[0] = accountEntry[0].substring(StartIndex, StartIndex + 3);
            digitAsRows[1] = accountEntry[1].substring(StartIndex, StartIndex + 3);
            digitAsRows[2] = accountEntry[2].substring(StartIndex, StartIndex + 3);
            digit = String.join(",",digitAsRows);
            stringbuilder.append(getDigit(digit, templateNumbers));

        }

        return stringbuilder.toString();
    }

    private static String getDigit(String digitAsRows, String[] templateNumbers) {

        if (digitAsRows.equals(templateNumbers[0])) {
            return "0";
        } else if (digitAsRows.equals(templateNumbers[1])) {
            return "1";
        } else if (digitAsRows.equals(templateNumbers[2])) {
            return "2";
        } else if (digitAsRows.equals(templateNumbers[3])) {
            return "3";
        } else if (digitAsRows.equals(templateNumbers[4])) {
            return "4";
        } else if (digitAsRows.equals(templateNumbers[5])) {
            return "5";
        } else if (digitAsRows.equals(templateNumbers[6])) {
            return "6";
        } else if (digitAsRows.equals(templateNumbers[7])) {
            return "7";
        } else if (digitAsRows.equals(templateNumbers[8])) {
            return "8";
        } else if (digitAsRows.equals(templateNumbers[9])) {
            return "9";
        } else {
            throw new IllegalArgumentException("Digit not found: " + digitAsRows);
        }
    }


}
