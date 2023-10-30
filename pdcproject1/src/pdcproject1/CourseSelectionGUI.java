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


    public CourseSelectionGUI() {
        frame = new JFrame("Course Selection System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new GridLayout(2, 2));

        createStudentInfoPanel();
        createCourseSelectionPanel();
        createCourseListPanel();
        createSelectedCoursesPanel();

        frame.setVisible(true);
    }

    private void createStudentInfoPanel() {
        studentInfoPanel = new JPanel();
        studentInfoPanel.setLayout(new GridLayout(4, 2));

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 14)); 
        firstNameField = new JTextField(20);
        firstNameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        lastNameField = new JTextField(20);
        lastNameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14)); 
        emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(Color.GREEN);
        submitButton.setForeground(Color.BLACK); 

        studentInfoPanel.add(firstNameLabel);
        studentInfoPanel.add(firstNameField);
        studentInfoPanel.add(lastNameLabel);
        studentInfoPanel.add(lastNameField);
        studentInfoPanel.add(emailLabel);
        studentInfoPanel.add(emailField);
        studentInfoPanel.add(new JLabel());
        studentInfoPanel.add(submitButton);

        frame.add(studentInfoPanel);
    }


    private void createCourseSelectionPanel() {
        courseSelectionPanel = new JPanel();
        courseSelectionPanel.setLayout(new BorderLayout());

        frame.add(courseSelectionPanel);
    }


    private void createCourseListPanel() {
        courseListPanel = new JPanel();
        courseListPanel.setLayout(new BorderLayout());

        courseListTextArea = new JTextArea();
        courseListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(courseListTextArea);
        courseListPanel.add(scrollPane);

        viewCoursesButton = new JButton("View Courses");
        viewCoursesButton.setFont(new Font("Arial", Font.BOLD, 16)); 
        viewCoursesButton.setBackground(Color.BLUE); 
        viewCoursesButton.setForeground(Color.BLACK);
        courseListPanel.add(viewCoursesButton, BorderLayout.SOUTH);

        frame.add(courseListPanel);
    }

   
    private void createSelectedCoursesPanel() {
        selectedCoursesPanel = new JPanel();
        selectedCoursesPanel.setLayout(new BorderLayout());

      
        selectedCoursesTextArea = new JTextArea();
        selectedCoursesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(selectedCoursesTextArea);
        selectedCoursesPanel.add(scrollPane);

        viewSelectedCoursesButton = new JButton("View Selected Courses");
        viewSelectedCoursesButton.setFont(new Font("Arial", Font.BOLD, 16)); 
        viewSelectedCoursesButton.setBackground(Color.BLUE);
        viewSelectedCoursesButton.setForeground(Color.BLACK);
        selectedCoursesPanel.add(viewSelectedCoursesButton, BorderLayout.SOUTH);

        frame.add(selectedCoursesPanel);
    }


    private void addListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();

                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int studentID = generateRandomStudentID(); 
                    Student student = new Student(studentID, firstName + " " + lastName, email);

                    JOptionPane.showMessageDialog(frame, "Student information saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        viewCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("resources/courses.txt"));
                    StringBuilder courseInfo = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        courseInfo.append(line).append("\n");
                    }

                    courseListTextArea.setText(courseInfo.toString());

                    reader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        viewSelectedCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAndDisplaySelectedCourses();
            }
        });
    }

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

            ex.printStackTrace();
        }
    }

    public void saveSelectedCourses(Set<String> selectedCourses) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("resources/selected_courses.txt"));

            for (String courseCode : selectedCourses) {
                writer.println(courseCode);
            }

            writer.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CourseSelectionGUI gui = new CourseSelectionGUI();
            gui.addListeners();
        });
    }

    private int generateRandomStudentID() {
        Random random = new Random();
        return 100000 + random.nextInt(900000);
    }
}