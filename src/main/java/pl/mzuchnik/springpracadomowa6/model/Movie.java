package pl.mzuchnik.springpracadomowa6.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private String name;
    private int year;
    private String producer;

}
