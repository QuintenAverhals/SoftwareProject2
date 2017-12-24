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


//Ophalen van trainingen 
//Route::resource('training','TrainingController');

//Route::get('training/data', 'TrainingController@data');
//Route::get( 'training/upload', 'TrainingController@upload');
//Route::post('training/upload', 'TrainingController@do_upload');
//Route::get('training/{training}/multipledelete', 'TrainingController@multipledelete');
Route::resource('training', 'TrainingCon');
Route::get('data','DataController@index');



//Login Logout

//get('/auth/login','Auth\AuthController@getLogin');
//post('/auth/login','Auth\AuthController@postLogin');
//get('/auth/logout','Auth@AuthController@getLogout');

//register

//get('/auth/register','Auth\AuthController@getRegister');
//post('/auth/register','Auth\AuthController@postRegister');





//Ophalen van Survy 
Route::resource('survey', 'Survey_AnswerController');

//Route::get('/home', 'HomeController@index')->name('home');

///Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');
//Route::get('/odataTest');

Route::get('register', 'CustomRegController@showRegisterForm')->name('register');

Route::post('register', 'CustomRegController@register');
Route::get('login', 'CustomRegController@showLoginForm')->name('login');

Route::post('login', 'CustomRegController@login');
