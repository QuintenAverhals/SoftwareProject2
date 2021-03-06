<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
//use DB;

use App\Training;


class TrainingCon extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        
         //   return '<centr><h1> De trainingen </h></centr>';

       //Training= DB::table('trainging')->get();

        //$training = Training::all();
       // return view('TrainingAanvragen')->withTraining($trainging);
        return view('TrainingAanvragen');

    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
            return '<centr><h1> Voeg nieuw trainging </h></centr>';
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //storing the new created section to the db 

        $training_name = $request->input('training_name');
        $start_date = $request->input('start_date');
        $eind_date = $request-> input('eind_date');
        $start_time = $request-> input('start_time');
        $eind_time=$request->input('end_time');
        $location = $request->input('location');
        $status = $request->input('status');
        $survy = $request->input('survy_id');
        $visibility= $request->input('visibility');


       // DB::table('TRAINING')->insert(['Training_name'=>$training_name,'start_date'=>$start_date,'end_date'=>$eind_date,'start_time'=>$start_time , 'end_time'=>$eind_time,'location_id'=>$location]);

        $new_training = new Training;
        $new_training->Training_name=$training_name;
        $new_training->start_date=$start_date;
        $new_training->end_date=$eind_date;
        $new_training->start_time=$start_time;
        $new_training->end_time=$eind_time;
        $new_training->location_id=$location;
        $new_training->status= $status;
        $new_training->survey_id=$survy;
        $new_training->visibility=$visibility;


        $new_training->save();
        return redirect('TrainingAanvragen');


    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
