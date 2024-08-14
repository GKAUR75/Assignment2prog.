
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentMarksDriver {

    // This class holds information about each student
    static class Student {
        String lastName;
        String firstName;
        String studentID;
        double a1, a2, a3;

        // Constructor to create a Student object
        Student(String lastName, String firstName, String studentID, double a1, double a2, double a3) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.studentID = studentID;
            this.a1 = a1;
            this.a2 = a2;
            this.a3 = a3;
        }

        // Method to calculate the total marks of the student
        double getTotalScore() {
            return a1 + a2 + a3;
        }
    }

    // List to store all students
    private List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        StudentMarksDriver driver = new StudentMarksDriver(); // Create an object to manage students
        Scanner scanner = new Scanner(System.in);

        // Ask for the name of the file to read
        System.out.println("Enter the file name:");
        String fileName = scanner.nextLine();

        try {
            // Read the file and load student data
            driver.readFile(fileName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // Simple menu to choose what to do
        while (true) {
            System.out.println("\nMenu");
            System.out.println("1. Show all students' total scores");
            System.out.println("2. Show students below a certain score");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();

            if (option == 1) {
                driver.showAllStudentScores();
            } else if (option == 2) {
                System.out.print("Enter the minimum score to filter students: ");
                double threshold = scanner.nextDouble();
                driver.showStudentsBelowScore(threshold);
            } else if (option == 3) {
                System.out.println("Goodbye!");
                break;
            } else { 
                System.out.println("Please choose a valid option.");
            }
        }
        scanner.close(); // Close the scanner
    }


    // Method to read the file and add students to the list
    void readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        reader.readLine(); // Skip the first line (title)
        reader.readLine(); // Skip the second line (headers)

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 6) {
                String lastName = parts[0];
                String firstName = parts[1];
                String studentID = parts[2];
                double a1 = parts[3].isEmpty() ? 0 : Double.parseDouble(parts[3]);
                double a2 = parts[4].isEmpty() ? 0 : Double.parseDouble(parts[4]);
                double a3 = parts[5].isEmpty() ? 0 : Double.parseDouble(parts[5]);
                students.add(new Student(lastName, firstName, studentID, a1, a2, a3));
            }
        }
        reader.close(); // Close the file after reading
    }

    // Method to show all students and their total scores
    void showAllStudentScores() {
        for (Student student : students) {
            System.out.println("Student: " + student.firstName + " " + student.lastName +
                               ", ID: " + student.studentID +
                               ", Total Score: " + student.getTotalScore());
        }
    }

    // Method to show students with total scores below a certain value
    void showStudentsBelowScore(double threshold) {
        for (Student student : students) {
            if (student.getTotalScore() < threshold) {
                System.out.println("Student: " + student.firstName + " " + student.lastName +
                                   ", ID: " + student.studentID +
                                   ", Total Score: " + student.getTotalScore());
            }
        }
    }
}



