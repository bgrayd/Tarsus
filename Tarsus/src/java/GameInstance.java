/*******************************************************
* Class for game instances, will be associated with
* sessions through being the session data.
*******************************************************/

import database.DBConnections;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
//Pulled from inclass exmple
import database.*;

public class GameInstance {
    PlayerCharacter playerChar;
    AresCharacter aresChar;
    stateEnum currentState, startingState;
    String accountName;
    DBConnections dataSource = null;
    Connection conn = null;
    Statement stat = null;
    int gold;

    
    int constantPtsPerLevel = 5;
    int constantWeaponPtsPerLevel = 3;
    int constantArmorPtsPerLevel = 5;
    
    
    GameInstance()
    {
        playerChar = null;
        aresChar = null;
        currentState = stateEnum.INIT;
        accountName = null;
        int gold = 0;
    }
    
    /****************************************************
     * Connect to the database using class variables
     * SQL Commands
     ***************************************************/
    void connectDB(){
        dataSource = DBConnections.getInstance();  
        conn = dataSource.getConnection();
    }  
    
    /****************************************************
     * Disconnect from the database using class variables
     * SQL Commands
     ***************************************************/
    void disconnectDB(){
        DBUtilities.closeStatement(stat);
        dataSource.freeConnection(conn);
    }
    
    /****************************************************
     * Returns the result set result of your query
     * @param query the SQL query you want to execute
     * @param out the printwriter
     ***************************************************/
    ResultSet sqlQuery(String query, PrintWriter out){
        
        ResultSet result = null;
        try{
            connectDB();
             stat = conn.createStatement();
             result = stat.executeQuery(query);
        }catch(Exception ex){
           out.println("Query error:");
           out.println(ex);
        }finally{
            return result;
        } 
    }
    /****************************************************
     * Returns true if your SQL command succeeded, 
     * returns false if it does not
     * @param command the SQL command you want to execute
     * @param out the printwriter
     ***************************************************/
    Boolean sqlCommand(String command, PrintWriter out){
        Boolean result = false;
        try{
            connectDB();
             stat = conn.createStatement();
             stat.execute(command);
             result = true;
             
        }catch(Exception ex){
            out.println("sqlCommand Exception: ");
            out.println(ex);
            
        }finally{
            return result;
        } 
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
                    out.println("Decision");
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
                    try{
                    nextState = accountCreation(out, request);}
                    catch(SQLException ex)
                    {
                        out.println("What the ");
                        out.println(ex);
                    }
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
     * Generates an armor item based on the player 
     * character's level
     * @param level the level of the player character
     * @return an item with a type of armor
     ***************************************************/
    Item generateArmor(int level)
    {
        //needs to be filled in
        int Spts=0, Apts=0, Mpts=0;
        
        Spts = (int) ((int) level*constantWeaponPtsPerLevel*((Math.random()*.4+.8)));
        Apts = (int) ((int) level*constantWeaponPtsPerLevel*((Math.random()*.4+.8)));
        Mpts = (int) ((int) level*constantWeaponPtsPerLevel*((Math.random()*.4+.8)));
        
        return new Item("Armor", 0, 2, 0, Spts, Apts, Mpts, 0);
    }
    
    /****************************************************
     * Generates a weapon item based on the player 
     * character's level
     * @param level the level of the player character
     * @return an item with a type of weapon
     ***************************************************/
    Item generateWeapon(int level)
    {
        int points, Spts=0, Apts=0, Mpts=0, type;
        type = ((int) (Math.random()*2+1));
        
        //calculate the strength value
        points = (int) ((int) level*constantWeaponPtsPerLevel*((Math.random()*.4+.8)));
        
        if(type == 1)
            Spts=points;
        else if(type==2)
            Apts=points;
        else if(type==3)
            Mpts=points;
        
        
        return new Item("Weapon", 0, 1, 0, Spts, Apts, Mpts, 0);
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
        if(startingState != stateEnum.INIT)
        {
            //print the page
            out.println("<html>\n" +
"	<head>\n" +
"	<!-- Call normalize.css -->\n" +
"	<link rel=\"stylesheet\" href=\"css/normalize.css\" type=\"text/css\" media=\"screen\">\n" +
"	<!-- Import Font to be used in titles and buttons -->\n" +
"	<link href='http://fonts.googleapis.com/css?family=Sanchez' rel='stylesheet' type='text/css'>\n" +
"	<link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>\n" +
"	<!-- Call style.css -->\n" +
"	<link rel=\"stylesheet\" href=\"css/grid.css\" type=\"text/css\" media=\"screen\">\n" +
"	<!-- Call style.css -->\n" +
"	<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"screen\">\n" +
"	<title> Tarsus </title>\n" +
"	</head>\n" +
"	<body>\n" +
"            <form action=\"Tarsus\" method=\"post\">\n" +
"		<div id=\"header\" class=\"grid10\" align=\"right\">\n" +
"			<input href=\"index.html\" id=\"tarsusTitle\" /> \n" +
"			<input class=\"button\" type=\"submit\" value=\"Log in\" name=\"Log in\" /> </div>\n" +

"		<div class=\"grid1\"> </div>\n" +
"		<div class=\"grid8 centered\">\n" +
"		<h1 id=\"title\" class=\"centered\">Welcome</h1>\n" +
"		<p align=\"justify\"> \n" +
"			Tarsus is a web based Role Playing Game that allows you to create your own character and use it to fight progressively more difficult enemies as you try to make your way to the top. If you already have an account, click the Log In button above. If not, you can make a character using our character maker or your can sign up and start your own adventure.\n" +
"		</p>\n" +
"		<div align=\"center\">\n" +
"                    <input type=\"submit\" value=\"Create a Character\" name=\"Create a Character\" class=frontPageButton />\n" +
"			<input type=\"submit\" value=\"Sign Up\" name=\"Sign Up\" class=frontPageButton />\n" +
"		</div>\n" +
"		</div>\n" +
"		<div class=\"grid1\"> </div>\n" +
"            </form>\n" +
"	</body>\n" +
"	\n" +
"</html>");
            return stateEnum.INIT;
        }
        else
        {
            String value1 = request.getParameter("Sign Up");
            String value2 = request.getParameter("Log in");
            String value3 = request.getParameter("Create a Character");
            //state changes


          
            String value = "";
            if(value1 != null)
                value = value1;
            if(value2!=null)
                value = value2;
            if(value3!=null)
                value = value3;
          
            if(value.equals("Log in"))
                return stateEnum.LOGIN;
            if(value.equals("Create a Character"))
                return stateEnum.UNREGISTERED_CHARACTER_CREATION;
            if("Sign Up".equals(value))
                return stateEnum.ACCOUNT_CREATION; 
        }
       return stateEnum.INIT;
        
    }

    /****************************************************
     * The initial state
     * @param out the print writer
     * @param request the servlet request
     * @return the next state
     ***************************************************/
    private stateEnum storeState(PrintWriter out, HttpServletRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		/*
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
				item_array[i] = new Item(name = "", id = null, type = (i % 9), upgradeCount = 0, strength = 0, agility = 0, magic = 0 );
				}
		}
		
		
		// if item bought, add to inventory
		*/
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
"	<link rel=\"stylesheet\" href=\"css/normalize.css\" type=\"text/css\" media=\"screen\">\n" +
"	<!-- Import Font to be used in titles and buttons -->\n" +
"	<link href='http://fonts.googleapis.com/css?family=Sanchez' rel='stylesheet' type='text/css'>\n" +
"	<link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>\n" +
"	<!-- Call style.css -->\n" +
"	<link rel=\"stylesheet\" href=\"css/grid.css\" type=\"text/css\" media=\"screen\">\n" +
"	<!-- Call style.css -->\n" +
"	<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"screen\">\n" +
"	<title> Tarsus </title>\n" +
"	</head>\n" +
"       <script>\n" +
"		function validateForm()\n" +
"		{\n" +
"		\n" +
"               var maxValue = ";
        String secondPart = " \n" +
"			var strength = parseInt(document.forms[\"createCharacterForm\"][\"strength\"].value); \n" +
"			var agility = parseInt(document.forms[\"createCharacterForm\"][\"agility\"].value);\n" +
"			var magic = parseInt(document.forms[\"createCharacterForm\"][\"magic\"].value);\n" +
"                       var health = parseInt(document.forms[\"createCharacterForm\"][\"magic\"].value);\n" +
"			var total = strength + agility + magic + health;\n" +
"			alert(\"Total Experience points used: \" + total);\n" +
"			if(total > maxValue)\n" +
"			{\n" +
"				alert(\"Cannot use more than\" + maxValue + \" experience points.\");\n" +
"				return false;\n" +
"			}\n" +
"		\n" +
"		}\n" +
"       </script>" + 
"	<body>\n" +
                "<form name=\"createCharacterForm\" action=\"Tarsus\" onsubmit=\"return validateForm()\" method=\"post\">\n" +
"		<div id=\"header\" class=\"grid10\" align=\"right\">\n" +
                "<input type=\"Submit\" name=\"Home\" value=\"Home\"  class=\"FrontPageButton\" />" +
"		<div class=\"grid1\"> </div></div>\n" +
"		<div class=\"grid8 centered\">\n" +
"		<h1 id=\"title\" class=\"centered\">Character Creation</h1>\n" +
"		\n" +
"		<div class=\"grid2\"> </div>\n" +
"               <input type = \"hidden\" name = \"level\" value=\"";
        String thirdPart = "\"/>\n"+
"		<div class=\"grid6\" align=\"center\">\n" +
"			<h3> Level ";
        String fourthPart = " </h3>\n" +
"			<p> Experience Points to Allocate: ";
        String fifthPart = "\n" +
"			</p>\n" +
"			<p> \n" +
"				Name: <input type=\"text\" name=\"name\"/>\n" +
"			</p>\n" +
"			<p> \n" +
"				Strength: <input type=\"number\" name=\"strength\"min=\"0\" max=\"100\" value=\"0\"/>\n" +
"			</p> \n" +
"			<p> \n" +
"				Agility: <input type=\"number\" name=\"agility\"min=\"0\" max=\"100\" value=\"0\"/>\n" +
"			</p>  \n" +
"			<p> \n" +
"				Magic: <input type=\"number\" name=\"magic\" min=\"0\" max=\"100\" value=\"0\"/>\n" +
"			</p>   \n" +
"			<p> \n" +
"				Health: <input type=\"number\" name=\"health\" min=\"0\" max=\"100\" value=\"0\"/>\n" +
"			</p>   \n" +
"			<p>\n" +
"				Biography:<textarea name=\"bio\" cols=\"35\" rows=\"3\" maxlength=\"300\"> </textarea> <br /> <a id=\"bioLimitID\">  (Max of 300 Chars)</a>\n" +
"			</p>\n";
                String lastPart = 
"		</div>\n"+
"		<div class=\"grid10\" align=\"center\">\n" +
"			<input type =\"submit\" value=\"Create a Character\" class=frontPageButton /> \n" +
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
            Integer level = (int)(Math.random()*50);
            int numItemChoices = 5;
            Item tempItem;
            String submitValue;
            
            out.printf(StartPage);
            out.println(((Integer)(level*constantPtsPerLevel)).toString());
            out.printf(secondPart);
            out.printf(level.toString());
            out.printf(thirdPart);
            out.printf(level.toString());
            out.printf(fourthPart);
            out.printf(((Integer)(level*constantPtsPerLevel)).toString());
            out.printf(fifthPart);
            out.printf("<input type=\"hidden\" name=\"level\" value=\"%d\" />\n",level);
            
            out.println("<table><tr><h2>Weapons</h2></tr><tr><th>Strength</th><th>Agility</th><th>Magic</th><th>select</th><tr>");
            for(int i=0; i<numItemChoices; i++)
            {
                tempItem = generateWeapon(level);
                submitValue = tempItem.getName()+"="+((Integer)tempItem.itemId).toString()+"+"+((Integer)tempItem.getStrength()).toString()+"-"+((Integer)tempItem.getAgility()).toString()+"*"+((Integer)tempItem.getMagic()).toString()+"_"+((Integer)tempItem.getType()).toString();
                out.printf("<tr><td>%d</td><td>%d</td><td>%d</td><td><input type=\"radio\" name=\"weapon\" value=\"%s\"></td></tr>\n",tempItem.getStrength(), tempItem.getAgility(), tempItem.getMagic(), submitValue);
            }
            out.println("</table>");
            
            out.println("<table><tr><h2>Armor</h2></tr><tr><th>Strength</th><th>Agility</th><th>Magic</th><th>select</th><tr>");
            for(int i=0; i<numItemChoices; i++)
            {
                tempItem = generateArmor(level);
                submitValue = tempItem.getName()+"="+((Integer)tempItem.itemId).toString()+"+"+((Integer)tempItem.getStrength()).toString()+"-"+((Integer)tempItem.getAgility()).toString()+"*"+((Integer)tempItem.getMagic()).toString()+"_"+((Integer)tempItem.getType()).toString();
                out.printf("<tr><td>%d</td><td>%d</td><td>%d</td><td><input type=\"radio\" name=\"armor\" value=\"%s\"></td></tr>\n",tempItem.getStrength(), tempItem.getAgility(), tempItem.getMagic(), submitValue);
            }
            out.println("</table>");
            out.println(lastPart);
            
            return stateEnum.UNREGISTERED_CHARACTER_CREATION;
        }
        else
        {
           if(request.getParameter("Home").equals("Home"))
           {
               return stateEnum.INIT;
           }

           String name = (String) request.getParameter("name");
           int level = Integer.parseInt(request.getParameter("level"));
           String bio = request.getParameter("bio");
           int health = Integer.parseInt(request.getParameter("health"));
           int strength = Integer.parseInt(request.getParameter("strength"));
           int agility = Integer.parseInt(request.getParameter("agility"));
           int magic = Integer.parseInt(request.getParameter("magic"));
           Item[] items = {new Item(request.getParameter("weapon")), new Item(request.getParameter("armor"))};

      
           if(isValidString(name) & isValidString(bio))
           {
               //newCharacter(name, level,bio, health, strength, agility, magic, items);
               
               out.println(name);
               out.printf("level: %d\n",level);
               out.println(bio);
               out.printf("health: %d\n",health);
               out.printf("strength: %d\n",strength);
               out.printf("agility: %d\n",agility);
               out.printf("magic: %d\n",magic);
               out.printf("%s\n",items[0].name);
               out.printf("%d\n",items[0].itemId);
               out.printf("%d\n",items[0].strength);
               out.printf("%d\n",items[0].agility);
               out.printf("%d\n",items[0].magic);
               out.printf("%d\n",items[0].type);
               
               out.printf("%s\n",items[1].name);
               out.printf("%d\n",items[1].itemId);
               out.printf("%d\n",items[1].strength);
               out.printf("%d\n",items[1].agility);
               out.printf("%d\n",items[1].magic);
               out.printf("%d\n",items[1].type);
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
        if(startingState != stateEnum.BLACKSMITH)
        {
            printBlacksmithState(out);
            return stateEnum.BLACKSMITH;
        }
        else
        {
            if(request.getParameter(accountName) != null)
                return stateEnum.DECISION;
            for (int i = 0; i < playerChar.itemsHeld.length - 1; i++){
                String tempValue = request.getParameter("Upgrade" + i);
                if(tempValue != null)
                {
                    playerChar.itemsHeld[i].upgradeItem();
                    printBlacksmithState(out);
                }
            }
            return stateEnum.BLACKSMITH;
        }
    }

    /****************************************************
     * Registered user login state
     * @param out the print writer
     * @param request the servlet request
     * @return the next state
     ***************************************************/
    stateEnum loginState(PrintWriter out, HttpServletRequest request) {
        if(startingState != stateEnum.LOGIN){
            out.println("<html>\n" +
            "	<head>\n" +
            "	<!-- Call normalize.css -->\n" +
            "	<link rel=\"stylesheet\" href=\"css/normalize.css\" type=\"text/css\" media=\"screen\">\n" +
            "	<!-- Import Font to be used in titles and buttons -->\n" +
            "	<link href='http://fonts.googleapis.com/css?family=Sanchez' rel='stylesheet' type='text/css'>\n" +
            "	<link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>\n" +
            "	<!-- Call style.css -->\n" +
            "	<link rel=\"stylesheet\" href=\"css/grid.css\" type=\"text/css\" media=\"screen\">\n" +
            "	<!-- Call style.css -->\n" +
            "	<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"screen\">\n" +
            "	<title> Tarsus </title>\n" +
            "	</head>\n" +
            "	<div id=\"header\" class=\"grid10\" align=\"right\"> \n" +
            "		<a href=\"index.jsp\" id=\"tarsusTitle\"> TARSUS </a> </div>\n" +
            "	<div class=\"grid1\"> </div>\n" +
            "	<div class=\"grid8 centered\">\n" +
            "		<h1 id=\"title\" class=\"centered\"> Log In</h1>\n" +
            "		<form method=\"post\" action=\"Tarsus\"> \n" +
            "			<p align=\"center\"> \n" +
            "				Username: <input name=\"username\" type=\"text\" /> \n" +
            "			</p>\n" +
            "			<p align=\"center\"> \n" +
            "				Password: <input name=\"password\" type=\"password\" /> \n" +
            "			</p>\n" +
            "			<p align=\"center\"> \n" +
            "				<input class=\"signUpButton\" value=\"Log In\" type=\"submit\"/>\n" +
            "			</p>\n" +
            "		</form>\n" +
            "	</div>\n" +
            "</html>");
                    
        }else{
            String username = request.getParameter("username");
            int password = request.getParameter("password").hashCode();
            if(!isValidString(username)){
                out.println("Error");
                return stateEnum.LOGIN;
            }
            String search = "SELECT * FROM Login WHERE username='" + username +
                    "' AND password= MD5('" + password+  "');";
            ResultSet result = sqlQuery(search, out);
            try{
            if(result.isBeforeFirst()){
                    accountName = username;
                    return stateEnum.DECISION;
            }else{
                out.println("<html>\n" +
                    "	<head>\n" +
                    "	<!-- Call normalize.css -->\n" +
                    "	<link rel=\"stylesheet\" href=\"css/normalize.css\" type=\"text/css\" media=\"screen\">\n" +
                    "	<!-- Import Font to be used in titles and buttons -->\n" +
                    "	<link href='http://fonts.googleapis.com/css?family=Sanchez' rel='stylesheet' type='text/css'>\n" +
                    "	<link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>\n" +
                    "	<!-- Call style.css -->\n" +
                    "	<link rel=\"stylesheet\" href=\"css/grid.css\" type=\"text/css\" media=\"screen\">\n" +
                    "	<!-- Call style.css -->\n" +
                    "	<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"screen\">\n" +
                    "	<title> Tarsus </title>\n" +
                    "	</head>\n" +
                    "	<div id=\"header\" class=\"grid10\" align=\"right\"> \n" +
                    "		<a href=\"index.jsp\" id=\"tarsusTitle\"> TARSUS </a> </div>\n" +
                    "	<div class=\"grid1\"> </div>\n" +
                    "	<div class=\"grid8 centered\">\n" +
                    "		<h1 id=\"title\" class=\"centered\"> Log In</h1>\n" +
                    "		<h3>Invalid Login </h3> \n " +
                    "		<form method=\"post\" action=\"Tarsus\"> \n " +
                    "			<p align=\"center\"> \n" +
                    "				Username: <input name=\"username\" type=\"text\" /> \n" +
                    "			</p>\n" +
                    "			<p align=\"center\"> \n" +
                    "				Password: <input name=\"password\" type=\"password\" /> \n" +
                    "			</p>\n" +
                    "			<p align=\"center\"> \n" +
                    "				<input class=\"signUpButton\" value=\"Log In\" type=\"submit\"/>\n" +
                    "			</p>\n" +
                    "		</form>\n" +
                    "	</div>\n" +
                    "</html>");
                return stateEnum.LOGIN;
            }
            }catch(Exception ex){
                out.println("Login SQL Error: " + ex);
            }
        }
        return stateEnum.LOGIN;
    }

    /****************************************************
     * Registered user creation state
     * @param out the print writer
     * @param request the servlet request
     * @return the next state
     ***************************************************/
    stateEnum accountCreation(PrintWriter out, HttpServletRequest request) throws SQLException {
        String accountPageBegin = "<html>\n" +
            "	<head>\n" +
            "	<!-- Call normalize.css -->\n" +
            "	<link rel=\"stylesheet\" href=\"css/normalize.css\" type=\"text/css\" media=\"screen\">\n" +
            "	<!-- Import Font to be used in titles and buttons -->\n" +
            "	<link href='http://fonts.googleapis.com/css?family=Sanchez' rel='stylesheet' type='text/css'>\n" +
            "	<link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>\n" +
            "	<!-- Call style.css -->\n" +
            "	<link rel=\"stylesheet\" href=\"css/grid.css\" type=\"text/css\" media=\"screen\">\n" +
            "	<!-- Call style.css -->\n" +
            "	<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\" media=\"screen\">\n" +
            "	<title> Tarsus </title>\n" +
            "	</head>\n" +
            "	<div id=\"header\" class=\"grid10\" align=\"right\"> \n" +
            "		<a href=\"index.jsp\" id=\"tarsusTitle\"> TARSUS </a> \n" +
            "		<a class=\"button\" href=\"login.html\"> Log In </a> </div>\n" +
            "	<div class=\"grid1\"> </div>\n" +
            "	<div class=\"grid8 centered\">\n" +
            "		<h1 id=\"title\" class=\"centered\"> Sign Up Below</h1>\n";
        String accountPageEnd = 
            "		<form method=\"post\" action=\"Tarsus\"> \n" +
            "			<p align=\"center\"> \n" +
            "				Username: <input name=\"username\" type=\"text\" /> \n" +
            "			</p>\n" +
            "			<p align=\"center\"> \n" +
            "				Password: <input name=\"password\" type=\"password\" /> \n" +
            "			</p>\n" +
            "			<p align=\"center\"> \n" +
            "				Confirm Password: <input name=\"confirmpassword\" type=\"password\" /> \n" +
            "			</p>\n" +
            "			<p align=\"center\"> \n" +
            "				<input class=\"signUpButton\" value=\"Sign Up\" type=\"submit\"/> \n" +
            "			</p>\n" +
            "		</form>\n" +
            "	</div>\n" +
            "	<div class=\"grid1\"> </div>\n" +
            "	\n" +
            "</html>";
        if(startingState != stateEnum.ACCOUNT_CREATION)
        {
            out.println(accountPageBegin + accountPageEnd);
            return stateEnum.ACCOUNT_CREATION;
        }
        else{
            
            String username = request.getParameter("username");
            String findUsername = "SELECT username FROM Login "
                    + "WHERE username = \"" + username + "\";";
            
            Boolean alreadyExists = false;
            try{
                ResultSet result = sqlQuery(findUsername, out);
                if(result.isBeforeFirst()){
                    alreadyExists= true;
                }
                
            }catch(Exception ex){
                out.println("username check failure");  //Test Check
                out.println(ex);
                alreadyExists=true;
            }
            
            DBUtilities.closeStatement(stat);
            disconnectDB();
            // Check to see if the username is valid
            if(!isValidString(username) || alreadyExists)
            {
               out.println(accountPageBegin + 
                        "<h3 id=\"title\" class=\"centered\"> Invalid Username "
                       + "</h3 \n" + accountPageEnd);
               return stateEnum.ACCOUNT_CREATION;
            }
            
            int password = request.getParameter("password").hashCode();
            int confirmPassword = request.getParameter("confirmpassword").hashCode();
            if(password != confirmPassword){
                out.println(accountPageBegin + 
                        "<h3 id=\"title\" class=\"centered\"> The Passwords Do "
                        + "Not Match </h3 \n" + accountPageEnd);
                return stateEnum.ACCOUNT_CREATION;  
            }
            String command = "INSERT INTO Login VALUES ('" + username + "', MD5('"
                    + password +"'));";
            
            try{
            if(sqlCommand(command, out))
            {
                
                DBUtilities.closeStatement(stat);
                disconnectDB();
                return stateEnum.LOGIN;
             
            } 
            
            else{
                out.println(accountPageBegin +"<h1> ERROR! </h1>"+ accountPageEnd);
                return stateEnum.ACCOUNT_CREATION;
                        
            } 
            }catch(Exception ex)
            {
                out.println("SQL Command Error:");
                out.println(ex);
                return stateEnum.ACCOUNT_CREATION;}
        }
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
    
    String maxValueScript(int value)
    {
    return ("<script> var maxValue=" + Integer.toString(value) +";</script>");
    }
    
    public void printBlacksmithState(PrintWriter out)
    {
        String startPart = "<html>\n" +
            "	<head>\n" +
            "	<!-- Call normalize.css -->\n" +
            "	<link rel=\"stylesheet\" href=\"../css/normalize.css\" type=\"text/css\" media=\"screen\">\n" +
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
            "			<input value=\"Character Page\" name=\"" + accountName + "\" type=\"submit\" id=\"tarsusTitle\" />\n" +
            "			<input class=\"button\" value=\"Log Out\" name=\"Log Out\" /> </div>\n" +
            "		<div class=\"grid1\"> </div>\n" +
            "		<div class=\"grid8 centered\">\n" +
            "		<h1 id=\"title\" class=\"centered\">Blacksmith</h1>\n" +
            "		<table id=\"table\" align=\"center\">\n" +
            "			<tr>\n" +
            "				<td> </td>\n" +
            "				<th> Name </th>\n" +
            "				<th> Strength </th>\n" +
            "				<th> Magic </th>\n" +
            "				<th> Agility </th>\n" +
            "				<th> Type </th>\n" +
            "			</tr>\n" +
            "			<tr>";
        String endPart = "		</table>\n" +
            "		</div>\n" +
            "		<div class=\"grid1\"> </div>\n" +
            "	</body>\n" +
            "	\n" +
            "</html>";
        
        out.println(startPart);
        for (int i = 0; i < playerChar.itemsHeld.length - 1; i++){
            if(playerChar.itemsHeld[i].getUpgradeCount() < 3)
            {
                out.println("<td> <input value=\"Upgrade" + i + " name=\"Upgrade" + i + " class=\"tableButton\"> /> </td>");
                out.println("<td>");
                out.println(playerChar.itemsHeld[i].getName());
                out.println("</td>");
                out.println("<td>");
                out.println(playerChar.itemsHeld[i].getStrength());
                out.println("</td>");
                out.println("<td>");
                out.println(playerChar.itemsHeld[i].getAgility());
                out.println("</td>");
                out.println("<td>");
                out.println(playerChar.itemsHeld[i].getMagic());
                out.println("</td>");
                out.println("<td>");
                out.println(playerChar.itemsHeld[i].getType());
                out.println("</td>");
                out.println("</tr>");
            }
        }
        out.println(endPart);
    }
}
