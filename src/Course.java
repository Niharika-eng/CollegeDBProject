public class Course {
    private int courseId;
    private String courseName;
    private CourseLevel level;
    private Professor professor;

    public Course(int courseId, String courseName, CourseLevel level, Professor professor) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.level = level;
        this.professor = professor;
    }

    public String getCourseName() {
        return courseName;
    }

    public Professor getProfessor() {
        return professor;
    }

    // ... other methods
    public int getCourseId() {
        return courseId;
    }

    public void displayInfo() {
        String profName = (professor != null) ? professor.getName() : "Unknown";
        System.out.println(courseName + " [" + level + "] - Taught by " + profName);
    }

}
