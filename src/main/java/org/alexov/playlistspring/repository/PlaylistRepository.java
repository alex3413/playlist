package org.alexov.playlistspring.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlaylistRepository {
    private final JdbcTemplate jdbcTemplate;

    public String getAllSongs() {
        return jdbcTemplate.queryForObject("select * from playlist", String.class);
    }
}
