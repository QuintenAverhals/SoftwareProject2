
<!DOCTYPE html>
<html>
	<head>

		<title>Send reminders</title>
		<link type='text/css' rel='stylesheet' href='software_project.css'/>
	</head>
<body>
	<?php
		session_start();
		include('connect.php');	

				
		$query="SELECT * FROM TRAINING t JOIN TRAININGPERWERKNEMER tw ON(t.TrainingID = tw.TrainingID) JOIN LOGIN_WEBSITE lw ON(tw.LoginID = lw.LoginID) WHERE (t.start_date -CURRENT_DATE = 1)";
		$result = mysqli_query($conn,$query);

		if (mysqli_num_rows($result) != 0) {

			while ($row = mysqli_fetch_array($result)) {
				$trainingName = $row['Training_Name'];
				$startDate = $row['start_date'];
				$endDate = $row['end_date'];
				$startTime = $row['start_time'];
				$endTime = $row['end_time'];
				$userName = $row['WebsiteUserName'];
				$email = $row['Email'];

				$to = $email;
				$headers = 'From: materiaal@Kaaimannen.be' ."\r \n".
				'Reply-To: materiaal@Kaaimannen.be' ."\r \n".
				'X-Mailer: PHP/' . phpversion()."\r \n".
				'MIME-Version: 1.0' . "\r\n".
				'Content-type: text/html; charset=iso-8859-1';
				$subject = "Reminder for training ".$trainingName. " on ".$startDate;


				$message = '<html><body>';
				$message .= '<table rules="all" style="border-color: #666;" cellpadding="10">';
				$message .= "<tr style='background: #eee;'><td><strong>Name:</strong> </td><td>" . strip_tags($userName) . "</td></tr>";
				$message .= "<tr style='background: #eee;'><td><strong>Training name:</strong> </td><td>" . strip_tags($trainingName) . "</td></tr>";
				$message .= "<tr style='background: #eee;'><td><strong>Start date:</strong> </td><td>" . strip_tags($startDate) . "</td></tr>";
				$message .= "<tr style='background: #eee;'><td><strong>End date:</strong> </td><td>" . strip_tags($endDate) . "</td></tr>";
				$message .= "<tr style='background: #eee;'><td><strong>Start time:</strong> </td><td>" . strip_tags($startTime) . "</td></tr>";
				$message .= "<tr style='background: #eee;'><td><strong>End time:</strong> </td><td>" . strip_tags($endTime) . "</td></tr>";
				$message .= "</table>";
				$message .= "</body></html>";

				//echo "The email is sent to your e-mail address.";
				mail($to,$subject,$message,$headers);
			}
		}	
	?>
</body>
</html>
