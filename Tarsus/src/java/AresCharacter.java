/*******************************************************
* Ares AI character class for storing the enemy and 
*   contains the AI
*******************************************************/
public class AresCharacter extends Character{
    AresCharacter(String name, int level, String bio, int health, int strength, int agility, int magic,Item[] itemsHeld)
    {
        super(name, level, bio, health, strength, agility, magic, itemsHeld);
    }
    
    /*******************************************************
     * Ares AI decides its next move
     * @return player action
     *******************************************************/
    actionEnum requestAction()
    {
        return actionEnum.attack;
    }
    
}
