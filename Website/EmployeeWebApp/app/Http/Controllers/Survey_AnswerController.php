<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Survey_answers;

use App\Survey_questions;

use App\Http\Controllers\Session;
class Survey_AnswerController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //

        return view('survey');
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    /*
    public function store(Request $request)
    {
        //opslagen van surveys en ophalen van de  view survey.blade.php
        //$waarde= $request->input('answer');
        $array = $request->except('_token');
        foreach ($array as $key => $value) {
            # code...
            $new_Answer= new Survey_answers();
            //wij moeten onze data naar een json convereren
            if(! is_array($value)){
                $newWaarde = $value('answer');
            } else {
                $newWaarde = json_encode($value['answer']);
            } 
        $new_Answer->Answer = $newWaarde;
        $new_Answer->question_ID = $key;
        $new_Answer->user_id= Auth::id();
        $new_Answer -> save(); 
        

    };
    return view('survey'); 


} */
 public function store(Request $request)
    {
        //opslagen van surveys en ophalen van de  view survey.blade.php
        //$waarde= $request->input('answer');
$Answer =[];
$QuestionID =[];
$survey_ID =[];
 $Answers = $request->input('Answer');
      foreach ($Answers as $row => $value ) {
        $Answer[] =  $value ;
        } 
        $QuestionIDs = $request->input('QuestionID');
        foreach ($QuestionIDs as $row => $value ) {
        $QuestionID[] =  $value ;
        }

     $survey_IDs = $request->input('survey_ID');
        foreach ($survey_IDs as $row => $value ) {
        $survey_ID[] =  $value ;
        }   
 for($i=0;$i<sizeof($Answer);$i++)
 { 
         $Survey_answers[] = [
           'Answer'=>$Answer[$i],
           'QuestionID'=> $QuestionID[$i],
           'SurveyID' => $survey_ID[$i]
       ];
   }
  Survey_answers::insert($Survey_answers);

    $questions = Survey_questions::all();
    \Session::flash('message', 'write something here '); 
    return view('survey')->with('questions', $questions);


} 






    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show()
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit()
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update()
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy()
    {
        //
    }
}
