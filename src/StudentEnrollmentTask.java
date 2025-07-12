public class StudentEnrollmentTask extends Thread {
    private Student student;
    private Course course;
    private int grade;

    public StudentEnrollmentTask(Student student, Course course, int grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    @Override
    public void run() {
        synchronized (student) {
            System.out.println("🔁 " + student.getName() + " is being enrolled in " + course.getCourseName() + " by "
                    + Thread.currentThread().getName());
            student.enrollInCourse(course);
            student.assignGrade(course, grade);
            System.out.println("✅ " + student.getName() + " enrolled and graded in " + course.getCourseName());
        }
    }
}
