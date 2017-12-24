<!DOCTYPE html>
<html>
	<head>
		<title>Password reset</title>

		<link type='text/css' rel='stylesheet' href='software_project.css'/>
	</head>
	<body>
		<?php
			session_start();
			include('connect.php');
			
			if(!isset($_POST['submit'])){
				$userid = $_GET['a'];
				$lengthPassword = $_GET['nbvcb'];
				$emailadres = $_GET['xwmlc'];
				//echo $userid;
			}

			$query="SELECT * FROM LOGIN WHERE UserID ='" .$_GET['a']."'";
			$result = mysqli_query($conn,$query);
			$row = mysqli_fetch_array($result);

			if($userid == $row['UserID'] & $lengthPassword == strlen($row['password']) & $emailadres == $row['email']){
				if(ISSET($_POST['submit'])){
					$Userid = $_POST['Userid'];
					$query="SELECT * FROM LOGIN WHERE UserID ='" .$Userid."'";
					//echo $query;
					$result = mysqli_query($conn,$query);

					if($_POST['nieuwwachtwoord'] == $_POST['nieuwwachtwoordherhalen']){
						$salt = rand(0,50);
						$wachtwoordSalt = $_POST['nieuwwachtwoord'] . $salt;
						//echo $wachtwoordSalt;
						$hash = hash('sha512', $wachtwoordSalt);
						//echo $salt;
						//echo $hash;
						$query = "UPDATE LOGIN SET password ='" .$hash."', salt ='".$salt."' WHERE UserID='" .$Userid."'";
						//echo $query;
						$result = mysqli_query($conn,$query);
						echo "Your password is changed!";
						header("Refresh: 3; URL= index.php");
					}
					else{
						echo "Try again, passwords were not the same!";
						echo "<form action='wachtwoord_vergeten_reset.php' method='post' enctype='multipart/form-data'>
						<table class='responstable'><center>
						<tr>
							<td>New password:</td>
							<td> <input type='password' name='nieuwwachtwoord' id='nieuwwachtwoord' required></td>
						</tr>
						<tr>
							<td>Repeat new password:</td>
							<td> <input type='password' name='nieuwwachtwoordherhalen' id='nieuwwachtwoordherhalen' required></td>
						</tr>
						</center></table>
						<input type='hidden' name= 'Userid' value= $userid>
						<input type='submit' name='submit' value='Send' class='buttonSave button1'>
						</form>";
					}
				}
				else{
					echo "<form action='wachtwoord_vergeten_reset.php' method='post' enctype='multipart/form-data'>
					<table class='responstable'><center>
					<tr>
						<td>New password:</td>
						<td> <input type='password' name='nieuwwachtwoord' id='nieuwwachtwoord' required></td>
					</tr>
					<tr>
						<td>Repeat new password:</td>
						<td> <input type='password' name='nieuwwachtwoordherhalen' id='nieuwwachtwoordherhalen' required></td>
					</tr>
					</center></table>
					<input type='hidden' name= 'Userid' value= $userid>
					<input type='submit' name='submit' value='Send' class='buttonSave button1'>
					</form>";
				}
			}
			else{
				echo "You have no access!!";
				header("Refresh: 3; URL= index.php");
			}
		?>
	</body>	
</html>