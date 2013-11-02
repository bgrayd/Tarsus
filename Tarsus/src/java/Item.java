/*******************************************************
* Item class for holding items
*******************************************************/
class Item {
    String name;
    int itemId, type, upgradeCount, strength, agility, magic;
    
    //type: 0 = error, 1 = weapon, 2 = armor, 3 = consumable
    Item(String name, int itemId, int type, int upgradeCount, int strength, int agility, int magic)
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
