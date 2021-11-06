package ch.fenix.timemanagementfrontend.controller.view;

import ch.fenix.timemanagementfrontend.data.Scenes;
import ch.fenix.timemanagementfrontend.models.User;
import ch.fenix.timemanagementfrontend.service.jfx.DataTransferService;
import ch.fenix.timemanagementfrontend.service.jfx.SceneService;
import ch.fenix.timemanagementfrontend.service.api.UserService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewUsersController implements Initializable {
    public TableView<User> table;
    public TableColumn<User, Long> id;
    public TableColumn<User, String> username;
    public TableColumn<User, String> role;
    @Autowired
    private SceneService sceneService;
    @Autowired
    private DataTransferService dataTransferService;
    @Autowired
    private UserService userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<User> data = FXCollections.observableArrayList(userService.getAllUsers());
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        role.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getRole() == null? "None": "Admin"));
        table.setItems(data);
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                dataTransferService.setObject(newSelection);
                sceneService.loadScene(Scenes.EDIT_USER);
            }
        });
    }

    public void back() {
        sceneService.loadScene(Scenes.VIEW_ENTRIES);
    }

    public void create() {
        sceneService.loadScene(Scenes.CREATE_USER);
    }
}
