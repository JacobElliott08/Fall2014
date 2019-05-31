/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assingmenttwo;

import java.util.*;

/**
 *
 * @author jacobelliott
 */
public class AssingmentTwo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashMap <String, ArrayList<Integer>> map = new HashMap<String,ArrayList<Integer>>();
        ArrayList<String> names = new ArrayList<String>();
        
        names.add("Jacob");
        names.add("Steve");
        names.add("Erin");
        names.add("Jenn");
        names.add("Bernice");
        names.add("Jennie");
        names.add("Jacob");
        names.add("Steve");
        names.add("Erin");
        names.add("Jenn");
        names.add("Bernice");
        names.add("Jennie");
        names.add("Jacob");
        names.add("Steve");
        names.add("Erin");
        names.add("Jenn");
        names.add("Bernice");
        names.add("Jennie");
        names.add("Jacob");
        names.add("Steve");
        names.add("Erin");
        names.add("Jenn");
        names.add("Bernice");
        names.add("Jennie");
        
        for(int i = 0; i < names.size(); i ++)
        {
            if(map.containsKey(names.get(i)))
            {
                map.get(names.get(i)).add(i);
            }
            
            
            if(!map.containsKey(names.get(i)))
            {
                ArrayList<Integer> newArray = new ArrayList<>();
                newArray.add(names.indexOf(names.get(i)));
                System.out.println(names.indexOf(names.get(i)));
                map.put(names.get(i), newArray);
            }
        }
        Iterator it = map.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pairs = (Map.Entry)it.next();
            System.out.println(pairs.getKey() + " = " + pairs.getValue());
        }   
        
    }
    
}
