@extends('master')
<!--zorgt ervoor dat in plaats elke  @yield('content') wordt vervangen door @section('content') --> 
@section('content')

<div class="container">

<table class="table">
  
{!! Form::open(["url"=>"survey"]) !!}
{{ csrf_field() }}
<!--$questions wordt opgehaald van de Survey_QuestionsController  --> 
   @foreach($questions as $key => $data)

<tr> 

  <td>{{$data -> Question }} </td>

  <!-- Hier worde answer geplaatst --> 

  <!-- 
  	een forloop of foreach voor answers  : ik heb het gedaan in de Survey_Answer Controller 
  -->
  <td>{!! Form::text("answer") !!}</td>
</tr>
  @endforeach
<tr>

  {!! Form::submit("Stuur Survey",["class"=>"btn btn-info"]) !!}

</tr>
   {!! Form::close() !!}


 </table>

   </div>



	<footer class="container">
   
&copy;Groep 7

  </footer>

</body>
</html>