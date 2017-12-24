<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Survey_answers extends Model
{
    //

   protected $fillable = "Answer";
   protected $table = "SURVEY_ANSWERS";
  // protected $primaryKey ="SurveyID";

   public function survey_Question(){
   	return $this->belongsTo('App/Survey_questions','survey_ID','question_ID');

   }

}
