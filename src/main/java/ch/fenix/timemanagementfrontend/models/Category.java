package ch.fenix.timemanagementfrontend.models;

import ch.fenix.timemanagementfrontend.data.Interval;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Category {
    private long id;
    private String name;
    private float min;
    private Interval interval;

    public Category(String name, float min, Interval interval) {
        this.name = name;
        this.min = min;
        this.interval = interval;
    }
}
