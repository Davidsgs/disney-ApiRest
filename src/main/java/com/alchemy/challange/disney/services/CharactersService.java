package com.alchemy.challange.disney.services;

import com.alchemy.challange.disney.adapters.CharacterAdapter;
import com.alchemy.challange.disney.models.CharactersModel;
import com.alchemy.challange.disney.repositories.CharactersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharactersService {
    @Autowired
    CharactersRepository charactersRepository;

    public ArrayList<CharacterAdapter> getCharacters(){
        var charactersModel = (ArrayList<CharactersModel>) charactersRepository.findAll();
        return (ArrayList<CharacterAdapter>) charactersModel.stream().map(x -> x.toAdapter()).collect(Collectors.toList());
    }

    public CharactersModel saveCharacter(CharactersModel character){
        return charactersRepository.save(character);
    }

    public Optional<CharactersModel> findById(Long id) {
        return charactersRepository.findById(id);
    }


    public boolean deleteById(Long id){
        try{
            charactersRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //Punto 6

    public ArrayList<CharactersModel> findByAge(Integer age){
        return charactersRepository.findByAge(age);
    }

    public ArrayList<CharactersModel> findByName(String name){
        return charactersRepository.findByName(name);
    }

    public ArrayList<CharactersModel> findByMovie(Integer idMovie){
        return charactersRepository.findByMovieId(idMovie);
    }

}
