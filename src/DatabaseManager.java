import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/college";
    private static final String USER = "root";
    private static final String PASSWORD = "Shivansh15";

    private Connection conn;

    public DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // ⬅️ add this line
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to MySQL database!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }

    // Example method: Fetch all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("student_id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                students.add(new Student(id, name, email));
            }

        } catch (SQLSyntaxErrorException e) {
            System.out.println("❌ Syntax error in SQL: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Database error: " + e.getMessage());
        }

        return students;
    }

    public void closeConnection() {
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }

    public List<Course> getAllCourses(List<Professor> professorList) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                String levelStr = rs.getString("level");
                int professorId = rs.getInt("professor_id");

                // Get Professor object from professorList
                Professor prof = null;
                for (Professor p : professorList) {
                    if (p.getId() == professorId) {
                        prof = p;
                        break;
                    }
                }

                CourseLevel level = CourseLevel.valueOf(levelStr);
                Course course = new Course(courseId, courseName, level, prof);
                courses.add(course);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching courses: " + e.getMessage());
        }

        return courses;
    }

    public List<Professor> getAllProfessors() {
        List<Professor> professors = new ArrayList<>();
        String query = "SELECT * FROM professors";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("professor_id");
                String name = rs.getString("name");
                String email = rs.getString("email");

                Professor prof = new Professor(id, name, email);
                professors.add(prof);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching professors: " + e.getMessage());
        }

        return professors;
    }

    public void loadEnrollments(List<Student> students, List<Course> courses) {
        String query = "SELECT * FROM enrollments";

        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                int courseId = rs.getInt("course_id");
                int grade = rs.getInt("grade");

                // Find matching Student object
                Student matchedStudent = null;
                for (Student s : students) {
                    if (s.getId() == studentId) {
                        matchedStudent = s;
                        break;
                    }
                }

                // Find matching Course object
                Course matchedCourse = null;
                for (Course c : courses) {
                    if (c.getCourseId() == courseId) {
                        matchedCourse = c;
                        break;
                    }
                }

                if (matchedStudent != null && matchedCourse != null) {
                    matchedStudent.enrollInCourse(matchedCourse);
                    matchedStudent.assignGrade(matchedCourse, grade);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error loading enrollments: " + e.getMessage());
        }
    }

    public Student addStudent(String name, String email) {
        String query = "INSERT INTO students (name, email) VALUES (?, ?)";
        Student newStudent = null;

        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int newId = rs.getInt(1);
                    newStudent = new Student(newId, name, email);
                    System.out.println("✅ Added student: " + name + " (ID: " + newId + ")");
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Error adding student: " + e.getMessage());
        }

        return newStudent;
    }

}
