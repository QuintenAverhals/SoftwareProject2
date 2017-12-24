<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class PasswordsetController extends Controller
{
    //


    public function passwordset($token){

    	return view('passwordSet')->with(['token'=>$token]);
    }
}
