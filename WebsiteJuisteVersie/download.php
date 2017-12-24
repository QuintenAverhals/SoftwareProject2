<!DOCTYPE html>
<html>
	<head>

		<title>Certificaat downloaden</title>
	</head>
	<body>
		
		<?php	       
			$file_name = $_GET['doc_name'];
			$file_url = 'http://www.kaaimannen.be/Seppe/uploads/' . $file_name;
			header('Content-Type: application/octet-stream');
			header("Content-Transfer-Encoding: Binary"); 
			header("Content-disposition: attachment; filename=\"".$file_name."\""); 
			ob_clean();
    		flush();
			readfile($file_url);
			exit;
			//echo "string";
			//$document_download = $_GET['doc_name'];
			//$document_download = 'Colruyt.pdf';

			/*$ftp_server = "ftp.jijmaaktmechelen.be";
		    $ftp_username = "jijmaaktmechelen.be";
		    $ftp_userpass = "JijMaaktMechelen";
		    $ftp_conn = ftp_connect($ftp_server) or die("Could not connect to $ftp_server");
		    $login = ftp_login($ftp_conn, $ftp_username, $ftp_userpass);

			// define some variables
			$local_file = 'uploads/'.$document_download;
			$server_file = 'Seppe/SW2/'. $document_download;

			// try to download $server_file and save to $local_file
			if (ftp_get($ftp_conn, $local_file, $server_file, FTP_BINARY)) {
			    echo "Successfully written to $local_file\n";
			} else {
			    echo "There was a problem\n";
			}

			// close the connection
			ftp_close($conn_id);*/

/*
			$document_php_server = 'uploads/'.$document_download;

			if (file_exists($document_php_server)) {
			    header('Content-Description: File Transfer');
			    header('Content-Type: uploads/');
			    header('Content-Disposition: attachment; filename="'.basename($document_php_server).'"');
			    header('Expires: 0');
			    header('Cache-Control: must-revalidate');
			    header('Pragma: public');
			    header('Content-Length: ' . filesize($document_php_server));
			    readfile($document_php_server);
			    exit;
			}*/

			/*if(unlink($document_php_server)){
				echo "Verwijderd!";
			}
			else{
				echo "Mislukt...";
			}*/
		?>
	</body>	
</html>
