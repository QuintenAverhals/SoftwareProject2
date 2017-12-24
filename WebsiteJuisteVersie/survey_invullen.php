
<!DOCTYPE html>
<html>
	<head>

		<title>Survey invullen</title>
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
		
		$surveyID = $_GET['surveyID'];
		$aantal = $_GET['aantal'];
		
		

		if(ISSET($_POST['submit'])){
			$teller = $_POST['tellerVraag'];
			$surveyNr = $_POST['surveyNummer'];
			//echo $teller;

			$insertQuestion = "INSERT INTO SURVEY_ANSWERS (UserID, SurveyID, QuestionID, Answer) VALUES ('".$loginID."', '".$surveyNr."', '".($teller-1)."', '".$_POST['question']."')";
			//echo $insertLocation;
			$resultQuestion = mysqli_query($conn,$insertQuestion);

			$queryGetAantal = "SELECT * FROM SURVEY_QUESTIONS WHERE survey_ID = '".$surveyNr."' ORDER BY question_ID DESC LIMIT 1";
			$resultGetAantal = mysqli_query($conn,$queryGetAantal);

			while ($rowGetAantal = mysqli_fetch_array($resultGetAantal)) {
				$maxAantal = $rowGetAantal['question_ID'];
			}

			if($teller <= $maxAantal){
				echo "<button class='buttonSave button1' onclick='surveyInvullenVolgende(". json_encode($surveyNr).", ".json_encode($teller).")'>Volgende vraag</button>";
				//header("URL= survey_invullen.php?surveyID='".$surveyID."'&aantal='".$aantal."'");
			}
			else{
				$queryUserName = "SELECT WebsiteUserName FROM LOGIN_WEBSITE WHERE LoginID = '".$loginID."'";
				$resultUserName = mysqli_query($conn,$queryUserName);
				while ($rowUserName = mysqli_fetch_array($resultUserName)) {
					$username = $rowUserName['WebsiteUserName'];
				}

				$action = "User: " .$username. " filled in survey: " .$surveyNr . "." ;
				//echo $action;
		 		$log = "INSERT INTO LOGFILE_WEBSITE (LoginID, action, datum) VALUES ('" .$loginID. "', '" . $action. "', NOW())";
			    //echo $query;
				$resultLog = mysqli_query($conn,$log);
				echo "Bedankt, voor het invullen van de survey.";
				header("Refresh: 3; URL= overzicht_surveys.php");
			}

		}
		else{
			$query = "SELECT * FROM SURVEY_QUESTIONS WHERE survey_ID = '".$surveyID."' AND question_ID = '".$aantal."'";
			//echo $query;
			$result = mysqli_query($conn,$query);

			while ($row = mysqli_fetch_array($result)) {
				$question = $row['Question'];
			}
			$aantal = $aantal + 1;

			echo "Indien u de webpagina verlaat, dient u uw administrator te verwittigen om de survey opnieuw te activeren.";
			echo '
			<form action="survey_invullen.php" method="post">
				<table class="responstable"><center>
					<tr>
						<td>'.$question.': </td>
						<td><input type="text" name="question" id="question" required></td>
					</tr>
				</center></table>
				<input type="submit" value="Save question" name="submit" class="buttonSave button1">
				<input type="hidden" name="tellerVraag" value="'.$aantal.'">
				<input type="hidden" name="surveyNummer" value="'.$surveyID.'">
			</form>';
		}
	?>
	<script>
		function surveyInvullenVolgende(surveyID, aantal) {
			window.location.href = "survey_invullen.php?surveyID=" + surveyID + "&aantal=" + aantal;
		}
	</script>
</body>
</html>
