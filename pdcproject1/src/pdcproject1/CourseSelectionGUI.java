package pdcproject1;

/**
 *
 * @author jeromejoseph
 */
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

public class CourseSelectionGUI {

    private JFrame frame;
    private JPanel cards;
    private CardLayout cardLayout;

    private JPanel studentInfoPanel;
    private JPanel courseSelectionPanel;
    private JPanel courseListPanel;
    private JPanel selectedCoursesPanel;
    private JPanel signUpPanel;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JLabel studentIDLabel;
    private JTextArea courseListTextArea;
    private JTextArea selectedCoursesTextArea;

    private JButton submitButton;
    private JButton viewCoursesButton;
    private JButton viewSelectedCoursesButton;
    private JButton signUpButton;

    private boolean isValidFirstName(String firstName) {
        return firstName.matches("[a-zA-Z]+");
    }

    private boolean isValidLastName(String lastName) {
        return lastName.matches("[a-zA-Z]+");
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    // Constructor
    public CourseSelectionGUI() {
        frame = new JFrame("Course Selection System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create the cards panel with CardLayout
        cards = new JPanel();
        cardLayout = new CardLayout();
        cards.setLayout(cardLayout);

        // Create and add different cards (panels) for each page
        createStudentInfoPanel();
        createCourseListPanel();
        createCourseSelectionPanel();
        createSelectedCoursesPanel();
        createSignUpPanel(); // Add the sign-up panel

        // Add the cards panel to the frame
        frame.add(cards);

        frame.setVisible(true);
    }

    // Create Student Information Panel
private void createStudentInfoPanel() {
    studentInfoPanel = new JPanel();
    studentInfoPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for better alignment control

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

    // Add labels, text fields, and the submit button...
    JLabel firstNameLabel = new JLabel("First Name:");
    firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.LINE_END; // Right-align the label
    studentInfoPanel.add(firstNameLabel, gbc);

    firstNameField = new JTextField(20);
    firstNameField.setFont(new Font("Arial", Font.PLAIN, 14));
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.LINE_START; // Left-align the text field
    studentInfoPanel.add(firstNameField, gbc);

    JLabel lastNameLabel = new JLabel("Last Name:");
    lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.LINE_END;
    studentInfoPanel.add(lastNameLabel, gbc);

    lastNameField = new JTextField(20);
    lastNameField.setFont(new Font("Arial", Font.PLAIN, 14));
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.anchor = GridBagConstraints.LINE_START;
    studentInfoPanel.add(lastNameField, gbc);

    JLabel emailLabel = new JLabel("Email:");
    emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.LINE_END;
    studentInfoPanel.add(emailLabel, gbc);

    emailField = new JTextField(20);
    emailField.setFont(new Font("Arial", Font.PLAIN, 14));
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.anchor = GridBagConstraints.LINE_START;
    studentInfoPanel.add(emailField, gbc);

    submitButton = new JButton("Submit");
    submitButton.setFont(new Font("Arial", Font.BOLD, 16));
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.anchor = GridBagConstraints.CENTER; // Center the submit button
    studentInfoPanel.add(submitButton, gbc);

    // Create the "Next Page" button
    JButton nextPageButton = new JButton("Next Page");
    nextPageButton.setFont(new Font("Arial", Font.PLAIN, 12));
    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.anchor = GridBagConstraints.LINE_END; // Right-align the "Next Page" button
    studentInfoPanel.add(nextPageButton, gbc);

    nextPageButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cards, "CourseSelection");
        }
    });

    frame.add(studentInfoPanel);
    cards.add(studentInfoPanel, "StudentInfo");
}



    // Create Sign-Up Panel
    private void createSignUpPanel() {
        signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridLayout(4, 4));

        // Add labels, text fields, and the sign-up button...
        JLabel signUpLabel = new JLabel("Sign Up", SwingConstants.CENTER);
        signUpLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font and make it bold

        signUpPanel.add(signUpLabel);
        signUpPanel.add(new JLabel()); // Empty label for spacing

        JLabel newFirstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
        newFirstNameLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font
        JTextField newFirstNameField = new JTextField(20);
        newFirstNameField.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font

        JLabel newLastNameLabel = new JLabel("Last Name:", SwingConstants.RIGHT);
        newLastNameLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font
        JTextField newLastNameField = new JTextField(20);
        newLastNameField.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font

        JLabel newEmailLabel = new JLabel("Email:", SwingConstants.RIGHT);
        newEmailLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font
        JTextField newEmailField = new JTextField(20);
        newEmailField.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font

        signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and make it bold
        signUpButton.setForeground(Color.BLACK); // Set button text color

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the user's input from the new text fields
                String newFirstName = newFirstNameField.getText();
                String newLastName = newLastNameField.getText();
                String newEmail = newEmailField.getText();

                // Perform input validation
                if (!isValidFirstName(newFirstName) || !isValidLastName(newLastName) || !isValidEmail(newEmail)) {
                    JOptionPane.showMessageDialog(frame, "Invalid input. Please check your information and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // If all input is valid, you can process the sign-up, e.g., save the user's information.
                    // Here, you can add code to handle the sign-up process.
                    JOptionPane.showMessageDialog(frame, "Sign-up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Clear the sign-up fields
                    newFirstNameField.setText("");
                    newLastNameField.setText("");
                    newEmailField.setText("");
                    cardLayout.show(cards, "StudentInfo"); // Switch back to the Student Info page
                }

            }
        });

        signUpPanel.add(newFirstNameLabel);
        signUpPanel.add(newFirstNameField);
        signUpPanel.add(newLastNameLabel);
        signUpPanel.add(newLastNameField);
        signUpPanel.add(newEmailLabel);
        signUpPanel.add(newEmailField);
        signUpPanel.add(new JLabel()); // Empty label for spacing
        signUpPanel.add(signUpButton);

        frame.add(signUpPanel);
        cards.add(signUpPanel, "SignUp");
    }

    // Create Course Selection Panel
    // Create Course Selection Panel
    private void createCourseSelectionPanel() {
        courseSelectionPanel = new JPanel();
        courseSelectionPanel.setLayout(new BorderLayout());

        // Create an array of course checkboxes
        JCheckBox[] courseCheckboxes = new JCheckBox[5]; // Change the array size as needed

        // Populate the checkboxes with course names
        courseCheckboxes[0] = new JCheckBox("Course 1");
        courseCheckboxes[1] = new JCheckBox("Course 2");
        courseCheckboxes[2] = new JCheckBox("Course 3");
        courseCheckboxes[3] = new JCheckBox("Course 4");
        courseCheckboxes[4] = new JCheckBox("Course 5");

        // Create a button to perform course selection
        JButton selectCoursesButton = new JButton("Select Courses");

        // Add checkboxes to a panel
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(0, 1)); // A vertical layout for checkboxes
        for (JCheckBox checkbox : courseCheckboxes) {
            checkboxPanel.add(checkbox);
        }

        // Add the checkbox panel to the center of the courseSelectionPanel
        courseSelectionPanel.add(checkboxPanel, BorderLayout.CENTER);

        // Add the "Select Courses" button to the bottom
        courseSelectionPanel.add(selectCoursesButton, BorderLayout.SOUTH);

        // Add an action listener for the "Select Courses" button
        selectCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle course selection here
                StringBuilder selectedCourses = new StringBuilder("Selected Courses:\n");

                for (JCheckBox checkbox : courseCheckboxes) {
                    if (checkbox.isSelected()) {
                        selectedCourses.append(checkbox.getText()).append("\n");
                    }
                }

                // Display the selected courses
                JOptionPane.showMessageDialog(frame, selectedCourses.toString(), "Selected Courses", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        cards.add(courseSelectionPanel, "CourseSelection");
    }

    // Create Course List Panel
    private void createCourseListPanel() {
        courseListPanel = new JPanel();
        courseListPanel.setLayout(new BorderLayout());

        // Add a scrollable text area for course list...
        courseListTextArea = new JTextArea();
        courseListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(courseListTextArea);
        courseListPanel.add(scrollPane);

        viewCoursesButton = new JButton("View Courses");
        viewCoursesButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and make it bold
        viewCoursesButton.setForeground(Color.BLACK); // Set button text color
        courseListPanel.add(viewCoursesButton, BorderLayout.SOUTH);

        frame.add(courseListPanel);
        cards.add(courseListPanel, "CourseList");
    }

    // Create Selected Courses Panel
    private void createSelectedCoursesPanel() {
        courseSelectionPanel = new JPanel();
        courseSelectionPanel.setLayout(new BorderLayout());

        // Create an array of course checkboxes
        JCheckBox[] courseCheckboxes = new JCheckBox[5]; // Change the array size as needed

        // Populate the checkboxes with course names
        courseCheckboxes[0] = new JCheckBox("Course 1");
        courseCheckboxes[1] = new JCheckBox("Course 2");
        courseCheckboxes[2] = new JCheckBox("Course 3");
        courseCheckboxes[3] = new JCheckBox("Course 4");
        courseCheckboxes[4] = new JCheckBox("Course 5");

        // Create a button to view courses
        JButton viewCoursesButton = new JButton("View Courses");
        viewCoursesButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and make it bold
        viewCoursesButton.setForeground(Color.BLACK); // Set button text color

        // Create a button to select courses
        JButton selectCoursesButton = new JButton("Select Courses");
        selectCoursesButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and make it bold
        selectCoursesButton.setForeground(Color.BLACK); // Set button text color

        // Create a button to view courses
        viewSelectedCoursesButton = new JButton("View Courses");
        viewSelectedCoursesButton.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and make it bold
        viewSelectedCoursesButton.setForeground(Color.BLACK); // Set button text color

        // Add checkboxes to a panel
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new GridLayout(0, 1)); // A vertical layout for checkboxes
        for (JCheckBox checkbox : courseCheckboxes) {
            checkboxPanel.add(checkbox);
        }

        // Add the checkbox panel to the center of the courseSelectionPanel
        courseSelectionPanel.add(checkboxPanel, BorderLayout.CENTER);

        // Add the "View Courses" and "Select Courses" buttons to the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(viewCoursesButton);
        buttonPanel.add(selectCoursesButton);
        courseSelectionPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for the buttons
        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the view courses button action...
                // You can add code here to load and display available courses.
                // Example: Display a list of courses in a dialog.
                String[] courses = {"Course 1", "Course 2", "Course 3", "Course 4", "Course 5"};
                String selectedCourse = (String) JOptionPane.showInputDialog(frame, "Select a course to view:", "View Courses", JOptionPane.QUESTION_MESSAGE, null, courses, courses[0]);

                if (selectedCourse != null) {
                    JOptionPane.showMessageDialog(frame, "Course Information: " + selectedCourse, "Course Selected", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        selectCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the select courses button action...
                // You can add code here to process the selected courses.
                StringBuilder selectedCourses = new StringBuilder("Selected Courses:\n");

                for (JCheckBox checkbox : courseCheckboxes) {
                    if (checkbox.isSelected()) {
                        selectedCourses.append(checkbox.getText()).append("\n");
                    }
                }

                // Display the selected courses
                JOptionPane.showMessageDialog(frame, selectedCourses.toString(), "Selected Courses", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        cards.add(courseSelectionPanel, "CourseSelection");
    }

    // Action listeners for buttons
    private void addListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the user's input from text fields
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();

                // Perform input validation
                if (!isValidFirstName(firstName)) {
                    JOptionPane.showMessageDialog(frame, "Invalid first name. Please enter letters only.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Stop processing further if validation fails
                }

                if (!isValidLastName(lastName)) {
                    JOptionPane.showMessageDialog(frame, "Invalid last name. Please enter letters only.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Stop processing further if validation fails
                }

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(frame, "Invalid email address. Please enter a valid email.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Stop processing further if validation fails
                }

                // If all input is valid, proceed to create the Student object
                int studentID = generateRandomStudentID();
                Student student = new Student(studentID, firstName + " " + lastName, email);

                // You can perform further actions here, such as saving the student object
                // or displaying a confirmation message.
                JOptionPane.showMessageDialog(frame, "Student information saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "CourseSelection"); // Switch to the Course Selection page

            }
        });

        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Read and display course information from the file in courseListTextArea
                try {
                    // Replace with the actual path to your .txt file
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
                    // Handle any exceptions here, e.g., display an error message
                    ex.printStackTrace();
                }
            }
        });

        viewSelectedCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the view selected courses button action...
                // You can add code here to load and display selected courses from a file.
                loadAndDisplaySelectedCourses();
            }
        });
    }

    // Load and display selected courses
    private void loadAndDisplaySelectedCourses() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("resources/selected_courses.txt"));
            StringBuilder selectedCourseInfo = new StringBuilder("Selected Courses:\n\n");
            String courseCode;

            while ((courseCode = reader.readLine()) != null) {
                // You can fetch detailed course information based on the course code
                // and append it to the StringBuilder.
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
