package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.time.LocalDate;

@Data
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
