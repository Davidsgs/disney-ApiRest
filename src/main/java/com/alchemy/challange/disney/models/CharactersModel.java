package com.alchemy.challange.disney.models;

import com.alchemy.challange.disney.adapters.CharacterAdapter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "characters")
public class CharactersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    private String name;
    private String image;
    private int age;
    private int weight;
    private String history;

    public CharacterAdapter toAdapter(){
        var aux = new CharacterAdapter(this.getName(), this.getImage());
        return aux;
    }

    @ManyToMany(targetEntity = MovieOrSerieModel.class, mappedBy = "associatedCharacters", cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnoreProperties({"associatedCharacters"})
    @EqualsAndHashCode.Exclude
    private Set<MovieOrSerieModel> associatedMoviesOrSeries;
}
