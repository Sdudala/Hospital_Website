
<div style="float:left; margin-left:200px; ">
<form:form action="searchProfile" name="searchForm" method="post">
     <table border="1" width="700">
         <tr><th colspan="2" align="left">Look up by Lastname</th></tr>
         <tr><td colspan = "2" align="left"> Please enter the lastname of the person you want to look up.</td></tr>    
         <tr>
         <td align="right"><input type="text" name="lastname"></td>
         <td align="left"><input type = "submit" name="submit" value="See Profile" onclick="return checkName()"/></td>
     </table>
     <br/><br/>
     <table border="1" width="700">
         <tr><th colspan="2" align="left">Quick Search</th></tr>
         <tr>
         <td colspan="2">Show Me: <select name="gender">
                          <option value="All">All</option>
                          <option value="Female">Female</option>
                          <option value="Male">Male</option>
                       </select>
         </td>
         </tr>
        <!--  <tr>
         <td align="right"> Age: </td>
         <td align="left"><input type="text" name="ageL"> &nbsp;&nbsp;to&nbsp;&nbsp; <input type="text" name="ageU"></td>
         </tr> -->
         <tr>
         <td align="right"> Search Location: </td>
         <td align="left">
             State: <input type="text" name="state">
             City: <input type="text" name="city">
         </td>
         </tr>
         <tr><td colspan="2" align="center"> <input type = "submit" name="submit" value="Search"/> </td> </tr>
         
     </table>


</form:form>
</div>

<script>
function checkName()
{
    if (!document.searchForm.lastname.value)
    {
        alert ("Please Enter a Lastname");
        return (false);
    }
    return (true);
}

</script>