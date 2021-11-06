package ch.fenix.timemanagementfrontend.models;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private long id;
    private String username;
    private List<UserCategory> userCategories;
    private Role role;

    public boolean isAdmin() {
        return role != null && role.getName().equals("ROLE_ADMIN");
    }
}
