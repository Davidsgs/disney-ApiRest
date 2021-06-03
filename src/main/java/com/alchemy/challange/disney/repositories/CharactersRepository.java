package com.alchemy.challange.disney.repositories;

import com.alchemy.challange.disney.models.CharactersModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface CharactersRepository extends CrudRepository<CharactersModel,Long> {
    public abstract ArrayList<CharactersModel> findByName(String name);
    public abstract ArrayList<CharactersModel> findByAge(Integer age);
    @Query(value = "SELECT * FROM characters c " +
            "INNER JOIN movies_characters mc ON mc.characters_id = c.id " +
            "INNER JOIN movie_or_serie m ON m.id = mc.movie_or_serie_id AND m.id = :idMovie",
            nativeQuery = true)
    public abstract ArrayList<CharactersModel> findByMovieId(@Param("idMovie") Integer idMovie);
}
