package com.dm4nk.servlet;

import com.dm4nk.dao.AuthorDAO;
import com.dm4nk.dto.AuthorDTO;
import com.dm4nk.entity.Author;
import com.dm4nk.service.AuthorService;
import com.dm4nk.util.HibernateSessionFactoryUtil;
import com.dm4nk.util.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AuthorServlet", value = {"/authors", "/createAuthor", "/editAuthor", "/deleteAuthor", "/getSimilarAuthors"})
public class AuthorServlet extends HttpServlet {
    private static AuthorService authorService;

    @Override
    public void init() throws ServletException {
        authorService = new AuthorService(new AuthorDAO(HibernateSessionFactoryUtil.getSessionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/createAuthor":
                request.getRequestDispatcher("author/createAuthor.jsp").forward(request, response);
                break;
            case "/editAuthor":
                long authorId = Long.parseLong(request.getParameter("id"));
                Author author = authorService.getAuthorById(authorId);
                request.setAttribute("author", author);
                request.getRequestDispatcher("author/editAuthor.jsp").forward(request, response);
                break;
            case "/deleteAuthor":
                long deleteAuthorId = Long.parseLong(request.getParameter("id"));
                authorService.deleteAuthor(deleteAuthorId);
                response.sendRedirect("authors");
                break;
            case "/getSimilarAuthors":
                String input = request.getParameter("input");
                List<Author> similarAuthors = authorService.getAuthorsWithSimilarName(input);
                Logger.debug(similarAuthors.toString());

                List<AuthorDTO> simAuthorDTOS = new ArrayList<>();
                for (Author simAuthor : similarAuthors) {
                    simAuthorDTOS.add(new AuthorDTO(simAuthor.getId(), simAuthor.getName(), -1));
                }

                // Преобразуем список имен в JSON и отправляем клиенту
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(simAuthorDTOS);
                Logger.debug(json);
                PrintWriter out = response.getWriter();
                out.print(json);
                out.flush();
                break;
            default:
                List<Author> authors = authorService.getAllAuthors();
                List<AuthorDTO> authorDTOS = new ArrayList<>();
                for (Author a : authors) {
                    authorDTOS.add(new AuthorDTO(a.getId(), a.getName(), a.getAlbums().size()));
                }
                request.setAttribute("authors", authorDTOS);
                request.getRequestDispatcher("author/authors.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/createAuthor":
                String name = request.getParameter("name");
                Author newAuthor = new Author();
                newAuthor.setName(name);
                authorService.saveAuthor(newAuthor);
                response.sendRedirect("authors");
                break;
            case "/editAuthor":
                long id = Long.parseLong(request.getParameter("id"));
                String newName = request.getParameter("name");
                Author author = authorService.getAuthorById(id);
                author.setName(newName);
                authorService.updateAuthor(author);
                response.sendRedirect("authors");
                break;
        }
    }
}

