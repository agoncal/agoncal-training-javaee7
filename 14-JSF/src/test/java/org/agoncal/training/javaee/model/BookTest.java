package org.agoncal.training.javaee.model;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


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

    @Test
    public void shouldCheckDatesAreChronological() throws ParseException {

        // Creates a book
        Book book = new Book("H2G2", 12.5f, "Best IT Scifi Book", 247, false, Language.ENGLISH);

        // Sets non chronological dates
        book.setEarlyAccessDate(dateFormat.parse("2018-01-01"));
        book.setPublicationDate(dateFormat.parse("2010-01-01"));

        // Validate the book
        Set<ConstraintViolation<Book>> constraints = validator.validate(book);
        assertEquals(1, constraints.size());

        // Sets non chronological dates
        book.setEarlyAccessDate(dateFormat.parse("2010-01-01"));
        book.setPublicationDate(dateFormat.parse("2018-01-01"));

        // Validate the book
        constraints = validator.validate(book);
        assertEquals(0, constraints.size());
    }
}
