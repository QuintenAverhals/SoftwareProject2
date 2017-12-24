
<!DOCTYPE html>
<html>
<head>
	<title>Training Aanvraag</title>

	<link rel="stylesheet" type="text/css" href="{{asset('css/docs.min')}}">
    <style type="text/css">
    	
    	body{
    		background: url("{{asset('images/backgroundFoto.jpg')}}") no-repeat center center fixed; 
    		background-size: 100% auto;
    	}
    	header{opacity: 0.7;}
    	footer{background-color: #937f7b;opacity: 0.9;text-align: center;}
    </style>

</head>
<body>
	<header class="jumbotron">
    <div class="container">
      <h1>Nieuwe Training Aanvraag</h1>
      <p> U kunt via deze form een nieuwe aanvraag indienen </p>
    </div>

			</header>
<div class="container">
<!--form >
  <div class="form-group row">
    <label for="TraingNaam" >Training naam</label>
    <input type="text" class="form-control" id="naamTraining" placeholder="Geef Training naam">
  </div>
  <div class="form-group">
    <label for="SoortTraining">Welke Training wil je volgen:</label>
    <input type="text" class="form-control" id="Soort" placeholder="Geef soort Training">
  </div>
   <div class="form-group">
    <label for="plaatsTraining">Waar wil je volgen:</label>
    <input type="text" class="form-control" id="plaats" placeholder="plaats van Training">
  </div>
  <button type="submit" class="btn btn-primary">Stuur</button>
</form-->

  <!--Aanmaken van de nieuwe aanvraag Training -->

<table class="table">
  
{!! Form::open(["url"=>"training"]) !!}

<tr> 

  <td>TrainingNaam : </td>
  <td>{!! Form::text("training_name") !!}</td>
</tr>
<tr>
   <td> Start-date :</td>
   <td>  {!! Form::text("start_date") !!}</td>
 </tr>

 <tr>
   <td>  Eind-date : </td>
   <td>{!! Form::text("eind_date") !!} </td>
 </tr>

 <tr>
   <td>Start-time : </td>
   <td>  {!! Form::text("start_time") !!}</td>
 </tr>

 <tr>
   <td>eind-time : </td>
  <td> {!! Form::text("end_time") !!} </td>
</tr>

<!--tr>
   <td> location : </td>
<td>{!! Form::text("location") !!} </td> 
</tr-->

<!--tr>
<td>  Survy :</td>
 <td> {!! Form::text("survy_id") !!} </td> 
</tr-->

<tr>
 <td> Status : </td>
  <td> {!! Form::text("status") !!} </td>
</tr>

<tr>
 <td> visibility : </td>
 <td> {!! Form::text("visibility") !!} </td>

</tr>
<tr> 

  <td>Stad: </td>
  <td>{!! Form::text("city") !!}</td>
</tr>
<tr>
   <td> Land:</td>
   <td>  {!! Form::text("country") !!}</td>
 </tr>

 <tr>
   <td>  zipcode : </td>
   <td>{!! Form::text("zip_code") !!} </td>
 </tr>

 <tr>
   <td>straat naam : </td>
   <td>  {!! Form::text("streetName") !!}</td>
 </tr>

 <tr>
   <td>bus : </td>
  <td> {!! Form::text("bus") !!} </td>



</tr>
<tr>
   <td>straat nummer: </td>
  <td> {!! Form::text("streetnumber") !!} </td>
  
</tr>

<tr>

  {!! Form::submit("Stuur Aanvraag",["class"=>"btn btn-info"]) !!}

</tr>
   {!! Form::close() !!}


 </table>









   </div>



	<footer class="container">
   
&copy;Groep 7

  </footer>

</body>
</html>