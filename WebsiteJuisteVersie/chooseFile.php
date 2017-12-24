
<!DOCTYPE html>
<html>
	<head>

		<title>Choose file</title>
		<link type='text/css' rel='stylesheet' href='software_project.css'/>
	</head>
<body>
	<?php
		include('menubalk.html');
		session_start();
		include('connect.php');	
		//ZET HIER DE LOGIN ID DIE WORDT DOORGEGEVEN VAN DE VORIGE PAGINA (PERSOONLIJK SCHEMA)
		if(isset($_SESSION['ingelogd'])){
			$loginID = $_SESSION['loginID'];
			//echo $loginID;
		}
		else{
			header("Location: index.php");
		}
		//$loginID = 1;

		$query2 ="SELECT WebsiteUserName FROM LOGIN_WEBSITE WHERE LoginID = $loginID";
		$result2 = mysqli_query($conn,$query2);
		while($row2 = mysqli_fetch_array($result2)){
			$username = $row2['WebsiteUserName'];			
		}	

		$query="SELECT t.TrainingID, t.Training_Name FROM TRAINING t JOIN TRAININGPERWERKNEMER tw ON (t.TrainingID = tw.TrainingID) WHERE tw.LoginID = $loginID AND t.TrainingID NOT IN (SELECT TrainingID FROM CERTIFICATE WHERE employee_name = '".$username."')";
		$result = mysqli_query($conn,$query);
	?>

<form action="upload.php" method="post" enctype="multipart/form-data">
	<table class='responstable'><center>
		<tr>
			<td>TrainingsID: </td>
			<td><select name="trainingsID" id="trainingsID">
				<?php
					while ($row = mysqli_fetch_array($result)) {
						echo "<option value='".$row['TrainingID']."'>".$row['Training_Name']."</option>";
					}
				?>
			</td>
		</tr>
		<!--<tr>-->
			<!--HIER KOMT HET MEEGEGEVEN LOGINID (PERSOONLIJK) EN KAN NIET MEER AANGEPAST WORDEN NADIEN-->
			<!--<td>UserID: </td>
			<td><input type="number" name="userID" id="userID" required></td>
		</tr>-->
		
		<tr>
			<td>Employee name: </td>
			<td><input type="text" name="employeeName" value= <?php echo "$username" ?> id="employeeName" disabled></td>
		</tr>
		<tr>
			<td>Select file to upload: </td>
			<td><input type="file" name="fileToUpload" id="fileToUpload" class='buttonUpload button1' required></td>
		</tr>
	</center></table>
	<input type="submit" value="Upload file" name="submit" class='buttonSave button1'>
</form>


</body>
</html>
