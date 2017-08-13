# Java-Student-Serialization

Implementation of various aspects of serialization in Java and working with files of serialized objects, and exception handling. 

Program implements a serialized student roster. 

<strong>Student class</strong> is serialized except double <strong>grade</strong> and any static variable. 

<strong>StudentHandler</strong> contains following methods: 

• StudentHandler() – A single parameterless constructor, should just instantiate the member
data

• void saveStudents(Scanner s) – This method will ask the user for a filename to save under
and save the entire collection of students held in this StudentHandler as serialized objects

• void loadStudents(Scanner s) – This method will first clear all students out of the StudentHandler,
then ask the user for a filename to load and load an entire collection of students into the
StudentHandler

• void addStudent(Scanner s) – This method will prompt the user for the various pieces of a
Student object (name, grades, etc.) and create a new object and add it to the StudentHandler

• void printAllStudents() – This method will print all of the students, sorted by name (last
name first, if those are the same, then sort by first name) as well as the count of the number of
student records (you are REQUIRED to use the static totalStudents member data for this
count)

• void clearAllStudents() – This method will clear all of the students from the collection/array
