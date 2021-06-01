package com.alchemy.challange.disney.repositories;

import com.alchemy.challange.disney.models.CharactersModel;
import com.alchemy.challange.disney.models.GenderModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CharactersRepository extends CrudRepository<CharactersModel,Long> {
    public abstract ArrayList<CharactersModel> findByName(String name);
}
