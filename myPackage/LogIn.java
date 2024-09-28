package myPackage;

import java.sql.*;
import java.util.Scanner;

public class LogIn {
    private static int id;
    private static String first_name;
    private static String last_name;
    private static String email;
    private static String password;

    private LogIn(){}
	
	private static ResultSet RetrieveInfoFromDB(String email)
	{
		try {
			// 1. Get a connection to database
			Connection myConn = DriverManager.getConnection(DBInfo.url, DBInfo.user, DBInfo.password);
			
			// 2. Create a statement
			Statement myStmt = myConn.createStatement();
			
			// 3.Execute SQL query
			ResultSet myRs = myStmt.executeQuery("select * from "+DBInfo.table+" where email = "+"\'"+email+"\';");
			
            return myRs;
			
		} catch (Exception ex) {
			ex.printStackTrace();
            return null;
		}
	}


    public static int checkInfo()
    {
        Scanner  scan = new Scanner(System.in);  // Create a Scanner object

        System.out.print("");

        while (true) {
            
            System.out.print("\nEmail     : ");
            email = scan.nextLine();

            try
            {
                ResultSet myRs = RetrieveInfoFromDB(email);

                if(myRs.next())
                {
                    id = Integer.parseInt(myRs.getString("id"));
                    first_name = myRs.getString("first_name");
                    last_name = myRs.getString("last_name");
                    password = myRs.getString("encrypted_password");
                    break;
                }
                else
                {
                    System.out.println("\n / The email has no account /");
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }

        while (true)
        {
            System.out.print("password  : ");
            String temp_password = scan.nextLine();

            if (temp_password.equals(PasswordSecurity.decrypt(password)))
            {
                System.out.println("\n////////////// WELCOME ///////////////////\n");
                System.out.println("Your login was successful, " + first_name + ".\n");
                break;
            }
            else
            {
                System.out.println("\n / Password does not match /");
            }
        }
        return id;
    }

}
