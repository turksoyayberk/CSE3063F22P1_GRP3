import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * The Transcript class that holds transcript information and contains related methods
 */
public class Transcript {
    
	/*
     * Multi-dimensional array to store the information of each course
     * Courses separated by semesters
     */
    private String[][][] transcript;
    
    /*
     * Cumulative GPA of all semesters
     */
    private double cumulativeGPA;
    
    /**
     * Array to store semester GPAs
     */
    private double[] GPAs;
    
    /*
     * Current semester number
     */
    private int currentSemester;
    
    /*
     * Reference owner of the transcript
     */
    private Student student;
    
    /*
     * Transcript creation date
     */
    private String creationDate;
    
    /*
     * Transcript JSON object
     */
    private JSONObject transcriptJSONObject;
    
    /*
     * Sum of taken credits in all semesters
     */
    private int cumulativeTakenCredits;
    
    /*
     * Sum of passed course credits in all semesters
     */
    private int cumulativeCompletedCredits;
    
    /*
     * By default, last semester will be assigned to current year
     */
    private final String currentYear;
   
    /*
     * Constructor of the Transcript
     */
    public Transcript(Student student, boolean includeNewCourses) {
        this.student = student;
        Date date = new Date();
        creationDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
        currentYear = new SimpleDateFormat("yyyy").format(date);
        transcript = new String[student.getSemester()][student.getDepartment().getCourseTakeLimit()][5];
        GPAs = new double[student.getSemester()];
        currentSemester = student.getSemester();
        addCourses(student.getPreviousCourses());
        cumulativeGPA = 0;
        if (includeNewCourses) {
            addCourses(student.getNewlyTakenCourses());
        }
        transcriptJSONObject = createTranscript();
    }

    /*
     * Insert given courses into the transcript
     */
    public void addCourses(HashMap<DepartmentCourse, StudentCourse> courses) {
        for (DepartmentCourse departmentCourse : courses.keySet()) {
            StudentCourse course = courses.get(departmentCourse);
            int semesterIndex = course.getMainCourse().getSemester() - 1;
            if (course.getMainCourse() instanceof ElectiveCourse) {
                semesterIndex = student.getElectiveSemester((ElectiveCourse) course.getMainCourse()) - 1;
            }
            if (semesterIndex >= transcript.length || semesterIndex < 0) {
                continue;
            }
            // Insert course to first empty slot in transcript
            int i = 0;
            while (transcript[semesterIndex][i][0] != null && i < transcript[semesterIndex].length - 1) {
                i++;
            }
            transcript[semesterIndex][i][0] = course.getMainCourse().getCourseId();
            transcript[semesterIndex][i][1] = course.getMainCourse().getCourseName();
            transcript[semesterIndex][i][2] = String.valueOf(course.getMainCourse().getCredits());
            if (course.getRegisteredSemester() == currentSemester) {
                transcript[semesterIndex][i][3] = "";
                transcript[semesterIndex][i][4] = "";
            } else {
                transcript[semesterIndex][i][3] = course.getLetterGrade();
                transcript[semesterIndex][i][4] = String.valueOf(course.getPoint());
            }
        }
    }

    /*
     * Each transcript object is created using json objects and array.
     */
    private JSONObject createTranscript() {
        JSONObject transcriptObject = new JSONObject();
        JSONArray semesters = new JSONArray();
        for (int semesterNumber = 1; semesterNumber <= transcript.length; semesterNumber++) {
            semesters.add(createSemesterJSONObject(semesterNumber, semesterNumber == transcript.length));
        }
        transcriptObject.put("date", creationDate);
        transcriptObject.put("name", student.getName());
        transcriptObject.put("id", student.getStudentID());
        transcriptObject.put("level", student.getEducationLevel());
        transcriptObject.put("faculty", student.getFaculty());
        transcriptObject.put("department", student.getDepartment().getName());
        transcriptObject.put("semesters", semesters);
        return transcriptObject;
    }

    /*
     * Saves transcripts to the given filename
     */
    private void saveTranscript(JSONObject transcript, String outputFileName) {
        try (FileWriter file = new FileWriter(outputFileName)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(transcript.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * A semester object is created that contains the number of semesters, the name of the semester,
     * the credits taken in the semester, the credits completed in the semester, the semester gpa,
     * the cumulative gpa, the cumulative credits taken, the cumulative completed credits and the course lists.
     */
    private JSONObject createSemesterJSONObject(int semesterNumber, boolean lastSemester) {
        JSONObject semesterObj = new JSONObject();
        JSONArray courseList = new JSONArray();
        int takenCredits = 0;
        int completedCredits = 0;
        double gpa = 0;
        String[][] semester = transcript[semesterNumber - 1];
        for (String[] courseDetails : semester) {
            if (courseDetails[0] != null) {
                int courseCredits = Integer.parseInt(courseDetails[2]);
                takenCredits += courseCredits;
                if (!(courseDetails[3].equals("FF") || courseDetails[3].equals("FD"))) {
                    completedCredits += courseCredits;
                }
                if (courseDetails[4].isEmpty()) {
                    courseDetails[4] = "0.0";
                }
                gpa += courseCredits * Double.parseDouble(courseDetails[4]);
                GPAs[semesterNumber - 1] = gpa;
                JSONObject course = createCourseJSONObject(courseDetails);
                courseList.add(course);
            }
        }
        gpa = roundGpa(gpa / takenCredits);
        cumulativeGPA += gpa;
        cumulativeTakenCredits += takenCredits;
        cumulativeCompletedCredits += completedCredits;
        semesterObj.put("semester", semesterNumber);
        semesterObj.put("semesterName", getSemesterName(semesterNumber) + getSemesterYear(semesterNumber));
        semesterObj.put("takenCredits", takenCredits);
        if (!lastSemester) {
            semesterObj.put("completedCredits", completedCredits);
            semesterObj.put("gpa", gpa);
            semesterObj.put("cumulativeGpa", roundGpa(cumulativeGPA / semesterNumber));
        }
        semesterObj.put("cumulativeTakenCredits", cumulativeTakenCredits);
        semesterObj.put("cumulativeCompletedCredits", cumulativeCompletedCredits);
        semesterObj.put("courses", courseList);
        return semesterObj;
    }

    /*
     * A result object is created that holds detailed course information.
     */
    private JSONObject createCourseJSONObject(String[] courseDetails) {
        JSONObject result = new JSONObject();
        for (int i = 0; i < courseDetails.length; i++) {
            result.put("courseId", courseDetails[0]);
            result.put("courseName", courseDetails[1]);
            result.put("credits", Integer.parseInt(courseDetails[2]));
            result.put("grade", courseDetails[3]);
            result.put("points", Double.parseDouble(courseDetails[4]));
        }
        return result;
    }

    /*
     * Find which year student attended to given semester
     * By default last semester in current year
     */
    private String getSemesterYear(int semesterNumber) {
        int currentYear = Integer.parseInt(this.currentYear);
        int semesterYear = currentYear - ((student.getSemester() - semesterNumber) / 2);
        return String.valueOf(semesterYear);
    }

    /*
     * gpa converter
     */
    private double roundGpa(double gpa) {
        return Math.round(100 * gpa) / 100.0;
    }

    /*
     * Gets semester number
     */
    private String getSemesterName(int semesterNumber) {
        return (semesterNumber % 2 == 0 ? "Spring" : "Fall");
    }

    /*
     * Gets transcript object
     */
    public JSONObject getTranscriptObject() {
        return transcriptJSONObject;
    }

    /*
     * Gets Transcript
     */
    public String[][][] getTranscript() {
        return transcript;
    }

    /**
     * Gets cumulative gpa
     */
    public double getCumulativeGPA() {
        return cumulativeGPA;
    }

    /*
     * Gets all gpas
     */
    public double[] getGPAs() {
        return GPAs;
    }

    /**
     * Gets Current Semester number
     */
    public int getCurrentSemester() {
        return currentSemester;
    }

    /*
     * Gets Student object
     */
    public Student getStudent() {
        return student;
    }

    /*
     * Gets Date of creation
     */
    public String getDateOfCreation() {
        return creationDate;
    }

    /*
     * Gets cumulative taken credits
     */
    public int getCumulativeTakenCredits() {
        return cumulativeTakenCredits;
    }

    /*
     * Gets cumulative completed credits
     */
    public int getCumulativeCompletedCredits() {
        return cumulativeCompletedCredits;
    }

    /*
     * Gets current Year
     */
    public String getCurrentYear() {
        return currentYear;
    }
}