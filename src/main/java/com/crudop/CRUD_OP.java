package com.crudop;

import java.sql.*;
import java.util.Scanner;

public class CRUD_OP {

    static String url = "jdbc:mysql://localhost:3306/CRUD_OP";
    static String user = "root";
    static String password = "Nivesh@1218";

    public static void main(String[] args) {

        createTables();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n------ MENU ------");
            System.out.println("1. Insert Department");
            System.out.println("2. Insert Employee");
            System.out.println("3. View Employees");
            System.out.println("4. Update Employee Salary");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 : insertDept();
                case 2 : insertEmployee();
                case 3 : viewEmployees();
                case 4 : updateEmployee();
                case 5 : deleteEmployee();
                case 6 : System.out.println("Exiting...");
                default : System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    // ================= TABLE CREATION =================
    static void createTables() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {

            String deptTable =
                    "CREATE TABLE IF NOT EXISTS dept (" +
                    "dept_id INT PRIMARY KEY, " +
                    "dept_name VARCHAR(50))";

            String empTable =
                    "CREATE TABLE IF NOT EXISTS employee (" +
                    "emp_id INT PRIMARY KEY, " +
                    "emp_name VARCHAR(50), " +
                    "salary DOUBLE, " +
                    "dept_id INT, " +
                    "FOREIGN KEY (dept_id) REFERENCES dept(dept_id))";

            st.execute(deptTable);
            st.execute(empTable);

            System.out.println("Tables created or already exist.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= INSERT DEPARTMENT =================
    static void insertDept() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Dept ID: ");
            int id = sc.nextInt();

            System.out.print("Enter Dept Name: ");
            String name = sc.next();

            String sql =
                    "INSERT INTO dept VALUES (" +
                    id + ", '" + name + "')";

            st.executeUpdate(sql);
            System.out.println("Department inserted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= INSERT EMPLOYEE =================
    static void insertEmployee() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Emp ID: ");
            int id = sc.nextInt();

            System.out.print("Enter Emp Name: ");
            String name = sc.next();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            System.out.print("Enter Dept ID: ");
            int deptId = sc.nextInt();

            String sql =
                    "INSERT INTO employee VALUES (" +
                    id + ", '" + name + "', " + salary + ", " + deptId + ")";

            st.executeUpdate(sql);
            System.out.println("Employee inserted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 // ================= READ (JOIN) =================
    static void viewEmployees() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {

            String sql =
                    "SELECT e.emp_id, e.emp_name, e.salary, d.dept_name " +
                    "FROM employee e " +
                    "JOIN dept d ON e.dept_id = d.dept_id";

            ResultSet rs = st.executeQuery(sql);

            System.out.println("\nID  Name  Salary  Dept");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("emp_id") + "  " +
                        rs.getString("emp_name") + "  " +
                        rs.getDouble("salary") + "  " +
                        rs.getString("dept_name")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    static void updateEmployee() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Emp ID: ");
            int id = sc.nextInt();

            System.out.print("Enter New Salary: ");
            double salary = sc.nextDouble();

            String sql =
                    "UPDATE employee SET salary = " +
                    salary + " WHERE emp_id = " + id;

            st.executeUpdate(sql);
            System.out.println("Employee updated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= DELETE =================
    static void deleteEmployee() {
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement st = con.createStatement()) {

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter Emp ID to delete: ");
            int id = sc.nextInt();

            String sql =
                    "DELETE FROM employee WHERE emp_id = " + id;

            st.executeUpdate(sql);
            System.out.println("Employee deleted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}