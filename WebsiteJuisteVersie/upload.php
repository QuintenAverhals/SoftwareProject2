<!DOCTYPE html>
<html>
	<head>
		<title>Upload file</title>
		<link type='text/css' rel='stylesheet' href='software_project.css'/>
	</head>
<body>


<?php

	function bevestiging($geslaagd){
    	if($geslaagd == 0){
			echo "Sorry, there was an error uploading your file.";
    	}
    	else {
    		echo "The file has been uploaded.";
    	}
    	header("Refresh: 3; URL= overzicht_certificaten.php");		
    }

    function dubbelFile(){
    	echo "Sorry, file already exists.";
    	header("Refresh: 3; URL= overzicht_certificaten.php");		
    }

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
	//$loginID = 23;
	
	$trainingsID = $_POST['trainingsID'];
	$userID = 99;
	//$employeeName = $_POST['employeeName'];



   /* $ftp_server = "ftp.jijmaaktmechelen.be";
    $ftp_username = "jijmaaktmechelen.be";
    $ftp_userpass = "JijMaaktMechelen";*/

	$target_dir = "uploads/";
	$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
	$fileName = basename($_FILES["fileToUpload"]["name"]);
	$uploadOk = 1;
	$imageFileType = pathinfo($target_file,PATHINFO_EXTENSION);

	/*$ftp_conn = ftp_connect($ftp_server) or die("Could not connect to $ftp_server");
    $login = ftp_login($ftp_conn, $ftp_username, $ftp_userpass);

    // define some variables
	$server_file = 'Seppe/SW2/'. basename($_FILES["fileToUpload"]["name"]);

	// try to download $server_file and save to $local_file
	ftp_get($ftp_conn, $target_file, $server_file, FTP_BINARY);*/

	if (file_exists($target_file)) {
	    //echo "Sorry, file already exists.";
        //echo "<br>";
        //fileAlreadyExists();
	    //$uploadOk = 0;
	    
	   	dubbelFile();


	    //unlink($target_file);
	}
	else{
		if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) {
        //echo "The file ". basename( $_FILES["fileToUpload"]["name"]). " has been uploaded.";
        //echo "<br>";

		$query2 ="SELECT WebsiteUserName FROM LOGIN_WEBSITE WHERE LoginID = $loginID";
		$result2 = mysqli_query($conn,$query2);
		while($row2 = mysqli_fetch_array($result2)){
			$username = $row2['WebsiteUserName'];			
		}

        $query = "INSERT INTO CERTIFICATE (TrainingID, UserID, employee_name, doc_name) VALUES ('" . $trainingsID. "', '" . $userID. "', '" . $username. "' , '". $fileName."')";
	    //echo $query;
		$result = mysqli_query($conn,$query);
		$uploadOk = 1;
		bevestiging($uploadOk);

		$action = "User: " .$username. " uploaded certificate: " .$fileName . " to server." ;
		//echo $action;
 		$log = "INSERT INTO LOGFILE_WEBSITE (LoginID, action, datum) VALUES ('" .$loginID. "', '" . $action. "', NOW())";
	    //echo $query;
		$resultLog = mysqli_query($conn,$log);
	    } 
	    else {
	        //echo "Sorry, there was an error uploading your file.";
	        //echo "<br>";
	        //uploadMislukt();
	        $uploadOk = 0;
	        bevestiging($uploadOk);
    	}
	}

	
 	

    /*$ftp_conn = ftp_connect($ftp_server) or die("Could not connect to $ftp_server");
    $login = ftp_login($ftp_conn, $ftp_username, $ftp_userpass);

    $remote_file = "Seppe/SW2/" . basename($_FILES["fileToUpload"]["name"]);
    // upload file
    if (ftp_put($ftp_conn, $remote_file, $target_file, FTP_ASCII)){
    	echo "Successfully uploaded $file.";
      	echo "<br>";
    }
    else{
      	echo "Error uploading $file.";
      	echo "<br>";
    }

    // close connection
    ftp_close($ftp_conn);
    
    //unlink($target_file);*/
?>
</body>
</html>
