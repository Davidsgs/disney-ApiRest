package com.alchemy.challange.disney.controllers;

import com.alchemy.challange.disney.adapters.CharacterAdapter;
import com.alchemy.challange.disney.models.CharactersModel;
import com.alchemy.challange.disney.services.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/characters")
public class CharactersController {
    @Autowired()
    CharactersService charactersService;

    @GetMapping()
    public ArrayList<CharacterAdapter> getCharacters(){
        return charactersService.getCharacters();
    }

    @PostMapping()
    public CharactersModel saveCharacter(@RequestBody CharactersModel character){
        return this.charactersService.saveCharacter(character);
    }

    @GetMapping(path = "/{id}")
    public Optional<CharactersModel> findCharacterById(@PathVariable("id") Long id){
        return this.charactersService.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.charactersService.deleteById(id);
        if(ok){
            return "Se eliminó el usuario con id " + id;
        }else{
            return "No se pudo eliminar el usuario con id " + id;
        }
    }

    //punto 6

    @RequestMapping(method = RequestMethod.GET, params = "name")
    public ArrayList<CharactersModel> findCharacterByName(@RequestParam(value = "name") String name){
        return this.charactersService.findByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, params = "age")
    public ArrayList<CharactersModel> findCharacterByName(@RequestParam(value = "age") Integer age){
        return this.charactersService.findByAge(age);
    }

    @RequestMapping(method = RequestMethod.GET, params = "movies")
    public ArrayList<CharactersModel> findCharacterByMovie(@RequestParam(value = "movies") Integer movie){
        return this.charactersService.findByMovie(movie);
    }

}
