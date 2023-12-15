package com.dm4nk.dao;

import com.dm4nk.entity.Album;
import com.dm4nk.entity.Author;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AuthorDAO {
    private final SessionFactory sessionFactory;

    public AuthorDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveAuthor(Author author) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Author getAuthorById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Author.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Author> getAllAuthors() {
        try (Session session = sessionFactory.openSession()) {
            List<Author> authors = session.createQuery("FROM Author a ORDER BY a.id", Author.class).list();
            for (Author author : authors) {
                Hibernate.initialize(author.getAlbums());
                for (Album album : author.getAlbums()) {
                    Hibernate.initialize(album.getTracks());
                }
            }
            return authors;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateAuthor(Author author) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(author);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteAuthor(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Author author = session.get(Author.class, id);
            if (author != null) {
                session.delete(author);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Author> getAuthorsWithSimilarName(String input) {
        try (Session session = sessionFactory.openSession()) {
            List<Author> authors = session.createQuery("FROM Author WHERE name LIKE :input", Author.class).setParameter("input", "%" + input + "%").list();
            return authors;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
