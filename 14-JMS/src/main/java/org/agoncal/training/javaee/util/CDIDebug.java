package org.agoncal.training.javaee.util;

import org.apache.logging.log4j.LogManager;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.*;

public class CDIDebug implements Extension {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CDIDebug.class);

    // Before
    private void beforeBeanDiscovery(@Observes BeforeBeanDiscovery event) {
        logger.debug("&&&&&&&&&&&&&&&&&&&&& CDI Debug &&&&&&&&&&&&&&&&&&&&&");
    }

    // Beans
    private <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> event) {
        if (event.getAnnotatedType().toString().contains("org.agoncal.training.javaee"))
            logger.debug("ProcessAnnotatedType: " + event.getAnnotatedType().toString());
    }

    private void afterBeanDiscovery(@Observes AfterDeploymentValidation event, BeanManager beanManager) {
        logger.debug("&&&&&&&&&&&&&&&&&&&&& CDI Debug &&&&&&&&&&&&&&&&&&&&&\n");
    }
}