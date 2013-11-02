/*******************************************************
* Ares AI character class for storing the enemy and 
*   contains the AI
*******************************************************/
public class AresCharacter extends Character{
    
    AresCharacter(String name, String bio, int level, int health, int strength, int agility, int magic, Item[] itemsHeld, Item weapon, Item armor)
    {
        super(name, bio, level, health, strength, agility, magic, itemsHeld, weapon, armor);
    }
    
    /*******************************************************
     * Ares AI decides its next move
     * @return player action
     *******************************************************/
    actionEnum requestAction()
    {
        return actionEnum.ATTACK;
    }
    
}
