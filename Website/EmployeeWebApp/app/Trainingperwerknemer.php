<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Trainingperwerknemer extends Model
{
    //


       public function werknemer(){
    	//eerst de naam van Model die wilt het linken , tweede is de forgein key , en dan de primary key
    	return $this->hasMany(Training::class);
    }
}
