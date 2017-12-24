<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Survey_answers extends Model
{
    //

   protected $fillable = ['QuestionID','Answer'];
   protected $hidden = ['QuestionID'];

   protected $table = "SURVEY_ANSWERS";
    public $timestamps = false;
  // protected $primaryKey ="SurveyID";


    public function user() {
        return $this->belongsTo(Login_Website::class);
    }
   public function question(){
   	return $this->belongsTo(Survey_questions::class);

   }

}
