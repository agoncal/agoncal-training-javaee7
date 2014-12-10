package org.agoncal.training.javaee.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class ChapterTest extends AbstractPersistentTest {

    // ======================================
    // =             Unit tests             =
    // ======================================

    @Test
    public void shouldCreateAChapter() {

        // Creates a chapter
        Chapter chapter = new Chapter("Gone with the wind", "Scarlet jumps into the bed, crying.");

        // Creates a chapter
        tx.begin();
        em.persist(chapter);
        tx.commit();
        Long id = chapter.getId();

        // Finds the chapter by primary key
        chapter = em.find(Chapter.class, id);
        assertEquals("Gone with the wind", chapter.getTitle());

        // Updates the chapter
        tx.begin();
        chapter.setTitle("Just gone");
        tx.commit();

        // Finds the chapter by primary key
        chapter = em.find(Chapter.class, id);
        assertEquals("Just gone", chapter.getTitle());

        // Deletes the chapter
        tx.begin();
        em.remove(chapter);
        tx.commit();

        assertNull("Chapter should has been deleted", em.find(Chapter.class, id));
    }
}