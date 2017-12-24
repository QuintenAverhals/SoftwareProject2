<!DOCTYPE html>
<html>
	<head>

		<title>Home</title>
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
			//ZET HIER DE LOGIN ID DIE WORDT DOORGEGEVEN VAN DE VORIGE PAGINA (PERSOONLIJK SCHEMA)
			//$loginID = 1;
			
			$query="SELECT t.TrainingID, t.Training_Name FROM TRAINING t JOIN TRAININGPERWERKNEMER tw ON (t.TrainingID = tw.TrainingID) WHERE tw.LoginID = $loginID AND t.TrainingID NOT IN (SELECT TrainingID FROM CERTIFICATE WHERE employee_name = '".$username."')";
			$result = mysqli_query($conn,$query);

			echo "<div id='tabel1'>";
			echo "<table class='responstable'><center>";
			echo "<tr>";
			echo "<th>" . "Training" . "</th>";
			echo "<th>" . "Certificaat uploaden" . "</th>";
			echo "</tr>";

			while($row = mysqli_fetch_array($result)) {
				echo "<tr>";
				echo "<td>" . $row['Training_Name']. "</td>";			
				echo "<td>" . "<button class='button button1' onclick='uploaden()'>Uploaden</button>" . "</td>";
				//echo "<td><a href=download.php?doc_name=".$row['doc_name'] . ">Downloaden</a></td>";
				echo "</tr>";
			}
			echo "</center></table>";	
			echo "</div>";

			
			$query="SELECT * FROM TRAINING t JOIN TRAININGPERWERKNEMER tw ON (t.TrainingID = tw.TrainingID) JOIN LOCATION l ON(t.location_id = l.locationID) WHERE tw.LoginID = $loginID AND end_date > CURDATE() AND visibility != 0";
			$result = mysqli_query($conn,$query);
			echo "<div id='tabel2'>";
			echo "<table class='responstable'><center>";
			echo "<tr>";
			echo "<th>" . "Training name" . "</th>";
			echo "<th>" . "Start date" . "</th>";
			echo "<th>" . "End date" . "</th>";
			echo "<th>" . "Status" . "</th>";
			echo "<th>" . "More info". "</th>";
			echo "</tr>";

			while($row = mysqli_fetch_array($result)) {
				$trainingname = $row['Training_Name'];
				$startdate = $row['start_date'];
				$enddate = $row['end_date'];
				$trainingID = $row['TrainingID'];

				$cancel = $row['cancel'];
				$afgelast = 'CANCELED';
				$ongoing = 'ONGOING';
				
				echo "<tr>";
				echo "<td>" . $trainingname . "</td>";
				echo "<td>" . $startdate. "</td>";			
				echo "<td>" . $enddate . "</td>";
				if($cancel == 1){
					echo "<td>" . $afgelast . "</td>";
				}
				else{
					echo "<td>" . $ongoing . "</td>";
				}
				echo "<td>" . "<button class='button button1' onclick='meerInfo(". json_encode($trainingID).")'>Meer info</button>" . "</td>";
				//echo "<td><a href=maps.php?trainingID=".$row['TrainingID'] . ">Meer info</a></td>";				
				echo "</tr>";
			}
			echo "</center></table>";	
			echo "</div>";

			$query="SELECT * FROM TRAININGPERWERKNEMER tw JOIN TRAINING t ON (tw.TrainingID = t.TrainingID) WHERE tw.LoginID = '".$loginID."' AND t.survey_id IS NOT NULL AND t.survey_id != 1";
			$result = mysqli_query($conn,$query);

			echo "<div id='tabel3'>";
			echo "<table class='responstable'><center>";
			echo "<tr>";
			echo "<th>" . "Training" . "</th>";
			echo "<th>" . "Fill in survey" . "</th>";
			echo "</tr>";

			while($row = mysqli_fetch_array($result)) {
				$surveyID = $row['survey_id'];
				$trainingname = $row['Training_Name'];
				$queryCheckBestaatAl = "SELECT * FROM SURVEY_ANSWERS WHERE UserID = '".$loginID."' AND SurveyID = '".$surveyID."'";
				$resultCheckBestaatAl = mysqli_query($conn,$queryCheckBestaatAl);

				if (mysqli_num_rows($resultCheckBestaatAl) == 0) {
					echo "<tr>";
					echo "<td>" . $trainingname . "</td>";
					echo "<td>" . "<button class='button button1' onclick='surveyInvullen(". json_encode($surveyID).")'>Fill in survey</button>" . "</td>";
					//echo "<td><a href=maps.php?trainingID=".$row['TrainingID'] . ">Meer info</a></td>";				
					echo "</tr>";
				}
			}
			echo "</center></table>";	
			echo "</div>";
		?>
		<script>
			function uploaden() {
				window.location.href = "chooseFile.php";
			}
		</script>
		<script>
			function meerInfo(trainingID) {
				window.location.href = "maps.php?trainingID=" + trainingID;
			}
		</script>
		<script>
			function surveyInvullen(surveyID) {
				window.location.href = "survey_invullen.php?surveyID=" + surveyID + "&aantal=" + 1;
			}
		</script>
	</body>	
</html>

