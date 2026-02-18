package com.l1ngq.labs.service;

import com.l1ngq.labs.model.Character;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class CharacterService {

    public Set<String> getUniqueOrigins(Collection<Character> characters) {
        Set<String> uniqueOrigins = new HashSet<>();
        for (Character character : characters) {
            String origin = character.getOriginName();
            if (origin != null) {
                uniqueOrigins.add(origin);
            }
        }
        return uniqueOrigins;
    }

    public void saveOrigins(Set<String> origins, String outputFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write("origin/name");
            writer.newLine();
            for (String origin : origins) {
                writer.write(origin);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи файла: " + outputFileName, e);
        }
    }

}
