/*******************************************************
* Class for game instances, will be associated with
* sessions through being the session data.
*******************************************************/

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GameInstance {
    PlayerCharacter playerChar;
    AresCharacter aresChar;
    stateEnum currentState, startingState;
    String accountName;
    
    int constantPtsPerLevel = 5;
    
    GameInstance()
    {
        playerChar = null;
        aresChar = null;
        currentState = stateEnum.INIT;
        accountName = null;
    }
    
    /****************************************************
     * state machine case switch function, called from 
     *      the servlet.
     * @param out output PrintWriter
     * @param request the servlet request
     ***************************************************/
    void advanceGame(PrintWriter out, HttpServletRequest request)
    {
        stateEnum nextState = currentState;
        startingState = currentState;
        do
        {
            currentState = nextState;
            
            switch(currentState)
            {
                case INIT:
                    //first connection
                    nextState = initState(out, request);
                    break;

                case BATTLE:
                    //battle function
                    nextState = battleState(out, request);
                    break;

                case STORE:
                    //store function
                    nextState = storeState(out, request);
                    break;

                case REGISTERED_CHARACTER_CREATION:
                    //character creation
                    nextState = registeredCharacterCreationState(out, request);
                    break;

               case UNREGISTERED_CHARACTER_CREATION:
                    //character creation
                    nextState = unregisteredCharacterCreationState(out, request);
                    break;

                case IDLING:
                    //idle state
                    nextState = idling(out, request);
                    break;

                case DECISION:
                    //this state is for asking what to do next
                    nextState = decisionState(out, request);
                    break;

                case BLACKSMITH:
                    //upgrade items
                    nextState = blackSmithState(out, request);
                    break;
                    
                case LOGIN:
                    //login
                    nextState = loginState(out, request);
                    break;
                    
                case ACCOUNT_CREATION:
                    nextState = accountCreation(out, request);
                    break;

                default:
                    //this should go to a specified state
                    nextState = stateEnum.INIT;
                    initState(out, request);
                    break;
            }
        }while(currentState != nextState);
    }
    
    
    /****************************************************
     * Generates an inventory for the store based on the
     *      player character's level
     * @param level the level of the player character
     * @return an array of new items for the store
     ***************************************************/
    Item[] getStoreInventory(int level)
    {
        throw new UnsupportedOperationException("Not supported yet.");
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
     * @return did it work
     ***************************************************/
    Boolean newCharacter(String name, int level, String bio, int health, int strength, int agility, int magic,Item[] itemsHeld)
    {
        return false;
    }

    
    /****************************************************
     * The initial state
     * @param out the print writer
     * @param request the servlet request
     * @return the next state
     ***************************************************/
    stateEnum initState(PrintWriter out, HttpServletRequest request) {
        //can log in or unregistered user creation
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /****************************************************
     * The initial state
     * @param out the print writer
     * @param request the servlet request
     * @return the next state
     ***************************************************/
    private stateEnum storeState(PrintWriter out, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		
		// have store level as well as the items be static so that it is the same each time the player comes back to the 
		// store unless the player has increased in level
		static store_level = 1;
		const int STORE_SIZE = 20;
		static item[] item_array = new item[STORE_SIZE];
		
		// if level has changed create a new item inventory for the store
		// based on some hash function of the character's level
		if(playerChar.getLevel() != store_level)
		{
			store_level = playerChar.getLevel();
			for(int i = 0; i < STORE_SIZE; i++)
				{
				// need to place the parameters for how each item could be created
				item_array[i] = new Item(name = "", id = null, type = (i % 9), upgradeCount = 0, strength = , agility = , magic =  );
				}
		}
		
		
		// if item bought, add to inventory
		
    }

    /****************************************************
     * The initial state
     * @param out the print writer
     * @param request the servlet request
     * @return the next state
     ***************************************************/
    private stateEnum battleState(PrintWriter out, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /****************************************************
     * Create a registered user character
     * @param out the print writer
     * @param request the servlet request
     * @return the next state
     ***************************************************/
    stateEnum registeredCharacterCreationState(PrintWriter out, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /****************************************************
     * Create an unregistered user character
     * @param out the print writer
     * @param request the servlet request
     * @return the next state
     ***************************************************/
    stateEnum unregisteredCharacterCreationState(PrintWriter out, HttpServletRequest request) {
        String StartPage = "<html>\n" +
"	<head>\n" +
"	<!-- Call normalize.css -->\n" +
"	<link rel=\"stylesheet\" href=\"./css/normalize.css\" type=\"text/css\" media=\"screen\">\n" +
"	<!-- Import Font to be used in titles and buttons -->\n" +
"	<link href='http://fonts.googleapis.com/css?family=Sanchez' rel='stylesheet' type='text/css'>\n" +
"	<link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>\n" +
"	<!-- Call style.css -->\n" +
"	<link rel=\"stylesheet\" href=\"../css/grid.css\" type=\"text/css\" media=\"screen\">\n" +
"	<!-- Call style.css -->\n" +
"	<link rel=\"stylesheet\" href=\"../css/style.css\" type=\"text/css\" media=\"screen\">\n" +
"	<title> Tarsus </title>\n" +
"	</head>\n" +
"	<body>\n" +
"		<div id=\"header\" class=\"grid10\" align=\"right\">\n" +
"			<a href=\"profile.html\" id=\"tarsusTitle\"> Unregistered User Character Creation </a> \n" +
"			<a class=\"button\" href=\"../index.html\"> Log Out </a> </div>\n" +
"		<div class=\"grid1\"> </div>\n" +
"		<div class=\"grid8 centered\">\n" +
"		<h1 id=\"title\" class=\"centered\">Character Creation</h1>\n" +
"		\n" +
"		<div class=\"grid2\"> </div>\n" +
"		<form method=\"post\" action=\"Tarsus\">\n" +
"               <input type = \"hidden\" name = \"level\"> value=\"%f\"/>\n"+
"		<div class=\"grid6\" align=\"center\">\n" +
"			<h3> Level %f </h3>\n" +
"			<p> Experience Points to Allocate: %f\n" +
"			</p>\n" +
"			<p> \n" +
"				Name: <input type=\"text\" name=\"name\"/>\n" +
"			</p>\n" +
"			<p> \n" +
"				Strength: <input type=\"number\" name=\"name\"min=\"0\" max=\"100\" value=\"0\"/>\n" +
"			</p> \n" +
"			<p> \n" +
"				Agility: <input type=\"number\" name=\"agility\"min=\"0\" max=\"100\" value=\"0\"/>\n" +
"			</p>  \n" +
"			<p> \n" +
"				Magic: <input type=\"number\" name=\"magic\" min=\"0\" max=\"100\" value=\"0\"/>\n" +
"			</p>   \n" +
"			<p>\n" +
"				Biography:<textarea name=\"bio\" cols=\"35\" rows=\"3\" maxlength=\"300\"> </textarea> <br /> <a id=\"bioLimitID\">  (Max of 300 Chars)</a>\n" +
"			</p>\n" +
"		</div>\n" +
"		<div class=\"grid10\" align=\"center\">\n" +
"			<a href=\"continuechar.html\" class=frontPageButton>Create Character</a>\n" +
"		</div>\n" +
"		</form>\n" +
"		</div>\n" +
"		<div class=\"grid1\"> </div>\n" +
"	</body>\n" +
"	\n" +
"</html>";
        if(startingState != stateEnum.UNREGISTERED_CHARACTER_CREATION)
        {
            //create new page for it
            int level = (int)(Math.random()*50);
            
            out.printf(StartPage);
            
            return stateEnum.UNREGISTERED_CHARACTER_CREATION;
        }
        else
        {
           String name = (String) request.getAttribute("name");
           int level = (Integer) request.getAttribute("level");
           String bio = (String) request.getAttribute("bio");
           int health = (Integer) request.getAttribute("health");
           int strength = (Integer) request.getAttribute("strength");
           int agility = (Integer) request.getAttribute("agility");
           int magic = (Integer) request.getAttribute("magic");
      
           if(isValidString(name) & isValidString(bio))
           {
               //newCharacter(name, level,bio, health, strength, agility, magic);
                return stateEnum.INIT;
           }
           else
           {
                return stateEnum.UNREGISTERED_CHARACTER_CREATION;
           }
        }
    }

    /****************************************************
     * The idling state, logs out after a certain amount of time
     * may be removed
     * @param out the print writer
     * @param request the servlet request
     * @return the next state
     ***************************************************/
    stateEnum idling(PrintWriter out, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /****************************************************
     * Asking what the player wants to do next
     * @param out the print writer
     * @param request the servlet request
     * @return the next state
     ***************************************************/
    stateEnum decisionState(PrintWriter out, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /****************************************************
     * At the blacksmith and can upgrade items
     * @param out the print writer
     * @param request the servlet request
     * @return the next state
     ***************************************************/
    stateEnum blackSmithState(PrintWriter out, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /****************************************************
     * Registered user login state
     * @param out the print writer
     * @param request the servlet request
     * @return the next state
     ***************************************************/
    stateEnum loginState(PrintWriter out, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /****************************************************
     * Registered user creation state
     * @param out the print writer
     * @param request the servlet request
     * @return the next state
     ***************************************************/
    stateEnum accountCreation(PrintWriter out, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /****************************************************
     * Registered user creation state
     * @param string the string to check for validity
     * @return the validity
     ***************************************************/
    Boolean isValidString(String string)
    {
        Boolean toBeReturned = true;
        
        if(string.contains("Drop"))
            toBeReturned = false;
        if(string.contains("Delete"))
            toBeReturned = false;
        if(string.contains(";"))
            toBeReturned = false;
        
        return toBeReturned;
    }
    
}
