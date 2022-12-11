import java.util.ArrayList;

// The TeachingAssistant class that holds teaching assistant information and contains related methods. This is a subtype of the class Person

public class TeachingAssistant extends Person {

    // List of the lab courses of teaching assistant
    private final ArrayList<LabCourse> labCourses;

    // Constructor for teaching assistant
    public TeachingAssistant(String name, Department department) {
        super(name, department);
        this.labCourses = new ArrayList<>();
    }

    // Assign lab course to the teaching assistant
    public void assignLabCourse(LabCourse labCourse) {
        this.labCourses.add(labCourse);
    }

    // Getter method for list of the labcourse of teaching assitant
    public ArrayList<LabCourse> getLabCourses() {
        return labCourses;
    }
}