package com.dm4nk.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.List;


@Entity
@Table(name = "albums")
@SequenceGenerator(name = "ALBUMS_SEQ_ID", sequenceName = "ALBUMS_SEQ_ID", allocationSize = 1)
public class Album {
    @Id
    @GeneratedValue(generator = "ALBUMS_SEQ_ID")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "genre", nullable = false)
    private String genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private Author author;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "album")
    private List<Track> tracks;

    public Album(String name, String genre, Author author, List<Track> tracks) {
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.tracks = tracks;
    }

    public Album(long id, String name, String genre, Author author, List<Track> tracks) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.tracks = tracks;
    }

    public Album(long id) {
        this.id = id;
    }

    public Album() {
    }

    @Override
    public String toString() {
        return String.format("ID: %s Name: %s Genre: %s AuthorID: %s", this.getId(), this.getName(), this.getGenre(), this.getAuthor().getId());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
