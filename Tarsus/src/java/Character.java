/*******************************************************
* Abstract character class
*   the requestAction function needs to be implemented 
*   by child classes
*******************************************************/
public abstract class Character {
    Item[] itemsHeld;
    int health, strength, agility, magic, level;
    Item[] equipedItems;
    String name, bio;
    
    Character(String name, int level, String bio, int health, int strength, int agility, int magic,Item[] itemsHeld)
    {
        
    }
    
    /****************************************************
     * Abstracted function for getting the action
     * @return an actionEnum for the next move
     ***************************************************/
    abstract actionEnum requestAction();
    
    
    /***************************************************
     * Equip an item to the character
     * @param newItem the item to be equipped
     **************************************************/
    void equipItem(Item newItem)
    {
        
    }
    
    /***************************************************
     * Use a one time use item
     * @param itemToUse the item to be used
     **************************************************/
    void useItem(Item itemToUse)
    {
        
    }
    
    void setHealth(int health)
    {
        
    }
    
    int getHealth()
    {
        return health;
    }
    
    void setStrength(int strength)
    {
        
    }
    
    int getStrength()
    {
        return strength;
    }
    
    void setAgility(int agility)
    {
        
    }
    
    int getAgility()
    {
        return agility;
    }
    
    void setMagic(int magic)
    {
        
    }
    
    int getMagic()
    {
        return magic;
    }
            
    void setLevel(int level)
    {
        
    }
    
    int getLevel()
    {
        return level;
    }
    
}
