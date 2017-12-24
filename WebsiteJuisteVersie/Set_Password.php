<?php
require_once("employee_config.php");
?>

<!DOCTYPE html>
<html >
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>
    <title>Set password</title>    
   	<link type='text/css' rel='stylesheet' href='software_project.css'/>
</head>
<body>
	<?php
		session_start();
		include('connect.php');
		

		if(isset($_POST['submit'])){
			$queryZoekUserName = "SELECT * FROM LOGIN_WEBSITE WHERE WebsiteUserName = '".$_POST['userNAME']."'";
			$resultZoekUserName = mysqli_query($conn,$queryZoekUserName);
			if (mysqli_num_rows($resultZoekUserName) == 0) {
				if ($_POST['Password'] == $_POST['ConfirmPassword']) {
    			$email = $employee->getOdataEmail($_POST['userNAME']);
    			$wachtwoord = $_POST['Password'];
				$salt = rand(0,50);
				$wachtwoordSalt = $wachtwoord . $salt;
				$hashed = hash('sha512', $wachtwoordSalt);

        		$query = "INSERT INTO LOGIN_WEBSITE (WebsiteUserName, Wachtwoord, Email, visibility, salt) VALUES ('" . $_POST['userNAME']. "', '" . $hashed. "', '" . $email. "' , 1,'". $salt."')";
        		//echo $query;
        		$result = mysqli_query($conn,$query);

        		$action = "User: " .$_POST['userNAME']. " is registered." ;
				//echo $action;
				$querySelect = "SELECT LoginID FROM LOGIN_WEBSITE WHERE WebsiteUserName = '".$_POST['userNAME']."' AND Email = '".$email."' AND Wachtwoord =  '".$hashed."' AND salt = '".$salt."'";
				//echo $querySelect;
        		$resultSelect = mysqli_query($conn,$querySelect);
        		while($rowSelect = mysqli_fetch_array($resultSelect)){
					$loginID = $rowSelect['LoginID'];			
				}

		 		$log = "INSERT INTO LOGFILE_WEBSITE (LoginID, action, datum) VALUES ('" .$loginID. "', '" . $action. "', NOW())";
			    //echo $query;
				$resultLog = mysqli_query($conn,$log);
				echo "Your account is correct!";
				header("Refresh: 3; URL= index.php");
	    		}
	    		else{
	    			echo "Passwords are not the same. <br>";
					header("Refresh: 3; URL= index.php");
				}
    		}
    		else{
    			echo "User already exists. <br>";
    			header("Refresh: 3; URL= index.php");
    		}
		}
		else{
			echo "
				<div id='fg_membersite'>
				<form id='register' action='Set_Password.php' method='post' accept-charset='UTF-8'>
					<table class='responstable'><center>
						<tr>
							<td>Username</td>
							<td><input type='text' name='UserName' id='UserName' value='".$_GET['User']."' disabled></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type='password' name='Password' id='Password'</td>
						</tr>
						<tr>
							<td>Confirm password</td>
							<td><input type='password' name='ConfirmPassword' id='ConfirmPassword'</td>
						</tr>
					</center></table>
					<input type='hidden' name='userNAME' id='userNAME' value='".$_GET['User']."'/>
					<input type='hidden' name='submitted' id='submitted' value='1'/>
					<input type='submit' name='submit' class='buttonSave button1' value='Save password'>
				</form>
			";
		}
	?>
</body>
</html>