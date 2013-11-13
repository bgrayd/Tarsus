<%-- 
    Document   : index
    Created on : Oct 11, 2013, 9:58:03 AM
    Author     : Billy
--%>

<html>
	<head>
	<!-- Call normalize.css -->
	<link rel="stylesheet" href="css/normalize.css" type="text/css" media="screen">
	<!-- Import Font to be used in titles and buttons -->
	<link href='http://fonts.googleapis.com/css?family=Sanchez' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Prosto+One' rel='stylesheet' type='text/css'>
	<!-- Call style.css -->
	<link rel="stylesheet" href="css/grid.css" type="text/css" media="screen">
	<!-- Call style.css -->
	<link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
	<title> Tarsus </title>
	</head>
	<body>
            <form action="Tarsus" method="post">
                <input type="hidden" name="RESET" value="RESET" />
		<div id="header" class="grid10" align="right">
			<input href="index.html" id="tarsusTitle" /> 
			<input class="button" type="submit" value="Log In" name="Log In" /> </div>
		<div class="grid1"> </div>
		<div class="grid8 centered">
		<h1 id="title" class="centered">Welcome</h1>
		<p align="justify"> 
			Tarsus is a web based Role Playing Game that allows you to create your own character and use it to fight progressively more difficult enemies as you try to make your way to the top. If you already have an account, click the Log In button above. If not, you can make a character using our character maker or your can sign up and start your own adventure.
		</p>
		<div align="center">
                    <input type="submit" value="Create a Character" name="Create a Character" class=frontPageButton />
			<input type="submit" value="Sign Up" name="Sign Up" class=frontPageButton />
		</div>
		</div>
		<div class="grid1"> </div>
            </form>
	</body>
	
</html>