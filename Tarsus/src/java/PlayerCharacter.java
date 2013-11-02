/*******************************************************
* Class for the player's character, child of Character
*   class.  requestAction function gets user input.
*******************************************************/
public class PlayerCharacter extends Character {
    
    PlayerCharacter(String name, String bio, int level, int health, int strength, int agility, int magic, Item[] itemsHeld)
    {
        super(name, bio, level, health, strength, agility, magic, itemsHeld);
    }
    
    /*******************************************************
     * interprets an action from a form given to the player
     * @return player action
     *******************************************************/
    actionEnum requestAction()
    {
        return actionEnum.ATTACK;
    }
    
}
