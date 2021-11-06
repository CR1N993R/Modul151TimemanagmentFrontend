package ch.fenix.timemanagementfrontend.configuration;

import ch.fenix.timemanagementfrontend.controller.*;
import ch.fenix.timemanagementfrontend.controller.create.CreateCategoryController;
import ch.fenix.timemanagementfrontend.controller.create.CreateEntryController;
import ch.fenix.timemanagementfrontend.controller.create.CreateUserController;
import ch.fenix.timemanagementfrontend.controller.edit.EditCategoryController;
import ch.fenix.timemanagementfrontend.controller.edit.EditEntryController;
import ch.fenix.timemanagementfrontend.controller.edit.EditUserController;
import ch.fenix.timemanagementfrontend.controller.view.ViewBalanceController;
import ch.fenix.timemanagementfrontend.controller.view.ViewCategoriesController;
import ch.fenix.timemanagementfrontend.controller.view.ViewEntriesController;
import ch.fenix.timemanagementfrontend.controller.view.ViewUsersController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfig {
    @Bean
    @Scope("prototype")
    public ViewCategoriesController viewCategoriesController() {
        return new ViewCategoriesController();
    }

    @Bean
    @Scope("prototype")
    public CreateCategoryController createCategoryController() {
        return new CreateCategoryController();
    }

    @Bean
    @Scope("prototype")
    public CreateEntryController createEntryController() {
        return new CreateEntryController();
    }

    @Bean
    @Scope("prototype")
    public EditCategoryController editCategoryController() {
        return new EditCategoryController();
    }

    @Bean
    @Scope("prototype")
    public EditEntryController editEntryController() {
        return new EditEntryController();
    }

    @Bean
    @Scope("prototype")
    public EditUserController editUserController() {
        return new EditUserController();
    }

    @Bean
    @Scope("prototype")
    public LoginController loginController() {
        return new LoginController();
    }

    @Bean
    @Scope("prototype")
    public ViewEntriesController viewEntriesController() {
        return new ViewEntriesController();
    }

    @Bean
    @Scope("prototype")
    public ViewBalanceController viewBalanceController() {
        return new ViewBalanceController();
    }

    @Bean
    @Scope("prototype")
    public ViewUsersController viewUsersController() {
        return new ViewUsersController();
    }

    @Bean
    @Scope("prototype")
    public CreateUserController createUserController() {
        return new CreateUserController();
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}
