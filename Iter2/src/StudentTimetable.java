import java.util.ArrayList;
import java.util.HashMap;

// The StudentTimetable class that holds timetable information and contains related methods

public class StudentTimetable {
    private int numberOfHours;
    private int numberOfDays;
    private HashMap<Integer, HashMap<Integer, ArrayList<Course>>> timetable;

    // Constructor of Student Timetable
    public StudentTimetable(Student student) {
        this.numberOfDays = student.getDepartment().getNumberOfLectureDays();
        this.numberOfHours = student.getDepartment().getNumberOfLectureHours();
        timetable = new HashMap<>();
        for (int i = 0; i < numberOfDays; i++) {
            timetable.put(i + 1, new HashMap<>());
            for (int j = 0; j < numberOfHours; j++) {
                timetable.get(i + 1).put(j + 1, new ArrayList<>());
            }
        }
    }

    // Course assign method with given day and hour vaue
    public void assignCourse(int day, int hour, Course course) {
        timetable.get(day).computeIfAbsent(hour, k -> new ArrayList<>());
        timetable.get(day).get(hour).add(course);
    }

    // Course assign method
    public void assignCourse(Course course) {
        for (int[] lectureHours : course.getCourseSchedule()) {
            assignCourse(lectureHours[0], lectureHours[1], course);
        }
    }

    // Checks if there is any colision with other courses
    public boolean courseHasCollision(Course course) {
        for (int[] lectureTime : course.getCourseSchedule()) {
            if (isCourseAssigned(lectureTime[0], lectureTime[1])) {
                return true;
            }
        }
        return false;
    }

    // Checks if course is assigned to given day and hour
    public boolean isCourseAssigned(int day, int hour) {
        return timetable.get(day).get(hour).size() > 0;
    }

    // Getter method for number of courses at given day and hour
    public int numberOfAssignedCourses(int day, int hour) {
        return timetable.get(day).get(hour).size();
    }

    // Getter method of number of days
    public int getNumberOfDays() {
        return numberOfDays;
    }

    // Getter method of number of hours
    public int getNumberOfHours() {
        return numberOfHours;
    }

    // Getter method of time table
    public HashMap<Integer, HashMap<Integer, ArrayList<Course>>> getTimetable() {
        return timetable;
    }
}
