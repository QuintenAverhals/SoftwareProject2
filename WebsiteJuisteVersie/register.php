<?PHP
require_once("employee_config.php");
?>
<!DOCTYPE html>
<html >
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>
    <title>Registreren</title>
	<link type='text/css' rel='stylesheet' href='software_project.css'/>
    <script type='text/javascript' src='scripts/gen_validatorv31.js'></script>
    <script src="scripts/pwdwidget.js" type="text/javascript"></script>      
</head>
<body>
	<?php
		if(isset($_POST['submitted'])){
  			if ($employee->RegisterUser($_POST["UserName"])) {
				$UserName = $_POST["UserName"];
  				//$emailadres = $employee->getOdataEmail($UserName);
  				$emailadres = 'seppevriens@hotmail.com';
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
			  <title>Set password</title>
			</head>
			<body>
			    Hello <br><br>

			    If you want to set your password for the application, 
			    <a href="http://www.kaaimannen.be/Seppe/Set_Password.php?User='.$UserName.'">click here.</a>  <br><br>
			    Greetings <br><br>


			    IT
			</body>
			</html>
			';
                echo "The email is sent to your e-mail address.";
                header("Refresh: 3; URL= index.php");

                mail($to,$subject,$txt,$headers);
  			}
  			else{
  				echo "This username is not used in the database of the company. <br>";
  				echo "Ask HR for mor information.";
  			}
		}
		else{
			echo "
				<div id='fg_membersite'>
				<form id='register' action='' method='post' accept-charset='UTF-8'>
					<table class='responstable'><center>
						<tr>
							<td>Username</td>
							<td><input type='text' name='UserName' id='UserName'</td>
						</tr>
					</center></table>
					<input type='hidden' name='submitted' id='submitted' value='1'/>
					<input type='submit' name='submit' class='buttonSave button1' value='Registreer'>
					
				</form>
				</div>
			";
		}
	?>
</body>
</html>