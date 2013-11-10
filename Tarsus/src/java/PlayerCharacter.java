
import javax.servlet.http.HttpServletRequest;

/*******************************************************
* Class for the player's character, child of Character
*   class.  requestAction function gets user input.
*******************************************************/
public class PlayerCharacter extends Character {
    
    PlayerCharacter(String name, String bio, int level, int health, int strength, int agility, int magic, Item[] itemsHeld, Item weapon, Item armor, int timesAttacked, int timesSwitchedToStrength, int timesSwitchedToAgility, int timesSwitchedToMagic)
    {
        super(name, bio, level, health, strength, agility, magic, itemsHeld, weapon, armor, timesAttacked, timesSwitchedToStrength, timesSwitchedToAgility, timesSwitchedToMagic);
    }
    
    /*******************************************************
     * interprets an action from a form given to the player
     * @return player action
     *******************************************************/
    actionEnum requestAction(HttpServletRequest request)
    {
        return actionEnum.ATTACK;
    }
    
}
