package org.agoncal.training.javaee.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@EightDigits
public class IssnGenerator implements NumberGenerator {

    // ======================================
    // =             Attributes             =
    // ======================================

    private int postfix;

    private static final Logger logger = LogManager.getLogger(IssnGenerator.class.getName());

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
        String number = "8-" + postfix++;
        logger.debug("Generated Issn Number: " + number);
        return number;
    }

}
