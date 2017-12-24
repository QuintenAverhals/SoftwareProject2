<!DOCTYPE html>
<html>
	<head>

		<title>Login</title>

		<link type='text/css' rel='stylesheet' href='software_project.css'/>
	</head>
	<body>
		<?php
			session_start();
			include('connect.php');

			

			if (ISSET($_POST['submit'])) {
			
				$query = "SELECT salt FROM LOGIN_WEBSITE WHERE WebsiteUserName ='" .$_POST['gebruikersnaam']. "'";
				//echo $query;
				$result = mysqli_query($conn,$query);
				//echo $count;
				//echo "</br>";
			
				while($row = mysqli_fetch_array($result)) {
					$salt = $row['salt'];
					//echo $salt;
				}

				$wachtwoordSalt = $_POST['wachtwoord'] . $salt;
				$hash = hash('sha512', $wachtwoordSalt);

				$queryWachtwoord = "SELECT * FROM LOGIN_WEBSITE WHERE WebsiteUserName = '".$_POST['gebruikersnaam']."' AND Wachtwoord = '".$hash."'"; 
				//echo $queryWachtwoord;
				$resultWachtwoord = mysqli_query($conn,$queryWachtwoord);

				while($rowWachtwoord = mysqli_fetch_array($resultWachtwoord)) {
					$loginID = $rowWachtwoord['LoginID'];
					$username = $rowWachtwoord['WebsiteUserName'];
					//echo $loginID;
				}

				$count=mysqli_num_rows($resultWachtwoord);


				if($count==1){	
			 	
					$_SESSION['ingelogd'] = 1;
					//echo $_SESSION['ingelogd'];
					$_SESSION['loginID'] = $loginID;
					//echo $_SESSION['naam'];

					$action = "User: ".$username. " logged in.";
					//echo $action;
			 		$query = "INSERT INTO LOGFILE_WEBSITE (LoginID, action, datum) VALUES ('" .$_SESSION['loginID']. "', '" . $action. "', NOW())";
				    //echo $query;
					$result = mysqli_query($conn,$query);
					header("Location: home.php"); 
				}
			 	else {
			 		echo "

					<div id='login'>

						<h2><span class='fontawesome-lock'></span>Aanmelden</h2>

						<form action='index.php' method='post'>

							<fieldset>
								<div class = vekeerd>Foute gebruikersnaam en/of wachtwoord.</div>
								<p><label for='gebruikersnaam'>Gebruikersnaam</label></p>
								<p><input type='text' name='gebruikersnaam' id='gebruikersnaam'</p>

								<p><label for='wachtwoord'>Wachtwoord</label></p>
								<p><input type='password' name='wachtwoord' id='wachtwoord'</p>

								<p><input type='submit' name='submit' value='Aanmelden'></p>
								<a href='wachtwoord_vergeten.php' class='vergeten'>Wachtwoord vergeten?</a>
								<a href='register.php' class='register'>Registreren</a>
							</fieldset>
						</form>
					</div>";
				}
			}else{

				echo "
				<div id='login'>

					<h2><span class='fontawesome-lock'></span>Aanmelden</h2>

					<form action='index.php' method='post'>

						<fieldset>

							<p><label for='gebruikersnaam'>Gebruikersnaam</label></p>
							<p><input type='text' name='gebruikersnaam' id='gebruikersnaam'</p>

							<p><label for='wachtwoord'>Wachtwoord</label></p>
							<p><input type='password' name='wachtwoord' id='wachtwoord'</p>
							
							<p><input type='submit' name='submit' value='Aanmelden'></p>
							<a href='wachtwoord_vergeten.php' class='vergeten'>Wachtwoord vergeten?</a>
							<a href='register.php' class='register'>Registreren</a>

						</fieldset>
					</form>
				</div>";
			}
		?></body>	
</html>