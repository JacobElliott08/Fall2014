/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author jacobelliott
 */

class Deck {
    
    ArrayList<Card> cards;
    
    public int getCardCount()
    {
        return cards.size();
    }
    
    public Card getCard(int pos)
    {
        return cards.get(pos);
    }
    
    public void addCard(Card card)
    {
        cards.add(card);
    }
    
    public Card removeCard(int pos)
    {
        Card removed = cards.get(pos);
        cards.remove(pos);
        return removed;
    }
    
    
    public Deck()
    {
        cards = new ArrayList<Card>();
        
        for(int i = 1; i < 14; i ++)
        {
            cards.add(new Card(Suit.SPADES,i));
        }
         
        for(int i = 1; i < 14; i ++)
        {
            cards.add(new Card(Suit.HEARTS,i));
        }
        
        for(int i = 1; i < 14; i ++)
        {
            cards.add(new Card(Suit.DIAMONDS,i));
        }
         
        for(int i = 1; i < 14; i ++)
        {
            cards.add(new Card(Suit.CLUBS,i));
        }
        
    }
    ArrayList<Card> getCards() 
    {
        return cards;
       
    }

    void shuffle() {
       Collections.shuffle(cards);
    }
    
}
