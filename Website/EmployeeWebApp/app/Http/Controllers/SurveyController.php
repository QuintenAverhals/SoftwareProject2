<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class SurveyController extends Controller
{
    //

//wordt eerst naar de auth gestuurd als het niet authogiseerd  wordt de controller niet door getsuurd
public function __construct(){
	$this->middleware('auth');

}

/*
public function survey(Request $request){
	$surveys = Survey::get();

   return view('survey' ,compact('surveys'));
} */




}
