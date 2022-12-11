import java.util.ArrayList;
import java.util.HashMap;

// The Curriculum class that creates curriculum for department

public class Curriculum {

    // Semesters and its curriculum informations
    private HashMap<Integer, ArrayList<DepartmentCourse>> semesters;

    // Curriculum constructor
    public Curriculum() {
        semesters = new HashMap<>();
        for (int i = 1; i <= 8; i++) {
            semesters.put(i, new ArrayList<>());
        }
    }

    // Getter method for information of the courses of the department in the
    // selected semester
    public ArrayList<DepartmentCourse> getSemester(int semester) {
        return semesters.get(semester);
    }

    // Add a course. If this course is available in the department do not add this
    // course
    public void addCourse(int semesterNo, DepartmentCourse course) {
        ArrayList<DepartmentCourse> semester = getSemester(semesterNo);
        if (!semester.contains(course)) {
            semester.add(course);
        }
    }

    // Get semesters and its curriculum informations , return Semesters and its
    // curriculum informations
    public HashMap<Integer, ArrayList<DepartmentCourse>> getSemesters() {
        return semesters;
    }
}
