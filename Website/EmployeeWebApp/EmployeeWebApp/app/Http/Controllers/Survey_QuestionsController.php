<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

App\Survey_questions;
class Survey_QuestionsController extends Controller
{
    //

  



//ophalen van questions
public function questions(){

$questions = DB::table('Survey_questions')->count(DB::raw('DISTINCT question_ID')) -> get();
return view('survey',['questions' => $questions]);

}
}
