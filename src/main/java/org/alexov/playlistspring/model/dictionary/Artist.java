package org.alexov.playlistspring.model.dictionary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    private int id;
    private String name;

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}
