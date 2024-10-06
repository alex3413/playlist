package org.alexov.playlistspring.fx;

import jakarta.annotation.PostConstruct;
import javafx.collections.ObservableList;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.alexov.playlistspring.model.dictionary.Album;
import org.alexov.playlistspring.model.dictionary.Artist;
import org.alexov.playlistspring.model.dictionary.Award;
import org.alexov.playlistspring.model.dictionary.Genre;
import org.alexov.playlistspring.model.dto.Song;
import org.alexov.playlistspring.model.entity.Compostion;
import org.alexov.playlistspring.model.entity.CompostionAwards;
import org.alexov.playlistspring.service.PlaylistService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static javafx.collections.FXCollections.observableArrayList;

@Component
@Data
@RequiredArgsConstructor
public class PlaylistStates {
    private final PlaylistService playlistService;
    private ObservableList<Compostion> compostions;
    private ObservableList<Award> awards;
    private ObservableList<Genre> genres;
    private ObservableList<Album> albums;
    private ObservableList<Artist> artists;
    private ObservableList<Song> songs;
    private Map<Integer, List<Award>> songAwards;

    private Map<String,Award> awardsMap;
    private Map<String,Genre> genresMap;
    private Map<Integer,Album> albumsMap;
    private Map<Integer,Artist> artistsMap;

    @PostConstruct
    public void init(){
        this.compostions = observableArrayList(playlistService.findAllComposition());
        this.awards = observableArrayList(playlistService.findAllAwards());
        this.genres = observableArrayList(playlistService.findAllGenres());
        this.albums = observableArrayList(playlistService.findAllAlbums());
        this.artists = observableArrayList(playlistService.findAllArtists());
        this.songs = observableArrayList();

        awardsMap = awards.stream().collect(Collectors.toMap(Award::getCode, e->e));
        genresMap = genres.stream().collect(Collectors.toMap(Genre::getCode, e->e));
        albumsMap = albums.stream().collect(Collectors.toMap(Album::getId, e->e));
        artistsMap = artists.stream().collect(Collectors.toMap(Artist::getId, e->e));
        songAwards = playlistService.findAllCompositionAwards().stream()
                .collect(Collectors.groupingBy(CompostionAwards::getSongId,
                        Collectors.mapping(e -> awardsMap.get(e.getAwardCode()), Collectors.toList())));

    }

    public ObservableList<Song> getSongs() {
        compostions.forEach(c ->
                songs.add(Song.builder()
                        .id(c.getId())
                        .songName(c.getName())
                        .songAwards(songAwards.get(c.getId()))
                        .songArtist(artistsMap.get(c.getArtistId()))
                        .songYear(Optional.ofNullable(c.getSongYear()).map(LocalDate::getYear).orElse(null))
                        .songRating(c.getSongRating())
                        .songAlbum(albumsMap.get(c.getSongAlbumId()))
                        .songGenre(genresMap.get(c.getSongGenreCode()))
                        .build()));

        return songs;
    }
}
