package ch.fenix.timemanagementfrontend.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Entry {
    private long id;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Category category;

}
