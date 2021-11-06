package ch.fenix.timemanagementfrontend.controller.edit;

import ch.fenix.timemanagementfrontend.data.Scenes;
import ch.fenix.timemanagementfrontend.models.User;
import ch.fenix.timemanagementfrontend.service.jfx.DataTransferService;
import ch.fenix.timemanagementfrontend.service.jfx.SceneService;
import ch.fenix.timemanagementfrontend.service.TokenService;
import ch.fenix.timemanagementfrontend.service.api.UserService;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

public class EditUserController implements Initializable {
    public PasswordField password;
    public TextField username;
    public Label error;
    public Button btnDelete;
    private User user;

    @Autowired
    private SceneService sceneService;
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private DataTransferService dataTransferService;

    public void back() {
        sceneService.loadScene(Scenes.VIEW_ENTRIES);
    }

    public void update() {
        String username = this.username.getText();
        String password = this.password.getText();
        if (username.length() != 0) {
            if (this.username.isEditable()) {
                if (userService.editUser(username,password, user.getId()) != null) {
                    sceneService.loadScene(Scenes.VIEW_USERS);
                } else {
                    error.setVisible(true);
                }
            }else {
                if (userService.editUser(password) != null) {
                    tokenService.clearToken();
                    sceneService.loadScene(Scenes.LOGIN);
                } else {
                    error.setVisible(true);
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user = (User) dataTransferService.getObject();
        if (user == null) {
            user = userService.getUser();
            btnDelete.setVisible(false);
            username.setEditable(false);
        }
        username.setText(user.getUsername());
    }

    public void delete() {
       userService.deleteUser(user.getId());
       sceneService.loadScene(Scenes.VIEW_USERS);
    }
}
