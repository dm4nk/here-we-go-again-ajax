package com.dm4nk.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tracks")
@SequenceGenerator(name = "TRACKS_SEQ_ID", sequenceName = "TRACKS_SEQ_ID", allocationSize = 1)
public class Track {

    @Id
    @GeneratedValue(generator = "TRACKS_SEQ_ID")
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "duration", nullable = false)
    private int duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album")
    private Album album;

    public Track() {
    }

    public Track(long id, String name, int duration, Album album) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.album = album;
    }

    public Track(String name, int duration, Album album) {
        this.name = name;
        this.duration = duration;
        this.album = album;
    }

    @Override
    public String toString() {
        return String.format("ID: %s Name: %s Duration: %s AlbumID: %s", this.getId(), this.getName(), this.getDuration(), this.getAlbum().getId());
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
