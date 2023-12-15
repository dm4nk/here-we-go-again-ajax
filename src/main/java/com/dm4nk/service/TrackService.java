package com.dm4nk.service;

import com.dm4nk.dao.TrackDAO;
import com.dm4nk.entity.Track;

import java.util.List;

public class TrackService {
    private final TrackDAO trackDAO;

    public TrackService(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public void saveTrack(Track track) {
        trackDAO.saveTrack(track);
    }

    public Track getTrackById(long id) {
        return trackDAO.getTrackById(id);
    }

    public Track getTrackByIdWithAlbum(long id) {
        return trackDAO.getTrackByIdWithAlbum(id);
    }

    public List<Track> getAllTracks() {
        return trackDAO.getAllTracks();
    }

    public void updateTrack(Track track) {
        trackDAO.updateTrack(track);
    }

    public void deleteTrack(long id) {
        trackDAO.deleteTrack(id);
    }
}
