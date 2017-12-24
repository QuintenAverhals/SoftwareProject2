<!DOCTYPE html>
<html>
	<head>

		<title>Gevolgde trainingen</title>
		<link type='text/css' rel='stylesheet' href='software_project.css'/>
	</head>
	<body>
		
		<?php
			include('menubalk.html');
			session_start();
			include('connect.php');			
			//ZET HIER DE LOGIN ID DIE WORDT DOORGEGEVEN VAN DE VORIGE PAGINA (PERSOONLIJK SCHEMA)
			//$loginID = 1;
			if(isset($_SESSION['ingelogd'])){
				$loginID = $_SESSION['loginID'];
				//echo $loginID;
			}
			else{
				header("Location: index.php");
			}
			
			$query="SELECT * FROM TRAINING t JOIN TRAININGPERWERKNEMER tw ON (t.TrainingID = tw.TrainingID) JOIN LOCATION l ON(t.location_id = l.locationID) WHERE tw.LoginID = $loginID AND end_date < CURDATE() AND visibility != 0";
			$result = mysqli_query($conn,$query);
			echo "<div id='tabel'>";
			echo "<table class='responstable'><center>";
			echo "<tr>";
			echo "<th>" . "Training name" . "</th>";
			echo "<th>" . "Start date" . "</th>";
			echo "<th>" . "End date" . "</th>";
			echo "<th>" . "Start time" . "</th>";
			echo "<th>" . "End time" . "</th>";
			echo "<th>" . "Streetname + number" . "</th>";
			echo "<th>" . "City + zipcode" . "</th>";
			echo "<th>" . "Country" . "</th>";
			echo "<th>" . "Status" . "</th>";
			echo "<th>" . "More info". "</th>";
			echo "</tr>";

			while($row = mysqli_fetch_array($result)) {
				$trainingname = $row['Training_Name'];
				$startdate = $row['start_date'];
				$enddate = $row['end_date'];
				$starttime = $row['start_time'];
				$endtime = $row['end_time'];
				$streetname = $row['streetName'];
				$bus = $row['bus'];
				$streetnumber = $row['streetnumber'];
				$city = $row['city'];
				$zipcode = $row['zipcode'];
				$country = $row['country'];
				$trainingID = $row['TrainingID'];
				$cancel = $row['cancel'];
				$afgelast = 'CANCELED';
				$ongoing = 'ONGOING';


				echo "<tr>";
				echo "<td>" . $trainingname . "</td>";
				echo "<td>" . $startdate. "</td>";			
				echo "<td>" . $enddate . "</td>";
				echo "<td>" . $starttime . "</td>";
				echo "<td>" . $endtime . "</td>";
				echo "<td>" . $streetname . ' ' .$streetnumber. '/' .$bus ."</td>";
				echo "<td>" . $city. ' - ' .$zipcode . "</td>";
				echo "<td>" . $country . "</td>";
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
		?>
		<script>
			function meerInfo(trainingID) {
				window.location.href = "maps.php?trainingID=" + trainingID;
			}
		</script>
	</body>	
</html>

