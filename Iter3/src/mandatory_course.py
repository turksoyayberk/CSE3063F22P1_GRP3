import department_course as depCourse


class MandatoryCourse(depCourse.DepartmentCourse):
    def __init__(self, course_id, course_name, course_credits, semester, lecture_hours):
        super().__init__(course_id, course_name, course_credits, semester, lecture_hours)
