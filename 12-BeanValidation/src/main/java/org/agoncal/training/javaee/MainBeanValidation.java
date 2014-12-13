package org.agoncal.training.javaee;

import org.agoncal.training.javaee.model.*;
import org.agoncal.training.javaee.service.ItemService;
import org.agoncal.training.javaee.service.MockGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.Vetoed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Vetoed
public class MainBeanValidation {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static final Logger logger = LogManager.getLogger(MainBeanValidation.class);

    private static ValidatorFactory vf;
    private static Validator validator;

    // ======================================
    // =          Business methods          =
    // ======================================

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // Gets a Validator
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();

        // Creates a book
        Book book = new Book(4044L, "H2G2", 12.5F, "Best IT Scifi Book", 247, true, Language.ENGLISH);

        // Validates the book
        Set<ConstraintViolation<Book>> constraints = validator.validate(book);
        logger.info("Number of violated constraints : " + constraints.size());

        vf.close();
    }
}


