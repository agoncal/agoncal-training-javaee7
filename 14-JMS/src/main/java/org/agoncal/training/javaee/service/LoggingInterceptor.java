package org.agoncal.training.javaee.service;

import org.apache.logging.log4j.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class LoggingInterceptor {

    // ======================================
    // =             Attributes             =
    // ======================================

    private Logger logger = Logger.getLogger("org.agoncal.training.javaee6");

    // ======================================
    // =          Business methods          =
    // ======================================

    @AroundInvoke
    public Object logMethod(InvocationContext ic) throws Exception {
        logger.entering(ic.getTarget().getClass().getName(), ic.getMethod().getName());
        try {
            return ic.proceed();
        } finally {
            logger.exiting(ic.getTarget().getClass().getName(), ic.getMethod().getName());
        }
    }

}
