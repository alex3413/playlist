package org.alexov.playlistspring.repository;

import lombok.RequiredArgsConstructor;
import org.alexov.playlistspring.model.dictionary.Album;
import org.alexov.playlistspring.model.dictionary.Artist;
import org.alexov.playlistspring.model.dictionary.Award;
import org.alexov.playlistspring.model.dictionary.Genre;
import org.alexov.playlistspring.model.dto.Song;
import org.alexov.playlistspring.model.entity.Compostion;
import org.alexov.playlistspring.model.entity.CompostionAwards;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlaylistRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Compostion> getAllSongs() {
        String query = """
        select * from song s join rl_song_rating r on s.id = r.song_id;
        """;
        return jdbcTemplate.query(query,new BeanPropertyRowMapper<>(Compostion.class));
    }

    public List<Genre> getAllGenres() {
        return jdbcTemplate.query(" select * from dc_genre;",new BeanPropertyRowMapper<>(Genre.class));
    }

    public List<Artist> getAllArtists() {
        return jdbcTemplate.query(" select * from dc_artist;",new BeanPropertyRowMapper<>(Artist.class));
    }

    public List<Album> getAllAlbums() {
          return jdbcTemplate.query(" select * from dc_album;",new BeanPropertyRowMapper<>(Album.class));
    }

    public List<Award> getAllAwards(){
           return jdbcTemplate.query(" select * from dc_award;",new BeanPropertyRowMapper<>(Award.class));
    }
    public List<CompostionAwards> getAllCompostionAwards(){
        return jdbcTemplate.query("select * from song_award", new BeanPropertyRowMapper<>(CompostionAwards.class));
    }
}
