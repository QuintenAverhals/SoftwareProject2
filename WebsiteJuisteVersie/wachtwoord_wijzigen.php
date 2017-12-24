<!DOCTYPE html>
<html>
	<head>

		<title>Wachtwoord wijzigen</title>

		<link type='text/css' rel='stylesheet' href='software_project.css'/>
	</head>
	<body>
		
		<?php
			include('menubalk.html');
			session_start();
			include('connect.php');
			if(isset($_SESSION['ingelogd'])){
				$loginID = $_SESSION['loginID'];
				//echo $loginID;
			}
			else{
				header("Location: index.php");
			}	

			if(ISSET($_POST['submit'])){
				$query = "SELECT * FROM LOGIN_WEBSITE WHERE LoginID = '".$loginID."'";
				$result = mysqli_query($conn,$query);
				while ($row = mysqli_fetch_array($result)) {
					$wachtwoord = $row['Wachtwoord'];
					$salt = $row['salt'];
				}

				$wachtwoordSalt = $_POST['oudwachtwoord'] . $salt;
				//echo $wachtwoordSalt;
				$hash = hash('sha512', $wachtwoordSalt);

				if($hash == $wachtwoord){
					if ($_POST['nieuwwachtwoord'] == $_POST['herhaalwachtwoord']) {
					$wachtwoord = $_POST['nieuwwachtwoord'];
					$salt = rand(0,50);
					$wachtwoordSalt = $wachtwoord . $salt;
					//echo $wachtwoordSalt;
					$hashed = hash('sha512', $wachtwoordSalt);

					$aanpassen = "UPDATE LOGIN_WEBSITE SET Wachtwoord = '" . $hashed . "', salt = '".$salt."' WHERE LoginID = '" . $loginID . "'";
					//echo($aanpassen);
					$result = mysqli_query($conn,$aanpassen);
					echo "The password is changed.";
					header("Refresh: 3; URL= home.php");
					}
					else {
						echo "Passwords are not the same.";
						header("Refresh: 3; URL= gebruikers.php");
					}
				}
				else{
					echo "The old password is incorrect.";
					header("Refresh: 3; URL= home.php");
				}

				
			}	
			else{
				echo "<div id='tabel'>";
				echo "<form action='wachtwoord_wijzigen.php' method='post'>
				<table class='responstable'><center>
				<tr>
				<td>Old password:</td><td> <input type='password' name='oudwachtwoord' required></td>
				</tr>
				<tr>
				<td>New password:</td><td> <input type='password' name='nieuwwachtwoord' required></td>
				</tr>
				<tr>
				<td>Repeat new password:</td><td> <input type='password' name='herhaalwachtwoord' required></td>
				</tr>
				</center></table>
				<input type='submit' name='submit' value='Change password' class='buttonSave button1'>
				</form> </div>";
			}		
		?>
	</body>	
</html>