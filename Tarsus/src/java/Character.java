/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Billy
 */
public abstract class Character {
    item[] itemsHeld;
    int health, strength, agility, magic, level;
    item[] equipedItems;
    String name, bio;
    
    Character(String name, int level, String bio, int health, int strength, int agility, int magic,item[] itemsHeld)
    {
        
    }
    
    abstract actionEnum requestAction();
    
    void equipItem(item newItem)
    {
        
    }
    
    void useItem(item itemToUse)
    {
        
    }
    
}
