package com.alchemy.challange.disney.adapters;

import com.alchemy.challange.disney.models.CharactersModel;
import lombok.Data;

@Data
public class CharacterAdapter {

    private String name;
    private String image;

    public CharacterAdapter(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public static CharacterAdapter adaptCharacter(CharactersModel character){
        return new CharacterAdapter(character.getName(),character.getImage());
    }
}
