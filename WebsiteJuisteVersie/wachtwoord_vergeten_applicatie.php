
<!DOCTYPE html>
<html>
	<head>

		<title>Forgot password application</title>
		<link type='text/css' rel='stylesheet' href='software_project.css'/>
	</head>
<body>
	<?php
		session_start();
		include('connect.php');	
		

		if(ISSET($_POST['submit'])){
				
			$query="SELECT * FROM LOGIN WHERE Username ='" .$_POST['username']."'";
			$result = mysqli_query($conn,$query);

			if (mysqli_num_rows($result) == 0) {
				echo "This username is not in our database.";
				header("Refresh: 3; URL= index.php");		
			}
			else{
				
				$row = mysqli_fetch_array($result);
				$password = $row['password'];
				$lengthPassword = strlen($password);
				$userid = $row['UserID'];
				$emailadres = $row['email'];

				$to = $emailadres;
				$headers = 'From: materiaal@Kaaimannen.be' ."\r \n".
					'Reply-To: materiaal@Kaaimannen.be' ."\r \n".
					'X-Mailer: PHP/' . phpversion()."\r \n".
					'MIME-Version: 1.0' . "\r\n".
					'Content-type: text/html; charset=iso-8859-1';
				$subject = "Reset password for application";
				$txt = '
<html>
<head>
  <title>Forgot password</title>
</head>
<body>
	Hello <br><br>

	If you want to change your password for the application, 
	<a href="http://kaaimannen.be/Seppe/wachtwoord_vergeten_reset.php?a='.$userid.'&nbvcb='.$lengthPassword.'&xwmlc='.$emailadres.'">click here.</a>  <br><br>

	Greetings <br><br>


	IT
</body>
</html>
';
				echo "The email is sent to your e-mail address.";
				header("Refresh: 3; URL= index.php");

				mail($to,$subject,$txt,$headers);
			}	
		}
		else{
			echo "<form action='wachtwoord_vergeten_applicatie.php' method='post' enctype='multipart/form-data'>
			<table class='responstable'><center>
			<tr>
				<td>Username:</td>
				<td> <input type='text' name='username' id='username' required></td>
			</tr>
			</center></table>
			<input type='submit' name='submit' value='Verzenden' class='buttonSave button1'>
			</form>";
		}
	?>
</body>
</html>
