<!DOCTYPE html>
<html>
<head>
	<title>Register</title>


	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body>
	<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-3">
            <div class="panel panel-default">
                <div class="panel-heading">Register</div>
                
   <form class="form-horizontal" action="{{route('register')}}" method="post">
   	    {{ csrf_field() }}

  <fieldset>
    
    <div class="form-group">
      <label for="exampleInputEmail1">Email address</label>
      <input class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" type="email" name="email">
     <!--  <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small> -->


    </div>

      <div class="form-group">
      <label for="exampleInputEmail1">User name</label>
      <input class="form-control"  type="text" name =UserName">

    </div>
    
    <button type="submit" class="btn btn-block">Register</button>
  </fieldset>
</form>




         </div>

         </div>
     </div>
 </div>
</div>


   <script  src="https://code.jquery.com/jquery-3.2.1.js" integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE=" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>

</body>
</html>