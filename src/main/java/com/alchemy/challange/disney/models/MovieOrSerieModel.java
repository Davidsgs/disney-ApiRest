package com.alchemy.challange.disney.models;
import com.alchemy.challange.disney.adapters.MovieOrSerieAdapter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "movie_or_serie")
public class MovieOrSerieModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    private String image;
    private String title;
    private Calendar date;

    public MovieOrSerieAdapter toMovieAdapter(){
        return new MovieOrSerieAdapter(this.getImage(),this.getTitle(),this.getDate());
    }

    @ManyToMany(targetEntity = CharactersModel.class, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "movies_characters", joinColumns = @JoinColumn(name = "movie_or_serie_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "characters_id", referencedColumnName = "id"))
    @JsonIgnoreProperties({"associatedMovies"})
    @EqualsAndHashCode.Exclude
    private Set<CharactersModel> associatedCharacters = new HashSet<>();

    @ManyToOne(targetEntity = GenreModel.class, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "genre_id")
    @JsonIgnoreProperties("associatedMovies")
    private GenreModel genre;

}
