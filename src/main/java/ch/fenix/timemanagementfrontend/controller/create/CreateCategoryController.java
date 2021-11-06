package ch.fenix.timemanagementfrontend.controller.create;

import ch.fenix.timemanagementfrontend.data.Interval;
import ch.fenix.timemanagementfrontend.data.Scenes;
import ch.fenix.timemanagementfrontend.models.Category;
import ch.fenix.timemanagementfrontend.service.api.CategoryService;
import ch.fenix.timemanagementfrontend.service.jfx.SceneService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateCategoryController implements Initializable {
    public Label error;
    public TextField name;
    public TextField value;
    public ComboBox<String> interval;
    @Autowired
    private SceneService sceneService;
    @Autowired
    private CategoryService categoryService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("YEARLY");
        list.add("MONTHLY");
        list.add("WEEKLY");
        list.add("DAILY");
        interval.setItems(list);
        value.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                value.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    public void create() {
        Interval interval = null;
        if (value.getText().length() == 0) {
            value.setText("0.0");
        }
        if(this.interval.getSelectionModel().getSelectedItem() != null) {
            interval = Interval.valueOf(this.interval.getSelectionModel().getSelectedItem());
        }
        if (name.getText().length() != 0) {
            Category category = new Category(name.getText(), Float.parseFloat(value.getText()), interval);
            categoryService.createCategory(category);
            sceneService.loadScene(Scenes.VIEW_CATEGORIES);
        } else {
            error.setVisible(true);
        }
    }

    public void back() {
        sceneService.loadScene(Scenes.VIEW_CATEGORIES);
    }
}
