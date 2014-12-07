package org.agoncal.training.javaee.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
public class CDTest extends AbstractPersistentTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateACD() {

        // Creates a CD
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Persists the CD
        tx.begin();
        em.persist(cd);
        tx.commit();
        Long id = cd.getId();

        // Finds the CD by primary key
        cd = em.find(CD.class, id);
        assertEquals("St Pepper", cd.getTitle());

        // Updates the CD
        tx.begin();
        cd.setTitle("Help");
        tx.commit();

        // Finds the CD by primary key
        cd = em.find(CD.class, id);
        assertEquals("Help", cd.getTitle());

        // Deletes the CD
        tx.begin();
        em.remove(cd);
        tx.commit();

        // Checks the CD has been deleted
        assertNull("CD should has been deleted", em.find(CD.class, id));
    }

    @Test
    public void shouldCreateACDWithTracks() {

        // Creates a CD
        CD cd = new CD("St Pepper", 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Adds tracks
        Track track1 = new Track("Sgt Pepper Lonely Heart Club Band", 4.53f, "Listen to the trumpet carefully, it's George Harrison playing");
        Track track2 = new Track("Fixing a Hole", 3.34f, "Beleive it or not, this song is about drugs");
        List<Track> tracks = new ArrayList<>();
        tracks.add(track1);
        tracks.add(track2);
        cd.setTracks(tracks);

        // Persists the CD
        tx.begin();
        em.persist(cd);
        tx.commit();
        Long id = cd.getId();

        // Finds the CD by primary key
        cd = em.find(CD.class, id);
        assertEquals("St Pepper", cd.getTitle());

        // Finds the track by primary key
        track1 = em.find(Track.class, track1.getId());
        assertEquals("Sgt Pepper Lonely Heart Club Band", track1.getTitle());

        // Checks the number of tracks
        assertEquals(2, cd.getTracks().size());

        // Deletes the CD
        tx.begin();
        em.remove(cd);
        tx.commit();

        // Checks the CD has been deleted
        assertNull("CD should has been deleted", em.find(CD.class, id));
        assertNull("Track should has been deleted", em.find(Track.class, track1.getId()));
    }

    @Test(expected = Exception.class)
    public void shouldNotCreateACDWithANullTitle() {

        // Creates a CD with null title
        CD cd = new CD(null, 12.80f, "Beatles master piece", "Apple", 1, 53.32f, "Pop");

        // Persists the CD
        tx.begin();
        em.persist(cd);
        tx.commit();
    }
}