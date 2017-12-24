<?php

namespace App;

use Illuminate\Database\Eloquent\Model;


class Survey_questions extends Model
{
    //post



  public function answers(){

  	return $this->hasMany(Survey_answers::class);
  }

  protected $table='SURVEY_QUESTIONS';
  

}
