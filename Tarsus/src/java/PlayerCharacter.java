/**
 *
 * @author Billy
 */
public class PlayerCharacter extends Character{
    
    PlayerCharacter(String name, int level, String bio, int health, int strength, int agility, int magic,item[] itemsHeld)
    {
        super(name, level, bio, health, strength, agility, magic, itemsHeld);
    }
    
    /*******************************************************
     * interprets an action from a form given to the player
     * @return player action
     *******************************************************/
    actionEnum requestAction()
    {
        return actionEnum.attack;
    }
    
}
