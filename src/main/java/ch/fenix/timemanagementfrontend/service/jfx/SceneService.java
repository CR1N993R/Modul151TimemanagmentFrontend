package ch.fenix.timemanagementfrontend.service.jfx;

import ch.fenix.timemanagementfrontend.data.Scenes;
import ch.fenix.timemanagementfrontend.events.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SceneService implements ApplicationListener<StageReadyEvent> {
    private static Stage stage;
    private static ApplicationContext context;

    public void loadScene(Scenes scene) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(scene.getPath()));
            loader.setControllerFactory(context::getBean);
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        SceneService.stage = event.getStage();
        SceneService.context = event.getContext();
        loadScene(Scenes.LOGIN);
    }
}
