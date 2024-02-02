package com.example.pokeamum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    String name;
    List<Card> cards = new ArrayList<>();
    Card playedCard;
    int Score;
}
