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

    }

    public void addPrerequisiteTo(DepartmentCourse course) {

    }

    public String getCourseId(){
        return courseId;

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList getRegisteredStudents() {
        return registeredStudents;
    }

    public void setRegisteredStudents(ArrayList registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public ArrayList getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList prerequisites) {
        this.prerequisites = prerequisites;
    }

    public ArrayList getPrerequisiteTo() {
        return prerequisiteTo;
    }

    public void setPrerequisiteTo(ArrayList prerequisiteTo) {
        this.prerequisiteTo = prerequisiteTo;
    }

}
