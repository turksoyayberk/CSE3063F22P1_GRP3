public class Input {
    private String semester;
    private int ffRate;
    private int maxNumOfSelectionForCourses;
    private String courseJsonFileName;
    private String electiveNTEJsonFileName;
    private String electiveFTEJsonFileName;

    public void setSemester(String semester){
        this.semester = semester;
    }

    public String getSemester(){
        return semester;
    }

    public void setFFRate(int ffRate){
        this.ffRate = ffRate;
    }

    public int getFFRate(){
        return ffRate;
    }


    public int getMaxNumOfSelectionForCourses() {
        return maxNumOfSelectionForCourses;
    }

    public void setMaxNumOfSelectionForCourses(int maxNumOfSelectionForCourses) {
        this.maxNumOfSelectionForCourses = maxNumOfSelectionForCourses;
    }


    public String getCourseJsonFileName() {
        return courseJsonFileName;
    }

    public void setCourseJsonFileName(String courseJsonFileName) {
        this.courseJsonFileName = courseJsonFileName;
    }

    public String getElectiveNTEJsonFileName() {
        return electiveNTEJsonFileName;
    }

    public void setElectiveNTEJsonFileName(String electiveNTEJsonFileName) {
        this.electiveNTEJsonFileName = electiveNTEJsonFileName;
    }

    public String getElectiveFTEJsonFileName() {
        return electiveFTEJsonFileName;
    }

    public void setElectiveFTEJsonFileName(String electiveFTEJsonFileName) {
        this.electiveFTEJsonFileName = electiveFTEJsonFileName;
    }
}
