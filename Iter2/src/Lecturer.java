import java.util.ArrayList;
import java.util.List;

// The Lecturer class extends Person class that includes lecturer informations and contains related methods

public class Lecturer extends Person {
    // Advised students by lecturer
    private ArrayList<Student> advisedStudents;
    // Courses given by lecturer
    private ArrayList<DepartmentCourse> givenCourses;

    // Lecturer Constructor
    public Lecturer(String name, Department department) {
        super(name, department);
        givenCourses = new ArrayList<>();
        advisedStudents = new ArrayList<>();
    }

    // Add course to lecturer
    public void addCourse(DepartmentCourse course) {
        givenCourses.add(course);
    }

    // Add student to lecturer
    public void addStudent(Student student) {
        advisedStudents.add(student);
    }

    // Get given courses by lecturer
    public List<DepartmentCourse> getGivenCourses() {
        return givenCourses;
    }

    // Get advised students by lecturer
    public List<Student> getAdvisedStudents() {
        return advisedStudents;
    }
}
