package ch.fenix.timemanagementfrontend.controller.create;

import ch.fenix.timemanagementfrontend.data.Scenes;
import ch.fenix.timemanagementfrontend.models.Category;
import ch.fenix.timemanagementfrontend.models.Entry;
import ch.fenix.timemanagementfrontend.service.api.CategoryService;
import ch.fenix.timemanagementfrontend.service.api.EntryService;
import ch.fenix.timemanagementfrontend.service.jfx.SceneService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class CreateEntryController implements Initializable {
    @Autowired
    private EntryService entryService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SceneService sceneService;

    public DatePicker startDate;
    public TextField startHour;
    public TextField startMinute;
    public DatePicker endDate;
    public TextField endHour;
    public TextField endMinute;
    public Label error;
    public ComboBox<String> categories;

    public void create() {
        try {
            LocalTime startTime = LocalTime.of(Integer.parseInt(startHour.getText()), Integer.parseInt(startMinute.getText()));
            LocalTime endTime = LocalTime.of(Integer.parseInt(endHour.getText()), Integer.parseInt(endMinute.getText()));
            LocalDateTime checkIn =LocalDateTime.of(startDate.getValue(), startTime);
            LocalDateTime checkOut =LocalDateTime.of(endDate.getValue(), endTime);
            String name = categories.getSelectionModel().getSelectedItem();
            Category category = null;
            for (Category cat : categoryService.getCategories()) {
                if (cat.getName().equals(name)) {
                    category = cat;
                    break;
                }
            }
            if (category != null) {
                Entry response = entryService.createEntry(checkIn,checkOut,category.getId());
                if (response != null) {
                    sceneService.loadScene(Scenes.VIEW_ENTRIES);
                }else {
                    error.setText("Something went Wrong");
                    error.setVisible(true);
                }
            }else {
                error.setText("Please Select Category");
                error.setVisible(true);
            }
        }catch (Exception e) {
            error.setText("Pleas enter time in the Correct format");
            error.setVisible(true);
        }
    }

    public void back() {
        sceneService.loadScene(Scenes.VIEW_ENTRIES);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Category> categories = categoryService.getCategories();
        ObservableList<String> categoryObservableList = FXCollections.observableArrayList();
        categories.forEach(category -> categoryObservableList.add(category.getName()));
        this.categories.setItems(categoryObservableList);
    }
}
