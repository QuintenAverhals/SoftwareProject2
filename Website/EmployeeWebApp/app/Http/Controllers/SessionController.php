<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Login_Website;

class SessionController extends Controller
{
    //


public function create(){

	  return view('auth.log');


}

public function store(){

//Auth attempt to login the user 
	if(! auth()->attempt(request(['WebsiteUserName','Wachtwoord']))){
		return back()->withErrors([
        'message'=> 'Als je nog niet geregistereed doe je het eerst, of check nog eens je Usernaam , en password'
		]);
	}

}

 public function destroy(){
 	auth()->logout();

 	return redirect()->home(); 
 }
}
