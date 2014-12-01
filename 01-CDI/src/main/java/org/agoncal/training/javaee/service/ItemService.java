package org.agoncal.training.javaee.service;

import javax.inject.Inject;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class ItemService {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    @ThirteenDigits
    private NumberGenerator numberGenerator;

    private Logger logger = Logger.getLogger(ItemService.class.getName());

    // ======================================
    // =          Business methods          =
    // ======================================

    public String createBook() {
        String number = numberGenerator.generateNumber();
        logger.fine("Book created with number " + number);
        return number;
    }
}
