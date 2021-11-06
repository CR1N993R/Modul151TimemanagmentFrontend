package ch.fenix.timemanagementfrontend.controller.edit;

import ch.fenix.timemanagementfrontend.service.api.CategoryService;
import ch.fenix.timemanagementfrontend.service.jfx.DataTransferService;
import ch.fenix.timemanagementfrontend.service.jfx.SceneService;
import ch.fenix.timemanagementfrontend.data.Scenes;
import ch.fenix.timemanagementfrontend.models.Category;
import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCategoryController implements Initializable {
    @Autowired
    public DataTransferService dataTransferService;
    @Autowired
    public CategoryService categoryService;
    @Autowired
    public SceneService sceneService;

    private Category category;

    public void delete() {
        if (categoryService.deleteCategory(category.getId())) {
            sceneService.loadScene(Scenes.VIEW_CATEGORIES);
        }
    }

    public void back() {
        sceneService.loadScene(Scenes.VIEW_CATEGORIES);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        category = (Category) dataTransferService.getObject();
    }
}
