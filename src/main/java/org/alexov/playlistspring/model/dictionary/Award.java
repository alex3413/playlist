package org.alexov.playlistspring.model.dictionary;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Award extends AbstractDictionary {
    private String code;
    private String name;

    @Override
    public String toString() {
        return name + " (" + code + ")";
    }
}
