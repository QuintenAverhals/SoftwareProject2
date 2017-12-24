<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Login_Website extends Model
{
    //

   public $table = "LOGIN_WEBSITE";
   protected $fillable = [
        'WebsiteUserName', 'Wachtwoord'
    ];
   protected $hidden = [
        'Wachtwoord', 'remember_token',
    ];


      

}
