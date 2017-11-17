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

Route::get('/', function () {
    return view('welcome');
});

//this id wordt in de url gegeven 
Route::get('ID/{id}', function ($id) {

//een id opgeven 
	echo 'ID: '.$id; 

});

//deze parameter wordt niet door of door de url gegeven 

Route::get('/user/{name?}',function($name='Ahemd &Masar')
{
	echo "Name: ".$name;
});




Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');
