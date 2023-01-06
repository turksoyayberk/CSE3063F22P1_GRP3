import java.util.ArrayList;
public class LabCourse implements Course{
	private String courseId;
	private DepartmentCourse mainCourse;
	private int capacity;
	private ArrayList<Student> registeredStudents;
	private ArrayList<int[]> courseSchedule;
	private TeachingAssistant lecturer;
	private int sectionNumber;

	
	public LabCourse() {
		this.mainCourse = mainCourse;
		this.courseId = mainCourse.getCourseId() + "." + sectionNumber;
		this.registeredStudents = new ArrayList<>();
		this.sectionNumber = mainCourse.labSectionsCount() + 1;
		this.courseSchedule = new ArrayList<>();
		if (this.lecturer != null) {
            	this.lecturer.assignLabCourse(this);
       		}
	}
	public void addStudent(Student student) {
		if(this.capacity > 0) {
			registeredStudents.add(student);
			this.capacity--;
		}
	}
	
	public void addLectureHour(int day, int lectureHour) {
        	courseSchedule.add(new int[] { day, lectureHour });
    	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getCapacity() {
		return capacity;
	}
	public ArrayList<int[]> getCourseSchedule() {
        	return courseSchedule;
    	}
	public boolean hasCapacity() {
		return this.capacity > 0;
	}
	@Override
	public String getCourseId() {
		return courseId;
	}
	public void decreaseCapacity() {
        	this.capacity--;
    	}

    	public DepartmentCourse getMainCourse() {
        	return mainCourse;
    	}

    	public TeachingAssistant getLecturer() {
        	return lecturer;
    	}

    	public ArrayList<Student> getRegisteredStudents() {
        	return registeredStudents;
    	}

    	public int getSectionNumber() {
        	return sectionNumber;
    	}
}

