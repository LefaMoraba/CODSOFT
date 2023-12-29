import org.sqlite.SQLiteErrorCode;

import java.sql.*;

public class DBConnect {
    private static Connection connection;
    private static Statement statement;

    public DBConnect(String url) {
        try {
            // Establishing a connection to the SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:" + url);
            statement = connection.createStatement();
            createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        try {
            // Creating a table to store student information if it doesn't exist
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS students (" +
                            "rollnum INTEGER PRIMARY KEY," +
                            "name TEXT," +
                            "gender TEXT," +
                            "grade TEXT)"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertStudent(int rollnum, String name, String gender, String grade) {
        try {
            // Inserting a new student into the SQLite database
            statement.executeUpdate(
                    "INSERT INTO students (rollnum, name, gender, grade) " +
                            "VALUES (" + rollnum + ", '" + name + "', '" + gender + "', '" + grade + "')"
            );
        } catch (SQLException e) {
            if (e.getErrorCode() == SQLiteErrorCode.SQLITE_CONSTRAINT.code) {
                // Handle primary key constraint violation (duplicate rollnum)
                int lastInsertedRollnum = getLastInsertedRollnum();
                System.out.println("Error: Duplicate rollnum. A student with the same rollnum already exists.");
                System.out.println("Last Inserted Rollnum: " + lastInsertedRollnum);

            } else {
                // Handle other SQLExceptions
                e.printStackTrace();
            }
        }
    }

    public static void modifyStudent(int rollnum, String newName, String newGender, String newGrade) {
        try {
            // Modifying student information based on roll number in the SQLite database
            statement.executeUpdate(
                    "UPDATE students " +
                            "SET name='" + newName + "', gender='" + newGender + "', grade='" + newGrade + "' " +
                            "WHERE rollnum=" + rollnum
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayStudent(String studentName) {
    try {
        // Displaying student information based on name
        ResultSet resultSet = statement.executeQuery("SELECT * FROM students WHERE name='" + studentName + "'");
        if (resultSet.next()) {
            System.out.println("Roll Number: " + resultSet.getInt("rollnum"));
            System.out.println("Name: " + resultSet.getString("name"));
            System.out.println("Gender: " + resultSet.getString("gender"));
            System.out.println("Grade: " + resultSet.getString("grade"));
            System.out.println("------------");
        } else {
            System.out.println("Student not found.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public static void displayAllStudentsInfo() {
        try {
            // Displaying all student information one by one
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
            while (resultSet.next()) {
                System.out.println("Roll Number: " + resultSet.getInt("rollnum"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Gender: " + resultSet.getString("gender"));
                System.out.println("Grade: " + resultSet.getString("grade"));
                System.out.println("------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayStudentByRollNum(int rollnum) {
        try {
            // Displaying student information based on rollnum
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students WHERE rollnum=" + rollnum);
            if (resultSet.next()) {
                System.out.println("Roll Number: " + resultSet.getInt("rollnum"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Gender: " + resultSet.getString("gender"));
                System.out.println("Grade: " + resultSet.getString("grade"));
                System.out.println("------------");
            } else {
                System.out.println("Student with Roll Number " + rollnum + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void closeConnection() {
        try {
            // Closing the SQLite database connection
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudentByName(String name) {
        try {
            // Deleting a student from the SQLite database based on name
            statement.executeUpdate("DELETE FROM students WHERE name='" + name + "'");
            System.out.println("Student with Name '" + name + "' deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int getLastInsertedRollnum() {
        int lastRollnum = -1; // Default value if no rows are found


        try {
            // Retrieving the maximum rollnum from the SQLite database
            ResultSet resultSet = statement.executeQuery("SELECT MAX(rollnum) FROM students");
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastRollnum;
    }

    public static void main(String[] args) {
        // Example usage
        String url = "student.db"; // Change the file name as needed

        DBConnect db = new DBConnect(url);

        // Inserting a new student
        db.insertStudent(2, "John Doe", "Male", "A");

        // Modifying student information
        db.modifyStudent(1, "Jane Doe", "Female", "B");

        // Displaying all students
        //db.displayStudent("John Doe");
        db.displayAllStudentsInfo();
        // Closing the SQLite database connection
        db.closeConnection();
    }
}



