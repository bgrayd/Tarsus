/*******************************************************
* Abstract character class
*   the requestAction function needs to be implemented 
*   by child classes
*******************************************************/
public abstract class Character {
    String name, bio;
    int level, health, strength, agility, magic;
    Item[] itemsHeld;
    
    Character(String name, String bio, int level, int health, int strength, int agility, int magic, Item[] itemsHeld)
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
        if (newItem.isEquipt() == true)
        {
            //say something about item is already equipt
        }
        else{ //newItem.isEquipt() == false
            if (newItem.getType() == 1)
            {
                for (int i = 0; i < itemsHeld.length; i++)
                {
                    if (itemsHeld[i].getType() == 1 && itemsHeld[i].isEquipt() == true)
                    {
                        itemsHeld[i].unequipItem();
                        break;
                    }
                }
                newItem.equipItem();
            }
            else if (newItem.getType() == 2)
            {
                for (int i = 0; i < itemsHeld.length; i++)
                {
                    if (itemsHeld[i].getType() == 2 && itemsHeld[i].isEquipt() == true)
                    {
                        itemsHeld[i].unequipItem();
                        break;
                    }
                }
                newItem.equipItem();
            }
            else
            {
                //print something about how you can't do this
            }
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
    
    void setLevel(int level)
    {
        this.level = level;
    }
    int getLevel()
    {
        return level;
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
    
}
