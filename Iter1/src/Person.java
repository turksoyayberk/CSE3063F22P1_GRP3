public class Person {

        private String name;
        private Department department;
        
        public Person(String name, Department department) {
            this.name = name;
            this.department = department;
        }

        public String setName() {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Department setDepartment() {
            this.department = department;
        }

        public Department getDepartment() {
            return department;
        }

        public String getFaculty() {
            return department.getFaculty();
        }
    }
