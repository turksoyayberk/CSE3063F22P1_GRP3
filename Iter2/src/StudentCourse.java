/**
 * The StudentCourse class that holds information and methods regarding the relationship between students and courses.
 */
public class StudentCourse {
    /**
     * Student's course grade [0, 100]
     */
    private int grade;
    /**
     * Student's course point [0, 4]
     */
    private double point;
    /**
     * Letter grade calculated from the course grade
     */
    private String letterGrade;
    /**
     * Main department course (lectures)
     */
    private DepartmentCourse mainCourse;
    /**
     * Student is passed or not
     */
    private boolean passed;
    /**
     * In which semester the student took the course
     */
    private int registeredSemester;
    /**
     * Registration warning messages
     */
    private final String[] StudentRegistrationMessages = {
            "Student is not eligible for course %s because prerequisite %s did not meet.",
            "Advisor did not approve take of %s because he/she has another course at course time.",
            "Advisor did not approve take of %s because he/she reached credit take limit (semesters: %s).",
            "Student is could not take %s because no capacity left.",
            "Student is could not take this lab section %s because no capacity left.",
            "Advisor did not approve taking %s because he/she has another course at that time.",
            "Student cannot take %s in this semester because the course belongs to another semester.",
            "Student cannot take %s because he/she did not meet minimum completed credits.",
    };

    private final String succesful = "Student successfully registered to %s";

    public StudentCourse(DepartmentCourse course, int grade, int semester) {
        this.mainCourse = course;
        setGrade(grade);
        this.point = getPoint();
        this.letterGrade = getLetterGrade();
        this.registeredSemester = semester;
    }

    public void checkRules(Student student, DepartmentCourse course) {

        Department department = student.getDepartment();
        String message = "";
        String succesfulMessage = "";
        String courseIdName = course.getCourseId() + " " + course.getCourseName();
        int semester = course.getSemester();


        if (semester == 0 && course instanceof ElectiveCourse) {
            semester = student.getElectiveSemester((ElectiveCourse) course);
        }

        if (!student.getFailedPrerequisites(course).isEmpty()) { // Check prerequisites
            message = String.format(StudentRegistrationMessages[0], courseIdName, student.getFailedPrerequisites(course).get(0).getCourseName());
            student.addRegistrationMessage(message);
            department.addCourseRegistrationMessage(0, course);
        }
        if (student.getTimetable().courseHasCollision(course)) { // Check collisions
            message = String.format(StudentRegistrationMessages[1], courseIdName);
            student.addRegistrationMessage(message);
            department.addCourseRegistrationMessage(1, course);
        }
        if (student.getTakenCredits(semester) + course.getCredits() > department.getMaximumCredits()) { // Check credits
            message = String.format(StudentRegistrationMessages[2], courseIdName, course.getSemester());
            student.addRegistrationMessage(message);
            department.addCourseRegistrationMessage(2, course);
        }

        if (course instanceof ElectiveCourse && !((ElectiveCourse) course).hasCapacity()) { // Check capacity
            message = String.format(StudentRegistrationMessages[3], courseIdName);
            student.addRegistrationMessage(message);
            department.addCourseRegistrationMessage(3, course);
        }

        if (!course.getLabSections().isEmpty()) { //Check lab sections
            int random = (int) (Math.random() * course.getLabSections().size());
            LabCourse randomLabSection = course.getLabSections().get(random);


            if (!randomLabSection.hasCapacity()) { // Check lab section capacity
                message = String.format(StudentRegistrationMessages[4], randomLabSection.getCourseId());
                student.addRegistrationMessage(message);
                department.addCourseRegistrationMessage(4, randomLabSection);
            }

            if (student.getTimetable().courseHasCollision(randomLabSection)) { // Check lab section collisions
                message = String.format(StudentRegistrationMessages[5], randomLabSection.getCourseId());
                student.addRegistrationMessage(message);
                department.addCourseRegistrationMessage(5, randomLabSection);
            }
            if (randomLabSection.hasCapacity() && !student.getTimetable().courseHasCollision(randomLabSection) && message.isEmpty()) {
                student.getTimetable().assignCourse(randomLabSection);
                randomLabSection.addStudent(student);
            }
        }

        if (course.getSemester() > student.getSemester()) { // Check semester
            message = String.format(StudentRegistrationMessages[6], courseIdName);
            student.addRegistrationMessage(message);
            department.addCourseRegistrationMessage(6, course);
        }

        if (course.getMinCompletedCredits() > 0 && student.getCompletedCredits(semester - 1) < course.getMinCompletedCredits()) { // Check completed credits
            message = String.format(StudentRegistrationMessages[7], courseIdName, course.getSemester());
            student.addRegistrationMessage(message);
            department.addCourseRegistrationMessage(7, course);
        }

        if (message.isEmpty()) { //check if registration is successful
            student.registerCourse(course, RandomStudentGenerator.randomGrade(), semester);
            succesfulMessage = String.format(succesful, courseIdName);
            student.addRegistrationMessage(succesfulMessage);
        }

    }

    public void setGrade(int grade) {
        this.grade = grade;
        setLetterGrade(grade);
        if (point < 1.5) {
            passed = false;
        }
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(int grade) {
        if (grade >= 90) {
            letterGrade = "AA";
            point = 4;
        } else if (grade >= 85) {
            letterGrade = "BA";
            point = 3.5;
        } else if (grade >= 80) {
            letterGrade = "BB";
            point = 3;
        } else if (grade >= 75) {
            letterGrade = "CB";
            point = 2.5;
        } else if (grade >= 65) {
            letterGrade = "CC";
            point = 2;
        } else if (grade >= 55) {
            letterGrade = "DC";
            point = 1.5;
        } else if (grade >= 50) {
            letterGrade = "DD";
            point = 1;
        } else if (grade >= 45) {
            letterGrade = "FD";
            point = 0.5;
        } else {
            letterGrade = "FF";
            point = 0;
        }
    }


    public DepartmentCourse getMainCourse() {
        return mainCourse;
    }


    public int getRegisteredSemester() {
        return registeredSemester;
    }

    public double getPoint() {
        return point;
    }


    public int getGrade() {
        return grade;
    }

    public boolean isPassed() {
        return passed;
    }
}
