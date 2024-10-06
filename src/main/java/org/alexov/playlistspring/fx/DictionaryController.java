package org.alexov.playlistspring.fx;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/playlist-dictionary.fxml")
public class DictionaryController {

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
}
