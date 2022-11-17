import java.util.ArrayList;

public interface Course {

    String getCourseID();
    ArrayList<int[]> getCourseSchedule();
    void addStudent(Student student);
    void addLectureHour(int day, int lectureHour);

}
