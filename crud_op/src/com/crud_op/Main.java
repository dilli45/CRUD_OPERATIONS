package com.crud_op;
import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.System.exit;
public class Main {
	static final String url ="jdbc:mysql://localhost:3306/mydb";
    static final String uname = "root";
    static final String pass = "krish";
    public static void createData(){
       
        Scanner sc = new Scanner(System.in);
        System.out.println("*Please Remember 'Your ID' number for further Updatation.....");
        System.out.print("Enter employee ID number : ");
        int id = sc.nextInt();
        System.out.print("Enter employee name : ");
        String name = sc.next();
        System.out.print("Enter employee department : ");
        String department = sc.next();
        System.out.print("Enter employee Location : ");
        String location = sc.next();
        System.out.print("Enter employee Salary : ");
        int salary = sc.nextInt();
        String query = "INSERT INTO employees (id, name, department, location, salary) VALUES ('"
        	    + id + "','" + name + "','" + department + "','" + location + "','" + salary + "')";
        Connection con;

        try{
            con=DriverManager.getConnection(url,uname,pass);
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(query);
            if(rows==1) {
                System.out.println("Successfully Created....");
            }else {
                System.out.println("Something went Wrong.....!! ");
            }
            menu();


        }catch (Exception e){
            e.printStackTrace();

        }

    }
    public static void readData(){
        
        String query = "select * from employees";

        Connection con;
        try {
            con = DriverManager.getConnection(url, uname, pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String department = rs.getString("department");
                String location = rs.getString("location");
                int salary = rs.getInt("salary");

                System.out.println("Emp ID : "+id+"  Name : "+name+"    Department : "+department+"    Location : "+location+" Salary : "+salary );
            }
            menu();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void deleteData(){
       
        System.out.print("Enter Employee ID number : ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        String query = "DELETE  FROM employees where id ='"+id+"' ";

        Connection con;
        try{
            con = DriverManager.getConnection(url,uname,pass);
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(query);
            if(rows==1) {
                System.out.println(" Sucessfully Deleted....");
            }else {
                System.out.println("Please enter correct ID number.....!! ");
                deleteData();
            }
            menu();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void updateData(){
        
        String query = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("1 : ID no. Updates");
        System.out.println("2 : Name Updates");
        System.out.println("3 : Location Updates");
        System.out.println("4 : Department Updates");
        System.out.println("5 : Salary Updates");
        System.out.print(" Choose Option to Update the data : ");
        int opt = sc.nextInt();
        if(opt==1){
            System.out.print("\nEnter name of Employee : ");
            String name = sc.next();
            System.out.print("Enter to updates ID number : ");
            int id = sc.nextInt();
            query = "UPDATE employees SET id ='"+id+"' where name ='"+name+"' ";
        }else if(opt==2){
            System.out.print("\nEnter employee ID number : ");
            int id = sc.nextInt();
            System.out.print("Enter Names to Updates  : ");
            String name = sc.next();
            query = "UPDATE employees SET name ='"+name+"' where id ='"+id+"' ";
        }
        else if(opt==3){
            System.out.print("\nEnter employee ID number : ");
            int id = sc.nextInt();
            System.out.print("Enter Location to updates  : ");
            String location = sc.next();
            query = "UPDATE employees SET location ='"+location+"' where id ='"+id+"' ";

        }
        else if(opt==4){
            System.out.print("\nEnter employee ID number : ");
            int id = sc.nextInt();
            System.out.print("Enter Department Names to Updates  : ");
            String department = sc.next();
            query = "UPDATE employees SET department ='"+department+"' where id ='"+id+"' ";
        }
   
        else if(opt==5){
            System.out.print("\nEnter employee ID number : ");
            int id = sc.nextInt();
            System.out.print("Enter Salary to Updates  : ");
            int salary = sc.nextInt();
        query = "UPDATE employees SET salary ='"+salary+"' where id ='"+id+"' ";
    }
        else {
            System.out.println("\nChoose correct option ......");
            updateData();
        }
        Connection con;
        try{
            con = DriverManager.getConnection(url,uname,pass);
            Statement stmt = con.createStatement();
            int rows = stmt.executeUpdate(query);
            if(rows==1) {
                System.out.println(" Successfully Updated....");
            }else {
                System.out.println("Please enter correct data .....!! ");
                updateData();
            }
            menu();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void menu() {
       // System.out.println("\n\n\t\t\t --------- CRUD OPERATION USING JAVA --------\n");
        System.out.println("1 : Create Data ");
        System.out.println("2 : Read Data ");
        System.out.println("3 : Update Data ");
        System.out.println("4 : Delete Data ");
        System.out.println("5 : Exit");
        System.out.print(" Select Option  :  ");
        Scanner sc = new Scanner(System.in);
        int select= sc.nextInt();
        switch (select) {
            case 1 -> {
                System.out.println("\n Create Employee Data \n");
                createData();
            }
            case 2 -> {
                System.out.println("\n Employees Data \n");
                readData();
            }
            case 3 -> {
                System.out.println("\n Updates Employee Data \n");
                updateData();
            }
            case 4 -> {
                System.out.println("\n Delete Employee Data \n");
                deleteData();
            }
            case 5 -> {
                System.out.println(" Exiting....");
                exit(0);
            }
            default -> {
                System.out.println("Wrong Option...!");
                    menu();
                }
            }

        }
        public static void main(String[] args) {
            menu();

        }
	
}
