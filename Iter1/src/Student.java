import java.util.ArrayList;

public class Student extends Person {
   private int studentID;
   private int semester;
   private int registerYear;
   private Lecturer advisor;
   private ArrayList<DepartmentCourse> allCourses;
   private ArrayList<String> registrationMessages;

   public Student(String name) {
      super(name);
      registrationMessages = new ArrayList<>();
    }


   public int getStudentID() {
      return studentID;
   }
   public void setStudentID(int studentID) {
      this.studentID = studentID;
   }

   public int getSemester() {
      return semester;
   }
   public void setSemester(int semester) {
      this.semester = semester;
   }

   public int getRegisterYear() {
        return registerYear;
   }
   public void setRegisterYear(int registerYear) {
      this.registerYear = registerYear;
   }
   
   public Lecturer getAdvisor() {
      return advisor;
   }
   public void setAdvisor(Lecturer advisor) {
      this.advisor = advisor;
   }

   public void addRegistrationMessage(String message) {
      registrationMessages.add(message);
    }
   public ArrayList<String> getRegistrationMessages() {
      return registrationMessages;
    }
}