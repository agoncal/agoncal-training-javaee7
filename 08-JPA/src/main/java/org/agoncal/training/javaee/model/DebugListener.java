package org.agoncal.training.javaee.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class DebugListener {

    // ======================================
    // =             Attributes             =
    // ======================================

    // TODO Instead, @Inject the Logger and see what happens
    private static final Logger logger = LogManager.getLogger(DebugListener.class);

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @PrePersist
    void prePersist(Object object) {
        logger.trace("### prePersist(" + object + ")");
    }

    @PostPersist
    void postPersist(Object object) {
        logger.trace("### postPersist(" + object + ")");
    }

    @PreUpdate
    void preUpdate(Object object) {
        logger.trace("### preUpdate(" + object + ")");
    }

    @PostUpdate
    void postUpdate(Object object) {
        logger.trace("### postUpdate(" + object + ")");
    }

    @PreRemove
    void preRemove(Object object) {
        logger.trace("### preRemove(" + object + ")");
    }

    @PostRemove
    void postRemove(Object object) {
        logger.trace("### postRemove(" + object + ")");
    }

    @PostLoad
    void postLoad(Object object) {
        logger.trace("### postLoad(" + object + ")");
    }
}
