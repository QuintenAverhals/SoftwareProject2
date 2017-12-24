
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
      <h1>Employees</h1>
      <p> ophalen employees </p>
    </div>

			</header>
<div class="container">

  <div class="panel panel-default">
        <div class="panel-heading"><h1></h1></div>
        <div class="panel-body">
            <div class="row">
              <tr> 
                @foreach ($results->value as $value)
             
                <td>   username : {{$value->UserName }} </td>
                <td>   Email : {{ $value-> Emails }} </td>

                @endforeach
              </tr>
            </div>
        </div>

        </div>
    </div>
  </div>

	<footer class="container">
   
&copy;Groep 7

  </footer>

</body>
</html>