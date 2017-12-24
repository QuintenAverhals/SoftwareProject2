<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Training extends Model
{
    //
   public $table = "TRAINING";
   public $timestamps = false;

   public function location(){
   	return $this->belongsTo('App\Location','location_id','locationID');
   }
   
    
}
