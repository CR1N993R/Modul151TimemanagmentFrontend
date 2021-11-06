package ch.fenix.timemanagementfrontend.controller.view;

import ch.fenix.timemanagementfrontend.data.Scenes;
import ch.fenix.timemanagementfrontend.models.Entry;
import ch.fenix.timemanagementfrontend.models.User;
import ch.fenix.timemanagementfrontend.service.*;
import ch.fenix.timemanagementfrontend.service.api.EntryService;
import ch.fenix.timemanagementfrontend.service.api.UserService;
import ch.fenix.timemanagementfrontend.service.jfx.DataTransferService;
import ch.fenix.timemanagementfrontend.service.jfx.SceneService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class ViewEntriesController implements Initializable {
    public TableColumn<Entry, Long> entryId;
    public TableColumn<Entry, String> checkIn;
    public TableColumn<Entry, String> checkOut;
    public TableColumn<Entry, String> category;
    public TableView<Entry> table;
    public Button manageUsers;
    public Button categoryMenu;
    private User user;
    @Autowired
    private SceneService sceneService;
    @Autowired
    private EntryService entryService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DataTransferService dataTransferService;

    public void create() {
        sceneService.loadScene(Scenes.CREATE_ENTRY);
    }

    public void logout() {
        tokenService.clearToken();
        sceneService.loadScene(Scenes.LOGIN);
    }

    public void categories() {
        sceneService.loadScene(Scenes.VIEW_CATEGORIES);
    }

    public void user() {
        if (user.isAdmin()){
            sceneService.loadScene(Scenes.VIEW_USERS);
        }else {
            sceneService.loadScene(Scenes.EDIT_USER);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user = userService.getUser();
        if (!user.isAdmin()) {
            manageUsers.setText("Manage User");
            categoryMenu.setVisible(false);
        }
        List<Entry> entries = entryService.getEntries();
        ObservableList<Entry> data = FXCollections.observableArrayList();
        data.addAll(entries);
        entryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        checkIn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCheckIn().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
        checkOut.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCheckOut().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
        category.setCellValueFactory(param -> {
            if (param.getValue().getCategory() == null) {
                return new SimpleStringProperty("Not Set");
            }
            return new SimpleStringProperty(param.getValue().getCategory().getName());
        });
        table.setItems(data);
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                dataTransferService.setObject(newSelection);
                sceneService.loadScene(Scenes.EDIT_ENTRY);
            }
        });
    }

    public void showBalances() {
        sceneService.loadScene(Scenes.VIEW_BALANCE);
    }
}
