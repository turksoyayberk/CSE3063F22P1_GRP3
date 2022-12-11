/*
   The MandatoryCourse class that holds mandatory class information and contains related methods. This is a subtype of the class department course.
 */
public class MandatoryCourse extends DepartmentCourse {

   // Store data for mandatory course.
  
    public MandatoryCourse(String courseId, String courseName, int credits, int semester, int lectureHours) {
        super(courseId, courseName, credits, semester, lectureHours);
    }
}
