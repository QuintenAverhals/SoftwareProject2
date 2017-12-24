<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Login_Website;

use Mail; 

class CustomRegController extends Controller
{
    //

    public function showRegisterForm(){

    	return view('auth.reg');
    }

    public function showLoginForm(){

    	return view('auth.log');
    }
    
     public function register(Request $request){

        $this->validation($request);

        return $request->all();
        
        //Creating of random password
        //bij het registeren maken wij geen password daarom gaan wij een random password creeren en daarna sturen wij een email met rest password om het password te resten. 


    }  

    //bij het aanmaken van Restpassword moet het gesaved worden aan de db
   $password = generatePassword();

    $user = new Login_Website;
    $user->WebsiteUserName = $request->input('UserName');
    $user->Wachtwoord = $password; 
    $user->save();


    public static function generatePassword(){
      return bcrypt(str_random(35));

    }


     public function login(Request $request){
        
        return $this->validate($request,[
              'UserName' =>'required|string|max:255',
              'Wachtwoord'=>'required|string|max:255',

        ]);
               
        if(Auth::attempt(['UserName'=>$request->UserName,'Wachtwoord'=>$request->Wachtwoord])){
        	Login_Website::create($request->all());

        	return "Logged in!";

        } 
        return"Er is iets mis uitgelopen";     
         return direct('/');
    }

    }


   public function validation($results){
     return $this->validate($results,[

         //'email'=>'required|string|email|max:255|unique:users',
     	//the userName field will only be validated if it is present in the $results => Sometimes is a rule  
            
        'userName' => 'sometimes|required|string|max:255|unique:Login_Website',
        

        /*

        foreach ($results as $value => $value) {      
          $email = value->email[0];

          # code...

          //  Mail::to($results[$email])->send(new WelcomeMail($user));
 
        //return $user;
        }
        */
     ]);



   }

   public function data(){
   	$res= file_get_contents("http://services.odata.org/V4/(S(ykcab0l02qbs3euppy5louci))/TripPinServiceRW/People");

  //Ik heb hier mijn array aangemaakt
    $results=json_decode($res);
    //$userName = $results->value->UserName; 
     
 /*   foreach($results->value as $value)
      $emailAdres = $value->Email;
    endforeach */


    return $this->validation($results)with->$this->sendEmail($results);
 
   }
    
    public static function sendEmail($user){

      //Generate a new rest password token 

      $token = app('auth.password.broker')->createToken($user);

      //send email
      if(isset($results->Email)) {
        Mail:send('emails.WelcomeMail',['user'=>$user, 'token'=>$token],function($mail)use($user)){
        $email->from('masar.kbyeh@student.ehb.be','EmployeeWebsite');
        $email->to($results->Email,$results->UserName)->subject('Welkom to my website');
      }
      }
      
 } 




}
