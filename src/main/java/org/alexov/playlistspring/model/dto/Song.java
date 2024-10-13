package org.alexov.playlistspring.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.alexov.playlistspring.model.dictionary.Album;
import org.alexov.playlistspring.model.dictionary.Artist;
import org.alexov.playlistspring.model.dictionary.Award;
import org.alexov.playlistspring.model.dictionary.Genre;

import java.util.List;

@Setter
@Builder
public class Song {
    private int id;
    @Getter
    private String songName;
    private Artist songArtist;
    private Integer songRating;
    private Genre songGenre;
    private Album songAlbum;
    private Integer songYear;
    private String songArtistYear;
    private List<Award> songAwards;

    public String getSongYear() {
        return songYear == null ? null: songYear.toString();
    }

    public String getSongArtist() {
        return songArtist.getName();
    }

    public String getSongRating() {
        return songRating.toString();
    }

    public String getSongGenre() {
        return songGenre.getName();
    }

    public String getSongAlbum() {
        return songAlbum== null ? null: songAlbum.getName();
    }

    public String getSongAwards() {
        return this.songAwards == null ? "" : String.join("; ", songAwards.stream().map(Award::getName).toList());
    }

    public Album getAlbum(){
        return this.songAlbum;
    }

}
