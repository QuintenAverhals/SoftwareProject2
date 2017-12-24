<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use GuzzleHttp\Client ; 
//use GuzzleHttp\Message\Request;
use GuzzleHttp\Message\Response; 
use GuzzleHttp\Exception\GuzzleException;
use App\Login_Website;
use Illuminate\Support\Facades\Input;

use Auth;
use Mail; 
use App\Mail\SetPasswordMail; 

//use App\Http\Controllers\Controller;

class CustomRegController extends Controller
{
    //
//wij gaan eerst de register view tonen


  public function index(){}

  public function create(){
    return view('auth.reg');

  }

  public function Userdata() {

    $client = new Client();
    $res = $client->request('GET', 'http://services.odata.org/V4/(S(ykcab0l02qbs3euppy5louci))/TripPinServiceRW/People', [
      'auth' => ['user', 'pass']
    ]);
// 
    $body =  $res->getBody();
    $response = json_decode($body);
    dd($response);
    

       $this->store($response);
  }

  public function store($response){
      // dd($response);

  //validatie the user 
    $this->validate(request(),[
      'Username' => 'required'
    ]);
  //dd( $response->value[0]);
    $Email = $response->value[0]->Emails[0];
    $Email = $response->value->Emails[0];
    //dd($Email);
     //dd($Email);

  //Gaat checken of username bestaat in LOGIN_WEBSITE
    if (Login_Website::where('WebsiteUserName', '=', Input::get('Username'))->count() > 0) {
   //Stuur email om wachtwoord te maken 
      Mail::to($Email)->send(new SetPasswordMail);

    } else
    {
  //check of the user bestaat in de ODATA 

   //dd($response);
      foreach($response->value as $value){
        if(! Input::get('Username')==$value->UserName){
      //send email
          Mail::to($Email)->send(new SetPasswordMail);
          Login_Website::create(['UserName']);
          echo  "Welcome";
     return back()->withErrors([
        'message'=> 'user niet gekend in onze data'
    ]);        

      }
    }}


    
    return redirect()->route('survey');

  }
  //Create and Save the user

  //Send email with instructrion 

  //Give a message, er word een email verzonden , please confirm set your password. 

  
}








