/**Exercise 1:
 We'll say that a number is "teen" if it is in the range 13..19 inclusive. Given 2 int values, print to console true if one or the other is teen, but not both.
 Example:
 int a = 1;
 int b = 13;

 if a or b is "teen" print "teen" */

import java.util.Scanner;

 public class Main {

 public static void main(String[] args) {

     int a;
     int b;
    exercise1();
    System.exit(0);
 }

 public exercise1() {

     Scanner reader = new Scanner(System.in); // user input

     System.out.println("Enter a number:"); // user input first number
     a = reader.nextInt(); // read user input as integer
     System.out.println("Enter a second number:"); // user input second number
     b = reader.nextInt(); // read user input as integer
     reader.close();

     if ((a >= 13 && a <= 19) ^ (b >= 13 && b <= 19)) {
         System.out.println("teen");
     }
 }
 }