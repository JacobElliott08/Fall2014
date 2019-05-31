/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;

/**
 *
 * @author jacobelliott
 * Public method & variables
descriptions
Game(int numPlayers)
Create a deck object and an array of numPlayers players. How you initialize each player's properties does not matter
Deck getDeck()
Return the Deck object
int getPlayerCount()
Return the number of players
Player getPlayer(int pos)
Return the player at the given position.
void distributeCards(int rounds)
Distribute the deck of cards to players for the specified number of rounds, one card to a player each time. if rounds == 0, distribute cards til the deck is empty.
void collectCards()
Collect cards from all players back to the deck
void play()
A dummy method to be overrided by subclasses.
 */
public class Game {
 
    ArrayList<Player> players;
    Deck deck;
    
    public Game(int numPlayers)
    {
        deck = new Deck();
        
        players = new ArrayList<Player>();
        
        for(int i = 0; i < numPlayers; i ++)
        {
            players.add(new Player("Player"+(i+1), Player.ROLE.COMPUTER, 20));
        }
        
    }
    
    public Deck getDeck()
    {
        return deck;
    }
    
    public int getPlayerCount()
    {
        return players.size();
    }
    
    public Player getPlayer(int pos)
    {
        return players.get(pos);

    }
    
    public ArrayList<Player> getPlayers()
    {
        return players;
    }
    
    
    public void distributeCards(int number)
    {
        for(int i = 0; i < getPlayerCount(); i ++)
        {
            for(int a = 0; a < number; a ++)
            {   
                getPlayer(i).receiveCard(getDeck().removeCard(0));
            }
        }
    }
    
    public void collectAllCards()
    {
        for(Player p : getPlayers())
        {
            p.clearHand();
            for(int i = 0; i < p.getPlayedCardCount(); i ++)
            {
                getDeck().addCard(p.getCard(i));
            }
        }
    }
    
    
    
    
}
