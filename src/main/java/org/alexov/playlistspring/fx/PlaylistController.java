package org.alexov.playlistspring.fx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.converter.DefaultStringConverter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

@Component
@FxmlView("/playlist-view.fxml")
public class PlaylistController {
    @FXML
    public Button firstButton;
    public ListView<String> songList;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {

        ObservableList<String> songs = observableArrayList();
        songs.addAll(List.of("a", "b", "c", "d", "e", "f", "g", "h"));
        songList.setItems(songs);
        welcomeText.setText("Welcome to JavaFX Application!");
        firstButton.setText("Play");
        songListListener();
        songList.setCellFactory(p -> new TextFieldListCell<>(new DefaultStringConverter()));
    }

    public void editSongList(ListView.EditEvent<String> stringEditEvent) {
        stringEditEvent.getSource().getItems().forEach(System.out::println);
    }

    private void songListListener(){
        songList.getSelectionModel().selectedItemProperty().addListener((c, o, n) -> {System.out.println(o + " =Old; New= " + n);});
    }
}