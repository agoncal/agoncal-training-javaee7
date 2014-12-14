package org.agoncal.training.javaee.constraints;

import org.agoncal.training.javaee.model.Book;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class ChronologicalDatesValidator implements ConstraintValidator<ChronologicalDates, Book> {

    @Override
    public void initialize(ChronologicalDates constraintAnnotation) {
    }

    @Override
    public boolean isValid(Book book, ConstraintValidatorContext context) {
        if (book.getEarlyAccessDate() == null || book.getPublicationDate() == null)
            return true;

        if (book.getEarlyAccessDate().getTime() < book.getPublicationDate().getTime()) {
            return true;
        }
        return false;
    }
}
