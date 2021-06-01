package com.alchemy.challange.disney.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "gender")
public class GenderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    private String name;
    private String image;

    @OneToMany(targetEntity = MovieOrSerieModel.class)
    private Set<MovieOrSerieModel> associatedMoviesOrSeries;

}
