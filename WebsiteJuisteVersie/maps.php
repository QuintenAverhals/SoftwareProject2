<!DOCTYPE html>
<html>
	<head>

		<title>Info specifieke training</title>
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
			
			$trainingID = $_GET['trainingID'];
			//$yourAddress = 'Nijverheidskaai 170 Anderlecht';



			$query="SELECT * FROM TRAINING t JOIN LOCATION l ON(t.location_id = l.locationID) WHERE t.TrainingID = $trainingID ";
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
				$fulladres = $streetname . ' ' . $streetnumber . ' ' . $bus . ' ' . $zipcode . ' ' . $city . ' ' . $country;
				//echo $fulladres;

				echo "<tr>";
				echo "<td>" . $trainingname . "</td>";
				echo "<td>" . $startdate. "</td>";			
				echo "<td>" . $enddate . "</td>";
				echo "<td>" . $starttime . "</td>";
				echo "<td>" . $endtime . "</td>";
				echo "<td>" . $streetname . ' ' .$streetnumber. '/' .$bus ."</td>";
				echo "<td>" . $city. ' - ' .$zipcode . "</td>";
				echo "<td>" . $country . "</td>";
				echo "</tr>";
			}
			echo "</center></table>";	
			echo "</div>";
		?>
		<iframe width="640" height="410" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" class="maps" src="https://maps.google.it/maps?q=<?php echo $fulladres; ?>&output=embed"></iframe>
	</body>	
</html>

