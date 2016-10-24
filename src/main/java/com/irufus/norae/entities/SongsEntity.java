package com.irufus.norae.entities;

import javax.persistence.*;

/**
 * Created by Ishmael on 10/23/2016.
 */
@Entity
@Table(name = "songs")
public class SongsEntity {
    private int songId;
    private String songTitle;
    private String songSinger;
    private String songLyricist;
    private String songComposer;
    private Integer songlistCo;

    @Id
    @Column(name = "song_id")
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    @Basic
    @Column(name = "song_title")
    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    @Basic
    @Column(name = "song_singer")
    public String getSongSinger() {
        return songSinger;
    }

    public void setSongSinger(String songSinger) {
        this.songSinger = songSinger;
    }

    @Basic
    @Column(name = "song_lyricist")
    public String getSongLyricist() {
        return songLyricist;
    }

    public void setSongLyricist(String songLyricist) {
        this.songLyricist = songLyricist;
    }

    @Basic
    @Column(name = "song_composer")
    public String getSongComposer() {
        return songComposer;
    }

    public void setSongComposer(String songComposer) {
        this.songComposer = songComposer;
    }

    @Basic
    @Column(name = "songlist_co")
    public Integer getSonglistCo() {
        return songlistCo;
    }

    public void setSonglistCo(Integer songlistCo) {
        this.songlistCo = songlistCo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongsEntity that = (SongsEntity) o;

        if (songId != that.songId) return false;
        if (songTitle != null ? !songTitle.equals(that.songTitle) : that.songTitle != null) return false;
        if (songSinger != null ? !songSinger.equals(that.songSinger) : that.songSinger != null) return false;
        if (songLyricist != null ? !songLyricist.equals(that.songLyricist) : that.songLyricist != null) return false;
        if (songComposer != null ? !songComposer.equals(that.songComposer) : that.songComposer != null) return false;
        if (songlistCo != null ? !songlistCo.equals(that.songlistCo) : that.songlistCo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = songId;
        result = 31 * result + (songTitle != null ? songTitle.hashCode() : 0);
        result = 31 * result + (songSinger != null ? songSinger.hashCode() : 0);
        result = 31 * result + (songLyricist != null ? songLyricist.hashCode() : 0);
        result = 31 * result + (songComposer != null ? songComposer.hashCode() : 0);
        result = 31 * result + (songlistCo != null ? songlistCo.hashCode() : 0);
        return result;
    }
}
