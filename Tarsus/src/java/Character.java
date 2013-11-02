/*******************************************************
* Abstract character class
*   the requestAction function needs to be implemented 
*   by child classes
*******************************************************/
public abstract class Character {
    Item[] itemsHeld;
    int level, health, strength, agility, magic;
    Item[] equipedItems;
    String name, bio;
    Item weapon, armor;
    
    Character(String name, String bio, int level, int health, int strength, int agility, int magic, Item[] itemsHeld, Item weapon, Item armor)
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
        if (newItem.getType() == 1)
        {
            weapon = newItem;
        }
        else if (newItem.getType() == 2)
        {
            armor = newItem;
        }
        else
        {
            //print something about how you can't do this
        }
    }
    
    /***************************************************
     * Use a one time use item
     * @param itemToUse the item to be used
     **************************************************/
    void useItem(Item itemToUse)
    {
        if (itemToUse.getType() == 3)
        {
            //do whatever the item does
        }
        else
        {
            //cannot use that item
        }
    }
    
    void setHealth(int health)
    {
        this.health = health;
    }
    
    int getHealth()
    {
        return health;
    }
    
    void setStrength(int strength)
    {
        this.strength = strength;
    }
    
    int getStrength()
    {
        return strength;
    }
    
    void setAgility(int agility)
    {
        this.agility = agility;
    }
    
    int getAgility()
    {
        return agility;
    }
    
    void setMagic(int magic)
    {
        this.magic = magic;
    }
    
    int getMagic()
    {
        return magic;
    }
            
    void setLevel(int level)
    {
        this.level = level;
    }
    
    int getLevel()
    {
        return level;
    }
    
}
