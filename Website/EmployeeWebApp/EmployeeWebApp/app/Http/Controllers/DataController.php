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
   /*
   $client = new Client();
   $data_response = $client->get('http://services.odata.org/V4/(S(ykcab0l02qbs3euppy5louci))/TripPinServiceRW/People');

   $response = json_decode($data_response);
  //  $response =  json_decode(json_encode( $data_response),true);
  

   return view('odataTest',compact('response')); 

*/
  $client = $client = new Client();

  $res= file_get_contents("http://services.odata.org/V4/(S(ykcab0l02qbs3euppy5louci))/TripPinServiceRW/People");

 // $times=json_encode($res);
  //Ik heb hier mijn array aangemaakt
  $results=json_decode($res);
  
  // print_r($results);


  /*foreach( $results as $result){
  	  print_r($result);

  } */

//return view('odataTest',['results'=>$results]); 
  return view('data',compact('results')); 

   }

}
