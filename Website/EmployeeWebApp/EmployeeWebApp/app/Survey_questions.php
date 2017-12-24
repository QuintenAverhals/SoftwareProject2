<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Survey_questions extends Model
{
    //


    public function user(){
  	//vermoedelijk moet deze aangepast worden naar de juist user 
  	return $this->belongsTo(User::class);


  }


  public function Survey_answers(){

  	return $this->hasMany('App\Survey_answers','QuestionID','SurveyID');
  }

  protected $table='SURVEY_QUESTIONS';
  

}
