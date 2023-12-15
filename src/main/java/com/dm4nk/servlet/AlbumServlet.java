package com.dm4nk.servlet;

import com.dm4nk.dao.AlbumDAO;
import com.dm4nk.dto.AlbumDTO;
import com.dm4nk.entity.Album;
import com.dm4nk.entity.Author;
import com.dm4nk.entity.Track;
import com.dm4nk.service.AlbumService;
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

@WebServlet(name = "AlbumServlet", value = {"/albums", "/createAlbum", "/editAlbum", "/deleteAlbum", "/getTrackNamesByAlbumId", "/getSimilarAlbums"})
public class AlbumServlet extends HttpServlet {
    private static AlbumService albumService;

    @Override
    public void init() throws ServletException {
        albumService = new AlbumService(new AlbumDAO(HibernateSessionFactoryUtil.getSessionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/createAlbum":
                request.getRequestDispatcher("album/createAlbum.jsp").forward(request, response);
                break;
            case "/editAlbum":
                long albumId = Long.parseLong(request.getParameter("id"));
                Album album = albumService.getAlbumById(albumId);
                request.setAttribute("album", album);
                request.getRequestDispatcher("album/editAlbum.jsp").forward(request, response);
                break;
            case "/deleteAlbum":
                long deleteAlbumId = Long.parseLong(request.getParameter("id"));
                albumService.deleteAlbum(deleteAlbumId);
                response.sendRedirect("albums");
                break;
            case "/getTrackNamesByAlbumId":
                long albumIdAjax = Long.parseLong(request.getParameter("id"));
                Album albumAjax = albumService.getAlbumByIdWithTracks(albumIdAjax);
                List<Track> tracks = albumAjax.getTracks();

                List<String> trackNames = new ArrayList<>();
                for (Track track : tracks) {
                    trackNames.add(track.getName());
                }

                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(trackNames);

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                PrintWriter out = response.getWriter();
                out.print(json);
                out.flush();
                break;
            case "/getSimilarAlbums":
                String input = request.getParameter("input");
                List<Album> similarAlbums = albumService.getAlbumsWithSimilarName(input);
                Logger.debug(similarAlbums.toString());

                List<AlbumDTO> simAlbumsDTOS = new ArrayList<>();
                for (Album simAlbum : similarAlbums) {
                    simAlbumsDTOS.add(new AlbumDTO(simAlbum.getId(), simAlbum.getName()));
                }

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                ObjectMapper objectMapper2 = new ObjectMapper();
                String json2 = objectMapper2.writeValueAsString(simAlbumsDTOS);
                Logger.debug(json2);
                PrintWriter out2 = response.getWriter();
                out2.print(json2);
                out2.flush();
                break;
            default:
                List<Album> albums = albumService.getAllAlbums();
                request.setAttribute("albums", albums);
                request.getRequestDispatcher("album/albums.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/createAlbum":
                String name = request.getParameter("name");
                String genre = request.getParameter("genre");
                long author = Long.parseLong(request.getParameter("author"));
                Album newAlbum = new Album();
                newAlbum.setName(name);
                newAlbum.setGenre(genre);
                newAlbum.setAuthor(new Author(author));
                albumService.saveAlbum(newAlbum);
                response.sendRedirect("albums");
                break;
            case "/editAlbum":
                long id = Long.parseLong(request.getParameter("id"));
                String newName = request.getParameter("name");
                String newGenre = request.getParameter("genre");
                Album album = albumService.getAlbumById(id);
                album.setName(newName);
                album.setGenre(newGenre);
                albumService.updateAlbum(album);
                response.sendRedirect("albums");
                break;
        }
    }
}
