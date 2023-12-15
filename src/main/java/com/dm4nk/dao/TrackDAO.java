package com.dm4nk.dao;

import com.dm4nk.entity.Track;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TrackDAO {
    private final SessionFactory sessionFactory;

    public TrackDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveTrack(Track track) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(track);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Track getTrackById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Track.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Track> getAllTracks() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Track t ORDER BY t.id").list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateTrack(Track track) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(track);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTrack(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Track track = session.get(Track.class, id);
            if (track != null) {
                session.delete(track);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Track getTrackByIdWithAlbum(long id) {
        try (Session session = sessionFactory.openSession()) {
            Track track = session.get(Track.class, id);
            Hibernate.initialize(track.getAlbum());
            return track;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
