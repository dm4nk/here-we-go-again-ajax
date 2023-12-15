package com.dm4nk.service;

import com.dm4nk.dao.AlbumDAO;
import com.dm4nk.entity.Album;

import java.util.List;

public class AlbumService {
    private final AlbumDAO albumDAO;

    public AlbumService(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    public void saveAlbum(Album album) {
        albumDAO.saveAlbum(album);
    }

    public Album getAlbumById(long id) {
        return albumDAO.getAlbumById(id);
    }

    public List<Album> getAllAlbums() {
        return albumDAO.getAllAlbums();
    }

    public void updateAlbum(Album album) {
        albumDAO.updateAlbum(album);
    }

    public void deleteAlbum(long id) {
        albumDAO.deleteAlbum(id);
    }

    public Album getAlbumByIdWithTracks(long albumId) {
        return albumDAO.getAlbumByIdWithTracks(albumId);
    }

    public List<Album> getAlbumsWithSimilarName(String input) {
        return albumDAO.getAlbumsWithSimilarName(input);
    }
}
