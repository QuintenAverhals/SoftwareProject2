
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
      <h1>Nieuwe Survey</h1>
      <p> Geef je mening </p>
    </div>

      </header>
<div class="container">
<div class="container-fluid">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading">Set Password</div>
                <div class="panel-body">
                    @if (count($errors) > 0)
                        <div class="alert alert-danger">
                            <strong>Whoops!</strong> There were some problems with your input.<br><br>
                            <ul>
                                @foreach ($errors->all() as $error)
                                    <li>{{ $error }}</li>
                                @endforeach
                            </ul>
                        </div>
                    @endif

                    <form class="form-horizontal" role="form" method="POST" action="{{ url('/password/reset') }}">
                        <input type="hidden" name="_token" value="{{ csrf_token() }}">
                        <input type="hidden" name="token" value="{{ $token }}">

                        <div class="form-group">
                            <label class="col-md-4 control-label">Password</label>
                            <div class="col-md-6">
                                <input type="password" class="form-control" name="password">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">Confirm Password</label>
                            <div class="col-md-6">
                                <input type="password" class="form-control" name="password_confirmation">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-4">
                                <button type="submit" class="btn btn-primary">
                                    Set Password
                                </button>
                            </div>
                        </div>
                    </form>
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