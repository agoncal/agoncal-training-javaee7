package org.agoncal.training.javaee.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class LoggingProducer {

    // ======================================
    // =          Producer methods          =
    // ======================================

    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        return LogManager.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}