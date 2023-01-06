import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    private static final Department department = new Department("input.json");

    public static void main(String[] args) throws IOException {
        Files.createDirectories(Paths.get("./output/"));
        RandomStudentGenerator generator = new RandomStudentGenerator();
        ArrayList<Student> randomStudents = generator.createStudents(department, 70);
        for (Student student : randomStudents) {
            student.saveToFile("./output/" + student.getStudentID() + ".json");
        }
        department.saveToFile("./output/department_output_" + department.getCurrentSemester() + ".json");
    }
}
