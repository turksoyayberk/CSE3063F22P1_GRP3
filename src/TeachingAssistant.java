import java.util.ArrayList;

public class TeachingAssistant extends Person {
    private final ArrayList<LabCourse> labCourses;
     public TeachingAssistant(String name, Department department) {
        super(name, department);
        this.labCourses = new ArrayList<>();
    }
  
    public ArrayList<LabCourse> getLabCourses() {
        return labCourses;
    }
    public void assignLabCourse(LabCourse labCourse) {
        this.labCourses.add(labCourse);
    }

}
