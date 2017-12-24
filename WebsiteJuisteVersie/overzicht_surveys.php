<!DOCTYPE html>
<html>
	<head>

		<title>Overzicht survey</title>
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
			
			$query="SELECT * FROM TRAININGPERWERKNEMER tw JOIN TRAINING t ON (tw.TrainingID = t.TrainingID) WHERE tw.LoginID = '".$loginID."' AND t.survey_id IS NOT NULL AND t.survey_id != 1";
			$result = mysqli_query($conn,$query);

			echo "<div id='tabel'>";
			echo "<table class='responstable'><center>";
			echo "<tr>";
			echo "<th>" . "Training name" . "</th>";
			echo "<th>" . "Start date" . "</th>";
			echo "<th>" . "End date" . "</th>";
			echo "<th>" . "Fill in survey". "</th>";
			echo "</tr>";

			while($row = mysqli_fetch_array($result)) {
				$trainingname = $row['Training_Name'];
				$startdate = $row['start_date'];
				$enddate = $row['end_date'];
				
				$surveyID = $row['survey_id'];
				$trainingID = $row['TrainingID'];

				$queryCheckBestaatAl = "SELECT * FROM SURVEY_ANSWERS WHERE UserID = '".$loginID."' AND SurveyID = '".$surveyID."'";
				$resultCheckBestaatAl = mysqli_query($conn,$queryCheckBestaatAl);

				if (mysqli_num_rows($resultCheckBestaatAl) == 0) {
					echo "<tr>";
					echo "<td>" . $trainingname . "</td>";
					echo "<td>" . $startdate. "</td>";			
					echo "<td>" . $enddate . "</td>";
					echo "<td>" . "<button class='button button1' onclick='surveyInvullen(". json_encode($surveyID).")'>Fill in survey</button>" . "</td>";
					//echo "<td><a href=maps.php?trainingID=".$row['TrainingID'] . ">Meer info</a></td>";				
					echo "</tr>";
				}				
			}
			echo "</center></table>";	
			echo "</div>";
		?>
		<script>
			function surveyInvullen(surveyID) {
				window.location.href = "survey_invullen.php?surveyID=" + surveyID + "&aantal=" + 1;
			}
		</script>
	</body>	
</html>

