class Curriculum:
    __semesters = []

    def __init__(self):
        pass

    def get_semester(self, semester_no):
        return self.__semesters[semester_no]

    def add_course(self, semester_no : int, course):
        semester = self.get_semester(semester_no)
        if course not in semester:
            semester.append(course)

    def get_semesters(self):
        return self.__semesters
