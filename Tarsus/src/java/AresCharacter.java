
import javax.servlet.http.HttpServletRequest;

/*******************************************************
* Ares AI character class for storing the enemy and 
*   contains the AI
*******************************************************/
public class AresCharacter extends Character {
    
    AresCharacter(String name, String bio, int level, int health, int strength, int agility, int magic, Item[] itemsHeld, Item weapon, Item armor, int timesAttacked, int timesSwitchedToStrength, int timesSwitchedToAgility, int timesSwitchedToMagic)
    {
        super(name, bio, level, health, strength, agility, magic, itemsHeld, weapon, armor, timesAttacked, timesSwitchedToStrength, timesSwitchedToAgility, timesSwitchedToMagic);
    }
    
    /*******************************************************
     * Ares AI decides its next move
     * @return player action
     *******************************************************/
    actionEnum requestAction(HttpServletRequest request)
    {
        //return actionEnum.ATTACK;
        return basicAI(request);
    }
    
    actionEnum basicAI(HttpServletRequest request)
    {
        return actionEnum.ATTACK;
    }
    
    actionEnum randomAI(HttpServletRequest request)
    {
        // Attacks half the time. The other half it randomly changes equiped gear
        
        double random = Math.random();
        if(random < 0.5)
        {
        return actionEnum.ATTACK;
        }
        else if (itemsHeld.length < 1)
        {
            
            return actionEnum.ATTACK;
        }
        else
        {
            int index = (int)(Math.random() * itemsHeld.length); 
            Item selectedItem = itemsHeld[index];
            // just attacks if an item already equpied is selected or has been used up
            if((selectedItem == weapon) || (selectedItem == armor) || (selectedItem == null))
            {
                return actionEnum.ATTACK;
            }
            else if(selectedItem.getType() == 1)
            {
                weapon = selectedItem;
                return actionEnum.EQUIP;
            }
            else if(selectedItem.getType() == 2)
            {
                armor = selectedItem;
                return actionEnum.EQUIP;
            }
            else if(selectedItem.getType() == 3)
            {
                health += selectedItem.getHeal();
                // making this part generic. Right nothing should happen
                // as all items are potions and should only affect health
                strength += selectedItem.getStrength();
                agility += selectedItem.getAgility();
                magic += selectedItem.getMagic();
                itemsHeld[index] = null; // I was being redundant with the selected Item
                return actionEnum.USE_ITEM; // instead of just using ItemsHeld[index]
            }
            else
            {
                // this is considered an error if this else statement is forced
                // to run
                return actionEnum.ATTACK;
            }
            
        }
    }
    
    actionEnum biasedRandomAI(HttpServletRequest request)
    {
        // Will eventually be made into the probability weighting AI
        return actionEnum.ATTACK;
    }
}
