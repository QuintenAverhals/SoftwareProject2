<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

//get => 
//post => om informatie te sturen 
// /user , /contact
// return is response 

/* Route::get('/', function () {
    return view('welcome');
});

*/ 
Route::get('/', function () {
    return '<center> <h1>Welkom to Employee online website </h1> </center> ';
});


/*

//Route :: get();
get('/', function(){

    return 'Welkom to Employee online website ';

});

*/

//De naam wordt getoond als een bepaalde naam wordt ingegeven 
Route::get('user/{userName}',function($userName){
 return 'Welkom ' .$userName ; 

})->where('userName','[A-Za-z]+');


//Ophalen van tr('ainingen 
//Route::resource('training','TrainingController');

//Route::get('training/data', 'TrainingController@data');
//Route::get( 'training/upload', 'TrainingController@upload');
//Route::post('training/upload', 'TrainingController@do_upload');
//Route::get('training/{training}/multipledelete', 'TrainingController@multipledelete');
Route::resource('training', 'TrainingCon');







//Ophalen van Survy 




