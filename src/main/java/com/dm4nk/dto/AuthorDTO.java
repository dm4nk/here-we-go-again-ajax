package com.dm4nk.dto;

public class AuthorDTO {
    private final long id;

    private final String name;

    private final int albums;

    public AuthorDTO(long id, String name, int albums) {
        this.id = id;
        this.name = name;
        this.albums = albums;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAlbums() {
        return albums;
    }
}
