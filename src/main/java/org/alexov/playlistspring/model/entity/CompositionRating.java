package org.alexov.playlistspring.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompositionRating {
    private int compositionId;
    private int rating;
    private String genreCode;
    private String albumId;
}
