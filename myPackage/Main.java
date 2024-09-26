package myPackage;

import java.util.Scanner;

public class Main {

        public static void main(String[] args) {

                Scanner scan = new Scanner(System.in);  // Create a Scanner object

                System.out.print("Type \'S\' for Signup, \'L\' for Login : ");
                String loginOrSignup = scan.nextLine();  // Read user input

                if (loginOrSignup.equals("S")) // Sign Up
                {
                        SignUp.setInfo();
                }
                else if (loginOrSignup.equals("L"))
                {
                        LogIn.checkInfo();
                }

                scan.close();
        }
}