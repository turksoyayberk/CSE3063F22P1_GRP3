import java.util.ArrayList;

public class LabCourse extends Course {

  private int sectionNumber;
	private int capacity;
	private DepartmentCourse mainCourse;
	private TeachingAssistant lecturer;

	public LabCourse(DepartmentCourse mainCourse) {
		this.mainCourse = mainCourse;
		this.lecturer = mainCourse.getTeachingAssistant(mainCourse.labSectionsCount());

		if (this.lecturer != null) {
			this.lecturer.assignLabCourse(this);
		}

		this.sectionNumber = mainCourse.labSectionsCount() + 1;
		this.courseId = mainCourse.getCourseId() + "." + this.sectionNumber;
		this.courseSchedule = new ArrayList<>();
	}

	public void addStudent(Student student) {
		if (this.capacity > 0) {
			registeredStudents.add(student);
			this.capacity--;
		}
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public boolean hasCapacity() {
		return this.capacity > 0;
	}

	public void decreaseCapacity() {
		this.capacity--;
	}

	public int getSectionNumber() {
		return this.sectionNumber;
	}

	public DepartmentCourse getMainCourse() {
		return this.mainCourse;
	}

	public TeachingAssistant getLecturer() {
		return this.lecturer;
	}

	public ArrayList<Student> getRegisteredStudents() {
		return this.registeredStudents;
	}

}
