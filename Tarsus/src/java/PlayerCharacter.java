
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
    @Override
    actionEnum requestAction(HttpServletRequest request)
    {
        String value = null, valueAttack=request.getParameter("attack"), valueUse=request.getParameter("use"), valueOK=request.getParameter("OK"), itemName = null;
        if(valueAttack!=null)
            value = valueAttack;
        if(valueUse!=null)
        {
            value=valueUse;
            itemName = request.getParameter("itemSelected");
        }
        if(valueOK!=null)
            value=valueOK;
        
        if("Attack".equals(value))
        {
            timesAttacked++;
            return actionEnum.ATTACK;
        }
        if("Use item".equals(value))
        {
            Item item;
            for(int i=0; i < itemsHeld.length;i++)
            {
                if((itemsHeld[i].name).equals(itemName))
                {
                    item = itemsHeld[i];
                    if(item.getType()==1)
                    {
                        weapon=itemsHeld[i];
                        if(weapon.getStrength()!=0)
                            timesSwitchedToStrength++;
                        if(weapon.getAgility()!=0)
                            timesSwitchedToAgility++;
                        if(weapon.getMagic()!=0)
                            timesSwitchedToMagic++;
                    }
                    else if(item.getType()==2)
                        armor = itemsHeld[i];
                    else if(item.getType()==3)
                    {
                        health+=item.getHeal();
                        itemsHeld[i]=null;
                        return actionEnum.USE_ITEM;
                    }
                    return actionEnum.EQUIP;
                }
            }
            return actionEnum.EQUIP;
        }
        
        return actionEnum.ATTACK;
    }
    
}
