/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Billy
 */

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
    
    item[] getStoreInventory(int level)
    {
        
    }
    
    AresCharacter getNextEnemy(int level)
    {
    
    }
    
    PlayerCharacter getCurrentCharacter()
    {
        
    }
    
    void newCharacter(String name, int level, String bio, int health, int strength, int agility, int magic,item[] itemsHeld)
    {
        
    }
    
}
