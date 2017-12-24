<!DOCTYPE html>
<html>
  <head>
    <title>Training Aanvraag</title>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
    
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
      @if($messsage = Session::has('message'))
      <p class="alert alert-success">{{ Session::get('message') }}</p>
      @endif
       @if($messsage = Session::has('errorLocation'))
      <p class="alert alert-danger">{{ Session::get('errorLocation') }}</p>
      @endif
       {!! Form::open(["url"=>"training"]) !!}
        {{ csrf_field() }}
      <table class="table">
        
       
         <tr>
          <td>TrainingNaam : </td>
          <td>{!! Form::text("training_name") !!}</td>
          @if ($errors->has('training_name'))
          <span class="help-block alert-danger "> <br>
            <strong>{{ $errors->first('training_name') }}</strong>
          </span>
          @endif
        </tr>
        <tr>
          <td> Start-date :</td>
          <td>  {!! Form::date("start_date") !!}</td>
          @if ($errors->has('start_date'))
          <span class="help-block alert-danger "> <br>
            <strong>{{ $errors->first('start_date') }}</strong>
          </span>
          @endif
        </tr>
        <tr>
          <td>  Eind-date : </td>
          <td>{!! Form::date("eind_date") !!} </td>
          @if ($errors->has('eind_date'))
          <span class="help-block alert-danger "> <br>
            <strong>{{ $errors->first('eind_date') }}</strong>
          </span>
          @endif
        </tr>
        <tr>
          <td>Start-time : </td>
          <td>  {!! Form::time("start_time") !!}</td>
          @if ($errors->has('start_time'))
          <span class="help-block alert-danger "> <br>
            <strong>{{ $errors->first('start_time') }}</strong>
          </span>
          @endif
        </tr>
        <tr>
          <td>eind-time : </td>
          <td> {!! Form::time("end_time") !!} </td>
          @if ($errors->has('end_time'))
          <span class="help-block alert-danger "> <br>
            <strong>{{ $errors->first('end_time') }}</strong>
          </span>
          @endif
        </tr>
        <td>Stad: </td>
        <td>{!! Form::text("city") !!}</td>
        @if ($errors->has('city'))
        <span class="help-block alert-danger "> <br>
          <strong>{{ $errors->first('city') }}</strong>
        </span>
        @endif
      </tr>
      <tr>
        <td> Land:</td>
        <td>  {!! Form::text("country") !!}</td>
        @if ($errors->has('country'))
        <span class="help-block alert-danger "> <br>
          <strong>{{ $errors->first('country') }}</strong>
        </span>
        @endif
      </tr>
      <tr>
        <td>  zipcode : </td>
        <td>{!! Form::text("zip_code") !!} </td>
        @if ($errors->has('zip_code'))
        <span class="help-block alert-danger "> <br>
          <strong>{{ $errors->first('zip_code') }}</strong>
        </span>
        @endif
      </tr>
      <tr>
        <td>straat naam : </td>
        <td>  {!! Form::text("streetName") !!}</td>
        @if ($errors->has('streetName'))
        <span class="help-block alert-danger "> <br>
          <strong>{{ $errors->first('streetName') }}</strong>
        </span>
        @endif
      </tr>
      <tr>
        <td>bus : </td>
        <td> {!! Form::text("bus") !!} </td>
        @if ($errors->has('bus'))
        <span class="help-block alert-danger "> <br>
          <strong>{{ $errors->first('bus') }}</strong>
        </span>
        @endif
      </tr>
      <tr>
        <td>straat nummer: </td>
        <td> {!! Form::text("streetnumber") !!} </td>
        @if ($errors->has('streetnumber'))
        <span class="help-block alert-danger "> <br>
          <strong>{{ $errors->first('streetnumber') }}</strong>
        </span>
        @endif
      </tr>
      <tr>
        
      </tr>
     
    </table>
     {!! Form::submit("Stuur Aanvraag",["class"=>"btn btn-info"]) !!}
     {!! Form::close() !!}
  </div>
  <footer class="container">
    
    &copy;Groep 7
  </footer>
  <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
</body>
</html>