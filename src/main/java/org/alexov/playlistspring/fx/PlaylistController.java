package org.alexov.playlistspring.fx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.alexov.playlistspring.model.dictionary.*;
import org.alexov.playlistspring.model.dto.Song;
import org.alexov.playlistspring.repository.PlaylistRepository;
import org.controlsfx.control.spreadsheet.StringConverterWithFormat;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static javafx.collections.FXCollections.observableArrayList;

@Component
@FxmlView("/playlist-view.fxml")
@RequiredArgsConstructor
public class PlaylistController {
    private final FxWeaver fxWeaver;
    private final PlaylistStates playlistStates;

    @FXML
    private HBox textFieldSongBox;
    @FXML
    private HBox listFieldSongBox;
    @FXML
    private ListView<Award> awardListView;
    @FXML
    private ComboBox<Artist> artistListView;
    @FXML
    private ComboBox<Genre> genreListView;
    @FXML
    private ComboBox<Album> albumListView;
    @FXML
    private TableView<Song> songTable;
    @FXML
    private ListView<Song> songList;
    @FXML
    private TextField songNameText;
    @FXML
    private Label welcomeText;


    public void initialize() {
        songTable.setItems(playlistStates.getSongs());
        songTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        awardListView.setItems(playlistStates.getAwards());
        awardListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        artistListView.setItems(playlistStates.getArtists());
        genreListView.setItems(playlistStates.getGenres());
        albumListView.setItems(playlistStates.getAlbums());

        awardListView.setCellFactory(p -> getAwardTextFieldListCell());
        artistListView.setCellFactory(p -> getArtistTextFieldListCell());
        genreListView.setCellFactory(p -> getGenreTextFieldListCell());
        albumListView.setCellFactory(p -> getAbstrsctTextFieldListCell(Album.class));

        configureColumn(0, "songName");
        configureColumn(1, "songArtist");
        configureColumn(2, "songYear");
        configureColumn(3, "songRating");
        configureColumn(4, "songAlbum");
        configureColumn(5, "songGenre");
        configureColumn(6, "songAwards");

        songListListener();
        songTableListener();


        songList.setCellFactory(p -> getSongTextFieldListCell());
        welcomeText.setText("Добро пожаловать в приложение \"плейлист\" " );

    }


    public void onEditNameCell(TableColumn.CellEditEvent<Song, String> t) {
        playlistStates.getSongs().get(t.getTablePosition().getRow()).setSongName(t.getNewValue());
    }

    public void onEditArtistCell(TableColumn.CellEditEvent<Song, String> t) {
        playlistStates.getSongs().get(t.getTablePosition().getRow());

    }

    public void onAddSelectedSong() {
        var nodeTextFieldMap = textFieldSongBox.getChildren().stream()
                .collect(Collectors.toMap(Node::getId, e -> ((TextField) e).getText()));
        var nodeListFieldMap = listFieldSongBox.getChildren().stream()
                .collect(Collectors.toMap(Node::getId, e -> {
                    if (e instanceof ComboBox<?> box) {
                        return box.getValue() == null ? null : box.getValue();
                    }
                    return null;
                }));


        var awards = awardListView.getSelectionModel().getSelectedItems();

        var songBuilder = Song.builder()
                .songName(nodeTextFieldMap.get("songName"))
                .songYear(Integer.valueOf(nodeTextFieldMap.get("songRating")))
                .songRating(Integer.valueOf(nodeTextFieldMap.get("songYear")))
                .songArtist((Artist) nodeListFieldMap.get("songArtist"))
                .songGenre((Genre) nodeListFieldMap.get("songGenre"))
                .songAlbum((Album) nodeListFieldMap.get("songAlbum"))
                .songAwards(awards).build();

        playlistStates.getSongs().add(songBuilder);

    }

    public void onEditSongList(ListView.EditEvent<Song> stringEditEvent) {
        var newValue = stringEditEvent.getNewValue();
        var items = songList.getItems();
        if (items.contains(newValue)) {
            welcomeText.setText("Песня с наименованием " + newValue + " уже есть в списке");
            return;
        }
        items.set(stringEditEvent.getIndex(), newValue);
    }


    private void songListListener() {
        songList.getSelectionModel().selectedItemProperty().addListener((c, o, n) -> {
        });
    }

    private void songTableListener() {
        songTable.getSelectionModel().selectedItemProperty().addListener((c, o, n) -> {
            welcomeText.setText("Вы выбрали песню: " + n.getSongName());
        });
    }

    private void configureColumn(int index, String property) {
        TableColumn<Song, String> songNameColumn = (TableColumn<Song, String>) songTable.getColumns().get(index);
        songNameColumn.setCellValueFactory(new PropertyValueFactory<>(property));
        songNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void onOpenDictionary(ActionEvent actionEvent) {
        fxWeaver.loadController(DictionaryController.class).show();
    }

    public void onShowReportsView(ActionEvent actionEvent) {
        fxWeaver.loadController(ReportController.class).show();
    }

    private static TextFieldListCell<Award> getAwardTextFieldListCell() {
        return new TextFieldListCell<>(new StringConverterWithFormat<>() {
            @Override
            public String toString(Award award) {
                return award.getName();
            }

            @Override
            public Award fromString(String s) {
                throw new UnsupportedOperationException();
            }
        });
    }
    private static TextFieldListCell<Artist> getArtistTextFieldListCell() {
        return new TextFieldListCell<>(new StringConverterWithFormat<>() {
            @Override
            public String toString(Artist award) {
                return award.getName();
            }

            @Override
            public Artist fromString(String s) {
                throw new UnsupportedOperationException();
            }
        });
    }
    private static TextFieldListCell<Genre> getGenreTextFieldListCell() {
        return new TextFieldListCell<>(new StringConverterWithFormat<>() {
            @Override
            public String toString(Genre award) {
                return award.getName();
            }

            @Override
            public Genre fromString(String s) {
                throw new UnsupportedOperationException();
            }
        });
    }
    private static <T extends AbstractDictionary> TextFieldListCell<T> getAbstrsctTextFieldListCell(Class<T> clazz) {
        return new TextFieldListCell<>(new StringConverterWithFormat<T>() {
            @Override
            public String toString(T award) {
                return clazz.cast(award).getName();
            }

            @Override
            public T fromString(String s) {
                throw new UnsupportedOperationException();
            }
        });
    }
    private static TextFieldListCell<Album> getAlbumTextFieldListCell() {
        return new TextFieldListCell<>(new StringConverterWithFormat<>() {
            @Override
            public String toString(Album award) {
                return award.getName();
            }

            @Override
            public Album fromString(String s) {
                throw new UnsupportedOperationException();
            }
        });
    }


    private static TextFieldListCell<Song> getSongTextFieldListCell() {
        return new TextFieldListCell<>(new StringConverter<>() {
            @Override
            public String toString(Song song) {
                return String.format("Название: %s. Исполнитель %s. Награды: %s", song.getSongName(), song.getSongArtist(), song.getSongAwards());
            }

            @Override
            public Song fromString(String s) {
                 throw new UnsupportedOperationException();
            }
        });
    }

    public void onDeleteTableItem(ActionEvent actionEvent) {
        var selectedIndices = songTable.getSelectionModel().getSelectedIndices();
        selectedIndices.forEach(i -> songTable.getItems().remove(i.intValue()));
    }

    public void onAddSongPlaylist(ActionEvent actionEvent) {
        var selectedSongs = songTable.getSelectionModel().getSelectedItems();
        songList.getItems().addAll(selectedSongs);
    }

}