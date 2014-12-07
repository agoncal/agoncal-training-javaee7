package org.agoncal.training.javaee.model;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author Antonio Goncalves
 *         APress Book - Beginning Java EE 6 with Glassfish
 *         http://www.apress.com/
 *         http://www.antoniogoncalves.org
 *         --
 */
public abstract class AbstractPersistentTest {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static String PERSISTENCE_UNIT_NAME = "trainingPU";

    private EntityManagerFactory emf;
    protected EntityManager em;
    protected EntityTransaction tx;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @Before
    public void initTransaction() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void closeEntityManager() {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }
}