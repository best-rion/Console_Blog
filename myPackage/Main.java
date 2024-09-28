package myPackage;

import java.sql.*;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Main{

        private static void showPosts(int current_user_id)
        {       
                String post_title;
                String post_date;
                String post_content;

                try
                {
			// 1. Get a connection to database
			Connection myConn = DriverManager.getConnection(DBInfo.url, DBInfo.user, DBInfo.password);
			
			// 2. Create a statement
			Statement myStmt = myConn.createStatement();
			
			// 3.Execute SQL query
			ResultSet myRs = myStmt.executeQuery("select * from "+"posts"+" where author_id = "+current_user_id+";");

                        int count = 1;

                        System.out.println("\n////////////// YOUR POSTS ///////////////////\n");
                        while (myRs.next()) {

                                post_title = myRs.getString("post_title");
                                post_date = myRs.getString("post_date");
                                post_content = myRs.getString("post_content");

                                System.out.println(count+". "+post_title+" ("+post_date+")\n" );
                                System.out.println(post_content+"\n");

                                count++;
                        }
                                
                } catch (Exception ex) {
                         ex.printStackTrace();
		}
        }

        private static void writeAPost(int current_user_id)
        {
                Scanner scan = new Scanner(System.in);

                System.out.print("\n////////////// WRITE A POST ///////////////////\n");

                System.out.print("\nTitle     : ");
                String post_title = scan.nextLine();

                String post_content = "";
                System.out.print("\nContent   : \n\n");

                while (true)
                {
                        String new_line = scan.nextLine();
                        
                        if(new_line.equals("end")){break;}
                        
                        post_content = post_content + "   " + new_line + "\n";

                }
                
                LocalDate date = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String post_date = date.format(formatter);

                try
                {

                        String statement = "INSERT INTO "+"posts"+" (post_title, post_content, post_date, author_id) "
                                + "VALUES (\'"+post_title+"\',\'"+post_content+"\',\'"+post_date+"\',"+current_user_id+");";


                        // 1. Get a connection to database
                        Connection myConn = DriverManager.getConnection(DBInfo.url, DBInfo.user, DBInfo.password);
                        
                        // 2. Create a statement
                        Statement myStmt = myConn.createStatement();
                        
                        // 3.Execute SQL query
                        boolean error = myStmt.execute(statement);
                        
                        if (!error)
                        {
                                System.out.println("\nYour post has been submitted successfully.");
                        }
                                    
                } catch (Exception ex) {
                        ex.printStackTrace();
                }

        }

        public static void main(String[] args) {

                int current_user_id = 0;

                Scanner scan = new Scanner(System.in);
                System.out.print("Type \'S\' for Signup, \'L\' for Login : ");
                String loginOrSignup = scan.nextLine();


                if (loginOrSignup.equals("S")) // Sign Up
                {
                        SignUp.setInfo();
                }
                else if (loginOrSignup.equals("L"))
                {
                        current_user_id = LogIn.checkInfo();
                }

                if ( current_user_id != 0)
                {
                        showPosts(current_user_id);
                        writeAPost(current_user_id);
                }

                scan.close();
        }
}