<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Location extends Model
{
    //


    public function trainingen(){
    	//eerst de naam van Model die wilt het linken , tweede is de forgein key , en dan de primary key
    	return $this->hasMany('App\Training','location_id','TrainingID');
    }
}
