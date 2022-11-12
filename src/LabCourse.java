import java.util.ArrayList;
public class LabCourse implements Course{
	private String courseId;
	private DepartmentCourse mainCourse;
	private int capacity;
	private ArrayList<Student> registeredStudents;
	
	public LabCourse() {
		this.mainCourse = mainCourse;
		this.courseId = mainCourse.getCourseId();
		this.registeredStudents = new ArrayList<>();
	}
	public void addStudent(Student student) {
		if(hasCapacity()) {
			registeredStudents.add(student);
			this.capacity--;
		}
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getCapacity() {
		return capacity;
	}
	public boolean hasCapacity() {
		return true;
	}
	@Override
	public String getCourseId() {
		return courseId;
	}
}

