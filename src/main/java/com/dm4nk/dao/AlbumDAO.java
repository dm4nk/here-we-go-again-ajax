package com.dm4nk.dao;

import com.dm4nk.entity.Album;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AlbumDAO {
    private final SessionFactory sessionFactory;

    public AlbumDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveAlbum(Album album) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(album);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Album getAlbumById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Album.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Album> getAllAlbums() {
        try (Session session = sessionFactory.openSession()) {
            List<Album> albums = session.createQuery("FROM Album a ORDER BY a.id", Album.class).list();
            for (Album album : albums) {
                Hibernate.initialize(album.getTracks());
                // Если у вас есть другие связанные коллекции в Track, инициализируйте их аналогично.
            }
            return albums;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateAlbum(Album album) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(album);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteAlbum(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Album album = session.get(Album.class, id);
            if (album != null) {
                session.delete(album);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Album getAlbumByIdWithTracks(long id) {
        try (Session session = sessionFactory.openSession()) {
            Album album = session.get(Album.class, id);
            Hibernate.initialize(album.getTracks());
            return album;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Album> getAlbumsWithSimilarName(String input) {
        try (Session session = sessionFactory.openSession()) {
            List<Album> albums = session.createQuery("FROM Album WHERE name LIKE :input", Album.class).setParameter("input", "%" + input + "%").list();
            return albums;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
