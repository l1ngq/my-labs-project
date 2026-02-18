package com.l1ngq.labs;

import com.l1ngq.labs.model.Character;
import com.l1ngq.labs.parser.CollectionParser;
import com.l1ngq.labs.parser.impl.HashSetCsvParser;
import com.l1ngq.labs.service.CharacterService;

import java.util.Collection;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        CollectionParser parser = new HashSetCsvParser("characters.csv");
        Collection<Character> characters = parser.parseAll();

        CharacterService characterService = new CharacterService();
        Set<String> uniqueOrigins = characterService.getUniqueOrigins(characters);

        System.out.println("origin/name");
        for (String origin : uniqueOrigins) {
            System.out.println(origin);
        }

        String outputFileName = "unique_origins.txt";
        characterService.saveOrigins(uniqueOrigins, outputFileName);
    }
}
