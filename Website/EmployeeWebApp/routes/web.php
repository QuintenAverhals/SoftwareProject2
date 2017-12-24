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

Route::resource('training', 'TrainingCon');
//check of de data bestaat 
Route::get('data','DataController@index');
//test van het ophalen van de questions 
Route::get('test','Survey_QuestionsController@questions');
//het versturen van de token naar een nieuwe gebruiker 
Route::get('/auth/passwordset/{token}', 'PasswordsetController@passwordset');
//Route::get('mail','MailController@sendemail');



//Login Logout

//get('/auth/login','Auth\AuthController@getLogin');
//post('/auth/login','Auth\AuthController@postLogin');
//get('/auth/logout','Auth@AuthController@getLogout');

//register

//get('/auth/register','Auth\AuthController@getRegister');
//post('/auth/register','Auth\AuthController@postRegister');





//Ophalen van Survy 
Route::resource('survey', 'Survey_AnswerController');
//Route::post('survey', 'Survey_AnswerController@store');
Route::get('survey','Survey_QuestionsController@questions');
//redirect naar  home page 
Route::get('/home', 'HomeController@index')->name('home');
//ophalen van het register form 
//Route::get('register', 'CustomRegController@showRegisterForm')->name('register');
//create nieuwe users =FORM 
Route::get('register', 'CustomRegController@create')->name('register');
Route::get('register/Userdata','CustomRegController@Userdata')->name('reg');
//als wij op de Register kliken worden wij verwijst naar de store. 
Route::post('register','CustomRegController@store');
//login nieuwe Users voor de website = FORM
/*
Route::get('login','SessionController@create')->name('login');
Route::get('logout','SessionController@destroy');
Route::post('login','SessionController@store'); */



//ophalen van de login form 
//Route::get('login', 'CustomRegController@showLoginForm')->name('login');

