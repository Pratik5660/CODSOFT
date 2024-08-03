import java.util.ArrayList;
import java.util.Scanner;

public class CourseManagement {
    private ArrayList<Course> courses;
    private ArrayList<Student> students;

    public CourseManagement() {
        courses = new ArrayList<>();
        students = new ArrayList<>();
        loadCourses();
        loadStudents();
    }

    private void loadCourses() {
        courses.add(new Course("CS101", "Introduction to Computer Science", "Basics of computer science", 30, "MWF 9:00-10:00 AM"));
        courses.add(new Course("MATH101", "Calculus I", "Introduction to calculus", 25, "TTh 10:00-11:30 AM"));
        courses.add(new Course("ENG101", "English Literature", "Study of English literature", 20, "MWF 11:00-12:00 PM"));
    }

    private void loadStudents() {
        students.add(new Student("S001", "Alice"));
        students.add(new Student("S002", "Bob"));
    }

    public void displayCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity());
            System.out.println("Enrolled: " + course.getEnrolled());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Available Slots: " + (course.getCapacity() - course.getEnrolled()));
            System.out.println();
        }
    }

    public Student findStudent(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    public Course findCourse(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public void registerStudentForCourse(Student student, Course course) {
        if (student.registerCourse(course)) {
            System.out.println("Successfully registered for the course.");
        } else {
            System.out.println("Registration failed. Course may be full.");
        }
    }

    public void dropStudentFromCourse(Student student, Course course) {
        if (student.dropCourse(course)) {
            System.out.println("Successfully dropped the course.");
        } else {
            System.out.println("Failed to drop the course.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CourseManagement management = new CourseManagement();

        while (true) {
            System.out.println("\nCourse Management System:");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    management.displayCourses();
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    Student student = management.findStudent(studentID);
                    if (student != null) {
                        System.out.print("Enter Course Code to Register: ");
                        String courseCode = scanner.nextLine();
                        Course course = management.findCourse(courseCode);
                        if (course != null) {
                            management.registerStudentForCourse(student, course);
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    student = management.findStudent(studentID);
                    if (student != null) {
                        System.out.print("Enter Course Code to Drop: ");
                        String courseCode = scanner.nextLine();
                        Course course = management.findCourse(courseCode);
                        if (course != null) {
                            management.dropStudentFromCourse(student, course);
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}