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
public class TrackTest extends AbstractPersistentTest {

    // ======================================
    // =             Unit tests             =
    // ======================================

    @Test
    public void shouldCreateATrack() {

        // Creates a track
        Track track = new Track("Sgt Pepper Lonely Heart Club Ban", 4.53f, "Listen to the trumpet carefully, it's George Harrison playing");

        // Creates a track
        tx.begin();
        em.persist(track);
        tx.commit();
        Long id = track.getId();

        // Finds the track by primary key
        track = em.find(Track.class, id);
        assertEquals("Sgt Pepper Lonely Heart Club Ban", track.getTitle());

        // Updates the track
        tx.begin();
        track.setTitle("Sgt Pepper Lonely Heart Club Band");
        tx.commit();

        // Finds the chapter by primary key
        track = em.find(Track.class, id);
        assertEquals("Sgt Pepper Lonely Heart Club Band", track.getTitle());

        // Deletes the chapter
        tx.begin();
        em.remove(track);
        tx.commit();

        assertNull("Track should has been deleted", em.find(Chapter.class, id));
    }
}