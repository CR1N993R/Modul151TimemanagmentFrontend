package ch.fenix.timemanagementfrontend.data;

import lombok.Getter;

@Getter
public enum Scenes {
    CREATE_CATEGORY("fxml/CreateCategory.fxml"),
    CREATE_ENTRY("fxml/CreateEntry.fxml"),
    CREATE_USER("fxml/CreateUser.fxml"),
    EDIT_CATEGORY("fxml/EditCategory.fxml"),
    EDIT_ENTRY("fxml/EditEntry.fxml"),
    EDIT_USER("fxml/EditUser.fxml"),
    LOGIN("fxml/Login.fxml"),
    VIEW_CATEGORIES("fxml/ViewCategories.fxml"),
    VIEW_ENTRIES("fxml/ViewEntries.fxml"),
    VIEW_BALANCE("fxml/ViewBalance.fxml"),
    VIEW_USERS("fxml/ViewUsers.fxml");

    String path;

    Scenes(String s) {
        path = s;
    }
}
