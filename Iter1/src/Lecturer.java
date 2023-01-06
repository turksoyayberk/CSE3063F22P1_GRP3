import java.util.*;

public class Lecturer extends Person {

    private List<DepartmentCourse> givenCourses;
    private List<Student> advisedStudents;


    public Lecturer(String name, Department department) {
        super(name,department);
    }

    public List<DepartmentCourse> getGivenCourses() {
        return givenCourses;
    }

    public List<Student> getAdvisedStudents() {
        return advisedStudents;
    }

    public void addCourse(DepartmentCourse course) {
        givenCourses.add(course);
    }

    public boolean removeCourse(DepartmentCourse course) {
        return coursesGiven.remove(course);
    }


}
