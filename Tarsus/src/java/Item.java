/*******************************************************
* Item class for holding items
*******************************************************/
class Item {
    String name;
    int itemId, type, upgradeCnt;
    Double strengthBonus, agilityBonus, magicBonus;
    
    Item(String name, int itemId, int type, Double strengthBonus, Double agilityBonus, Double magicBonus)
    {
        
    }
    
    void upgradeStrength(int bonus)
    {
        
    }
    
    void upgradeAgility(int bonus)
    {
        
    }
    
    void upgradeMagic(int bonus)
    {
        
    }
    
    Double getStrength()
    {
        return strengthBonus;
    }
    
    Double getAgility()
    {
        return agilityBonus;
    }
    
    Double getMagic()
    {
        return magicBonus;
    }
}
