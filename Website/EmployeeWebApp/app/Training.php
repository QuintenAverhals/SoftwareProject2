<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Training extends Model
{
    //
   public $table = "TRAINING";
   public $timestamps = false;
   protected $fillable =['Training_Name','start_date','end_date','start_time','end_time'];

  public function user() {
        return $this->belongsTo(Trainingperwerknemer::class);
    }

   public function location(){
   	return $this->belongsTo(Location::class);
   }  
    

   


}
