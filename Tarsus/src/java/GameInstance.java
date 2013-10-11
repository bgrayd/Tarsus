/*******************************************************
* Class for game instances, will be associated with
* sessions through being the session data.
*******************************************************/

import java.io.PrintWriter;

public class GameInstance {
    PlayerCharacter playerChar;
    AresCharacter aresChar;
    stateEnum currentState;
    String accountName;
    
    GameInstance()
    {
        playerChar = null;
        aresChar = null;
        currentState = stateEnum.startState;
        accountName = null;
    }
    
    /****************************************************
     * state machine case switch function, called from 
     *      the servlet.
     * @param out 
     ***************************************************/
    void advanceGame(PrintWriter out)
    {
        switch(currentState)
        {
            case battle:
                //battle function
                break;
                
            case store:
                //store function
                break;
                
            case characterCreation:
                //character creation
                break;
                
            case idling:
                //idle state
                break;
                
            case betweenStates:
                //this state is for asking what to do next
                break;
                
            default:
                //this should go to a specified state
                break;
        }
    }
    
    
    /****************************************************
     * Generates an inventory for the store based on the
     *      player character's level
     * @param level the level of the player character
     * @return an array of new items for the store
     ***************************************************/
    Item[] getStoreInventory(int level)
    {
        
    }
    
    /****************************************************
     * Loads a new enemy from the database
     * @param level the players level
     ***************************************************/
    void getNextEnemy(int level)
    {
    
    }
    
    /****************************************************
     * Loads the players current character from the 
     *      database
     ***************************************************/
    void getCurrentCharacter()
    {
        
    }
    
    /****************************************************
     * Adds a newly created character to the database
     * @param name the character's name
     * @param level the level of the character
     * @param bio  a biography about the character
     * @param health the health of the character
     * @param strength the strength of the character
     * @param agility the agility of the character
     * @param magic  the magic of the character
     * @param itemsHeld the items held by the character
     ***************************************************/
    void newCharacter(String name, int level, String bio, int health, int strength, int agility, int magic,Item[] itemsHeld)
    {
        
    }
    
}
