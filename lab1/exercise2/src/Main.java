/**Exercise 2:
 Given 3 int values, a b c, print to console their sum. However, if one of the values is 13 then it does not count towards the sum and values to its right do not count.
 So for example, if b is 13, then both b and c do not count.
 Example:
 int a = 1;
 int b = 13;
 int c = 2;

 should print 1*/

 import java.util.Scanner;
 public class Main {

     public static void main(String[] args)
     {

         int a;
         int b;
         int c;

         Scanner reader = new Scanner(System.in); // user input

         System.out.println("Enter a ="); // user input first number
         a = reader.nextInt(); // read user input as integer
         System.out.println("Enter b ="); // user input second number
         b = reader.nextInt(); // read user input as integer
         System.out.println("Enter c ="); // user input third number
         c = reader.nextInt(); // read user input as integer
         reader.close();

         if ( a != 13 )
         {
             if( b != 13 )
             {
                 if ( c != 13 )
                 {
                     System.out.println("Sum a + b + c = " + ( a + b + c) );
                 }
                 else System.out.println("C = 13, printing a + b = "+ (a + b) );
            }
            else System.out.println( "B = 13, printing only a = " + a );
         }

         System.exit(0);
    }
 }