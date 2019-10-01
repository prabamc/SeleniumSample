package com.selenium.testng.com.selenium.testng;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestSqldatabase {
	
private Connection connection;
private static Statement statement;
private static ResultSet rs;

 @BeforeClass
 public void setUp() {
         String databaseURL = "jdbc:mysql://localhost:3306/easy";
         String user = "root";
         String password = "Surya@2008";
         connection = null;
         try {
             Class.forName("com.mysql.jdbc.Driver");
             System.out.println("Connecting to Database...");
             connection = DriverManager.getConnection(databaseURL, user, password);
             if (connection != null) {
                 System.out.println("Connected to the Database...");
             }
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
         catch (ClassNotFoundException ex) {
            ex.printStackTrace();
         }
 }

 @Test
 public void getEmployeesFromDataBase() 
 {
     try {
         String query = "select * from Employee";
         statement = connection.createStatement();
         rs = statement.executeQuery(query);

         while(rs.next()){
             int Emp_Id= rs.getInt("Emp_Id");
             String Emp_Name= rs.getString("Emp_Name");
             String Emp_Address=rs.getString("Emp_Address");
             String Emp_Dept=rs.getString("Emp_Dept");
             String Emp_Salary= rs.getString("Emp_Salary");
             System.out.println(Emp_Id+"\t"+Emp_Name+"\t"+Emp_Address+"\t"+Emp_Dept+"\t"+Emp_Salary);
         }
     } catch (SQLException ex) {
        ex.printStackTrace();
     }
 }

 @AfterClass
 public void tearDown() {
   if (connection != null) {
             try {
                 System.out.println("Closing Database Connection...");
                 connection.close();
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
         }
   }
}