package org.alexov.playlistspring.fx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;
import org.alexov.playlistspring.model.dictionary.Album;
import org.alexov.playlistspring.model.dictionary.Artist;
import org.alexov.playlistspring.model.dictionary.Genre;
import org.alexov.playlistspring.model.dto.TotalGenreDto;
import org.alexov.playlistspring.service.PlaylistService;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static java.time.LocalDate.now;

@Component
@FxmlView("/playlist-report.fxml")
@RequiredArgsConstructor
public class ReportController {
    private final PlaylistService playlistService;
    private final PlaylistStates playlistStates;
    @FXML
    private ListView<Artist> topTenListView;
    @FXML
    private DatePicker genreYearField;
    @FXML
    private ListView<TotalGenreDto> totalGenreListView;
    @FXML
    private ListView<Album> topAlbumByGenreListView;
    @FXML
    private ListView<Genre> genreListView;
    @FXML
    private ListView<Artist> artistReleaseAlbumListView;
    @FXML
    private DatePicker albumPeriodFormField;
    @FXML
    private DatePicker albumPeriodToField;
    @FXML
    private VBox mainVbox;
    private Stage stage;


    @FXML
    public void initialize() {
        genreListView.setItems(playlistStates.getGenres());
        genreListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        this.stage = new Stage();
        stage.setScene(new Scene(mainVbox));
    }

    public void show() {
        stage.show();
    }
    public void getTopTenByAwards(ActionEvent actionEvent) {
        topTenListView.getItems().clear();
        topTenListView.getItems().addAll(playlistService.getTopTenArtistByAwards(10));
    }

    public void getTotalForGenreByYear(ActionEvent actionEvent) {
        totalGenreListView.getItems().clear();
        totalGenreListView.getItems().addAll(playlistService.getGenreWithTotalSOngsByYear(genreYearField.getValue()));
    }

    public void getTopAlbumByGenre(ActionEvent actionEvent) {
        topAlbumByGenreListView.getItems().clear();
        topAlbumByGenreListView.getItems().add(playlistService.getTopAlbumByGenres(genreListView.getSelectionModel().getSelectedItems()));
    }

    public void getArtistHaveAlbumByPeriod(ActionEvent actionEvent) {
        artistReleaseAlbumListView.getItems().clear();
        artistReleaseAlbumListView.getItems().addAll(playlistService.getArtistsHasOneAlbumbyPeriod(albumPeriodFormField.getValue(), albumPeriodToField.getValue()));
    }
}
