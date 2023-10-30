package pdcproject1;

/**
 *
 * @author jeromejoseph
 */
//import statements
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

public class CourseSelectionGUI { //Course Selection GUI class

    private JFrame frame;
    private JPanel studentInfoPanel;
    private JPanel courseSelectionPanel;
    private JPanel courseListPanel;
    private JPanel selectedCoursesPanel;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JLabel studentIDLabel;
    private JTextArea courseListTextArea;
    private JTextArea selectedCoursesTextArea;

    private JButton submitButton;
    private JButton viewCoursesButton;
    private JButton viewSelectedCoursesButton;

    private boolean isValidFirstName(String firstName) { //input validation for first name
        return firstName.matches("[a-zA-Z]+");
    }

    private boolean isValidLastName(String lastName) { //input validation for last name
        return lastName.matches("[a-zA-Z]+");
    }

    private boolean isValidEmail(String email) { //input validation for email
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    //constructor
    public CourseSelectionGUI() {
        frame = new JFrame("Course Selection System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(2, 4));

        //create and initialize components
        createStudentInfoPanel();
        createCourseSelectionPanel();
        createCourseListPanel();
        createSelectedCoursesPanel();

        frame.setVisible(true);
    }

    //create Student Information Panel
    private void createStudentInfoPanel() {
        studentInfoPanel = new JPanel();
        studentInfoPanel.setLayout(new GridLayout(8, 4));

        //add labels, text fields, and submit button
        JLabel firstNameLabel = new JLabel("First Name:", SwingConstants.CENTER);
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 14)); //set font
        firstNameField = new JTextField(20);
        firstNameField.setFont(new Font("Arial", Font.PLAIN, 14)); //set font

        JLabel lastNameLabel = new JLabel("Last Name:", SwingConstants.CENTER);
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 14)); //set font
        lastNameField = new JTextField(20);
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 14)); //set font

        JLabel emailLabel = new JLabel("Email:", SwingConstants.CENTER);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14)); //set font
        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14)); //set font

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16)); //set font and make it bold
        submitButton.setForeground(Color.BLACK); //set button text color

        //add components to the panel
        studentInfoPanel.add(firstNameLabel);
        studentInfoPanel.add(firstNameField);
        studentInfoPanel.add(lastNameLabel);
        studentInfoPanel.add(lastNameField);
        studentInfoPanel.add(emailLabel);
        studentInfoPanel.add(emailField);
        studentInfoPanel.add(new JLabel()); //empty label for spacing
        studentInfoPanel.add(submitButton);

        frame.add(studentInfoPanel);
    }

    //create Course Selection Panel
    private void createCourseSelectionPanel() {
        courseSelectionPanel = new JPanel();
        courseSelectionPanel.setLayout(new BorderLayout());

        //add course selection components
        frame.add(courseSelectionPanel);
    }

    //create Course List Panel
    private void createCourseListPanel() {
        courseListPanel = new JPanel();
        courseListPanel.setLayout(new BorderLayout());

        //add a scrollable text area
        courseListTextArea = new JTextArea();
        courseListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(courseListTextArea);
        courseListPanel.add(scrollPane);

        viewCoursesButton = new JButton("View Courses");
        viewCoursesButton.setFont(new Font("Arial", Font.BOLD, 16)); //set font and make it bold
        viewCoursesButton.setForeground(Color.BLACK); //set button text color
        courseListPanel.add(viewCoursesButton, BorderLayout.SOUTH);

        frame.add(courseListPanel);
    }

    //create Selected Courses Panel
    private void createSelectedCoursesPanel() {
        selectedCoursesPanel = new JPanel();
        selectedCoursesPanel.setLayout(new BorderLayout());

        //add a scrollable text area for selected courses
        selectedCoursesTextArea = new JTextArea();
        selectedCoursesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(selectedCoursesTextArea);
        selectedCoursesPanel.add(scrollPane);

        viewSelectedCoursesButton = new JButton("View Selected Courses");
        viewSelectedCoursesButton.setFont(new Font("Arial", Font.BOLD, 16)); //set font and make it bold
        viewSelectedCoursesButton.setForeground(Color.BLACK); //set button text color
        selectedCoursesPanel.add(viewSelectedCoursesButton, BorderLayout.SOUTH);

        frame.add(selectedCoursesPanel);
    }

    //action listeners for buttons
    private void addListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get user's input from text fields
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();

                //input validation
                if (!isValidFirstName(firstName)) {
                    JOptionPane.showMessageDialog(frame, "Invalid first name. Please enter letters only.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; //stop processing further if validation fails
                }

                if (!isValidLastName(lastName)) {
                    JOptionPane.showMessageDialog(frame, "Invalid last name. Please enter letters only.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; //stop processing further if validation fails
                }

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(frame, "Invalid email address. Please enter a valid email.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; //stop processing further if validation fails
                }

                //if all input is valid, proceed to create the Student object
                int studentID = generateRandomStudentID();
                Student student = new Student(studentID, firstName + " " + lastName, email);

                JOptionPane.showMessageDialog(frame, "Student information saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Read and display course information
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("resources/courses.txt"));
                    StringBuilder courseInfo = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        courseInfo.append(line).append("\n");
                    }

                    courseListTextArea.setCaretPosition(0);
                    courseListTextArea.setText(courseInfo.toString());

                    reader.close();
                } catch (IOException ex) {
                    //handle any exceptions
                    ex.printStackTrace();
                }
            }
        });

        viewSelectedCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //handle view selected courses button
                //you can add code here to load and display selected courses from a file.
                loadAndDisplaySelectedCourses();
            }
        });
    }

    //load and display selected courses
    private void loadAndDisplaySelectedCourses() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("resources/selected_courses.txt"));
            StringBuilder selectedCourseInfo = new StringBuilder("Selected Courses:\n\n");
            String courseCode;

            while ((courseCode = reader.readLine()) != null) {
                selectedCourseInfo.append(courseCode).append("\n");
            }

            selectedCoursesTextArea.setText(selectedCourseInfo.toString());

            reader.close();
        } catch (IOException ex) {
            //handle any exceptions
            ex.printStackTrace();
        }
    }

    //save selected courses to file
    public void saveSelectedCourses(Set<String> selectedCourses) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("resources/selected_courses.txt"));

            for (String courseCode : selectedCourses) {
                writer.println(courseCode);
            }

            writer.close();
        } catch (IOException e) {
            //handle exceptions
            e.printStackTrace();
        }
    }

    //main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CourseSelectionGUI gui = new CourseSelectionGUI();
            gui.addListeners(); //add action listeners after GUI setup
        });
    }

    //generate random student ID
    private int generateRandomStudentID() {
        Random random = new Random();
        return 100000 + random.nextInt(900000); //generates 6-digit random ID
    }
}
