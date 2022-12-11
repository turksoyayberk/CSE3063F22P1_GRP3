import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

	/**
	 * The Student class that holds student information and contains related methods. This is a subtype of the class Person
	 */
public class Student extends Person {
	private int studentID;
	private int semester;
	private int registerYear;
	private Lecturer advisor;
	private int[] takenCredits;
	private ArrayList<DepartmentCourse> allCourses;
	private HashMap<ElectiveCourse, HashMap<String, Integer>> electiveCourseChoices;
	private HashSet<ElectiveCourse> takenElectives;
	private HashMap<DepartmentCourse, StudentCourse> newlyTakenCourses;
	private HashMap<DepartmentCourse, StudentCourse> previousCourses;
	private HashSet<DepartmentCourse> failedCourses;
	private HashSet<DepartmentCourse> passedCourses;
	private Transcript transcript;
	private StudentTimetable timetable;
	private String educationLevel;
	private ArrayList<String> registrationMessages;
	private final Logger logger = Logger.getLogger(
	    Student.class.getName());

public Student(String name, Department department) {
	super(name, department);
	timetable = new StudentTimetable(this);
	newlyTakenCourses = new HashMap<>();
	previousCourses = new HashMap<>();
	registrationMessages = new ArrayList<>();
	allCourses = new ArrayList<>();
	electiveCourseChoices = new HashMap<>();
	takenElectives = new HashSet<>();
	takenCredits = new int[8];
	failedCourses = new HashSet<>();
	passedCourses = new HashSet<>();
	}

	    /**
	     * Takes all mandatory courses until current semester
	     */
public void takeMandatoryCourses() {
	 for (int i = 1; i <= semester; i++) {
	      ArrayList<DepartmentCourse> semesterCourses = this.getDepartment().getCurriculum().getSemester(i);
	      for (DepartmentCourse course : semesterCourses) {
	           if (!(course instanceof ElectiveCourse)) {
	                allCourses.add(course);
	                logger.info("Student " + this.getName() + " has taken " + course.getCourseName());
	           }
	      }
	  }
}

	    /**
	     * Takes all elective courses until current semester
	     */
 public void takeElectiveCourses() {
	   HashMap<String, DepartmentCourse> nonTechnicalElectives = this.getDepartment().getNonTechnicalElectiveCourses();
	   HashMap<String, DepartmentCourse> technicalElectives = this.getDepartment().getTechnicalElectiveCourses();
	   HashMap<String, DepartmentCourse> facultyElectives = this.getDepartment().getFacultyElectiveCourses();
	   HashMap<String, DepartmentCourse> universityElectives = this.getDepartment().getUniversityElectiveCourses();
	   for (int i = 1; i <= semester; i++) {
	       ArrayList<DepartmentCourse> semesterCourses = this.getDepartment().getCurriculum().getSemester(i);
	       for (DepartmentCourse course : semesterCourses) {
	            if (course instanceof ElectiveCourse) {
	                ArrayList<DepartmentCourse> electiveCourses = new ArrayList<>();
	                 switch (((ElectiveCourse) course).getType()) {
	                     case "NTE":
	                        electiveCourses.addAll(nonTechnicalElectives.values());
	                        break;
	                     case "TE":
	                        electiveCourses.addAll(technicalElectives.values());
	                        break;
	                     case "FTE":
	                        electiveCourses.addAll(facultyElectives.values());
	                        break;
	                     case "UE":
	                        electiveCourses.addAll(universityElectives.values());
	                        break;
	                    }
	                 
	                  // Get random elective course from the list
	                 int randomIndex = (int) (Math.random() * electiveCourses.size());
	                 ElectiveCourse randomElectiveCourse = (ElectiveCourse) electiveCourses.get(randomIndex);
	                 String message = "";
	                 if (takenElectives.contains(randomElectiveCourse)) {
	                    message = String.format("Student could not take %s because it has already taken in previous semesters", randomElectiveCourse.getCourseId());
	                    addRegistrationMessage(message);
	                    logger.warning(message);
	                 }
	                 if(electiveCourseChoices.get(randomElectiveCourse) != null) {
	                    message = String.format("Student could not take %s because it has already taken another %s course", randomElectiveCourse.getCourseId(), course.getCourseId());
	                    addRegistrationMessage(message);
	                    logger.warning(message);
	                 }
	                 if (message.isEmpty()) {
	                     allCourses.add(randomElectiveCourse);
	                     electiveCourseChoices.put(randomElectiveCourse, new HashMap<>());
	                     electiveCourseChoices.get(randomElectiveCourse).put(course.getCourseId(), i);
	                     takenElectives.add(randomElectiveCourse);
	                     logger.info("Student " + this.getName() + " has taken " + randomElectiveCourse.getCourseId());
	                 }
	            }
	        }
	    }
}

 /**
* Return which semester the student has taken the elective course
*/
public int getElectiveSemester(ElectiveCourse electiveCourse) {
	int semester = 0;
	HashMap<String, Integer> choice = electiveCourseChoices.get(electiveCourse);
	if (choice != null) {
	    for (String courseId : choice.keySet()) {
	         semester = choice.get(courseId);
	         break;
	    }
	}
	return semester;
}

 /**
 * Creates StudentCourse objects for all courses taken by the student
*/
public void createStudentCourses() {
for (DepartmentCourse course : allCourses) {
	   StudentCourse studentCourse = new StudentCourse(course, RandomStudentGenerator.randomGrade(), course.getSemester());
	   studentCourse.checkRules(this, course);
	   }
}
 /**
* Create a transcript for the student
*/

public Transcript generateTranscript(boolean includeNewCourses) {
	 transcript = new Transcript(this, includeNewCourses);
	 return transcript;
}

/**
 * Adds a new course to the student's timetable
*/
public void registerCourse(DepartmentCourse course, int grade, int semester) {
	 StudentCourse studentCourse = new StudentCourse(course, grade, semester);
	 if (semester == this.semester) {
	     if (newlyTakenCourses.size() == this.getDepartment().getCourseTakeLimit()) {
	         addRegistrationMessage(String.format("Student could not take %s because reached the course take limit.", course.getCourseId()));
	          return;
	      } else {
	         newlyTakenCourses.put(course, studentCourse);
	         timetable.assignCourse(course);
	         course.addStudent(this);
	      }
	 } else {
	      if (!studentCourse.isPassed()) {
	          failedCourses.add(studentCourse.getMainCourse());
	       } else {
	          passedCourses.add(studentCourse.getMainCourse());
	       }
	  previousCourses.put(course, studentCourse);
	  }
	 
	 takenCredits[semester - 1] += course.getCredits();
}

	    //Checks if the student passed prerequisites for the course
	    public ArrayList<DepartmentCourse> getFailedPrerequisites(DepartmentCourse course) {
	        ArrayList<DepartmentCourse> failedPrerequisites = new ArrayList<>();
	        for (DepartmentCourse prerequisite : course.getPrerequisites()) {
	            if (!passedCourses.contains(prerequisite)) {
	                failedPrerequisites.add(prerequisite);
	            }
	        }
	        return failedPrerequisites;
	    }


	
	     // Gets Passed courses of student
	    public HashSet<DepartmentCourse> getPassedCourses() {
	        return passedCourses;
	    }

	    
	     //Adds Passed courses of the student
	    public void addPassedCourse(StudentCourse studentCourse) {
	        passedCourses.add(studentCourse.getMainCourse());
	    }

	    // Gets id of the student
	    public int getStudentID() {
	        return studentID;
	    }

	    // Sets id of the student.
	    public void setStudentID(int studentID) {
	        this.studentID = studentID;
	    }

	    // Gets semester of the student.
	    public int getSemester() {
	        return semester;
	    }

	    //Sets semester of the student.
	    public void setSemester(int semester) {
	        this.semester = semester;
	    }

	    //Gets Advisor of the student
	    public Lecturer getAdvisor() {
	        return advisor;
	    }

	    //Sets advisor of the student
	    public void setAdvisor(Lecturer advisor) {
	        this.advisor = advisor;
	    }

	    //Gets time table of the student.
	    public StudentTimetable getTimetable() {
	        return timetable;
	    }

	    //Gets newly taken courses of the student
	    public HashMap<DepartmentCourse, StudentCourse> getNewlyTakenCourses() {
	        return newlyTakenCourses;
	    }

	    //Gets previous courses of the student.
	    public HashMap<DepartmentCourse, StudentCourse> getPreviousCourses() {
	        return previousCourses;
	    }

	    //Gets Education level of the student
	    public String getEducationLevel() {
	        return educationLevel;
	    }

	    //Sets education level of the student
	    public void setEducationLevel(String educationLevel) {
	        this.educationLevel = educationLevel;
	    }

	    //Gets registered year of the student
	    public int getRegisterYear() {
	        return registerYear;
	    }

	    //Sets registered year of the student
	    public void setRegisterYear(int registerYear) {
	        this.registerYear = registerYear;
	    }

	    // Gets transcript of the student
	    public Transcript getTranscript() {
	        return transcript;
	    }
      //Gets list of the Registration messages
	    public ArrayList<String> getRegistrationMessages() {
	        return registrationMessages;
	    }
	    //Adds registration message if any conditions are not met
	    public void addRegistrationMessage(String message) {
	        registrationMessages.add(message);
	    }

	    //Gets list of the all courses
	    public ArrayList<DepartmentCourse> getAllCourses() {
	        return allCourses;
	    }

	    //Calculates the sum of the credits taken up to the given semester.
	    public int getTakenCredits(int semester) {
	        int cumulativeCredits = 0;
	        for (int i = 0; i < semester; i++) {
	            cumulativeCredits += takenCredits[i];
	        }
	        return cumulativeCredits;
	    }

	    //Calculates the completed of the credits taken up to the given semester.
	    public int getCompletedCredits(int semester) {
	        int completedCredits = 0;
	        for (DepartmentCourse course : allCourses) {
	            if (!failedCourses.contains(course)) {
	                if (course instanceof ElectiveCourse && getElectiveSemester((ElectiveCourse) course) > semester) {
	                    continue;
	                }
	                completedCredits += course.getCredits();
	            }
	        }
	        return completedCredits;
	    }
	}


	


