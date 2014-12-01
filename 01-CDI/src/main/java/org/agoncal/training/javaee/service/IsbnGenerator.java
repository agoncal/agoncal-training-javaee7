package org.agoncal.training.javaee.service;

import javax.annotation.PostConstruct;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {

    // ======================================
    // =             Attributes             =
    // ======================================

    private int postfix;

    private Logger logger = Logger.getLogger(IsbnGenerator.class.getName());

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PostConstruct
    private void init() {
        postfix = Math.abs(new Random().nextInt());
        logger.fine("Postfix: " + postfix);
    }

    // ======================================
    // =          Business methods          =
    // ======================================

    public String generateNumber() {
        String number = "13-84356-" + postfix++;
        logger.fine("Generated Isbn Number: " + number);
        return number;
    }

}
