import java.io.*;
import java.util.*;

class InvalidGradeException extends Exception {
    public InvalidGradeException(String message) {
        super(message);
    }
}

public class Student extends Person {
    private List<Course> enrolledCourses = new ArrayList<>();
    private Map<Course, Integer> grades = new HashMap<>();

    public Student(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public void displayDetails() {
        System.out.println("Student: " + name + " (" + email + ")");
    }

    public void enrollInCourse(Course course) {
        enrolledCourses.add(course);
    }

    public void assignGrade(Course course, int grade) {
        try {
            if (grade < 0 || grade > 100) {
                throw new InvalidGradeException("Grade must be between 0 and 100");
            }
            grades.put(course, grade);
        } catch (InvalidGradeException e) {
            System.out.println("⚠️ " + e.getMessage());
        }
    }

    public void viewGrades() {
        for (Map.Entry<Course, Integer> entry : grades.entrySet()) {
            System.out.println(entry.getKey().getCourseName() + ": " + entry.getValue());
        }
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void writeGradeReportToFile() {
        String fileName = "GradeReport_" + name.replaceAll(" ", "_") + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(name + " - Grade Report");
            writer.println("-----------------------------");

            if (grades.isEmpty()) {
                writer.println("No courses enrolled.");
            } else {
                for (Map.Entry<Course, Integer> entry : grades.entrySet()) {
                    writer.println(entry.getKey().getCourseName() + ": " + entry.getValue());
                }
            }

            System.out.println("Report written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
    }

}
