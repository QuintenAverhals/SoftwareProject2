
<!DOCTYPE html>
<html>
<head>
	<title></title>
</head>
<body>
	<?php
	    $servername = "dt5.ehb.be";
		$username = "SP2_GR7";
		$password = "h7UsgLm";
		//zet naam van databank hieronder
		$db="SP2_GR7";

		// Create connection
		$conn = mysqli_connect($servername, $username, $password,$db);


		// Check connection
		if ($conn->connect_error) {
		    die("Connection failed: " . $conn->connect_error);
		} 
		//echo "Connected successfully";

		$optie = "SELECT Background_Color FROM OPTIONS";
		$resultOptie = mysqli_query($conn,$optie);

		while ($rowOptie = mysqli_fetch_array($resultOptie)) {
			$kleur = $rowOptie['Background_Color'];
			//echo $kleur;
		}
	?>

	<style>
		body {
		    background-color: #<?php echo $kleur?>;
		}
	</style>
	</body>
</html>