/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardgame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author jacobelliott
 */
public class War extends Game {

    private ArrayList<Pair<Card,Player>> cardsPlayed;
    private ArrayList<Player> warPlayers;
    
    public War(int numPlayers) {
        super(numPlayers);
        warPlayers = new ArrayList<>();
        for(int i = 0; i < numPlayers; i ++)
        {
            warPlayers.add(new warPlayer("Player"+(i+1), Player.ROLE.COMPUTER, i));
        }
    }

    
    public void play()
    {
        int round = 0;
        /*We distribute all the cards that we can to each individual player*/
        distributeCards(Math.floorDiv(getDeck().getCardCount(),getPlayerCount()));
        Scanner sc = new Scanner(System.in);        
        
        while(getPlayers().size() > 1)
        {
            /*The person with the highest card wins each round*/
            Pair<Card,Player> highest = null;
            
            /*The list of cards played each round*/
            cardsPlayed = new ArrayList<>();
            
            /*Each player plays the top card in their hand*/
            getPlayers().stream().forEach((p) -> {
                cardsPlayed.add(new Pair(p.playCard(),p));
            });
            
            /*Find the highest card*/
            highest = cardsPlayed.get(0);
            for(Pair<Card,Player> p : cardsPlayed)
            {
                System.out.println(p.getRight().getName()+" "+p.getLeft().toString());
                if(p.getLeft().getRank() > highest.getLeft().getRank())
                {
                    if(p.getLeft().getRank() == highest.getLeft().getRank())
                    {
                        if(p.getRight().getCardCount() > highest.getRight().getCardCount())
                        {
                            highest = p;
                        }
                        if(highest.getRight().getCardCount() > p.getRight().getCardCount())
                        {
                            highest = highest;
                        }
                    }
                    else if (p.getLeft().getRank() != highest.getLeft().getRank())
                    {
                        highest = p;
                    }
                    
                }                
            }
            
            /*Give the person who won the round those cards*/
            System.out.println(highest.getRight().getName()+" wins with the highest card.");
            for(Pair<Card,Player> p : cardsPlayed)
            {
                highest.getRight().receiveCard(p.getLeft());
            }
            
            //sc.nextLine();
            /*Display how many cards each person has left*/
            getPlayers().stream().forEach((p) -> {
                System.out.println(p.getName()+ " Has : "+p.getCardCount()+" cards left");
            });
            
            
            
            
                for(int i = 0; i < getPlayers().size(); i ++)
            {   
                if(getPlayers().get(i).getCardCount() == 0)
                {
                    getPlayers().remove(i);
                    i--;
                }
            }
            round++;
            //sc.nextLine();   
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("After "+round+" battles the war has ended.");
        System.out.println(getPlayers().get(0).getName()+" Wins");
    }
    
    
    public static void main(String [] args)
    {
        War w = new War(9);
        w.deck.shuffle();
        w.play();
    }
    
}
