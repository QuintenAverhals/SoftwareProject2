<?php


require_once("class.phpmailer.php");
require_once("formvalidator.php");
class employee
{

    public $username;
    public $pwd;
    public $database;
    public $tablename;
    public $connection;
    public $link;
    public $error_message;
    public $emailfrom="masarkbyeh@gmail.com";
    //-----Initialization -------
    function employee()
    {
       
    }
     function GetFromAddress()
     {
       return $this->emailfrom;
     }
    function siteLink($UserName)
    {
        //De localhost moet veranderen naar je host mail , anders werkt de mail niet! 
      $this->link = "http://www.kaaimannen.be/Seppe/Set_Password.php?User=".$UserName;
    }

    function InitDB($host,$uname,$pwd,$database,$tablename)
    {
        $this->db_host  = $host;
        $this->username = $uname;
        $this->pwd  = $pwd;
        $this->database  = $database;
        $this->tablename = $tablename;
        
    } 
    function HandleError($err)
    {
        $this->error_message .= $err."\r\n";
    }
    
    function HandleDBError($err)
    {
        $this->HandleError($err."\r\n mysqlerror:".mysqli_error($this->connection));
    }
function getOdataEmail($UserName)

{ 
$url ="http://services.odata.org/V4/(S(ykcab0l02qbs3euppy5louci))/TripPinServiceRW/People('".$UserName."')";
$json = file_get_contents($url);
$obj = json_decode($json);
if(isset($obj))
{ /*
	echo "UserName : ".$obj->UserName."<br>";
	echo "FisrtName : ".$obj->FirstName."<br>";
	//save to db 
	// sendEmail to :: $obj->Emails[0]
	 echo "Email : ".$obj->Emails[1]."<br>" ;*/
return  $obj->Emails[0];
       
}
else
{ 
return null;
}
}

 function SendUserWelcomeEmail($Email,$userName){
      
    /*$this->siteLink($userName);
 
    $mailer = new PHPMailer();
        
    $mailer->CharSet = 'utf-8';
        
    $mailer->AddAddress($Email,$userName);
        
    $mailer->Subject = "Welcome to ";

    $mailer->From = $this->GetFromAddress();        
    //dat is de body mail , bij link moet de user klikken komt hij aan de Set_password om zijn wachtwoord te verandeen
    $mailer->Body ="Hello ".$userName."\r\n\r\n".
    "Welcome! set your password please !.\r\n".$this->link;


    if(!$mailer->Send()){
        $this->HandleError("Failed sending user welcome email.");
        return false;        
    }  
    return true;*/
    
}

//login
function CheckLoginInDB($username,$password)
    {
        if(!$this->DBLogin())
        {
            $this->HandleError("Database login failed!");
            return false;
        }          
        $username = $this->SanitizeForSQL($username);
        $pwdmd5 = md5($password);
        $qry = "Select email from $this->tablename where username='$username' and password='$pwdmd5'";
        
        $result =mysqli_query($qry,$this->connection);
        
        if(!$result ||mysqli_num_rows($result) <= 0)
        {
            $this->HandleError("Error logging in. The username or password does not match");
           // return false;
            
        }
        
        $row =mysqli_fetch_assoc($result);
        
        
        $_SESSION['name_of_user']  = $row['name'];
        $_SESSION['email_of_user'] = $row['email'];
        
        return true;
    }
    
    
    function ChangePasswordInDB($user_rec, $newpwd)
    {
        $newpwd = $this->SanitizeForSQL($newpwd);
        
        $qry = "Update $this->tablename Set password='".md5($newpwd)."' Where  id_user=".$user_rec['id_user']."";
        
        if(!mysql_query( $qry ,$this->connection))
        {
            $this->HandleDBError("Error updating the password \nquery:$qry");
            return false;
        }     
        return true;
    }
/// @@@@@@@@@@@@@@@@@@ RegisterUser @@@@@@@@@@@@@@@@@@@@@@@@@
      function Register($UserName,$password)
    {
      
$data["UserName"] = $UserName;
$data["email"] = $this->getOdataEmail($UserName);
$data["password"] = $password;

        if(!$this->InsertIntoDB($data))
        {
            return false;
        }
        return true;
    }

     function RegisterUser($UserName)
    {
   
        
       /* if(!$this->ValidateRegistrationSubmission())
        {
            return false;
        }*/
      
        if(!$this->SaveToDatabase($UserName))
        {
            return false;
        }
        return true;
    }


    /// @@@@@@@@@@@@@@@@@@ validaiion @@@@@@@@@@@@@@@@@@@@@@@@@
    function ValidateRegistrationSubmission()
    {
       
        
        $validator = new FormValidator();
        $validator->addValidation("UserName","req","Please fill in UserName");

        
        if(!$validator->ValidateForm())
        {

            $error='';
            $error_hash = $validator->GetErrors();
            foreach($error_hash as $inpname => $inp_err)
            {
                $error .= $inpname.':'.$inp_err."\n";
            }
            $this->HandleError($error);
            return false;
        }        
        return true;
    }
    function SaveToDatabase($UserName)
    {
        
        if(!$this->DBLogin())
        {
            $this->HandleError("Database login failed!");
            return false;
        }
          
        if(!$this->Ensuretable())
        {
            return false;
        }

        
        if(!$this->IsFieldUnique($UserName))
        {
            $this->HandleError("This UserName is already used. Please try another username");
            return false;
        } else {  $this->HandleError("This UserName not exist in database"); }
         
     $email =$this->getOdataEmail($UserName);
         if($email!=null)
        {
             
            if($this->SendUserWelcomeEmail($email,$UserName))
                {$this->HandleError("Email sent"); }
            else  $this->HandleError("Email not sent");
            return true;
        }
        else $this->HandleError("This UserName not exists in odata");
               
return false;
     

       
    }
     function IsFieldUnique($where)
    {
       
        $qry = "select WebsiteUserName  from $this->tablename where WebsiteUserName ='".$where."'";
        $result =mysqli_query($this->connection,$qry);   
        if($result &&mysqli_num_rows($result) > 0)
        {
            return false;
        }
        return true;
    }

     function InsertIntoDB($data)
    { if(!$this->DBLogin())
        {
            $this->HandleError("Database login failed!");
            return false;
        }
          
        
        $qry = 'insert into '.$this->tablename.'(`Email`,`WebsiteUserName`,`Wachtwoord`,`visibility`,`salt`) values ("'. $data['email'].'","' . $data['UserName'].'","'.md5($data['password']).'",1,0)';

        if(!mysqli_query($this->connection,$qry))
        {
            $this->HandleError("Error inserting data to the table\nquery:$qry");
            return false;
        }        
        return true;
    }
    //Maakt connectie met de db DBLogin

function DBLogin()
    {

        $this->connection = mysqli_connect($this->db_host,$this->username,$this->pwd);

        if(!$this->connection)
        {   
            $this->HandleDBError("Database Login failed! Please make sure that the DB login credentials provided are correct");
            return false;
        }

        if(!mysqli_select_db( $this->connection,$this->database))
        {
            $this->HandleDBError('Failed to select database: '.$this->database.' Please make sure that the database name provided is correct');
            return false;
        }

        if(!mysqli_query($this->connection,"SET NAMES 'UTF8'"))
        {
            $this->HandleDBError('Error setting utf8 encoding');
            return false;
        }
        return true;
    }    
    
    function Ensuretable()
    {
        $result =mysqli_query($this->connection,"SHOW COLUMNS FROM $this->tablename");   
        if(!$result ||mysqli_num_rows($result) <= 0)
        {
            return $this->CreateTable();
        }
        return true;
    }
}
?>