package pdcproject1;

    import java.util.HashSet; //Import statements
    import java.util.Scanner;
    import java.util.Set;
    
/**
 *
 * @author Bryan Crombach & Jerome Joseph 
 */
    public class CourseSelectionSystem implements UserInterface{ //Public class
        private final CourseCatalog courseCatalog; //Private variable
        private final Set<Course> enrolledcourses = new HashSet<>(); //Private variable

        public CourseSelectionSystem(CourseCatalog courseCatalog) { //Constructor
            this.courseCatalog = courseCatalog;
        }

        @Override
        public void displayCourseList() { // Display list of available courses
            String courseList = CourseCatalog.printTxt();
            System.out.println(courseList);
        }

        @Override
        public Set<String> selectCourses(Student student,CourseCatalog catalog) {
            Scanner scan = new Scanner(System.in);
            Set<String> enrollments = new HashSet<>();
            Semester semester;
            int sem = 3;
            while(sem == 3){ //While loop to ensure input is 1 or 2 for semester
            System.out.println("All courses are given in semester 1 and 2\nWhich semester would you like to enrole courses into(1 or 2):");
            if (scan.hasNextInt()) {
                sem = scan.nextInt();
                if (sem >= 1 && sem <= 2) {
                switch (sem) {
                case 1:
                    System.out.println("You are now enroling into semester " + sem);
                    break;
                case 2:
                    System.out.println("You are now enroling into semester " + sem);
                    break;
                }
                } else {
                    System.out.println("\nInvalid option. Please enter a number of 1 or 2.\n");
                }
            } else {
                System.out.println("\nInvalid input. Please enter a valid number of 1 or 2.\n");
                scan.next(); // Consume the invalid input to avoid an infinite loop.
            }
        }
   
            semester = new Semester(sem); //Create semester object
            scan.nextLine();
            String choices = "";
            boolean validInput = false;
            while (!validInput) { //While loop to ensure course code is correctly inputted
            System.out.print("Please select courses (enter course codes separated by spaces):\n");
            choices = scan.nextLine();
            if (choices.matches("^(([a-zA-Z]{4}[0-9]{3})\\s*){1,}$")) {
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a course code.");
            }
            }
            String[] courseCode = choices.split(" ");
            boolean check;
            for (int i = 0; i < courseCode.length; i++){//for loops to check if courses exist
                check = false;
                for(int x = 0; x < catalog.size(); x++){
                    if(courseCode[i].equals(catalog.get(x).getCourseCode())){
                        enrollments.add(new Enrollment(student.getStudentID(), courseCode[i], semester.getSemesterID()).toString());
                        System.out.println("successfully enrolled into " + courseCode[i]);
                        check = true;
                    }
                }
                if(check == false){
                    System.out.println("Course doesnt exist");
                }
            }
            System.out.println("Thank you for enrolling for semester " + sem);
            return enrollments;
        }
    }
