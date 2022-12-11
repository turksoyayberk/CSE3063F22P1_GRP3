import java.util.ArrayList;

public abstract class Course {
	
    // course id
    public String courseId;
    
     // Schedule of course
     
    public ArrayList<int[]> courseSchedule = new ArrayList<>();
    
   //hold registered students in an array
    public ArrayList<Student> registeredStudents = new ArrayList<>();

    //get course id of the course
    public String getCourseId() {
        return courseId;
    }

   //get course schedule of the course
    public ArrayList<int[]> getCourseSchedule() {
        return courseSchedule;
    }

    
     //Create the course schedule in days and hours
     
    public void addLectureHour(int day, int lectureHour) {
        courseSchedule.add(new int[]{day, lectureHour});
    }

    
     //Add a new student to the course enrollment list
    public void addStudent(Student student) {
        registeredStudents.add(student);
    }
}
