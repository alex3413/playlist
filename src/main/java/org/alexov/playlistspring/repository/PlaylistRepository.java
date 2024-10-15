package org.alexov.playlistspring.repository;

import lombok.RequiredArgsConstructor;
import org.alexov.playlistspring.model.dictionary.Album;
import org.alexov.playlistspring.model.dictionary.Artist;
import org.alexov.playlistspring.model.dictionary.Award;
import org.alexov.playlistspring.model.dictionary.Genre;
import org.alexov.playlistspring.model.dto.TotalGenreDto;
import org.alexov.playlistspring.model.entity.Compostion;
import org.alexov.playlistspring.model.entity.CompostionAwards;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlaylistRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Compostion> getAllSongs() {
        String query = """
                select * from song s join rl_song_rating r on s.id = r.song_id;
                """;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Compostion.class));
    }

    public List<Genre> getAllGenres() {
        return jdbcTemplate.query(" select * from dc_genre;", new BeanPropertyRowMapper<>(Genre.class));
    }

    public List<Artist> getAllArtists() {
        return jdbcTemplate.query(" select * from dc_artist;", new BeanPropertyRowMapper<>(Artist.class));
    }

    public List<Album> getAllAlbums() {
        return jdbcTemplate.query(" select * from dc_album;", new BeanPropertyRowMapper<>(Album.class));
    }

    public List<Award> getAllAwards() {
        return jdbcTemplate.query(" select * from dc_award;", new BeanPropertyRowMapper<>(Award.class));
    }

    public List<CompostionAwards> getAllCompostionAwards() {
        return jdbcTemplate.query("select * from song_award", new BeanPropertyRowMapper<>(CompostionAwards.class));
    }

    public List<Artist> getTopTenArtistByAwards(int topNumbers) {
        var query = """
                select da.* from artist_award aw
                join dc_artist da on da.id =aw.artist_id
                group by da.id order by count(aw.award_code) desc limit ?;
                """;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Artist.class), topNumbers);
    }

    public List<TotalGenreDto> getGenreWithTotalSOngsByYear(LocalDate year) {
        var query = """
                select g."name" as genre, count(rsr.song_id) as totalSongs from song s
                join rl_song_rating rsr on rsr.song_id = s.id
                join dc_genre g on g.code = rsr.song_genre_code
                where s.song_year = ?
                group by g."name" ;
                """;

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(TotalGenreDto.class), year);
    }

    public Album getTopAlbumByGenres(List<String> genres) {
        var query = """
                select a.* from rl_song_rating rsr
                join dc_album a on a.id = rsr.song_album_id
                where rsr.song_genre_code in (:genres)
                group by a.id  order by avg(rsr.song_rating) desc limit 1;
                """;
        return namedParameterJdbcTemplate.queryForObject(query, new MapSqlParameterSource("genres", genres),
                (rs, row) -> new Album(rs.getInt("id"), rs.getString("name")));
    }

    public List<Artist> getArtistsHasOneAlbumbyPeriod(LocalDate from, LocalDate to) {
        var query = """
                select distinct a.*  from song s
                join dc_artist a on a.id = s.artist_id
                join rl_song_rating rsr on rsr.song_id = s.id
                where rsr.song_album_id notnull
                and s.song_year between ? and ?
                order by a.id desc;
                """;
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Artist.class), from, to);
    }
}
