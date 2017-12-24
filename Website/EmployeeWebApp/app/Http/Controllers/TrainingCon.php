<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
//use DB;

use App\Training;
use App\Location;


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


       $this->validate(request(),[
        'training_name'=> 'required|max:255|string',
         'start_date' => 'required',
          'eind_date' => 'required',
          'start_time' => 'required',
          'end_time' => 'required',
          'city' => 'required|max:255|string',
          'country' =>'required|max:255|string',
          'zip_code' => 'required|regex:/\b\d{4}\b/',
          'streetName'=> 'required|max:255|string',
          'bus' =>'required|integer|min:0',
          'streetnumber' => 'required|integer|min:0'

       ]);


        $training_name = $request->input('training_name');
        $start_date = $request->input('start_date');
        $eind_date = $request-> input('eind_date');
        $start_time = $request-> input('start_time');
        $eind_time=$request->input('end_time');


        $city= $request->input('city');
        $country = $request->input('country');
        $zipcode = $request->input('zip_code');
        $streetName=$request->input('streetName');
        $bus=$request->input('bus');
        $streetnumber=$request->input('streetnumber');
        //$loctionID =$request->LoctionID;


       // DB::table('TRAINING')->insert(['Training_name'=>$training_name,'start_date'=>$start_date,'end_date'=>$eind_date,'start_time'=>$start_time , 'end_time'=>$eind_time,'location_id'=>$location]);

        $new_training = new Training;
        $new_training->Training_Name= $training_name;
        $new_training->start_date=$start_date;
        $new_training->end_date=$eind_date;
        $new_training->start_time=$start_time;
        $new_training->end_time=$eind_time;
         $new_training->save();

        //toevoegen van de nieuwe locatie aan de db 
$Locatie = Location::where('city', '=' ,$city)->first();
if ($Locatie === null) {
     $new_Locatie = new Location;
        $new_Locatie->City = $city;
        $new_Locatie->country =$country;
        $new_Locatie->zipcode =$zipcode;
        $new_Locatie->streetName =$streetName;
        $new_Locatie->bus=$bus;
        $new_Locatie->streetnumber=$streetnumber; 
        $new_Locatie->save();
}else{
    \Session::flash('errorLocation', 'روح لله يرضى عليك '); 
}
       
            
     
        \Session::flash('message', 'write something here '); 
        return redirect('/training');

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
