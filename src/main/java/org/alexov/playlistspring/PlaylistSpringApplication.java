package org.alexov.playlistspring;

import javafx.application.Application;
import org.alexov.playlistspring.fx.PlaylistFxApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlaylistSpringApplication {

    public static void main(String[] args) {
        Application.launch(PlaylistFxApplication.class ,args);
    }

}
