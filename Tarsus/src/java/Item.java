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
    
    Item(String input)
    {
        //constructor that takes a string of the format name=id+strength-agility*magic_type
        //assumes health = 0
        int equals = input.indexOf("=");
        int plus = input.indexOf("+");
        int minus = input.indexOf("-");
        int star = input.indexOf("*");
        int underscore = input.indexOf("_");
        
        name = input.substring(0, equals);
        itemId = Integer.parseInt(input.substring(equals+1,plus));
        upgradeCount = 0;
        strength =  Integer.parseInt(input.substring(plus+1,minus));
        agility =  Integer.parseInt(input.substring(minus+1,star));
        magic =  Integer.parseInt(input.substring(star+1,underscore));
        type =  Integer.parseInt(input.substring(underscore+1));
    }

    void upgradeItem()
    {
        upgradeCount = upgradeCount + 1;
        
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
