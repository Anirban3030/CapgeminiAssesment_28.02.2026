package com.student.main;

import com.student.dao.*;
import com.student.model.Student;
import com.student.exception.*;
import java.util.*;

public class StudentMnagementSystem {

    static void validateAdd(Student student) throws InvaliddataStudentException {

        if (student.getName() == null || student.getName().isEmpty()
                || student.getName().matches("\\d+")) {
            throw new InvaliddataStudentException("Invalid Name!");
        }

        if (student.getEmail() == null || !student.getEmail().contains("@")) {
            throw new InvaliddataStudentException("Invalid Email!");
        }

        if (student.getAge() <= 0) {
            throw new InvaliddataStudentException("Age be positive!");
        }

        if (String.valueOf(student.getMobile()).length() != 10) {
            throw new InvaliddataStudentException("Mobile be 10 digits!");
        }
    }

    static void validateUpdate(Student student) throws InvaliddataStudentException {

        if (student.getEmail() == null || !student.getEmail().contains("@")) {
            throw new InvaliddataStudentException("Invalid Email!");
        }

        if (String.valueOf(student.getMobile()).length() != 10) {
            throw new InvaliddataStudentException("Mobile must be 10 digits!");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO obj = new MySQLStudentDAO();

        while (true) {

            System.out.println();
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student Email (by Mobile)");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {

                switch (choice) {

             
                    case 1:
                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        System.out.print("Age: ");
                        int age = sc.nextInt();

                        System.out.print("Mobile: ");
                        long mobile = sc.nextLong();

                        Student student = new Student(0, name, email, age, mobile);

                        validateAdd(student);
                        obj.add(student);
                        break;

                
                    case 2:
                        List<Student> list = obj.allStudent();
                        for (Student s : list) {
                            System.out.println(s.getId() + " "
                                    + s.getName() + " "
                                    + s.getEmail() + " "
                                    + s.getAge() + " "
                                    + s.getMobile());
                        }
                        break;

                    case 3:
                        System.out.print("Enter Existing Mobile: ");
                        long mobileUpdate = sc.nextLong();
                        sc.nextLine();

                        System.out.print("Enter New Email: ");
                        String newEmail = sc.nextLine();

                        Student updateStudent = new Student(newEmail, mobileUpdate);

                        validateUpdate(updateStudent);
                        obj.update(updateStudent);
                        break;

                    case 4:
                        System.out.print("Enter ID to Delete: ");
                        int deleteId = sc.nextInt();
                        obj.delete(deleteId);
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);
                }

            } catch (InvaliddataStudentException e) {
                System.out.println("Validation Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Database Error: " + e.getMessage());
            }
        }
    }
}