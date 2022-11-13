import java.util.ArrayList;

public class DepartmentCourse {
    private String courseId;
    private String courseName;
    private ArrayList<Student> registeredStudents;
    private Lecturer lecturer;
    private ArrayList<DepartmentCourse> prerequisites;
    private ArrayList<DepartmentCourse> prerequisiteTo;

    public void addStudent(Student student) {

    }

    public void addPrerequisite(DepartmentCourse course) {
        prerequisites.add(course);
    }

    public void addPrerequisiteTo(DepartmentCourse course) {
        prerequisiteTo.add(course);
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(ArrayList<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public ArrayList<DepartmentCourse> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList<DepartmentCourse> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public ArrayList<DepartmentCourse> getPrerequisiteTo() {
        return prerequisiteTo;
    }

    public void setPrerequisiteTo(ArrayList<DepartmentCourse> prerequisiteTo) {
        this.prerequisiteTo = prerequisiteTo;
    }

}
