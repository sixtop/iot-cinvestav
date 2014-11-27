<div id="container">
<div id="body">
		<script type="text/javascript">
                function ediC(){
                    window.location="layout.jsp?page=addController";
                }
                
                function addC(){
                    window.location="layout.jsp?page=addController";
                }
                   
            </script>
		
	<form action="">
	 <h3>Application</h3> <br />
	 <p>Name: </p><br />
	 <p>Controllers: </p><br />
	 	<table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Localization</th>
                <th></th>
            </tr>
            <tr>
            	<td>a1</td>
            	<td>a1</td>
            	<td>a1</td>
            	<td><input type="button" value="Edit" name="btEdit" id="btEdit" onClick="ediC();"></td>
            </tr>
        </table>	
        
	 	<table>
            <tr>
                <td><input type="button" value="Add" name="btAdd" id="btAdd" onClick="addC();"></td>
            </tr>
        </table>
	
	</form>
  </div>
</div>