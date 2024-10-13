package org.alexov.playlistspring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.alexov.playlistspring.model.dictionary.Genre;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalGenreDto {
    private Genre genre;
    private Integer totalSongs;
}
