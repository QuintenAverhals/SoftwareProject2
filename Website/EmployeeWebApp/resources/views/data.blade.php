
<tr>
@foreach($results->value as $value)
<td> {{$value->UserName}} </td>  <hr>
<td> {{$value->Emails[0]}} </td>
@endforeach

</tr>

