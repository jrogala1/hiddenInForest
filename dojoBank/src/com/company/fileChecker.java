package com.company;

import java.io.*;
import java.util.*;

public class fileChecker {

    private static String[] genNumbers;
    private final String file;
    private final String templateFile;
    private String[] fitDigits;
    private int figureIndex = 0;



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
        //String[] fitNumbers = tryToFit(templateFileContent);
        //System.out.println(fileContent.length);
        for (int lineIndex = 0; lineIndex < fileContent.length; lineIndex += 4) {
            String[] accountEntry = new String[3];
            accountEntry[0] = fileContent[lineIndex];
            accountEntry[1] = fileContent[lineIndex + 1];
            accountEntry[2] = fileContent[lineIndex + 2];

            accountNumbers.add(getNumbersFromEntry(accountEntry,templateNumbers));
        }
        System.out.println(Arrays.toString(accountNumbers.toArray()));
       // System.out.println(accountNumbers);
        return accountNumbers;
    }

    private String[] tryToFit(String[] templateFileContent) {

        String[] templateNumbers = new String[(templateFileContent.length)/3];
        int j = 0;
        int lineLength = 3;

        for (int i = 0; i < templateFileContent.length; i += 4 ) {
            String[] fitDigits = new String[3];

          //  if(templateFileContent[i].length() > lineLength) {
              //  while (templateFileContent[i].length() > lineLength) {
                    lineLength += 3;
                    fitDigits[0] = templateFileContent[i].substring(lineLength, lineLength + 3);
                    fitDigits[1] = templateFileContent[i + 1].substring(lineLength, lineLength + 3);
                    fitDigits[2] = templateFileContent[i + 2].substring(lineLength, lineLength + 3);
             //   }
              //  lineLength = 3;
                templateNumbers[j] = (String.join(",", fitDigits));
                System.out.println(templateNumbers[j]);
                j++;
           // }
        }
        return templateNumbers;
    }

    private String[] parseTemplate(String[] templateFileContent) {

        String[] templateNumbers = new String[10];
        int j = 0;
        int lineLength = 3;

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

    private String getNumbersFromEntry(String[] accountEntry, String[] templateNumbers) {

        String accountNumber[] = new String[9];
        String digit;
        for (int digitIndex = 0; digitIndex < 9; digitIndex++) {
            String[] digitAsRows = new String[3];

            int StartIndex = digitIndex * 3;
            digitAsRows[0] = accountEntry[0].substring(StartIndex, StartIndex + 3);
            digitAsRows[1] = accountEntry[1].substring(StartIndex, StartIndex + 3);
            digitAsRows[2] = accountEntry[2].substring(StartIndex, StartIndex + 3);
            digit = String.join(",",digitAsRows);
            accountNumber[digitIndex] = (getDigit(digit, templateNumbers, digitIndex));

        }

        System.out.println(checksum(accountNumber));

        System.out.println(Arrays.toString(accountNumber));
        return checksum(accountNumber);
    }

    private String checksum(String[] accountNumber) {

        int position = 1;
        int checksum = 0;
        String result = String.join("",accountNumber);
        System.out.println(result);
        for ( String number : accountNumber )
        {
            if(number == "?")
            {
                result += " ILL";
                return result;
            } else {
                int digitnumber = Integer.parseInt(number);
                checksum = checksum + (position * digitnumber);
                System.out.println(checksum);
                position++;
            }
        }

        if((checksum % 11) == 0)
        {
            figureIndex = 0;
            return result;
        }
        else if (figureIndex < 9 ) {
            System.out.println("probuje zmienic liczbe" + accountNumber[figureIndex]);
            accountNumber[figureIndex] = changeFigure(accountNumber[figureIndex]);
            System.out.println("nowa liczba " + accountNumber[figureIndex]);
            figureIndex++;
            return checksum(accountNumber);
        } else {
            figureIndex = 0;
            result += " ERR";
            return result;
        }
    }

    private String changeFigure(String number) {


        System.out.println("weszło " + number);
            if (number == "0") number = "8";
            else if (number == "1") number = "7";
            else if (number == "0")  number = "8";
            else if (number == "9")  number = "8";
            else if (number == "5")  number = "9";
            else number = number;
        return number;
    }

    private static String getDigit(String digitAsRows, String[] templateNumbers, int digitIndex) {



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
       // } else if (digitAsRows.equals(fitNumbers[0])) {
       //     return "test";
        } else {
            //System.out.println("Digit index:  " + digitIndex);
                //genNumbers[digitIndex] = digitAsRows;

            return "?";
        }
    }


}
