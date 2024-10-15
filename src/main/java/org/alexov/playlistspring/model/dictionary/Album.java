package org.alexov.playlistspring.model.dictionary;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Album extends AbstractDictionary {
    private int id;

    public Album(int id, String name) {
        this.id = id;
        setName(name);
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}
