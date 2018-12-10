package com.mygdx.game.Chapter6.src.fiftytwopickup;

import java.util.ArrayList;

import com.mygdx.game.Chapter6.src.base_A.*;

/**
 * Created by robertoguazon on 05/08/2016.
 */
public class Pile extends BaseActor {

    private ArrayList<Card> list;

    public Pile() {
        super();
        list = new ArrayList<>();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void addCard(Card c) {
        list.add(c);
    }

    public Card getTopCard() {
        if (list.isEmpty()) return null;
        else {
            return list.get(list.size()-1);
        }
    }

    public String getRank() {
        return getTopCard().getRank();
    }

    public String getSuit() {
        return getTopCard().getSuit();
    }

    public int getRankIndex() {
        return getTopCard().getRankIndex();
    }



}
