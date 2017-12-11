
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

  {!! Form::open(["url"=>"training","files"=>"true"]) !!}
  TrainingNaam :{!! Form::text("training_name") !!} <br/> 
  Start-date :{!! Form::text("start_date") !!} <br/> 
  Eind-date :{!! Form::text("eind_date") !!} <br/> 
  Start-time :{!! Form::text("start_time") !!} <br/> 
  eind-time :{!! Form::text("end_time") !!} <br/> 
  location :{!! Form::text("location") !!} <br/> 
  Survy :{!! Form::text("survy_id") !!} <br/> 
  Status :{!! Form::text("status") !!} <br/> 
  visibility :{!! Form::text("visibility") !!} <br/> 





  {!! Form::submit("Stuur Aanvraag",["class"=>"btn btn-info"]) !!}
   


  {!! Form::close() !!}



   </div>



	<footer class="container">
   
&copy;Groep 7

  </footer>

</body>
</html>