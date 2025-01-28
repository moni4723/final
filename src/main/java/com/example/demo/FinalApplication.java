package com.example.demo;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class FinalApplication implements CommandLineRunner {

	@Autowired
	private EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(FinalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			// Show options to the user
			System.out.println("\nChoose an operation:");
			System.out.println("1. Add Employee");
			System.out.println("2. Update Employee");
			System.out.println("3. Delete Employee");
			System.out.println("4. View All Employees");
			System.out.println("5. Exit");
			System.out.print("Enter choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();  // Consume the newline

			switch (choice) {
				case 1:
					// Add Employee
					addEmployee(scanner);
					break;
				case 2:
					// Update Employee
					updateEmployee(scanner);
					break;
				case 3:
					// Delete Employee
					deleteEmployee(scanner);
					break;
				case 4:
					// View All Employees
					viewAllEmployees();
					break;
				case 5:
					// Exit the program
					System.out.println("Exiting application...");
					return;
				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	// Method to add a new employee
	private void addEmployee(Scanner scanner) {
		System.out.println("Enter Employee Details:");

		// Name
		System.out.print("Enter Employee Name: ");
		String name = scanner.nextLine();

		// Department
		System.out.print("Enter Employee Department: ");
		String department = scanner.nextLine();

		// Salary
		System.out.print("Enter Employee Salary: ");
		double salary = scanner.nextDouble();

		// Creating an Employee object and saving it
		Employee employee = new Employee();
		employee.setName(name);
		employee.setDepartment(department);
		employee.setSalary(salary);

		// Save employee to the database
		employeeService.addEmployee(employee);

		// Output: Show the employee added
		System.out.println("Employee Added: " + employee);
	}

	// Method to update an existing employee
	private void updateEmployee(Scanner scanner) {
		System.out.print("Enter Employee ID to update: ");
		Long employeeId = scanner.nextLong();
		scanner.nextLine();  // Consume newline

		// Fetch the employee from the database
		Employee employee = employeeService.getEmployeeById(employeeId);

		if (employee == null) {
			System.out.println("Employee with ID " + employeeId + " not found.");
			return;
		}

		// Show the existing details and prompt for updates
		System.out.println("Existing Employee Details: " + employee);

		// Department
		System.out.print("Enter new Department (leave blank to keep existing): ");
		String newDepartment = scanner.nextLine();
		if (!newDepartment.isEmpty()) {
			employee.setDepartment(newDepartment);
		}

		// Salary
		System.out.print("Enter new Salary (leave blank to keep existing): ");
		String salaryInput = scanner.nextLine();
		if (!salaryInput.isEmpty()) {
			employee.setSalary(Double.parseDouble(salaryInput));
		}

		// Save the updated employee to the database
		employeeService.addEmployee(employee);

		// Output: Show the updated employee
		System.out.println("Employee Updated: " + employee);
	}

	// Method to delete an employee
	private void deleteEmployee(Scanner scanner) {
		System.out.print("Enter Employee ID to delete: ");
		Long employeeId = scanner.nextLong();

		// Delete employee from the database
		boolean isDeleted = employeeService.deleteEmployee(employeeId);

		// Output: Confirmation of deletion
		if (isDeleted) {
			System.out.println("Employee with ID " + employeeId + " has been deleted.");
		} else {
			System.out.println("Employee with ID " + employeeId + " not found.");
		}
	}

	// Method to view all employees
	private void viewAllEmployees() {
		System.out.println("All Employees in the Database: ");
		employeeService.getAllEmployees().forEach(System.out::println);
	}
}
