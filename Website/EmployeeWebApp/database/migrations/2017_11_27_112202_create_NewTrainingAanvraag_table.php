<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateNewTrainingAanvraagTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    //het uitvoeren van schema in db 
    public function up()
    {
        //Training : is de naam van de Table die zal gemaakt worden in de db 
        Schema::create('Trainingen', function (Blueprint $table) {
            $table->increments('id');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    //verwijderen van schema in db 
    public function down()
    {
        Schema::dropIfExists('Trainingen');
    }
}
