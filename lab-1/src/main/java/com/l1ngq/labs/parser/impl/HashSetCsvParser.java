package com.l1ngq.labs.parser.impl;

import com.l1ngq.labs.model.Character;
import com.l1ngq.labs.parser.CollectionParser;
import com.l1ngq.labs.parser.LineCsvParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashSetCsvParser extends LineCsvParser implements CollectionParser {

    private final String fileName;

    public HashSetCsvParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Collection<Character> parseAll() {
        Set<Character> collection = new HashSet<>();

        try (
                InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(fileName);
                InputStreamReader inputStreamReader = new InputStreamReader(Objects.requireNonNull(resourceAsStream));
                BufferedReader br = new BufferedReader(inputStreamReader)
        ) {
            String line = br.readLine();
            while (line != null) {
                Character character = parseLine(line);
                if (character != null) {
                    collection.add(character);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Возникла проблема при работе с файлом: " + fileName, e);
        }

        return collection;
    }

    @Override
    public Character parse() {
        return parseAll().iterator().next();
    }
}
