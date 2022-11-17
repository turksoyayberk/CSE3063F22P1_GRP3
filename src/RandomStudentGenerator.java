import java.text.SimpleDateFormat;
import java.util.*;

public class RandomStudentGenerator {
    private final String[] firstNames = {"JALE", "ALİ", "MAHMUT", "MANSUR KÜRŞAD", "GAMZE", "MİRAÇ", "YÜCEL", "KUBİLAY", "HAYATİ", "BEDRİYE MÜGE"};
    private final String[] lastNames = {"ŞEN", "KANDEMİR", "ÇEVİK", "ERKURAN", "TÜTEN", "ÖZTÜRK", "YÜZBAŞIOĞLU", "VURAL", "YÜCEL", "SÖNMEZ"};
    private int semester;
    private int registerYear;
    private int id;
    private Student student;
    private Lecturer advisor;
    private Set<DepartmentCourse> takenCourses;
    private Department department;

    public RandomStudentGenerator() {
        takenCourses = new HashSet<>();
    }

    public Student createStudent(Department department, int count, int semesterNo) {
        String name = nameGenerator();
        semester = semesterNo;
        setRegisterYear();
        this.department = department;
        generateId(count);
        setRandomAdvisor();
        student = new Student(name, department);
        student.setStudentID(id);
        student.setSemester(semester);
        student.setRegisterYear(registerYear);
        student.setEducationLevel(educationlevel: "undergraduate");
        student.setAdvisor(advisor);
        student.takeMandatoryCourses();
        student.takeElectiveCourses();
        student.assignCourses();
        department.addStudent(student);
        return student;
    }

    /**
     * Create equal number of students for each year
     *
     * @param department   Department to create students for
     * @param studentCount Total number of students for each year to create
     * @return List of students
     */
    public ArrayList<Student> createStudents(Department department, int studentCount) {
        ArrayList<Student> allStudents = new ArrayList<>();
        int[] fallSemesters = {1, 3, 5, 7};
        int[] springSemesters = {2, 4, 6, 8};
        int[] currentSemesters;
        if (department.getCurrentSemester().equals("fall")) {
            currentSemesters = fallSemesters;
        } else {
            currentSemesters = springSemesters;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < studentCount; j++) {
                allStudents.add(createStudent(department, j, currentSemesters[i]));
            }
            // Reset capacity after each year
            department.resetCapacities();
        }
        return allStudents;
    }

    public static int randomGrade() {
        int[] baseGrades = {10, 15, 20, 25, 30, 40, 40, 40, 50, 50, 60, 60, 60, 60, 70, 70, 70, 80, 80, 80, 85};
        int i = (int) (Math.random() * baseGrades.length);
        return (int) Math.ceil(baseGrades[i] + (Math.random() * 15));
    }

    private void setRandomAdvisor() {
        advisor = department.getLecturers().get(registerYear % 4);
    }

    private void setRegisterYear() {
        int currentYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        registerYear = (int) Math.ceil(currentYear - semester / 2.0);
    }

    private void generateId(int count) {
        id = department.getDepartmentNumber() * 100000 + (registerYear % 100) * 1000 + count;
    }

    private String nameGenerator() {
        String first = firstNames[(int) (Math.random() * firstNames.length)];
        String last = lastNames[(int) (Math.random() * lastNames.length)];
        return first + " " + last;
    }
}
