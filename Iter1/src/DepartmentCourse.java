import java.util.ArrayList;

public class DepartmentCourse {
    private String courseId;
    private String courseName;
    private ArrayList<int[]> courseSchedule;
    private ArrayList<Student> registeredStudents;
    private ArrayList<DepartmentCourse> prerequisites;
    private ArrayList<DepartmentCourse> prerequisiteTo;
    private ArrayList<LabCourse> labSections;
    private ArrayList<TeachingAssistant> teachingAssistants;
    private Lecturer lecturer;
    private int lectureHours;
    private int semester;
    private int credits;
    private int labHours;
    private int minCompletedCredits;
    
    public DepartmentCourse(String courseId, String courseName, int credits, int semester, int lectureHours) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.semester = semester;
        this.lectureHours = lectureHours;
        this.registeredStudents = new ArrayList<>();
        this.prerequisites = new ArrayList<>();
        this.prerequisiteTo = new ArrayList<>();
        this.labSections = new ArrayList<>();
        this.teachingAssistants = new ArrayList<>();
        this.courseSchedule = new ArrayList<>();
    }
    public DepartmentCourse(){}

    public void addStudent(Student student) {
        registeredStudents.add(student);
    }

    public void addLectureHour(int day, int lectureHour) {
        courseSchedule.add(new int[] { day, lectureHour });
    }

    public void addPrerequisite(DepartmentCourse course) {
        prerequisites.add(course);
    }

    public void addPrerequisiteTo(DepartmentCourse course) {
        prerequisiteTo.add(course);
    }

    public void setLabHours(int labHours) {
        this.labHours = labHours;
    }

    public int labSectionsCount() {
        return labSections.size();
    }

    public void addLabSection(LabCourse labCourse) {
        labSections.add(labCourse);
    }

    public void addTeachingAssistant(TeachingAssistant teachingAssistant) {
        teachingAssistants.add(teachingAssistant);
    }

    public TeachingAssistant getTeachingAssistant(int index) {
        if (teachingAssistants.size() > index) {
            return teachingAssistants.get(index);
        } else {
            return null;
        }
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public ArrayList<int[]> getCourseSchedule() {
        return courseSchedule;
    }

    public ArrayList<DepartmentCourse> getPrerequisites() {
        return prerequisites;
    }

    public ArrayList<LabCourse> getLabSections() {
        return labSections;
    }

    public int getSemester() {
        return semester;
    }

    public int getCredits() {
        return credits;
    }

    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public ArrayList<DepartmentCourse> getPrerequisiteTo() {
        return prerequisiteTo;
    }

    public ArrayList<TeachingAssistant> getTeachingAssistants() {
        return teachingAssistants;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public int getLectureHours() {
        return lectureHours;
    }

    public int getLabHours() {
        return labHours;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getMinCompletedCredits() {
        return minCompletedCredits;
    }

    public void setMinCompletedCredits(int minCompletedCredits) {
        this.minCompletedCredits = minCompletedCredits;
    }
