<div id="container">
<div id="body">
<script type="text/javascript">
	function addT() {
				window.location = "layout.jsp?page=addThing";
			}
		</script>
<form action="">
	 <h3>Things</h3> <br />
	 	<table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th></th>
            </tr>
            <tr>
            <td>aaaaaa</td>
            <td>aaaaaa</td>
            	<td><input type="button" value="Edit" name="btEdit" id="btEdit" onClick="ediT();"></td>
            </tr>
        </table>
        	
	 	<table>
            <tr>
                <td><input type="button" value="Add" name="btAdd" id="btAdd" onClick="addT();"></td>
            </tr>
        </table>
	
	</form>
  </div>
</div>