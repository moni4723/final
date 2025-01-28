package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public void addEmployee(Scanner scanner) {
        System.out.println("Enter Employee Details:");

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Employee Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Employee Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Employee Department: ");
        String department = scanner.nextLine();

        System.out.print("Enter Employee Designation: ");
        String designation = scanner.nextLine();

        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();

        System.out.print("Enter Employee Experience (in years): ");
        double experience = scanner.nextDouble();


        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        employee.setAddress(address);
        employee.setDepartment(department);
        employee.setDesignation(designation);
        employee.setSalary(salary);
        employee.setExperience(experience);


        employeeService.addEmployee(employee);

        System.out.println("Employee Added: " + employee);
    }

    public void updateEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to update: ");
        Long employeeId = scanner.nextLong();
        scanner.nextLine();

        Employee employee = employeeService.getEmployeeById(employeeId);

        if (employee == null) {
            System.out.println("Employee with ID " + employeeId + " not found.");
            return;
        }

        System.out.println("Existing Employee Details: " + employee);


        System.out.print("Enter new Name : ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            employee.setName(newName);
        }

        System.out.print("Enter new Age : ");
        String ageInput = scanner.nextLine();
        if (!ageInput.isEmpty()) {
            employee.setAge(Integer.parseInt(ageInput));
        }

        System.out.print("Enter new Address : ");
        String newAddress = scanner.nextLine();
        if (!newAddress.isEmpty()) {
            employee.setAddress(newAddress);
        }

        System.out.print("Enter new Department : ");
        String newDepartment = scanner.nextLine();
        if (!newDepartment.isEmpty()) {
            employee.setDepartment(newDepartment);
        }

        System.out.print("Enter new Designation : ");
        String newDesignation = scanner.nextLine();
        if (!newDesignation.isEmpty()) {
            employee.setDesignation(newDesignation);
        }

        System.out.print("Enter new Salary : ");
        String salaryInput = scanner.nextLine();
        if (!salaryInput.isEmpty()) {
            employee.setSalary(Double.parseDouble(salaryInput));
        }

        System.out.print("Enter new Experience : ");
        String experienceInput = scanner.nextLine();
        if (!experienceInput.isEmpty()) {
            employee.setExperience(Double.parseDouble(experienceInput));
        }


        employeeService.addEmployee(employee);

        System.out.println("Employee Updated: " + employee);
    }

    public void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to delete: ");
        Long employeeId = scanner.nextLong();

        boolean isDeleted = employeeService.deleteEmployee(employeeId);

        if (isDeleted) {
            System.out.println("Employee with ID " + employeeId + " has been deleted.");
        } else {
            System.out.println("Employee with ID " + employeeId + " not found.");
        }
    }

    public void viewAllEmployees() {
        System.out.println("All Employees in the Database: ");
        employeeService.getAllEmployees().forEach(System.out::println);
    }
}
