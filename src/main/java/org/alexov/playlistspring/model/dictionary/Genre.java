package org.alexov.playlistspring.model.dictionary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Genre extends AbstractDictionary {
    private String code;
    private String name;

    @Override
    public String toString() {
        return name + " (" + code + ")";
    }
}
