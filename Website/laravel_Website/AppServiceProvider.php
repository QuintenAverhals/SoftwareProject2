<?php

namespace App\Providers;

//Listing for query Events 
use Illuminate\Support\Facades\DB;
use Illuminate\Support\ServiceProvider;



class AppServiceProvider extends ServiceProvider
{
    /**
     * Bootstrap any application services.
     *
     * @return void
     */
    public function boot()
    {


     DB::listen(function ($query){

       // $query -> sql ; 
        //$query -> bindings ; 
        //$query -> time ; 
     });


        //
    }

    /**
     * Register any application services.
     *
     * @return void
     */
    public function register()
    {
        //
    }
}
