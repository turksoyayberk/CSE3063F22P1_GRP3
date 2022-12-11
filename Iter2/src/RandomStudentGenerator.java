import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * The RandomStudentGenerator class that creates random students with all randomized information and contains related methods
 */
public class RandomStudentGenerator {
    /**
     * Student first names String array
     */
    private final String[] firstNames = {"JALE", "ALI", "MAHMUT", "MANSUR", "GAMZE", "MAHMUT", "YUCEL", "KUBILAY", "HAYATI", "BEDRIYE MUGE", "BIRSEN", "SERDAL", "BUNYAMIN", "OZGUR", "FERDI", "REYHAN", "ILHAN", "GULSAH", "NALAN", "SEMIH", "ERGIN", "FATIH", "SENAY", "SERKAN", "EMRE", "BAHATTIN", "IRAZCA", "HATICE", "BARIS", "REZAN", "FATIH", "FUAT", "GOKHAN", "ORHAN", "MEHMET", "EVREN", "OKTAY", "HARUN", "YAVUZ", "PINAR", "MEHMET", "UMUT", "MESUDE", "HUSEYIN CAHIT", "HASIM ONUR", "EYYUP SABRI", "MUSTAFA", "UFUK", "AHMET ALI", "MEDIHA", "HASAN", "KAMIL", "NEBI", "OZCAN", "NAGIHAN", "CEREN", "SERKAN", "HASAN", "YUSUF KENAN", "TARKAN", "MERAL LEMAN", "ERGIN", "KENAN AHMET", "URAL", "YAHYA", "BENGU", "FATIH NAZMI", "DILEK", "MEHMET", "TUFAN AKIN", "MEHMET", "TURGAY YILMAZ", "GULDEHEN", "GOKMEN", "BULENT", "EROL", "BAHRI", "OZLEM", "SELMA", "TUGSEM", "NAZLI", "MURAT", "EBRU", "AHMET", "HUSEYIN YAVUZ", "BASAK", "AYSEGUL", "EVRIM", "YASER", "ULKU", "OZHAN", "UFUK", "AKSEL", "FULYA", "BURCU", "TAYLAN", "YILMAZ", "ZEYNEP", "BAYRAM", "GULAY", "RABIA", "SEVDA", "SERHAT", "ENGIN", "ASLI", "TUBA", "BARIS", "SEVGI", "KALENDER", "HALIL", "BOLGE", "FERDA", "EZGI", "AYSUN", "SEDA", "OZDEN", "KORAY", "SENEM", "ZEYNEP", "EMEL", "BATURAY KANSU", "NURAY", "AYDOGAN", "DENIZ", "ILKNUR", "TEVFIK", "HASAN SERKAN", "KIRAC", "SEYFI", "SEYMA", "ERSAGUN", "DILBER", "MESUT", "ELIF", "MUHAMMET FATIH", "OZGUR SINAN", "MEHMET OZGUR", "MAHPERI", "ONUR", "FARAH ZEYNEP", "VOLKAN", "HALE", "YENER", "SEDEF", "FADIL", "SULTAN", "MUAMMER HAYRI", };
    /*
     * Student last names String array
     */
    private final String[] lastNames = {"ADA", "KANDEMIR", "ATAK", "ERKURAN", "TASTAN", "OZTURK", "YAZICIOGLU", "VURAL", "YUCEL", "SONMEZ", "ERTEKAN", "DEDE", "UYANIK", "ASLAN", "AKBULUT", "ORHON", "UZ", "YAVUZ", "ERDEM", "KULAC", "KAYA", "SELVA", "AKPINAR", "ABACIOGLU", "ATAY", "ISIK", "ALZER", "OZDEMIR", "OZTURK", "TAHTACI", "BUYUKCAM", "KULAKSIZ", "AKSEL", "EROÄ�LU", "KARAKUM", "DAL", "Ã–CAL", "AYHAN", "YÄ°Ä�Ä°T", "YARBÄ°L", "GÃœMÃœÅ�AY", "MURT", "HALHALLI", "ULUÃ–Z", "Å�EYHANLI", "YILMAZ", "SARAÃ‡OÄ�LU", "SEZER", "DOÄ�AN", "DEMÄ°R", "KAYAYURT", "SÃœRÃœM", "YAVAÅ�Ä°", "TURGUT", "BARBAROS", "ALDÄ°NÃ‡", "TEKÄ°N", "GÃœLÅ�AN", "KÃœFECÄ°LER", "ALMACIOÄ�LU", "Ã‡Ä°LDÄ°R", "TÃœRKDOÄ�AN", "KAYA", "AKNER", "Å�ELÄ°MAN", "YAMAN", "ATÄ°K", "YÄ°Ä�Ä°T", "GÄ°RAY", "YALÃ‡INKAYA", "KILIÃ‡", "Å�ENTÃœRK", "KARABAC", "DEGIRMENCI", "BODUROGLU", "YILDIZ", "GULER", "ERASLAN", "UZER", "PISIRGEN", "ADANIR", "KOC", "KORKMAZ", "YENIDOGAN", "AYDOÄ�AN", "ACARBULUT", "ERGE", "ERDOÄ�AN", "Ã–Ä�ÃœT AYDIN", "KUÅ�KU", "KUCUR TÃœLÃœBAÅ�", "PEKTAÅ�", "KAYACAN", "GÃœLEN", "DOÄ�AN", "CANDAN", "TEMEL", "YENÄ°GÃœN", "YILDIRIM", "BEDER", "AKINCI", "Ã–ZDEMÄ°R", "ONUK", "AYDOÄ�AN", "YILMAZ", "CÃ–MERT", "TOPAL", "KARAHAN", "Å�AHÄ°N", "Ã‡ETÄ°N", "AYTAÃ‡", "KÄ°Å�Ä°", "GÃœNDÃœZ", "AK", "URFALI", "KARAMAN", "MEMETOÄ�LU", "KAZBEK", "KÄ°REÃ‡Ã‡Ä°", "AKIN", "YADÄ°GAROÄ�LU", "YÃœKSEL", "Ã–ZÃ‡ELÄ°K ORAL", "BABUÅ�", "KAPLAN", "AKÃ–Z", "KARTAL", "BÄ°LGÄ°Ã‡", "ERDEN", "TUÄ�CUGÄ°L", "KUMRAL", "ERBAÅ�", "ORAL", "KILAÃ‡", "CENGÄ°Z", "YILDIRIM", "BALABAN"};
    /**
     * Randomly generated semester year
     */
    private int semester;
    /**
     * Randomly generated student register year
     */
    private int registerYear;
    /**
     * Randomly generated student id
     */
    private int id;
    /**
     * A reference of the Student class
     */
    private Student student;
    /**
     * A reference of the Lecturer class
     */
    private Lecturer advisor;
    /**
     * A reference of the Department class
     */
    private Department department;

    /**
     * RandomStudentGenerator constructor
     */
    public RandomStudentGenerator() {
    }

    /**
     * Create a student and set student's information
     *
     * @param department Department to select
     * @param count      Student count to generate proper id
     * @param semesterNo To select semester
     * @return A student with his/her information
     */
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
        student.setEducationLevel("undergraduate");
        student.setAdvisor(advisor);
        student.takeMandatoryCourses();
        student.takeElectiveCourses();
        student.createStudentCourses();
        department.addStudent(student);
        advisor.addStudent(student);
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
        logStudent(allStudents);
        return allStudents;
    }

    /**
     * Generate a new random grade using base grades array
     *
     * @return A random grade
     */
    public static int randomGrade() {
        int[] baseGrades = {10, 15, 20, 25, 30, 40, 40, 40, 50, 50, 60, 60, 60, 60, 70, 70, 70, 80, 80, 80, 85};
        int i = (int) (Math.random() * baseGrades.length);
        return (int) Math.ceil(baseGrades[i] + (Math.random() * 15));
    }

    /**
     * Choose a random advisor
     */
    private void setRandomAdvisor() {
        advisor = department.getLecturers().get(registerYear % 4);
    }

    /**
     * Generate a register year
     */
    private void setRegisterYear() {
        int currentYear = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        registerYear = (int) Math.ceil(currentYear - semester / 2.0);
    }

    /**
     * Generate a id
     *
     * @param count To generate id for student
     */
    private void generateId(int count) {
        id = department.getDepartmentNumber() * 100000 + (registerYear % 100) * 1000 + count;
    }

    /**
     * Generate a random first and last name using firstNames and lastNames arrays
     *
     * @return A random name
     */
    private String nameGenerator() {
        String first = firstNames[(int) (Math.random() * firstNames.length)];
        String last = lastNames[(int) (Math.random() * lastNames.length)];
        return first + " " + last;
    }

    /**
     * Write student logs to Stulog.txt file
     *
     * @param students List of students
     */
    private void logStudent(List<Student> students) {
        Logger logger
                = Logger.getLogger(
                RandomStudentGenerator.class.getName());
        FileWriter stuWriter;
        try {
            stuWriter = new FileWriter("StuLog.txt");
            for (Student student : students) {
                StringBuilder studentLog = new StringBuilder("\n" + student.getName() + " (" + student.getStudentID() + ") " + "registered.\n");
                for (Map.Entry<DepartmentCourse, StudentCourse> course : student.getNewlyTakenCourses().entrySet()) {
                    studentLog.append("He/She taken ").append(course.getValue().getMainCourse().getCourseName()).append(" this year \n");
                }
                //logger.log(Level.INFO, String.valueOf(studentLog));
                stuWriter.append(studentLog.toString());
                stuWriter.flush();
            }
            stuWriter.close();
        } catch (IOException ex) {
            System.out.println("Couldn't create the file.");
        }
    }
}
