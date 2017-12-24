
<!DOCTYPE html>
<html>
	<head>

		<title>Training aanvragen</title>
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
		$correct = 1;
	

		if(ISSET($_POST['submit'])){
			$queryCheckOverlapping = "SELECT * FROM TRAININGPERWERKNEMER tw JOIN TRAINING t ON (tw.TrainingID = t.TrainingID) WHERE tw.LoginID = $loginID AND (('" . $_POST['startDate'] . "' BETWEEN t.start_date AND t.end_date) OR ('" . $_POST['endDate'] . "' BETWEEN t.start_date AND t.end_date) OR ('" . $_POST['startDate'] . "' <= t.start_date AND '" . $_POST['endDate'] . "' >= t.end_date))";
			$resultaatCheckOverlapping = mysqli_query($conn,$queryCheckOverlapping);

			if (mysqli_num_rows($resultaatCheckOverlapping) == 0){
				$queryGetMaxTrainingen = "SELECT maxTrainingen FROM OPTIONS LIMIT 1";
				$resultGetMaxTrainingen = mysqli_query($conn,$queryGetMaxTrainingen);
				while ($rowMaxTraining = mysqli_fetch_array($resultGetMaxTrainingen)) {
					$maxAantal = $rowMaxTraining['maxTrainingen'];
				}
				$queryCountAantalTrainingen = "SELECT * FROM TRAININGPERWERKNEMER tw JOIN TRAINING t ON (tw.TrainingID = t.TrainingID) WHERE tw.LoginID = '".$loginID."' AND DATE_FORMAT(t.start_date ,'%Y') = DATE_FORMAT('".$_POST['startDate']."' ,'%Y')";
				$resultCountAantalTrainingen = mysqli_query($conn,$queryCountAantalTrainingen);

				if (mysqli_num_rows($resultCountAantalTrainingen) <= $maxAantal){
					if($_POST['startDate'] == $_POST['endDate']){
						if($_POST['startTime'] > $_POST['endTime']){
							echo "Gelieve een correcte tijd op te geven.";
							$correct = 0;
						}
					}
					elseif ($_POST['startDate'] > $_POST['endDate']) {
						echo "Gelieve een correcte datum op te geven.";
						$correct = 0;
					}
					if($correct == 1){
						//echo "test";
						$query = "SELECT * FROM LOCATION WHERE streetName = '".$_POST['street']."' AND streetnumber = '".$_POST['streetNumber']."' AND bus = '".$_POST['bus']."' AND city = '".$_POST['city']."' AND country = '".$_POST['country']."' AND zipcode = '".$_POST['zipCode']."' ";
						//echo $query;
						$result = mysqli_query($conn,$query);

						if (mysqli_num_rows($result) != 0) {
							//echo "1";
							while ($row = mysqli_fetch_array($result)) {
								$locatieID = $row['locationID'];
								//echo $locatieID;
							}			
						}
						else{
							//echo "0";
							$insertLocation = "INSERT INTO LOCATION (city, country, zipcode, streetName, bus, streetnumber) VALUES ('".$_POST['city']."', '".$_POST['country']."', '".$_POST['zipCode']."', '".$_POST['street']."', '".$_POST['bus']."', '".$_POST['streetNumber']."')";
							//echo $insertLocation;
							$resultLocation = mysqli_query($conn,$insertLocation);

							$query = "SELECT * FROM LOCATION WHERE streetName = '".$_POST['street']."' AND streetnumber = '".$_POST['streetNumber']."' AND bus = '".$_POST['bus']."' AND city = '".$_POST['city']."' AND country = '".$_POST['country']."' AND zipcode = '".$_POST['zipCode']."' ";
							//echo $query;
							$result = mysqli_query($conn,$query);

							while ($row = mysqli_fetch_array($result)) {
								$locatieID = $row['locationID'];
								//echo $locatieID;
							}

							$queryUserName = "SELECT WebsiteUserName FROM LOGIN_WEBSITE WHERE LoginID = '".$loginID."'";
							$resultUserName = mysqli_query($conn,$queryUserName);
							while ($rowUserName = mysqli_fetch_array($resultUserName)) {
								$username = $rowUserName['WebsiteUserName'];
							}

							$action = "User: " .$username. " add location: " .$locatieID . "." ;
							//echo $action;
					 		$log = "INSERT INTO LOGFILE_WEBSITE (LoginID, action, datum) VALUES ('" .$loginID. "', '" . $action. "', NOW())";
						    //echo $query;
							$resultLog = mysqli_query($conn,$log);

						}

						$insertTraining = "INSERT INTO TRAINING (Training_Name, start_date, end_date, start_time, end_time, cancel, survey_id, location_id) VALUES ('".$_POST['trainingName']."', '".$_POST['startDate']."', '".$_POST['endDate']."', '".$_POST['startTime']."', '".$_POST['endTime']."', 0, 1, '".$locatieID."')";
						//echo $insertTraining;
						$resultTraining = mysqli_query($conn,$insertTraining);

						$selectTrainingID = "SELECT * FROM TRAINING WHERE Training_Name = '".$_POST['trainingName']."' AND start_date = '".$_POST['startDate']."' AND end_date = '".$_POST['endDate']."' AND start_time = '".$_POST['startTime']."' AND end_time = '".$_POST['endTime']."' AND location_id = '".$locatieID."'";
						$resultTrainingID = mysqli_query($conn,$selectTrainingID);
						while ($rowTrainingID = mysqli_fetch_array($resultTrainingID)) {
							$trainingID = $rowTrainingID['TrainingID'];
						}

						$insertTrainingPerWerknemer = "INSERT INTO TRAININGPERWERKNEMER (LoginID, TrainingID) VALUES ('".$loginID."', '".$trainingID."')";
						$resultTrainingPerWerknemer = mysqli_query($conn,$insertTrainingPerWerknemer);


						echo "Training is toegevoegd!";
					}
				}
				else{
					echo "U hebt al uw maximum aantal trainingen opgebruikt. Gelieve HR te contacteren voor meer info of aanvraag.";
				}				
			}
			else{
				echo "U hebt al een training op hetzelfde moment.";
			}
			header("Refresh: 3; URL= tevolgen_trainingen.php");
		}
		else{
			echo '
			<form action="training_aanvragen.php" method="post">
				<table class="responstable"><center>
					<tr>
						<td>Training naam: </td>
						<td><input type="text" name="trainingName" id="trainingName" required></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>Start date: </td>
						<td><input type="date" name="startDate" id="startDate" required></td>
						<td>End date: </td>
						<td><input type="date" name="endDate" id="endDate" required></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>Start time: </td>
						<td><input type="time" name="startTime" id="startTime" required></td>
						<td>End time: </td>
						<td><input type="time" name="endTime" id="endTime" required></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>Street: </td>
						<td><input type="text" name="street" id="street" required></td>
						<td>Number: </td>
						<td><input type="number" name="streetNumber" id="streetNumber" required></td>
						<td>Bus: </td>
						<td><input type="number" name="bus" id="bus"></td>
					</tr>
					<tr>
						<td>City: </td>
						<td><input type="text" name="city" id="city" required></td>
						<td>Zip Code: </td>
						<td><input type="text" name="zipCode" id="zipCode" required></td>
						<td>Country: </td>
						<td><input type="text" name="country" id="country" required></td>
					</tr>
				</center></table>
				<input type="submit" value="Save training" name="submit" class="buttonSave button1">
			</form>';
		}
	?>
</body>
</html>
