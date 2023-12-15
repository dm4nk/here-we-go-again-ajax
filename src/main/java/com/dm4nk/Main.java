package com.dm4nk;

import com.dm4nk.dao.AlbumDAO;
import com.dm4nk.dao.AuthorDAO;
import com.dm4nk.dao.TrackDAO;
import com.dm4nk.entity.Album;
import com.dm4nk.entity.Author;
import com.dm4nk.entity.Track;
import com.dm4nk.service.AlbumService;
import com.dm4nk.service.AuthorService;
import com.dm4nk.service.TrackService;
import com.dm4nk.util.HibernateSessionFactoryUtil;
import com.dm4nk.util.Logger;
import org.hibernate.SessionFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        try {
            AuthorService authorService = new AuthorService(new AuthorDAO(sessionFactory));
            AlbumService albumService = new AlbumService(new AlbumDAO(sessionFactory));
            TrackService trackService = new TrackService(new TrackDAO(sessionFactory));
            Logger.debug("\n1. Создаем 2 авторов, по 2 альбома и по 1 треку в каждом");

            for (int i = 1; i <= 3; i++) {
                Author author = new Author();
                author.setName("Author " + i);
                authorService.saveAuthor(author);

                for (int j = 1; j <= 2; j++) {
                    Album album = new Album();
                    album.setName("Album " + i + j);
                    album.setGenre("Genre " + j % 2);
                    album.setAuthor(author);
                    albumService.saveAlbum(album);

                    Track track = new Track();
                    track.setName("Track " + i + j);
                    track.setDuration(30 + j * i);
                    track.setAlbum(album);
                    trackService.saveTrack(track);
                }
            }

            List<Author> authors = authorService.getAllAuthors();
            printAuthors(authors);
            final long delId = authors.stream().findFirst().get().getId();
            Logger.debug("2. Обновляем имя у автора с ID: " + delId);

            Author authorToUpdate = authorService.getAuthorById(delId);
            // Обновляем имя исполнителя
            authorToUpdate.setName("НОВОЕ ИМЯ АВТОРА");
            authorService.updateAuthor(authorToUpdate);

            // Выводим обновленный список
            authors = authorService.getAllAuthors();
            printAuthors(authors);

            authorService.deleteAuthor(authorToUpdate.getId());
            authors = authorService.getAllAuthors();
            Logger.debug("3. Удаляем автора " + delId);
            printAuthors(authors);

            //authorService.deleteAllAuthors();
        } catch (
                Exception e) {
            Logger.error(e.getMessage());
        } finally {
            sessionFactory.close();
            Logger.debug("Сессия закрыта");
        }

    }

    private static void printAuthors(List<Author> authors) {
        System.out.println("\n");
        for (Author author : authors) {
            System.out.println("Author ID: " + author.getId() + ", Name: " + author.getName());

            List<Album> albums = author.getAlbums();
            for (Album album : albums) {
                System.out.println("Album ID: " + album.getId() + ", Name: " + album.getName() + ", Genre: " + album.getGenre());

                List<Track> tracks = album.getTracks();
                for (Track track : tracks) {
                    System.out.println("Track ID: " + track.getId() + ", Name: " + track.getName() + ", Duration: " + track.getDuration());
                }
            }
        }
        System.out.println("\n");
    }
}
