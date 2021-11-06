package ch.fenix.timemanagementfrontend.events;

import javafx.stage.Stage;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

public class StageReadyEvent extends ApplicationEvent {
    @Getter
    private ApplicationContext context;

    public StageReadyEvent(Stage source, ApplicationContext context) {
        super(source);
        this.context = context;
    }

    public Stage getStage() {
        return ((Stage) getSource());
    }
}
