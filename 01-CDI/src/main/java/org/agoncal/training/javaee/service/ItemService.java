package org.agoncal.training.javaee.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

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

    private static final Logger logger = LogManager.getLogger(ItemService.class.getName());

    // ======================================
    // =          Business methods          =
    // ======================================

    public String generateNumber() {
        String number = numberGenerator.generateNumber();
        logger.debug("Number generated" + number);
        return number;
    }
}
