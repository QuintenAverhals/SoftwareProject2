<?php
    // mysql database connection details
 	$host = "dt5.ehb.be";
	$username = "SP2_GR7";
	$password = "h7UsgLm";
	//zet naam van databank hieronder
	$dbname="SP2_GR7";

    // open connection to mysql database
    $connection = mysqli_connect($host, $username, $password, $dbname) or die("Connection Error " . mysqli_error($connection));
    
    // fetch mysql table rows
    $sql = "select * from TRAININGBOOK";
    $result = mysqli_query($connection, $sql) or die("Selection Error " . mysqli_error($connection));

    $fp = fopen('Backups/Training_book/training_book.csv', 'w');

    while($row = mysqli_fetch_assoc($result))
    {
        fputcsv($fp, $row);
    }
    
    fclose($fp);

    //close the db connection
    mysqli_close($connection);
?>