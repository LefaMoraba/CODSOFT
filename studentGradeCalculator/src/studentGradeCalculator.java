import java.util.Scanner;

public class studentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of subjects: ");
        int numOfSubjects = scanner.nextInt();

        int totalMarks = 0;
        int marksObtained;
        for (int i = 1; i <= numOfSubjects; i++) {
            System.out.print("Enter marks obtained in subject " + i + ": ");
            marksObtained = scanner.nextInt();

            // Validating marks (making sure marks are out of 100)
            if (marksObtained < 0 || marksObtained > 100) {
                System.out.println("Marks should be between 0 and 100. Please enter valid marks.");
                i--; // Decrement the counter to re-enter marks for the same subject
                continue;
            }

            totalMarks += marksObtained;
        }

        double averagePercentage = (double) totalMarks / numOfSubjects;

        System.out.println("\nResults:");
        System.out.println("Total Marks Obtained: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);

        String grade = calculateGrade(averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }

    // Method to calculate grades based on average percentage
    public static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A+";
        } else if (averagePercentage >= 80) {
            return "A";
        } else if (averagePercentage >= 70) {
            return "B";
        } else if (averagePercentage >= 60) {
            return "C";
        } else if (averagePercentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }
}
