<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use DB;


 use App\Survey_questions;
class Survey_QuestionsController extends Controller
{
    
//ophalen van questions
public function questions(){

//$questions = DB::table('SURVEY_QUESTIONS')->count(DB::raw('DISTINCT question_ID'))->get(['Question']);

	//$questions = DB::table('SURVEY_QUESTIONS')->pluck('Question');
	//dd($questions);
	$questions = Survey_questions::all();
	//dd($questions);
     //return view(('survey'), ['questions' => $q]);

	return view('survey', compact( 'questions'));

//print_r($questions);
}



}
