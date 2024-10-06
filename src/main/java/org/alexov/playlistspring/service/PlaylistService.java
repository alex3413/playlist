package org.alexov.playlistspring.service;

import lombok.RequiredArgsConstructor;
import org.alexov.playlistspring.model.dictionary.Album;
import org.alexov.playlistspring.model.dictionary.Artist;
import org.alexov.playlistspring.model.dictionary.Award;
import org.alexov.playlistspring.model.dictionary.Genre;
import org.alexov.playlistspring.model.entity.Compostion;
import org.alexov.playlistspring.model.entity.CompostionAwards;
import org.alexov.playlistspring.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

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
}
