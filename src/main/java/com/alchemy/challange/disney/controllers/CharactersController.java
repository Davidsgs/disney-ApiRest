package com.alchemy.challange.disney.controllers;

import com.alchemy.challange.disney.adapters.CharacterAdapter;
import com.alchemy.challange.disney.models.CharactersModel;
import com.alchemy.challange.disney.services.CharactersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
    public CharactersModel createCharacter(@RequestBody CharactersModel character){
        return this.charactersService.createCharacter(character);
    }

    @PutMapping()
    public CharactersModel updateCharacter(@RequestBody CharactersModel character){
        return charactersService.updateCharacter(character);
    }

    @GetMapping(path = "/{id}")
    public CharactersModel findCharacterById(@PathVariable("id") Long id){
        return this.charactersService.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id){
        boolean ok = this.charactersService.deleteById(id);
        if(ok){
            return "Has been deleted the character with id " + id;
        }else{
            return "Hasn't been deleted the character with id " + id;
        }
    }

    //punto 6

    @RequestMapping(method = RequestMethod.GET, params = "name")
    public CharactersModel findCharacterByName(@RequestParam(value = "name") String name){
        return this.charactersService.findByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, params = "age")
    public ArrayList<CharactersModel> findCharacterByName(@RequestParam(value = "age") Integer age){
        return this.charactersService.filterByAge(age);
    }

    @RequestMapping(method = RequestMethod.GET, params = "movies")
    public ArrayList<CharactersModel> findCharacterByMovie(@RequestParam(value = "movies") Long idMovie){
        return this.charactersService.filterByMovie(idMovie);
    }

    @RequestMapping(method = RequestMethod.GET, params = "weight")
    public ArrayList<CharactersModel> findByWeight(@RequestParam(value = "weight") Double weight){
        return charactersService.filterByWeight(weight);
    }

}
