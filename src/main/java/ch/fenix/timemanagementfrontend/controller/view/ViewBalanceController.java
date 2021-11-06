package ch.fenix.timemanagementfrontend.controller.view;

import ch.fenix.timemanagementfrontend.data.Scenes;
import ch.fenix.timemanagementfrontend.models.UserCategory;
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

public class ViewBalanceController implements Initializable {
    public TableColumn<UserCategory, String> category;
    public TableColumn<UserCategory, Float> balance;
    public TableView<UserCategory> table;
    @Autowired
    private SceneService sceneService;
    @Autowired
    private UserService userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<UserCategory> data = FXCollections.observableArrayList(userService.getUser().getUserCategories());
        category.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCategory().getName()));
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        table.setItems(data);
    }

    public void back() {
        sceneService.loadScene(Scenes.VIEW_ENTRIES);
    }
}
