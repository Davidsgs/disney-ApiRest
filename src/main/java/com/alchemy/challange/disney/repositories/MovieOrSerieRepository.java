package com.alchemy.challange.disney.repositories;

import com.alchemy.challange.disney.models.MovieOrSerieModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovieOrSerieRepository extends CrudRepository<MovieOrSerieModel,Long> {
    public abstract ArrayList<MovieOrSerieModel> findByTitle(String title);
    public abstract ArrayList<MovieOrSerieModel> findByGenre_Id(Long idGenre);
    public abstract ArrayList<MovieOrSerieModel> findByOrderByIdAsc();
    public abstract ArrayList<MovieOrSerieModel> findByOrderByIdDesc();
}
