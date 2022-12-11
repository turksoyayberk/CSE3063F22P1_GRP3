/**
 * The Person class that holds person information and contains related methods
 */
public class Person {
    /**
     * Name of the person
     */
    private String name;
    /**
     * Department of person
     */
    private Department department;

    /**
     * Store person's data
     *
     *
     */
    public Person(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    /**
     * get person name
     *
     
     */
    public String getName() {
        return name;
    }

    /**
     * get Department
     *

     */
    public Department getDepartment() {
        return department;
    }

    /**
     * get faculty of department
     *
  
     */
    public String getFaculty() {
        return department.getFaculty();
    }
}