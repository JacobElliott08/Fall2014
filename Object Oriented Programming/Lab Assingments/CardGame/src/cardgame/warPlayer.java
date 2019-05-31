/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.Random;

/**
 *
 * @author jacobelliott
 */
public class warPlayer extends Player {

    int score;
    private Random random;
    public warPlayer(String name, ROLE role, int credit) 
    {
        super(name, role, credit);
        random = new Random();
    }
    
    public void displayDeck()
    {
        for(Card c : cardsInHand)
        {
            System.out.println(c.toString());
        }
    }
    
    public Card playCard()
    {
        Card card = getCard(0);
        cardsInHand.remove(0);
        return card;
    }
    
    public int score()
    {
        return getCardCount();
    }
    
}
