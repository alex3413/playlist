package org.alexov.playlistspring;

import org.alexov.playlistspring.model.dto.TotalGenreDto;
import org.alexov.playlistspring.repository.PlaylistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class PlaylistSpringApplicationTests {
    @Autowired
    private PlaylistRepository repo;
    @Test
    void contextLoads() {
        List<TotalGenreDto> genreWithTotalSOngsByYear = repo.getGenreWithTotalSOngsByYear(LocalDate.of(1992, 1, 1));
        System.out.println(genreWithTotalSOngsByYear);
    }

}
