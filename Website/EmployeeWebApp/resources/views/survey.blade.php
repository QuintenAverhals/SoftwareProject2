<!DOCTYPE html>
<html>
  <head>
    <title>Training Aanvraag</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" type="text/css" href="{{asset('css/docs.min')}}">
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
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
        <h1>Nieuwe Survey</h1>
        <p> Geef je mening </p>
      </div>
    </header>
    <div class="container">
      @if($messsage = Session::has('message'))
      <p class="alert alert-success">{{ Session::get('message') }}</p>
      @endif
      {!! Form::open(array('route' => 'survey.store','method'=>'POST')) !!}
      {{ csrf_field() }}
      <table class="table">
        @foreach( $questions as $question)
        <tr>
          <td>{{$question->Question}} </td>
          
          <td> <input type="text" name="Answer[]">
          <input type="hidden" name="QuestionID[]" value="{{$question->question_ID}}">
           <input type="hidden" name="survey_ID[]" value="{{$question->survey_ID}}">
        </td>
      </tr>
      @endforeach
      <tr>
        
      </tr>
      
    </table>
        {!! Form::submit("Stuur Survey",["class"=>"btn btn-info pull-right"]) !!}

    {!! Form::close() !!}
  </div>
  <footer class="container">
    &copy;Groep 7
  </footer>
  <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
</body>
</html>