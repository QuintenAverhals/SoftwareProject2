<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Survey_answers;
class Survey_AnswerController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //opslagen van surveys en ophalen van de  view survey.blade.php

        
        $array = $request->except('_token');
        foreach ($array as $key => $value) {

            # code...
            $new_Answer= new Survey_answers;
            //wij moeten onze data naar een json convereren
            if(! is_array($value)){
                $newWaarde = $value('answer');
            } else {
                $newWaarde = json_encode($value['answer']);
            }
                
        $new_Answer->Answer = $newWaarde;
        $new_Answer->question_ID = $key;
        $new_Answer->user_id= Auth::id();


        $new_Answer -> save(); 
    };


        return redirect('survey'); 

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
