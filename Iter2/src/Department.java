//Read Json & JSONParser
//Add Lecturers
//Add Teaching Assistants
//Add Courses

//Generate random capacity for course

//Add Course Prerequesties

//Add given course to correct course list (NTE-TE-UE-FTE)

//Keep record of how many students failed to register courses

//Reset capacity of all courses after each semester

//Create JSON object for department

import java.util.ArrayList;
import java.util.HashMap;

public class Department {

  private String name;
  private String id;
  private String faculty;
  private String currentSemester;

  private int departmentNumber;
  private int numberOfLectureDays;
  private int numberOfLectureHours;
  private int maximumCredits;
  private int courseTakeLimit;
  private int labCapacityLimit;
  private int electiveCapacityLimit;

  private Curriculum curriculum;

  private HashMap<String, DepartmentCourse> courses;
  private HashMap<String, DepartmentCourse> mandatoryCourses;
  private HashMap<String, DepartmentCourse> technicalElectiveCourses;
  private HashMap<String, DepartmentCourse> nonTechnicalElectiveCourses;
  private HashMap<String, DepartmentCourse> facultyElectiveCourses;
  private HashMap<String, DepartmentCourse> universityElectiveCourses;

  private ArrayList<Lecturer> lecturers;
  private ArrayList<TeachingAssistant> teachingAssistants;
  private ArrayList<Student> students;

  private final HashMap<String, HashMap<String, Integer>> courseRegistrationMessages;
  private final String[] registrationMessageFormats = {
      "%s students couldn't register %s because of prerequisites did not ok.",
      "%s students couldn't register %s because there is a collision with another course.",
      "%s students couldn't register %s because credit limit exceeded.",
      "%s students couldn't register %s because of quota problem.",
      "%s students couldn't register %s because of quota problem.",
      "%s students couldn't register %s because it has collision with another course.",
      "%s students couldn't register %s because it doesn't include for current semester.",
      "%s students couldn't register %s because they did not complete minimum credits.",
  };

  public Department(String name, String id, String faculty) {
    this.name = name;
    this.id = id;
    this.faculty = faculty;
    lecturers = new ArrayList<>();
    teachingAssistants = new ArrayList<>();
    students = new ArrayList<>();
    courses = new HashMap<>();
    mandatoryCourses = new HashMap<>();
    technicalElectiveCourses = new HashMap<>();
    nonTechnicalElectiveCourses = new HashMap<>();
    facultyElectiveCourses = new HashMap<>();
    universityElectiveCourses = new HashMap<>();
    courseRegistrationMessages = new HashMap<>();
    curriculum = new Curriculum();
  }

  public DepartmentCourse createCourse(String courseId, String courseName, String courseType, int credits,
      int lectureHours, int semester, int capacity) {

    DepartmentCourse courseObject = new DepartmentCourse();

    switch (courseType) {
      case "TE":
      case "UE":
      case "NTE":
      case "FTE":
        courseObject = new ElectiveCourse(courseId, courseName, credits, semester, lectureHours, capacity, courseType);
        break;
      case "M":
        courseObject = new MandatoryCourse(courseId, courseName, credits, semester, lectureHours);
        break;
    }
    
    return courseObject;
  }

  public void addCourse(DepartmentCourse course) {
    if (course.getSemester() == 0 && course instanceof ElectiveCourse) {
      switch (((ElectiveCourse) course).getType()) {
        case "TE":
          technicalElectiveCourses.put(course.getCourseId(), course);
          break;
        case "UE":
          universityElectiveCourses.put(course.getCourseId(), course);
          break;
        case "NTE":
          nonTechnicalElectiveCourses.put(course.getCourseId(), course);
          break;
        case "FTE":
          facultyElectiveCourses.put(course.getCourseId(), course);
          break;
      }
      this.courses.put(course.getCourseId(), course);
    } else if (course instanceof MandatoryCourse) {
      mandatoryCourses.put(course.getCourseId(), course);
      this.courses.put(course.getCourseId(), course);
      this.curriculum.addCourse(course.getSemester(), course);
    } else if (course instanceof ElectiveCourse && course.getSemester() != 0) {
      this.curriculum.addCourse(course.getSemester(), course);
    }
  }

  public void resetCapacities() {
    for (DepartmentCourse course : this.courses.values()) {
      if (course instanceof MandatoryCourse && course.getLabSections().size() > 0) {
        for (LabCourse labSection : course.getLabSections()) {
          int randomSectionCapacity = (int) (Math.random() * labCapacityLimit) + 1;
          labSection.setCapacity(randomSectionCapacity);
        }
      } else if (course instanceof ElectiveCourse) {
        int randomCapacity = (int) (Math.random() * electiveCapacityLimit) + 1;
        ((ElectiveCourse) course).setCapacity(randomCapacity);
      }
    }
  }

  public DepartmentCourse getRandomCourse(Object[] courses) {
    int randomIndex = (int) (Math.random() * courses.length);
    return (DepartmentCourse) courses[randomIndex];
  }

  public DepartmentCourse getCourse(String courseId) {
    return this.courses.get(courseId);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getCourseTakeLimit() {
    return courseTakeLimit;
  }

  public void setCourseTakeLimit(int courseTakeLimit) {
    this.courseTakeLimit = courseTakeLimit;
  }

  public String getCurrentSemester() {
    return currentSemester;
  }

  public void setCurrentSemester(String currentSemester) {
    this.currentSemester = currentSemester;
  }

  public Curriculum getCurriculum() {
    return curriculum;
  }

  public ArrayList<Lecturer> getLecturers() {
    return lecturers;
  }

  public Lecturer getLecturer(int id) {
    return lecturers.get(id);
  }

  public void addLecturer(String name, Department department) {
    lecturers.add(new Lecturer(name, department));
  }

  public void addTeachingAssistant(String name, Department department) {
    teachingAssistants.add(new TeachingAssistant(name, department));
  }

  public void addStudent(Student student) {
    students.add(student);
  }

  public String getFaculty() {
    return faculty;
  }

  public void setFaculty(String faculty) {
    this.faculty = faculty;
  }

  public void setLabCapacityLimit(int labCapacityLimit) {
    this.labCapacityLimit = labCapacityLimit;
  }

  public void setElectiveCapacityLimit(int electiveCapacityLimit) {
    this.electiveCapacityLimit = electiveCapacityLimit;
  }

  public int getNumberOfLectureDays() {
    return numberOfLectureDays;
  }

  public void setNumberOfLectureDays(int numberOfLectureDays) {
    if (numberOfLectureDays > 7 || numberOfLectureDays < 1) {
      numberOfLectureDays = 5;
    }
    this.numberOfLectureDays = numberOfLectureDays;
  }

  public int getNumberOfLectureHours() {
    return numberOfLectureHours;
  }

  public void setNumberOfLectureHours(int numberOfLectureHours) {
    if (numberOfLectureHours > 24 || numberOfLectureHours < 1) {
      numberOfLectureHours = 15;
    }
    this.numberOfLectureHours = numberOfLectureHours;
  }

  public int getDepartmentNumber() {
    return departmentNumber;
  }

  public void setDepartmentNumber(int departmentNumber) {
    this.departmentNumber = departmentNumber;
  }

  public HashMap<String, DepartmentCourse> getCourses() {
    return courses;
  }

  public HashMap<String, DepartmentCourse> getMandatoryCourses() {
    return mandatoryCourses;
  }

  public HashMap<String, DepartmentCourse> getTechnicalElectiveCourses() {
    return technicalElectiveCourses;
  }

  public HashMap<String, DepartmentCourse> getNonTechnicalElectiveCourses() {
    return nonTechnicalElectiveCourses;
  }

  public HashMap<String, DepartmentCourse> getFacultyElectiveCourses() {
    return facultyElectiveCourses;
  }

  public HashMap<String, DepartmentCourse> getUniversityElectiveCourses() {
    return universityElectiveCourses;
  }

  public ArrayList<Student> getStudents() {
    return students;
  }

  public ArrayList<TeachingAssistant> getTeachingAssistants() {
    return teachingAssistants;
  }

  public TeachingAssistant getTeachingAssistant(int index) {
    return teachingAssistants.get(index);
  }

  public int getLabCapacityLimit() {
    return labCapacityLimit;
  }

  public int getElectiveCapacityLimit() {
    return electiveCapacityLimit;
  }

  public int getMaximumCredits() {
    return maximumCredits;
  }

  public void setMaximumCredits(int maximumCredits) {
    this.maximumCredits = maximumCredits;
  }

  public void setCurriculum(Curriculum curriculum) {
    this.curriculum = curriculum;
  }

  public void addCourseRegistrationMessage(String messageFormat, Course course) {
    if (!courseRegistrationMessages.containsKey(messageFormat)) {
      courseRegistrationMessages.put(messageFormat, new HashMap<>());
    } else {
      courseRegistrationMessages.get(messageFormat).merge(course.getCourseId(), 1, Integer::sum);
    }
  }

  public void addCourseRegistrationMessage(int messageId, Course course) {
    String messaageFormat = registrationMessageFormats[messageId];
    if (!courseRegistrationMessages.containsKey(messaageFormat)) {
      courseRegistrationMessages.put(messaageFormat, new HashMap<>());
    } else {
      courseRegistrationMessages.get(messaageFormat).merge(course.getCourseId(), 1, Integer::sum);
    }
  }

  public HashMap<String, HashMap<String, Integer>> getCourseRegistrationMessages() {
    return courseRegistrationMessages;
  }

}
