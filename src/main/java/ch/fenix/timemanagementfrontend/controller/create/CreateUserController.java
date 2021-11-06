package ch.fenix.timemanagementfrontend.controller.create;

import ch.fenix.timemanagementfrontend.data.Scenes;
import ch.fenix.timemanagementfrontend.models.User;
import ch.fenix.timemanagementfrontend.service.api.RoleService;
import ch.fenix.timemanagementfrontend.service.api.UserService;
import ch.fenix.timemanagementfrontend.service.jfx.SceneService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {
    public Label error;
    public TextField username;
    public PasswordField password;
    public ComboBox<String> role;

    @Autowired
    private UserService userService;
    @Autowired
    private SceneService sceneService;
    @Autowired
    private RoleService roleService;

    public void create() {
        if (username.getLength() != 0 && password.getLength() != 0) {
            User user = userService.createUser(username.getText(), password.getText());
            if (user != null) {
                if (role.getSelectionModel().getSelectedItem() != null) {
                    roleService.setRoleToUser(user.getId(), 1);
                }
                sceneService.loadScene(Scenes.VIEW_USERS);
            }
        }
        error.setVisible(true);
    }

    public void back() {
        sceneService.loadScene(Scenes.VIEW_USERS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> data = FXCollections.observableArrayList();
        data.add("Admin");
        this.role.setItems(data);
    }
}
