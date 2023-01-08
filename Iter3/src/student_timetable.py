import course
import student


class StudentTimetable:
    number_of_days: int
    number_of_hours: int
    timetable = {}

    def get_number_of_days(self):
        return self.number_of_days

    def get_number_of_hours(self):
        return self.number_of_hours

    def get_timetable(self):
        return self.timetable
