<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
//use App\Http\Request;

use GuzzleHttp\Client ; 
//use GuzzleHttp\Message\Request;
use GuzzleHttp\Message\Response; 


class DataController extends Controller
{
    
   public function index(){
   
   $client = new Client();
   $res = $client->request('GET', 'http://services.odata.org/V4/(S(ykcab0l02qbs3euppy5louci))/TripPinServiceRW/People', [
    'auth' => ['user', 'pass']
]);
// 
   $body =  $res->getBody();
   $response = json_decode($body);

   //dd($response);
   foreach($response->value as $value){
    $UserName = $value->UserName; 
    $Email = $value->Emails[0] ;
    /*echo "<p>" .$value->UserName . "</p>";
    echo "<p>" .$value->Emails[0] ."</p>"; */
    echo "<p>" . $UserName. "</p>";
    echo "<p>" .    $Email ."</p>";

   }
 
   }

}
