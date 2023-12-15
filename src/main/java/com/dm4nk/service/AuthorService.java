package com.dm4nk.service;

import com.dm4nk.dao.AuthorDAO;
import com.dm4nk.entity.Author;

import java.util.List;

public class AuthorService {
    private final AuthorDAO authorDAO;

    public AuthorService(AuthorDAO authorDAO) {
        this.authorDAO = authorDAO;
    }

    public void saveAuthor(Author author) {
        authorDAO.saveAuthor(author);
    }

    public Author getAuthorById(long id) {
        return authorDAO.getAuthorById(id);
    }

    public List<Author> getAllAuthors() {
        return authorDAO.getAllAuthors();
    }

    public void updateAuthor(Author author) {
        authorDAO.updateAuthor(author);
    }

    public void deleteAuthor(long id) {
        authorDAO.deleteAuthor(id);
    }

    public void deleteAllAuthors() {
        List<Author> authors = authorDAO.getAllAuthors();
        for (Author author : authors) {
            authorDAO.deleteAuthor(author.getId());
        }
    }

    public List<Author> getAuthorsWithSimilarName(String input) {
        return authorDAO.getAuthorsWithSimilarName(input);
    }
}
