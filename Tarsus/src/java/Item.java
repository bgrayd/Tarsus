/*******************************************************
* Item class for holding items
*******************************************************/
class Item {
    String name;
    int itemId, type, upgradeCount, strength, agility, magic, health;
    int CONSTANT_upgradeMax = 3;
    int CONSTANT_potionHeal = 20; //scalar
    
    Item(String name, int itemId, int type, int upgradeCount, int strength, int agility, int magic, int health)
    {
        this.name = name;
        this.itemId = itemId;
        this.type = type; //type: 0 = error, 1 = weapon, 2 = armor, 3 = consumable
        this.upgradeCount = upgradeCount;
        this.strength = strength;
        this.agility = agility;
        this.magic = magic;
        this.health = health;
    }

    String getName()
    {
        return name;
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
    int getHeal()
    {
        return CONSTANT_potionHeal * health;
    }
    
}
