import java.util.*;

public class Lecturer extends Person {

    private List<DepartmentCourse> coursesGiven;
    private List<Student> advisedStudents;


    public Lecturer(String name, Department department) {

        super(name,department);

    }

    public List<DepartmentCourse> getcoursesGiven() {

        return coursesGiven;
    }

    public List<Student> getAdvisedStudents() {

        return advisedStudents;
    }

    public void addCourse(DepartmentCourse course) {

        coursesGiven.add(course);
    }

    public boolean removeCourse(DepartmentCourse course) {

        return coursesGiven.remove(course);
    }


}
