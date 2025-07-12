import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();

        List<Student> students = db.getAllStudents();
        System.out.println("\n📚 Students:");
        for (Student s : students) {
            s.displayDetails();
        }

        List<Professor> professors = db.getAllProfessors();
        System.out.println("\n🎓 Professors:");
        for (Professor p : professors) {
            p.displayDetails();
        }

        List<Course> courses = db.getAllCourses(professors);
        System.out.println("\n📘 Courses:");
        for (Course c : courses) {
            c.displayInfo();
        }

        db.loadEnrollments(students, courses);

        System.out.println("\n📊 Student Grades:");
        for (Student s : students) {
            System.out.println("Grades for " + s.getName() + ":");
            s.viewGrades();
            System.out.println();
        }

        System.out.println("\n🧵 Starting concurrent enrollments...");

        Student s1 = students.get(0); // Aarav
        Course c1 = courses.get(0); // Intro to Java

        Thread t1 = new StudentEnrollmentTask(s1, c1, 95);
        Thread t2 = new StudentEnrollmentTask(s1, c1, 87); // both try to enroll same student

        t1.setName("Thread-1");
        t2.setName("Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n📋 Final Grades for " + s1.getName() + ":");
        s1.viewGrades();

        System.out.println("\n🚀 Starting thread pool enrollment simulation...");

        // Create a thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Prepare sample enrollment jobs
        executor.submit(new EnrollmentJob(students.get(0), courses.get(1), 89)); // Aarav → DB Systems
        executor.submit(new EnrollmentJob(students.get(1), courses.get(2), 91)); // Niharika → Adv DS
        executor.submit(new EnrollmentJob(students.get(2), courses.get(0), 85)); // Riya → Java
        executor.submit(new EnrollmentJob(students.get(3), courses.get(3), 72)); // Karan → OS
        executor.submit(new EnrollmentJob(students.get(4), courses.get(4), 98)); // Sneha → Web

        executor.shutdown(); // no more tasks

        try {
            executor.awaitTermination(5, TimeUnit.SECONDS); // wait for all threads to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n📋 Final Grades After Thread Pool Execution:");
        for (Student s : students) {
            System.out.println("Grades for " + s.getName() + ":");
            s.viewGrades();
        }

        System.out.println("\n🖨 Writing grade reports to files...");

        for (Student s : students) {
            s.writeGradeReportToFile();
        }

        Student newStudent = db.addStudent("Ishaan Kapoor", "ishaan.kapoor@example.com");

        if (newStudent != null) {
            students.add(newStudent); // update the list in Java
        }

        db.closeConnection();

    }
}
