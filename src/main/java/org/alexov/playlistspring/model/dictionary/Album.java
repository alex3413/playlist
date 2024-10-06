package org.alexov.playlistspring.model.dictionary;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Album extends AbstractDictionary {
    private int id;

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}
