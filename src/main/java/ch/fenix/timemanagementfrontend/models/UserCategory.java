package ch.fenix.timemanagementfrontend.models;

import lombok.Data;

@Data
public class UserCategory {
    private long id;
    private float balance;
    private Category category;
}
