package org.agoncal.training.javaee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class BookTest {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static ValidatorFactory vf;
    private static Validator validator;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initValidator() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void closeContainer() {
        if (vf != null) {
            vf.close();
        }
    }

    // ======================================
    // =             Unit tests             =
    // ======================================

    @Test
    public void shouldRaiseNoConstraintViolation() {

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);

        // Validate the book
        Set<ConstraintViolation<Book>> constraints = validator.validate(book);
        assertEquals(0, constraints.size());
    }

    @Test
    public void shouldRaiseConstraintsViolation() {

        // Creates a book
        Book book = new Book();

        // Validate the book
        Set<ConstraintViolation<Book>> constraints = validator.validate(book);
        assertEquals(1, constraints.size());
    }
}
