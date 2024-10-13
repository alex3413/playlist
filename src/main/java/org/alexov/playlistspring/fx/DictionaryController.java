package org.alexov.playlistspring.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.alexov.playlistspring.model.dictionary.AbstractDictionary;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/playlist-dictionary.fxml")
public class DictionaryController {

    public TableView<AbstractDictionary> DictionaryTableView;
    public ListView<AbstractDictionary> DicitonaryListView;
    public TextField idDictionaryField;
    public TextField nameDictionaryField;
    @FXML
    private SplitPane mainPane;
    private Stage stage;


    @FXML
    public void initialize() {
        this.stage = new Stage();
        stage.setScene(new Scene(mainPane));
    }

    public void show() {
        stage.show();
    }

    public void onSelectDictionary(MouseEvent mouseEvent) {

    }

    public void onAddDictItem(ActionEvent actionEvent) {

    }
}
