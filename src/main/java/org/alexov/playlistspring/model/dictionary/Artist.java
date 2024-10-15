package org.alexov.playlistspring.model.dictionary;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Artist extends AbstractDictionary {
    private int id;
    private String name;

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}
