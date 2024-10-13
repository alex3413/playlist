package org.alexov.playlistspring.fx;

import jakarta.annotation.PostConstruct;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.alexov.playlistspring.model.dictionary.*;
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
        fillSongsList();
        this.songs.addListener(getSongChangeListener());
        this.awards.addListener(getAwardChangeListener());
        this.albums.addListener(getAlbumChangeListener());

    }

    private void fillSongsList() {
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

    }
    private  ListChangeListener<Song> getSongChangeListener(){
        return (change) -> {
            change.next();
            if(change.wasAdded()) {

                System.out.println(change.getAddedSubList());
            }
            if(change.wasRemoved()){
                System.out.println(change.getRemoved());
            }

        };
    }
    private  ListChangeListener<Award> getAwardChangeListener(){
        return (change) -> {
            change.next();
            if(change.wasAdded()) {

                System.out.println(change.getAddedSubList());
            }
            if(change.wasRemoved()){
                System.out.println(change.getRemoved());
            }

        };
    }

    private  ListChangeListener<Album> getAlbumChangeListener(){
        return (change) -> {
            change.next();
            if(change.wasAdded()) {

                System.out.println(change.getAddedSubList());
            }
            if(change.wasRemoved()){
                Album deletedAlbum = change.getRemoved().get(0);
                editSongsByAlbum(deletedAlbum);
                //todo deleteAlbum
                System.out.println(change.getRemoved());
            }

        };
    }

    private <T extends AbstractDictionary>void editSongsByAlbum(T id) {
        songs.forEach(s -> {
            if(id instanceof Album && id.equals(s.getAlbum())){
                s.setSongAlbum(null);
                // todo сохранить песню в БД
            }
        });
    }
}
