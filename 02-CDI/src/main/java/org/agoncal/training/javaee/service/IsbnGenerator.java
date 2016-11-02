package org.agoncal.training.javaee.service;

import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Random;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@ThirteenDigits
// TODO Add @Loggable
public class IsbnGenerator implements NumberGenerator {

    // ======================================
    // =             Attributes             =
    // ======================================

    private int postfix;

    @Inject
    private Logger logger;

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PostConstruct
    private void init() {
        postfix = Math.abs(new Random().nextInt());
        logger.debug("Postfix: " + postfix);
    }

    // ======================================
    // =          Business methods          =
    // ======================================

    public String generateNumber() {
        String number = "13-84356-" + postfix++;
        logger.debug("Generated Isbn Number: " + number);
        return number;
    }

}
