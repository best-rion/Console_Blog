package myPackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class SignUp {

    private static String first_name;
    private static String last_name;
    private static String email;
    private static String password;

    private SignUp(){}

    private static void save()
    {

        try {

            String statement = "INSERT INTO "+DBInfo.table+" (first_name, last_name, email, encrypted_password) "
                             + "VALUES (\'"+first_name+"\'"+",\'"+last_name+"\'"+",\'"+email+"\'"+",\'"+password+"\');";


			// 1. Get a connection to database
			Connection myConn = DriverManager.getConnection(DBInfo.url, DBInfo.user, DBInfo.password);
			
			// 2. Create a statement
			Statement myStmt = myConn.createStatement();
			
			// 3.Execute SQL query
			boolean error = myStmt.execute(statement);
			
			if (!error)
            {
                System.out.println("\nWelcome, "+first_name+". Your signup was successfull!");
            }
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }

    private static void getEmailList(ArrayList<String> emailList)
    {
        try {
            // 1. Get a connection to database
            Connection myConn = DriverManager.getConnection(DBInfo.url, DBInfo.user, DBInfo.password);
            
            // 2. Create a statement
            Statement myStmt = myConn.createStatement();
            
            // 3.Execute SQL query
            ResultSet myRs = myStmt.executeQuery("select email from "+DBInfo.table+";");

            
            while (myRs.next()) {
                emailList.add(myRs.getString("email"));
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setInfo()
    {

        Scanner scan = new Scanner(System.in);  // Create a Scanner object

        System.out.print("\nFirst Name         : ");
        first_name = scan.nextLine();

        System.out.print("Last Name          : ");
        last_name = scan.nextLine();

        // Check Email Validity

        while( true )
        { 
            System.out.print("Email              : ");
            email = scan.nextLine();

            if ( email.contains("@") && email.contains(".") )
            {
                break;
            }
            else
            {
                System.out.println("\n / Please enter a valid email /");

            }
        }

        // Check Password
        while( true )
        { 
            System.out.print("Password           : ");
            password = scan.nextLine();

            System.out.print("Confirm Password   : ");

            if ( password.equals( scan.nextLine() ) )
            {
                password = PasswordSecurity.encrypt(password);
                break;
            }
            else
            {
                System.out.println("\n / Your passwords do not match. Please type again /");
                continue;
            }
        }


        // Check if Email already exists in Database

        ArrayList<String> emailList = new ArrayList<String>();

        getEmailList(emailList);

        if ( emailList.contains(email))
        {
            System.out.println("\n / You already have an account /");
        }
        else
        {
            save();
        }
        
        scan.close();
    }

}
