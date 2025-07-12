import java.util.*;

public class Professor extends Person {
    private List<Course> coursesTeaching = new ArrayList<>();

    public Professor(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public void displayDetails() {
        System.out.println("Professor: " + name + " (" + email + ")");
    }

    public void addCourse(Course course) {
        coursesTeaching.add(course);
    }

    public List<Course> getCoursesTeaching() {
        return coursesTeaching;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

}
