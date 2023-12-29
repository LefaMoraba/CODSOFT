import java.util.Scanner;

public class StudentManagementSystem {

    static DBConnect db = new DBConnect("student.db");
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    searchStudent();
                    break;
                case 5:
                    displayStudents();
                    break;
                case 6:
                    System.out.println("Thank you for using the Student Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 6);

        scanner.close();
    }

    public static void displayMenu() {
        System.out.println("\nWelcome to the Student Management System");
        System.out.println("How can we assist you?");
        System.out.println("1. Add new student");
        System.out.println("2. Edit existing student");
        System.out.println("3. Delete student");
        System.out.println("4. Search student");
        System.out.println("5. Show all students");
        System.out.println("6. Exit");
    }

    public static void displayStudents() {
        db.displayAllStudentsInfo();
        askToContinue();
    }

    public static void addStudent() {
        System.out.println("Please enter the student's details");
        System.out.print("Student's Roll Number: ");
        int rollNum = scanner.nextInt();
        System.out.print("Student's Name: ");
        String name = scanner.next();
        System.out.print("Student's Gender: ");
        String gender = scanner.next();
        System.out.print("Student's Grade: ");
        String grade = scanner.next();

        db.insertStudent(rollNum, name, gender, grade);
        db.displayStudent(name);
        System.out.println(name + "'s details have been successfully captured");
        askToContinue();
    }

    public static void editStudent() {
        System.out.print("Please enter the Roll Number of the Student you want to edit: ");
        int rollNum = scanner.nextInt();
        System.out.println("These are the details of the student you selected");
        db.displayStudentByRollNum(rollNum);

        System.out.println("Please enter the new details of the student");
        System.out.print("Student's Name: ");
        String name = scanner.next();
        System.out.print("Student's Gender: ");
        String gender = scanner.next();
        System.out.print("Student's Grade: ");
        String grade = scanner.next();

        db.modifyStudent(rollNum, name, gender, grade);
        askToContinue();
    }

    public static void deleteStudent() {
        System.out.print("Enter the name of the student you want to delete: ");
        String name = scanner.next();
        db.deleteStudentByName(name);
        askToContinue();
    }

    public static void searchStudent() {
        System.out.print("Enter the name of the student you want to search: ");
        String name = scanner.next();
        db.displayStudent(name);
        askToContinue();
    }

    public static void askToContinue() {
        System.out.print("Do you want to continue with the system? (Yes/No): ");
        String response = scanner.next().toLowerCase();
        if (response.equals("no")) {
            System.out.println("Thank you for using the system.");
            System.exit(0);
        }
    }
}
