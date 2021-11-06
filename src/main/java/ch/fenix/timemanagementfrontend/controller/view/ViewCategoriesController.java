package ch.fenix.timemanagementfrontend.controller.view;

import ch.fenix.timemanagementfrontend.data.Interval;
import ch.fenix.timemanagementfrontend.data.Scenes;
import ch.fenix.timemanagementfrontend.models.Category;
import ch.fenix.timemanagementfrontend.service.api.CategoryService;
import ch.fenix.timemanagementfrontend.service.jfx.DataTransferService;
import ch.fenix.timemanagementfrontend.service.jfx.SceneService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewCategoriesController implements Initializable {
    public TableView<Category> table;
    public TableColumn<Category, String> categoryId;
    public TableColumn<Category, String> name;
    public TableColumn<Category, Float> value;
    public TableColumn<Category, Interval> interval;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private DataTransferService dataTransferService;
    @Autowired
    private SceneService sceneService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Category> data = FXCollections.observableArrayList(categoryService.getCategories());
        categoryId.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        value.setCellValueFactory(new PropertyValueFactory<>("min"));
        interval.setCellValueFactory(new PropertyValueFactory<>("interval"));
        table.setItems(data);
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                dataTransferService.setObject(newSelection);
                sceneService.loadScene(Scenes.EDIT_CATEGORY);
            }
        });
    }

    public void create() {
        sceneService.loadScene(Scenes.CREATE_CATEGORY);
    }

    public void back() {
        sceneService.loadScene(Scenes.VIEW_ENTRIES);
    }
}
