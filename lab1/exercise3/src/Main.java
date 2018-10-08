/**Exercise 3:
 Given an array of ints, print to console true if the sequence of numbers 1, 2, 3 appears in the array somewhere.
 Example:
 int[] array = {3,2,14,1,2,3,6};

 should print true */

import java.lang.*;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in); 
        int elem;
        System.out.println("Enter number of array elements="); // user input number of array's elements
        elem = reader.nextInt(); // read user input as integer

        int arrayInt[] = new int[elem];

        for ( int i = 0; i < arrayInt.length; i++ )
        {
            System.out.println("Enter " + i + " element: "); // set 'i' element of array as integer read from user's input
            arrayInt[i] = reader.nextInt(); // read user input as integer
        }

        for (var num = 0; num < arrayInt.length - 1; num++) {
            if (arrayInt[num] == 1 && arrayInt[num + 1] == 2 && arrayInt[num + 2] == 3)
            {
                System.out.println(true); // print true if the sequence of numbers 1, 2, 3 appears in the array somewhere
            }
        }

        System.exit(0);
    }
}