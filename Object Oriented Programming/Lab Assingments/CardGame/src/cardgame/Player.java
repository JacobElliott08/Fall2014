/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jacobelliott
 */
public class Player {
    ArrayList<Card> cardsInHand;
    ArrayList<Card> cardsPlayed;
    
    /**
     * public enum ROLE{HUMAN, COMPUTER};
Indicate the identify of a player
public Player (String name, ROLE role, int credit)
Create a player object with the provided argument values
Mutators & Accessors :
1. String getName()/setName(String) 2. ROLE getRole()/setRole(ROLE)
3. int getCredit()/setCredit(int);
A Player gains/loses credits if he wins/loses a game.
int getCardCount()
Return the number of card in hand
Card getCard(int pos)
Return the card at a given position
void receiveCard(Card card)
Add a card to hand*/
    
    private String name;
    private ROLE role;
    private int credit;
    private int score;
    private Random random;
    public enum ROLE{HUMAN, COMPUTER};

    public Player(String name, ROLE role, int credit)
    {
        cardsInHand = new ArrayList<Card>();
        cardsPlayed = new ArrayList<Card>();
        this.name = name;
        this.role = role;
        this.credit = credit;
        random = new Random();
        score = 0;
    }
    
    public String getName()
    {
        return name;
    }
    public void setName(String newName)
    {
        this.name = newName;
    }
    public ROLE getRole()
    {
        return role;
    }
    public void setRole(ROLE newRole)
    {
        this.role = newRole;
    }
    

    
    /**
     * A Player gains/loses credits if he wins/loses a game.
    int getCardCount()
    Return the number of card in hand
    Card getCard(int pos)
    Return the card at a given position
    void receiveCard(Card card)
    Add a card to hand*/
     
    
    public int getCardCount()
    {
        return cardsInHand.size();
    }
    public Card getCard(int pos)
    {
        return cardsInHand.get(pos);

    }
    public void receiveCard(Card card)
    {
        cardsInHand.add(card); 
    }
    
    
    /**void playCards(ArrayList<Card> cards)
￼
Play a number of cards from the hand at once (for some card games, a player may play more than one card at a time). When a card is played, it goes from the hand to another internally maintained array for 'played cards'.
int score()
A dummy method to be overrided by derived classes.
void clearHand()
Move all cards from hand to the played cards list
int getPlayedCardCount()
Return the number of played cards
Card collectPlayedCard(int pos)
￼Remove and return a played card at the given position*/
    

    
    
    public int score(){ return score;}
    
    public void clearHand()
    {
        for(Card c : cardsInHand)
        {
            cardsPlayed.add(c);
            cardsInHand.remove(c);
        }
    }
    
    public void returnToHand()
    {
        for(Card c : cardsPlayed)
        {
            cardsInHand.add(c);
            cardsPlayed.remove(c);
        }
    }
    
    public int getPlayedCardCount()
    {
        return cardsPlayed.size();
    }
    
    public Card collectPlayedCard(int pos)
    {
        Card removed = cardsInHand.get(pos);
        cardsInHand.remove(pos);
        return removed;
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
    
}
