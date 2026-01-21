package com.klu.app;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.klu.model.Department;
import com.klu.model.Employee;
import com.klu.util.HibernateUtil;

public class MainApp {

    static SessionFactory factory = HibernateUtil.getSession();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Hibernate Menu =====");
            System.out.println("1. Insert Department");
            System.out.println("2. Insert Employee");
            System.out.println("3. View Employee");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 : insertDepartment(sc);
                case 2 : insertEmployee(sc);
                case 3 : viewEmployee(sc);
                case 4 : {
                    factory.close();
                    sc.close();
                    System.out.println("Closed");
                }
                default : System.out.println("Invalid choice");
            }
        } while (choice != 4);
    }

    private static void insertDepartment(Scanner sc) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            sc.nextLine();
            System.out.print("Enter Department Name: ");
            String deptname = sc.nextLine();

            Department dept = new Department();
            dept.setDeptName(deptname);

            session.save(dept);
            tx.commit();
            System.out.println("Department inserted");
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void insertEmployee(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            System.out.print("Enter Employee ID: ");
            while (!sc.hasNextInt()) {
                System.out.println("Enter valid numeric Employee ID");
                sc.next();
            }
            int empid = sc.nextInt();

            sc.nextLine();
            System.out.print("Enter Employee Name: ");
            String empname = sc.nextLine();

            System.out.print("Enter Employee Salary (numbers only): ");
            while (!sc.hasNextDouble()) {
                System.out.println("Invalid salary! Enter numeric value");
                sc.next();
            }
            double empsalary = sc.nextDouble();

            System.out.print("Enter Department ID: ");
            while (!sc.hasNextInt()) {
                System.out.println("Enter valid numeric Department ID");
                sc.next();
            }
            int deptid = sc.nextInt();

            Department dept = session.get(Department.class, deptid);
            if (dept == null) {
                System.out.println("Department not found");
                tx.rollback();
                return;
            }

            Employee emp = new Employee();
            emp.setEmpId(empid);
            emp.setEmpName(empname);
            emp.setEmpSal(empsalary);
            emp.setDep(dept);

            session.save(emp);
            tx.commit();
            System.out.println("Employee inserted successfully");

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    private static void viewEmployee(Scanner sc) {
        Session session = factory.openSession();
        try {
            System.out.print("Enter Employee ID: ");
            int empid = sc.nextInt();

            Employee emp = session.get(Employee.class, empid);
            if (emp != null) {
                System.out.println("ID   : " + emp.getEmptId());
                System.out.println("Name : " + emp.getEmpName());
                System.out.println("Sal  : " + emp.getEmpsal());
                System.out.println("Dept : " + emp.getdep().getDeptName());
            } else {
                System.out.println("Employee not found");
            }
        } finally {
            session.close();
        }
    }
}
