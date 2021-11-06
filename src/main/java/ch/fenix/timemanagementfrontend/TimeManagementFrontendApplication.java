package ch.fenix.timemanagementfrontend;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimeManagementFrontendApplication {
    public static void main(String[] args) {
        Application.launch(JavaFXInitializer.class, args);
    }
}
