package com.l1ngq.labs.parser;

import com.l1ngq.labs.model.Character;

import java.util.Collection;


public interface CollectionParser extends Parser {

    Collection<Character> parseAll();
}
