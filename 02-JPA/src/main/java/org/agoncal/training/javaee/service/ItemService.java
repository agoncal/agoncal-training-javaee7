package org.agoncal.training.javaee.service;

import org.agoncal.training.javaee.model.Book;
import org.agoncal.training.javaee.util.Loggable;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Loggable
public class ItemService {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    @ThirteenDigits
    private NumberGenerator numberGenerator;

    @Inject
    private Logger logger;

    // ======================================
    // =          Business methods          =
    // ======================================

    public Book createBook(Book book) {
        String number = numberGenerator.generateNumber();
        logger.debug("Book created with number " + number);
        book.setIsbn(number);
        return book;
    }
}
