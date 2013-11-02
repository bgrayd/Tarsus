/*******************************************************
* Item class for holding items
*******************************************************/
class Item {
    String name;
    boolean isEquipt;
    int itemId, type, upgradeCount, strength, agility, magic;
    
    //type: 0 = error, 1 = weapon, 2 = armor, 3 = consumable
    Item(String name, int itemId, int type, int upgradeCount, boolean isEquipt, int strength, int agility, int magic)
    {
        
    }

    int getType()
    {
        return type;
    }
    int getUpgradeCount()
    {
        return upgradeCount;
    }
    void equipItem()
    {
        isEquipt = true;
    }
    void unequipItem()
    {
        isEquipt = false;
    }
    boolean isEquipt()
    {
        if (isEquipt == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    int getStrength()
    {
        return strength;
    }
    
    int getAgility()
    {
        return agility;
    }
    
    int getMagic()
    {
        return magic;
    }
    
}
