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
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // ======================================
    // =          Business methods          =
    // ======================================

    public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException, NoSuchMethodException {

        // Gets a Validator
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();

        // Creates a book
        Book book = new Book(4044L, "H2G2", 12.5F, "Best IT Scifi Book", 247, true, Language.ENGLISH);

        // Validates the book
        Set<ConstraintViolation<Book>> constraints = validator.validate(book);
        logger.info("Number of violated constraints : " + constraints.size());
        for (ConstraintViolation<Book> violation : constraints) {
            logger.info("   Bean     : " + violation.getRootBeanClass().getSimpleName());
            logger.info("   Property : " + violation.getPropertyPath());
            logger.info("   Value    : " + violation.getInvalidValue());
            logger.info("   Template : " + violation.getMessageTemplate());
            logger.info("   Message  : " + violation.getMessage());
        }

        // Sets non chronological dates
        book.setEarlyAccessDate(dateFormat.parse("2018-01-01"));
        book.setPublicationDate(dateFormat.parse("2010-01-01"));

        // Validates the book
        constraints = validator.validate(book);
        logger.info("Number of violated constraints with dates : " + constraints.size());
        for (ConstraintViolation<Book> violation : constraints) {
            logger.info("   Bean     : " + violation.getRootBeanClass().getSimpleName());
            logger.info("   Property : " + violation.getPropertyPath());
            logger.info("   Value    : " + violation.getInvalidValue());
            logger.info("   Template : " + violation.getMessageTemplate());
            logger.info("   Message  : " + violation.getMessage());
        }

        ExecutableValidator executableValidator = validator.forExecutables();

        ItemService object = new ItemService();
        Method method = ItemService.class.getMethod("findCD", Long.class);
        Object[] parameterValues = new Object[]{null};

        Set<ConstraintViolation<ItemService>> violations = executableValidator.validateParameters(object, method, parameterValues);

        for (ConstraintViolation violation : violations) {
            logger.info("Number of violated parameter constraints : " + constraints.size());
            logger.info("   Bean     : " + violation.getRootBeanClass().getSimpleName());
            logger.info("   Property : " + violation.getPropertyPath());
            logger.info("   Value    : " + violation.getInvalidValue());
            logger.info("   Template : " + violation.getMessageTemplate());
            logger.info("   Message  : " + violation.getMessage());
        }


        vf.close();
    }
}


