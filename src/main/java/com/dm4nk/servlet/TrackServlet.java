package com.dm4nk.servlet;


import com.dm4nk.dao.TrackDAO;
import com.dm4nk.entity.Album;
import com.dm4nk.entity.Track;
import com.dm4nk.service.TrackService;
import com.dm4nk.util.HibernateSessionFactoryUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TrackServlet", value = {"/tracks", "/createTrack", "/editTrack", "/deleteTrack"})
public class TrackServlet extends HttpServlet {
    private static TrackService trackService;

    @Override
    public void init() throws ServletException {

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        trackService = new TrackService(new TrackDAO(HibernateSessionFactoryUtil.getSessionFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/createTrack":
                request.getRequestDispatcher("track/createTrack.jsp").forward(request, response);
                break;
            case "/editTrack":
                long trackId = Long.parseLong(request.getParameter("id"));
                Track track = trackService.getTrackById(trackId);
                request.setAttribute("tracks", track);
                request.getRequestDispatcher("track/editTrack.jsp").forward(request, response);
                break;
            case "/deleteTrack":
                long deleteTrackId = Long.parseLong(request.getParameter("id"));
                trackService.deleteTrack(deleteTrackId);
                response.sendRedirect("tracks");
                break;
            default:
                List<Track> tracks = trackService.getAllTracks();
                request.setAttribute("tracks", tracks);
                request.getRequestDispatcher("track/tracks.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/createTrack":
                String name = request.getParameter("name");
                int duration = Integer.parseInt(request.getParameter("duration"));
                long album = Long.parseLong(request.getParameter("album"));
                Track newTrack = new Track();
                newTrack.setName(name);
                newTrack.setDuration(duration);
                newTrack.setAlbum(new Album(album));
                trackService.saveTrack(newTrack);
                response.sendRedirect("tracks");
                break;
            case "/editTrack":
                long id = Long.parseLong(request.getParameter("id"));
                String newName = request.getParameter("name");
                int newDuration = Integer.parseInt(request.getParameter("duration"));
                Track track = trackService.getTrackById(id);
                track.setName(newName);
                track.setDuration(newDuration);
                trackService.updateTrack(track);
                response.sendRedirect("tracks");
                break;
        }
    }

}
