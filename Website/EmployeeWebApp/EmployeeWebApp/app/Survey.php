<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Survey extends Model
{
    //


  public function user(){
  	//vermoedelijk moet deze aangepast worden naar de juist user 
  	return $this->belongsTo(User::class);


  }




}
