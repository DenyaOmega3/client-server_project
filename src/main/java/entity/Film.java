package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    private int id;
    private String title;
    private String description;
    private int runtime;

    public Film(String title, String description, int runtime) {
        this.title = title;
        this.description = description;
        this.runtime = runtime;
    }
}
