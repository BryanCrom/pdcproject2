package pdcproject1;

import java.util.Set;

/**
 *
 * @author jeromejoseph and bryancrombach
 */
public interface UserInterface {
    void displayCourseList();

    Set<String> selectCourses(Student student, CourseCatalog catalog);
}
