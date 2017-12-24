<!DOCTYPE html>
<html>
	<head>

		<title>Overzicht certificaten</title>
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
				
			
			//echo "<class='buttonUpload button1' '>Certificaat uploaden</button>";
			echo "<a href='chooseFile.php' class='buttonSave button1'>Certificaat uploaden</a>";


			
			$query="SELECT * FROM CERTIFICATE c JOIN TRAINING t ON (c.TrainingID = t.TrainingID) JOIN LOGIN_WEBSITE l ON (c.employee_name = l.WebsiteUserName) WHERE l.LoginID = $loginID";
			$result = mysqli_query($conn,$query);

			echo "<div id='tabel'>";
			echo "<table class='responstable'><center>";
			echo "<tr>";
			echo "<th>" . "CertificateID" . "</th>";
			echo "<th>" . "Training" . "</th>";
			echo "<th>" . "User" . "</th>";
			echo "<th>" . "Employee Name" . "</th>";
			echo "<th>" . "Document Name" . "</th>";
			echo "<th>" . "Downloaden" . "</th>";
			echo "</tr>";

			while($row = mysqli_fetch_array($result)) {
				$trainingID = $row['TrainingID'];
				$userID = $row['UserID'];
				$docName = $row['doc_name'];

				/*$query3 ="SELECT Training_Name FROM Training WHERE TrainingID = trainingID";
				$result3 = mysqli_query($conn,$query3);
				$row3 = mysqli_fetch_array($result3);
				$trainingName = $row3['Training_Name'];*/

				echo "<tr>";
				echo "<td>" . $row['CertificateID'] . "</td>";
				echo "<td>" . $row['Training_Name']. "</td>";			
				echo "<td>" . $row['WebsiteUserName'] . "</td>";
				echo "<td>" . $row['employee_name'] . "</td>";
				echo "<td>" . $row['doc_name'] . "</td>";
				echo "<td>" . "<button class='button button1' onclick='downloaden(". json_encode($docName).")'>Downloaden</button>" . "</td>";
				//echo "<td><a href=download.php?doc_name=".$row['doc_name'] . ">Downloaden</a></td>";
				echo "</tr>";
			}
			echo "</center></table>";	
			echo "</div>";
		?>
		<script>
			function downloaden(docName) {
				window.location.href = "download.php?doc_name=" + docName;
			}
		</script>
		<script>
			function uploaden() {
				window.location.href = "chooseFile.php";
			}
		</script>
	</body>	
</html>

