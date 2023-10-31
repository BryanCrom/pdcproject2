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
import java.util.Set;
import java.awt.Font;

public class CourseSelectionGUI {

    private JFrame frame;
    private JPanel cards;
    private CardLayout cardLayout;

    private JPanel studentInfoPanel;
    private JPanel courseSelectionPanel;
    private JPanel courseListPanel;
    private JPanel selectedCoursesPanel;
    private JPanel logInPanel;
    private JPanel menuPanel;
    private JPanel signUpPanel;
    private JPanel menuAfterLoginPanel;

    private JTextField signUpFirstNameField;
    private JTextField signUpLastNameField;
    private JTextField signUpEmailField;
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
    private JButton logInButton;

    //Constructor
    public CourseSelectionGUI() {
        frame = new JFrame("Course Selection System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        //Create cards panel with CardLayout
        cards = new JPanel();
        cardLayout = new CardLayout();
        cards.setLayout(cardLayout);

        //Create and add different cards for each page
        createMenuPanel();
        createMenuAfterLoginPanel();
        createSignUpPanel();
        createCourseListPanel();
        createCourseSelectionPanel();
        createSelectedCoursesPanel();
        createLogInPanel(); //add log-in panel

        //add cards panel to the frame
        frame.add(cards);
        frame.setVisible(true);
    }

    //create Menu Panel
    private void createMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        //add title label
        JLabel titleLabel = new JLabel("Welcome to the Course Selection System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; //span two columns
        gbc.anchor = GridBagConstraints.CENTER;
        menuPanel.add(titleLabel, gbc);

        //add buttons for signing up and logging in
        signUpButton = new JButton("Sign Up");
        logInButton = new JButton("Log In");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        menuPanel.add(signUpButton, gbc);

        gbc.gridy = 2;
        menuPanel.add(logInButton, gbc);

        //add action listeners for the buttons
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "SignUp"); //switch to the sign-up page
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "logIn"); //switch to the log-in page
            }
        });

        frame.add(menuPanel);
        cards.add(menuPanel, "Menu");
    }



    //create Sign-Up Panel
    private void createSignUpPanel() {
        signUpPanel = new JPanel();
        signUpPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        //add labels, text fields, and the submit button for sign-up
        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; //span two columns
        gbc.anchor = GridBagConstraints.CENTER;
        signUpPanel.add(signUpLabel, gbc);

        JLabel newFirstNameLabel = new JLabel("First Name:");
        JTextField newFirstNameField = new JTextField(20);
        JLabel newLastNameLabel = new JLabel("Last Name:");
        JTextField newLastNameField = new JTextField(20);
        JLabel newEmailLabel = new JLabel("Email:");
        JTextField newEmailField = new JTextField(20);
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        gbc.gridwidth = 1; //reset to one column
        gbc.anchor = GridBagConstraints.LINE_END;

        gbc.gridx = 0;
        gbc.gridy = 1;
        signUpPanel.add(newFirstNameLabel, gbc);
        gbc.gridx = 1;
        signUpPanel.add(newFirstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        signUpPanel.add(newLastNameLabel, gbc);
        gbc.gridx = 1;
        signUpPanel.add(newLastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        signUpPanel.add(newEmailLabel, gbc);
        gbc.gridx = 1;
        signUpPanel.add(newEmailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        signUpPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        signUpPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        signUpPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        signUpPanel.add(passwordField, gbc);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.CENTER;
        signUpPanel.add(signUpButton, gbc);

        //add a "Back" button to go back to the menu
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        signUpPanel.add(backButton, gbc);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Menu"); //switch back to the menu page
            }
        });

        //add action listener for the sign-up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sign-up process
                String newFirstName = newFirstNameField.getText();
                String newLastName = newLastNameField.getText();
                String newEmail = newEmailField.getText();
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                //perform input validation (same as the log-in panel)
                if (!Validation.validateFirstName(newFirstName)) {
                    JOptionPane.showMessageDialog(frame, "Invalid first name. Please enter letters only.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!Validation.validateLastName(newLastName)) {
                    JOptionPane.showMessageDialog(frame, "Invalid last name. Please enter letters only.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!Validation.validateEmail(newEmail)) {
                    JOptionPane.showMessageDialog(frame, "Invalid email address. Please enter a valid email.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Username and password are required.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (username.contains(" ") || password.contains(" ")) {
                    JOptionPane.showMessageDialog(frame, "Username and password cannot contain spaces.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Sign-up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Database DB = new Database();
                    Account account = new Account(username, password, newFirstName, newLastName, newEmail);
                    DB.addAccount(account);
                    DB.close();

                    //clear the sign-up fields
                    newFirstNameField.setText("");
                    newLastNameField.setText("");
                    newEmailField.setText("");
                    usernameField.setText("");
                    passwordField.setText("");
                    cardLayout.show(cards, "CourseSelection"); //switch to the Course Selection page or another appropriate page
                    cardLayout.show(cards, "logIn");
                }
            }
        });

        frame.add(signUpPanel);
        cards.add(signUpPanel, "SignUp");
    }

    //create Log-In Panel
    private void createLogInPanel() {
        logInPanel = new JPanel();
        logInPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        //add labels
        JLabel logInLabel = new JLabel("Login");
        logInLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; //span two columns
        gbc.anchor = GridBagConstraints.CENTER;
        logInPanel.add(logInLabel, gbc);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        gbc.gridwidth = 1; //reset to one column
        gbc.anchor = GridBagConstraints.LINE_END;

        gbc.gridx = 0;
        gbc.gridy = 1;
        logInPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        logInPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        logInPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        logInPanel.add(passwordField, gbc);

        JButton logInButton = new JButton("Login");
        logInButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        logInPanel.add(logInButton, gbc);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        logInPanel.add(backButton, gbc);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Menu"); //switch back to the menu page
            }
        });

        //add action listener for the log-in button
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database DB = new Database();
                //log-in process
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                //perform input validation (e.g., check if username and password are not empty)
                if (Validation.validateUsername(username)) {
                    JOptionPane.showMessageDialog(frame, "valid Username is required.", "Error", JOptionPane.ERROR_MESSAGE);
                }else if(Validation.validatePassword(password)) {
                    JOptionPane.showMessageDialog(frame, "valid password is required.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    //clear the log-in fields
                    usernameField.setText("");
                    passwordField.setText("");
                    cardLayout.show(cards, "MenuAfterLogin");
                }
                DB.close();
            }
        });

        frame.add(logInPanel);
        cards.add(logInPanel, "logIn");
    }

    private void createMenuAfterLoginPanel() {
        menuAfterLoginPanel = new JPanel();
        menuAfterLoginPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        //add a title label
        JLabel titleLabel = new JLabel("Welcome to the Main Menu");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; //span two columns
        gbc.anchor = GridBagConstraints.CENTER;
        menuAfterLoginPanel.add(titleLabel, gbc);

        //add buttons for navigation
        JButton viewAndSelectCoursesButton = new JButton("View/Select Courses");
        JButton viewSelectedCoursesButton = new JButton("View Selected Courses");
        JButton viewStudentInfoButton = new JButton("View Student Info");

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        menuAfterLoginPanel.add(viewAndSelectCoursesButton, gbc);

        gbc.gridy = 2;
        menuAfterLoginPanel.add(viewSelectedCoursesButton, gbc);

        gbc.gridy = 3;
        menuAfterLoginPanel.add(viewStudentInfoButton, gbc);

        //add action listeners for the buttons
        viewAndSelectCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "CourseSelection");
            }
        });

        viewSelectedCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "SelectedCoursesView");
            }
        });

        viewStudentInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "StudentInfoView");
            }
        });

        //create a "Back to Menu" button
        JButton backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.setFont(new Font("Arial", Font.BOLD, 16));
        backToMenuButton.setForeground(Color.BLACK);

        //add an action listener for the "Back to Menu" button
        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "Menu"); //switch back to the main menu
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 4; //adjust the row number as needed
        gbc.anchor = GridBagConstraints.CENTER;
        menuAfterLoginPanel.add(backToMenuButton, gbc);

        frame.add(menuAfterLoginPanel);
        cards.add(menuAfterLoginPanel, "MenuAfterLogin");
    }

    //create Course Selection Panel
    private void createCourseSelectionPanel() {
        courseSelectionPanel = new JPanel();
        courseSelectionPanel.setLayout(new GridLayout(0, 1)); //vertical layout with a single column

        //create an array of course checkboxes
        JCheckBox[] courseCheckboxes = createCourseCheckboxes(5); //pass the number of checkboxes you need

        //create a button to perform course selection
        JButton selectCoursesButton = new JButton("Select Courses");

        //add checkboxes to the courseSelectionPanel
        for (JCheckBox checkbox : courseCheckboxes) {
            courseSelectionPanel.add(checkbox);
        }

        //add the "Select Courses" button to the bottom
        courseSelectionPanel.add(selectCoursesButton);

        //add an action listener for the "Select Courses" button
        selectCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder selectedCourses = new StringBuilder("Selected Courses:\n");

                for (JCheckBox checkbox : courseCheckboxes) {
                    if (checkbox.isSelected()) {
                        selectedCourses.append(checkbox.getText()).append("\n");
                    }
                }

                //display the selected courses
                JOptionPane.showMessageDialog(frame, selectedCourses.toString(), "Selected Courses", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.setFont(new Font("Arial", Font.BOLD, 16));
        backToMenuButton.setForeground(Color.BLACK);
        backToMenuButton.setPreferredSize(new Dimension(150, 50));

        //add an action listener for the "Back to Menu" button
        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "MenuAfterLogin"); //switch back to the menu after login page
            }
        });

        courseSelectionPanel.add(backToMenuButton);
        cards.add(courseSelectionPanel, "CourseSelection");
    }
    
//method to create an array of course checkboxes
    private JCheckBox[] createCourseCheckboxes(int numCheckboxes) {
        JCheckBox[] courseCheckboxes = new JCheckBox[numCheckboxes];

        for (int i = 0; i < numCheckboxes; i++) {
            courseCheckboxes[i] = new JCheckBox("Course " + (i + 1));
        }

        return courseCheckboxes;
    }

    //create Course List Panel
    private void createCourseListPanel() {
        courseListPanel = new JPanel();
        courseListPanel.setLayout(new GridLayout(2, 1)); //two rows, one column

        //add a scrollable text area for course list...
        courseListTextArea = new JTextArea();
        courseListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(courseListTextArea);

        courseListPanel.add(scrollPane);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //panel for the "View Courses" button
        viewCoursesButton = new JButton("View Courses");
        viewCoursesButton.setFont(new Font("Arial", Font.BOLD, 16)); //set font and make it bold
        viewCoursesButton.setForeground(Color.BLACK); //set button text color
        buttonPanel.add(viewCoursesButton);

        courseListPanel.add(buttonPanel);

        frame.add(courseListPanel);
        cards.add(courseListPanel, "CourseList");

        JButton backToMenuButton = new JButton("Back to Menu");
        backToMenuButton.setFont(new Font("Arial", Font.BOLD, 16)); //set font and make it bold
        backToMenuButton.setForeground(Color.BLACK); //set button text color
        backToMenuButton.setPreferredSize(new Dimension(150, 50));

        //add action listener for the "Back to Menu" button
        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cards, "MenuAfterLogin");
            }
        });

        buttonPanel.add(backToMenuButton);
    }

    //create Selected Courses Panel
    private void createSelectedCoursesPanel() {
        courseSelectionPanel = new JPanel();
        courseSelectionPanel.setLayout(new GridBagLayout());

        //create an array of course checkboxes
        JCheckBox[] courseCheckboxes = new JCheckBox[5]; //change the array size as needed

        //populate the checkboxes with course names
        courseCheckboxes[0] = new JCheckBox("Course 1");
        courseCheckboxes[1] = new JCheckBox("Course 2");
        courseCheckboxes[2] = new JCheckBox("Course 3");
        courseCheckboxes[3] = new JCheckBox("Course 4");
        courseCheckboxes[4] = new JCheckBox("Course 5");

        //create a button to view courses
        JButton viewCoursesButton = new JButton("View Courses");
        viewCoursesButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewCoursesButton.setForeground(Color.BLACK);
        viewCoursesButton.setPreferredSize(new Dimension(150, 50));

        //create a button to select courses
        JButton selectCoursesButton = new JButton("Select Courses");
        selectCoursesButton.setFont(new Font("Arial", Font.BOLD, 16));
        selectCoursesButton.setForeground(Color.BLACK);
        selectCoursesButton.setPreferredSize(new Dimension(150, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        //add checkboxes to a panel
        for (JCheckBox checkbox : courseCheckboxes) {
            courseSelectionPanel.add(checkbox, gbc);
            gbc.gridy++; //move to the next row
        }

        gbc.gridy++; //move to the next row
        courseSelectionPanel.add(viewCoursesButton, gbc);

        gbc.gridy++; //move to the next row
        courseSelectionPanel.add(selectCoursesButton, gbc);

        //add action listeners for the buttons
        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                StringBuilder selectedCourses = new StringBuilder("Selected Courses:\n");

                for (JCheckBox checkbox : courseCheckboxes) {
                    if (checkbox.isSelected()) {
                        selectedCourses.append(checkbox.getText()).append("\n");
                    }
                }

                //display the selected courses
                JOptionPane.showMessageDialog(frame, selectedCourses.toString(), "Selected Courses", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        cards.add(courseSelectionPanel, "CourseSelection");
    }

    private JPanel createSelectedCoursesViewPanel() {
        JPanel selectedCoursesViewPanel = new JPanel();
        selectedCoursesViewPanel.setLayout(new BorderLayout());

        //create a scrollable list of selected courses
        JList<String> selectedCoursesList = new JList<>(new String[]{"Course 1", "Course 2", "Course 3"});
        JScrollPane scrollPane = new JScrollPane(selectedCoursesList);

        selectedCoursesViewPanel.add(scrollPane, BorderLayout.CENTER);

        return selectedCoursesViewPanel;
    }

    //create the panel for the "Student Info" page
    private JPanel createStudentInfoViewPanel() {
        JPanel studentInformationPanel = new JPanel();
        studentInformationPanel.setLayout(new GridLayout(3, 2));

        //add labels and text fields for student information
        JLabel nameLabel = new JLabel("Full Name:");
        JTextField nameField = new JTextField(20);
        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField(6);
        JLabel userLabel = new JLabel("Username");
        JTextField userField = new JTextField(20);
        JLabel mailLabel = new JLabel("Email");
        JTextField mailField = new JTextField(20);

        studentInfoPanel.add(nameLabel);
        studentInfoPanel.add(nameField);
        studentInfoPanel.add(idLabel);
        studentInfoPanel.add(idField);
        studentInfoPanel.add(userLabel);
        studentInfoPanel.add(userField);
        studentInfoPanel.add(mailLabel);
        studentInfoPanel.add(mailField);

        return studentInfoPanel;
    }

    //action listeners for buttons
    private void addListeners() throws NullPointerException {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(frame, "Student information saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(cards, "CourseSelection"); //switch to the Course Selection page

            }
        });

        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //read and display course information from file

                    Database DB = new Database();
                    
                    courseListTextArea.setCaretPosition(0);
                    courseListTextArea.setText(DB.printCourses());
                    DB.close();
            }
        });

        viewSelectedCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
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
    
    public static void main(String[] args) {
        //creates lectureRoom and labRoom instances
        
        SwingUtilities.invokeLater(() -> {
            CourseSelectionGUI gui = new CourseSelectionGUI();
            gui.addListeners(); //add action listeners after GUI setup
        });
        Database DB = new Database();
    }
}
