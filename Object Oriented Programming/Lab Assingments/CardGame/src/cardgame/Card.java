/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import javax.swing.ImageIcon;

/**
 *
 * @author jacobelliott
 * 
 */
enum Suit {SPADES,HEARTS,CLUBS,DIAMONDS}

class Card {
    
    private static Object backImage;
    private Object image;
    private Suit suit;
    private int rank;
    private boolean faceUp;
    
    public Card(Suit suit, int rank)
    {
        this.suit = suit;
        this.rank = rank;
    }
    public int getRank()
    {
        return rank;
    }
    
    public void setRank(int rank)
    {
        this.rank = rank;
    }
    
    public Suit getSuit()
    {
        return this.suit;
    }
    
    public void setFaceup(boolean faceup)
    {
        this.faceUp = faceup;
    }
    public boolean getFaceup()
    {
        return this.faceUp;
    }
    public Object getImage()
    {
        return this.image;
    }
    public void setImage(Object image)
    {
        this.image = image;
    }
    public static void setBackImage(Object image)
    {
        backImage = image;
    }
    public static Object getBackImage()
    {
        return backImage;
    }
    public boolean equals(Object o)
    {
        if(o.getClass() == Card.class)
        {
            Card c = (Card)o;
            if(getRank() == c.getRank() && getSuit() != c.getSuit())
            {
                return true;
            }
        }
        return false;
    }

    public String toString()
    {
        return suit.name()+rank;
    }
}
