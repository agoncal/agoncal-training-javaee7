package org.agoncal.training.javaee.service;

import javax.enterprise.inject.Alternative;
import java.util.Random;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Alternative
@ThirteenDigits
public class MockGenerator implements NumberGenerator {

    // ======================================
    // =          Business methods          =
    // ======================================

    public String generateNumber() {
        return "mock-" + Math.abs(new Random().nextInt());
    }

}
