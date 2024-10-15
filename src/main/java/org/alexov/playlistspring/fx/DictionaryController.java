package org.alexov.playlistspring.fx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;
import org.alexov.playlistspring.model.dictionary.AbstractDictionary;
import org.alexov.playlistspring.model.dictionary.Album;
import org.springframework.stereotype.Component;

import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

@Component
@FxmlView("/playlist-dictionary.fxml")
@RequiredArgsConstructor
public class DictionaryController {
    private final PlaylistStates playlistStates;

    public ListView<String> dicitonaryListView;
    public TextField idDictionaryField;
    public TextField nameDictionaryField;
    public ListView<AbstractDictionary> selectedDictListView;
    @FXML
    private SplitPane mainPane;
    private Stage stage;


    @FXML
    public void initialize() {
        ObservableList<String> dicts = observableArrayList();
        dicts.add("Альбом");
        dicts.add("Жанр");
        dicts.add("Исполнитель");
        dicts.add("Награды");
        dicitonaryListView.setItems(dicts);
        this.stage = new Stage();
        stage.setScene(new Scene(mainPane));
    }

    public void show() {
        stage.show();
    }

    public void onSelectDictionary(MouseEvent mouseEvent) {
        var a = dicitonaryListView.getSelectionModel().getSelectedIndices().get(0);
        ObservableList<AbstractDictionary> dict = observableArrayList();
        switch (a) {
            case 0 -> dict = observableArrayList(playlistStates.getAlbums());
            case 1 -> dict = observableArrayList(playlistStates.getGenres());
            case 2 -> dict = observableArrayList(playlistStates.getArtists());
            case 3 -> dict = observableArrayList(playlistStates.getAwards());
        }
        selectedDictListView.setItems(dict);

        System.out.println(a);
    }

    public void onAddDictItem(ActionEvent actionEvent) {

    }
}
