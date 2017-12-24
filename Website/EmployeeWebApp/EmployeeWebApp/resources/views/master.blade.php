
<!DOCTYPE html>
<html>
<head>
	<title>Employee Webapp</title>
</head>

<body>
	<div class="container">
    <!-- menu-->
    <div class="row", style="padding-top:10px">

    <div class="center-align">

     <a class="btn" href="/"> Home </a>
     <!--
     <a class="btn" href="/training"> TrainingAanvraag </a>
     <a class="btn" href="/survey"> Survey  </a>
     <a class="btn" href="/ToekomTrainingen"> ToekomstigeTrainingen </a>
     <a class="btn" href="/AfgelegdeTraining"> AfgelegdeTrainingen </a>
    -->
     @if(Auth::check())
     <a class="btn" href="/logout">Logout </a>
     <a class="btn" href="#">{{Auth::user()->email}}</a>
     @else 
     <a class="btn" href="/login"> Login </a>
      <a class="btn" href="/register"> Register </a>
     @endif
    </div>
     </div>


     <div class="row">
              <div class="col s12 m10 offset-m1 l8 offset-l2" style="margin-top:10px;">
                @yield('content')
              </div>
          </div>

  

	</div>

</body>

<script src="{{ URL::asset('jquery.min.js') }}"></script>

   <script src="{{ URL::asset('init.js') }}"></script>
</html>