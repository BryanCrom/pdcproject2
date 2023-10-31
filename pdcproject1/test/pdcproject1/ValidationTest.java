/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package pdcproject1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bryan
 */
public class ValidationTest {
    
    public ValidationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validateUsername method, of class Validation.
     */
    @Test
    public void testValidateUsername() {
        String username = "";
        boolean expResult = false;
        boolean result = Validation.validateUsername(username);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidatePassword() {
        String password = "";
        boolean expResult = false;
        boolean result = Validation.validatePassword(password);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidateFirstName() {
        String firstName = "";
        boolean expResult = false;
        boolean result = Validation.validateFirstName(firstName);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidateLastName() {
        String lastName = "";
        boolean expResult = false;
        boolean result = Validation.validateLastName(lastName);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidateEmail() {
        String email = "";
        boolean expResult = false;
        boolean result = Validation.validateEmail(email);
        assertEquals(expResult, result);
    }
}
