package org.alexov.playlistspring.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compostion {
    private int id;
    private String name;
    private Integer artistId;
    private LocalDate songYear;
    private Integer songRating;
    private String songGenreCode;
    private Integer songAlbumId;
}
