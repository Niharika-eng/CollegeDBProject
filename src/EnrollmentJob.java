
public class EnrollmentJob implements Runnable {
    private Student student;
    private Course course;
    private int grade;

    public EnrollmentJob(Student student, Course course, int grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    @Override
    public void run() {
        synchronized (student) {
            System.out.println("🔁 " + Thread.currentThread().getName() + ": Enrolling " +
                    student.getName() + " in " + course.getCourseName());
            student.enrollInCourse(course);
            student.assignGrade(course, grade);
            System.out.println("✅ " + student.getName() + " enrolled in " +
                    course.getCourseName() + " with grade " + grade);
        }
    }
}
