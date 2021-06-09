package com.alchemy.challange.disney.services;

import com.alchemy.challange.disney.adapters.CharacterAdapter;
import com.alchemy.challange.disney.models.CharactersModel;
import com.alchemy.challange.disney.models.MovieOrSerieModel;
import com.alchemy.challange.disney.repositories.CharactersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class CharactersService {
    @Autowired
    CharactersRepository charactersRepository;

    public ArrayList<CharacterAdapter> getCharacters(){
        var charactersModel = (ArrayList<CharactersModel>) charactersRepository.findAll();
        return (ArrayList<CharacterAdapter>) charactersModel.stream().map(CharacterAdapter::adaptCharacter).collect(Collectors.toList());
    }

    public CharactersModel createCharacter(CharactersModel character){
        if(character == null){
            throw new NullPointerException("The character can't be null");
        }else if(character.getId() != null){
            throw new IllegalArgumentException("The Character already exist. Use PUT to update it.");
        }else{
            return charactersRepository.save(character);
        }

    }

    @Transactional
    public CharactersModel updateCharacter(CharactersModel character){
        var auxChar = findById(character.getId());
        if(character.getImage() != null){ auxChar.setImage(character.getImage());}
        if(character.getName() != null) {auxChar.setName(character.getName());}
        if(character.getWeight() != 0) {auxChar.setWeight(character.getWeight());}
        if(character.getHistory() != null) {auxChar.setHistory(character.getHistory());}
        if(character.getAge() != 0){auxChar.setAge(character.getAge());}
        if(!character.getAssociatedMovies().isEmpty()){auxChar.setAssociatedMovies(character.getAssociatedMovies());}
        return auxChar;
    }

    public CharactersModel findById(Long id) {
        return charactersRepository.findById(id).orElseThrow(
                () -> new NullPointerException("There is no character with the id : " + id));
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

    public CharactersModel findByName(String name){
        return charactersRepository.findByName(name).orElseThrow(
                () -> new IllegalStateException("There is no character with the name : " + name)
        );
    }

    public ArrayList<CharactersModel> filterByAge(Integer age){
        return charactersRepository.findByAge(age);
    }

    public ArrayList<CharactersModel> filterByWeight(Double weight){ return charactersRepository.findByWeight(weight); }

    /**
    public ArrayList<CharactersModel> findByMovie(Integer idMovie){
        return charactersRepository.findByMovieId(idMovie);
    }
    **/

    public ArrayList<CharactersModel> filterByMovie(Long idMovie){
        return charactersRepository.findByAssociatedMoviesId(idMovie);
    }
}
