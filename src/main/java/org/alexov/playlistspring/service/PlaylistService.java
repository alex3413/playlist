package org.alexov.playlistspring.service;

import lombok.RequiredArgsConstructor;
import org.alexov.playlistspring.model.dictionary.Album;
import org.alexov.playlistspring.model.dictionary.Artist;
import org.alexov.playlistspring.model.dictionary.Award;
import org.alexov.playlistspring.model.dictionary.Genre;
import org.alexov.playlistspring.model.dto.TotalGenreDto;
import org.alexov.playlistspring.model.entity.Compostion;
import org.alexov.playlistspring.model.entity.CompostionAwards;
import org.alexov.playlistspring.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public List<Compostion> findAllComposition() {
        return playlistRepository.getAllSongs();
    }


    public List<Award> findAllAwards() {
        return playlistRepository.getAllAwards();
    }

    public List<Genre> findAllGenres() {
        return playlistRepository.getAllGenres();
    }

    public List<Album> findAllAlbums() {
        return playlistRepository.getAllAlbums();
    }

    public List<Artist> findAllArtists() {
        return playlistRepository.getAllArtists();
    }
    public List<CompostionAwards> findAllCompositionAwards() {
        return playlistRepository.getAllCompostionAwards();
    }

    /**
     * A.	Составить топ 10 исполнителей, которые заработали больше всего наград
     * @param topNumbers
     * @return
     */
    public List<Artist> getTopTenArtistByAwards(int topNumbers) {
        return playlistRepository.getTopTenArtistByAwards(topNumbers);
    }

    /**
     * B.	По каждому жанру вывести количество песен, которые были выпущены в году, заданном пользователем
     * @param year
     * @return
     */
    public List<TotalGenreDto> getGenreWithTotalSOngsByYear(LocalDate year) {
        return playlistRepository.getGenreWithTotalSOngsByYear(year.getYear());
    }

    /**
     * C.	Найти альбом с самыми популярными песнями (наивысший средний балл) в указанном пользователе жанре
     * @param genres
     * @return
     */
    public Album getTopAlbumByGenres(List<Genre> genres) {
        return playlistRepository.getTopAlbumByGenres(genres.stream().map(Genre::getCode).toList());
    }

    /**
     * D.	Вывести в алфавитном порядке исполнителей, которые выпустили хотя бы один альбом за период
     * @param from
     * @param to
     * @return
     */
    public List<Artist> getArtistsHasOneAlbumbyPeriod(LocalDate from, LocalDate to) {
        return playlistRepository.getArtistsHasOneAlbumbyPeriod(from.getYear(), to.getYear());
    }
}
