import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();

        
        db.deleteStudent("ishaan.kapoor@example.com");

        List<Student> students = db.getAllStudents();
        System.out.println("\n Students:");
        for (Student s : students) {
            s.displayDetails();
        }

        List<Professor> professors = db.getAllProfessors();
        System.out.println("\n Professors:");
        for (Professor p : professors) {
            p.displayDetails();
        }

        List<Course> courses = db.getAllCourses(professors);
        System.out.println("\n Courses:");
        for (Course c : courses) {
            c.displayInfo();
        }

        db.loadEnrollments(students, courses);
        System.out.println("\nStudent Grades:");
        for (Student s : students) {
            System.out.println("Grades for " + s.getName() + ":");
            s.viewGrades();
            System.out.println();
        }

        System.out.println("\nStarting concurrent enrollments...");
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
        System.out.println("\nFinal Grades for " + s1.getName() + ":");
        s1.viewGrades();


        System.out.println("Starting student enrollments...");
        StudentEnrollmentTask t0 = new StudentEnrollmentTask(students.get(1), courses.get(2), 91); // Niharika → Adv DS
        StudentEnrollmentTask t3 = new StudentEnrollmentTask(students.get(2), courses.get(0), 85); // Riya → Java
        StudentEnrollmentTask t4 = new StudentEnrollmentTask(students.get(3), courses.get(3), 72); // Karan → OS
        StudentEnrollmentTask t5 = new StudentEnrollmentTask(students.get(4), courses.get(4), 98); // Sneha → Web Dev

        t0.start();
        t3.start();
        t4.start();
        t5.start();

        // Wait for all threads to finish
        try {
            t0.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print final grades
        System.out.println("\nFinal Grades After Enrollment:");
        for (Student s : students) {
            System.out.println("Grades for " + s.getName() + ":");
            s.viewGrades();
        }

        // Write grade reports to files
        System.out.println("\nWriting grade reports to files...");
        for (Student s : students) {
            s.writeGradeReportToFile();
        }

        //adding a student
        Student s_new = db.addStudent("Perry Mason", "perry.mason@example.com");
        Thread t_new = new StudentEnrollmentTask(s_new, courses.get(2), 70);
        Thread t_new2  = new StudentEnrollmentTask(s_new, courses.get(0), 77);
        t_new.start();
        t_new2.start();
        try {
            t_new.join();
            t_new2.join();
        } catch (Exception e) {
            System.out.println("an error occured");
        }

        s_new.writeGradeReportToFile();

        db.closeConnection();

    }
}
