package ch.fenix.timemanagementfrontend.controller;

import ch.fenix.timemanagementfrontend.service.jfx.SceneService;
import ch.fenix.timemanagementfrontend.service.api.UserService;
import ch.fenix.timemanagementfrontend.data.Scenes;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;


public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private SceneService sceneService;

    public PasswordField password;
    public TextField username;
    public Label error;

    public void login() {
        error.setVisible(false);
        if (password.getText().length() != 0 && username.getText().length() != 0){
            if (userService.login(username.getText(), password.getText())) {
                sceneService.loadScene(Scenes.VIEW_ENTRIES);
            } else {
                error.setVisible(true);
            }
        }else {
            error.setVisible(true);
        }
    }
}
