import java.util.ArrayList;

public class ElectiveCourse extends DepartmentCourse{
    private String type;
    private int capacity;
    
    public ElectiveCourse(){
        super();
        this.type = type;
    }
    
    @Override
    public void addStudent (Student student) {
        if (hasCapacity()) {
            getRegisteredStudents().add(student);
            decreaseCapacity();
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean hasCapacity() {
        return this.capacity != 0;
    }

    public void increaseCapacity(){
        this.capacity++;
    }

    public void decreaseCapacity(){
        this.capacity--;
    }
}
