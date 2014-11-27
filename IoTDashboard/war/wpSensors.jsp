<div id="container">
<div id="body">
<script type="text/javascript">
	function addS() {
				window.location = "layout.jsp?page=addSensor";
			}
	
	
	function ediS() {
		window.location = "layout.jsp?page=addSensor";
	}
		</script>
		
		
	<form action="">
		<h3>Sensors</h3>
		<br />
		<table>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Description</th>
				<th>Unit</th>
			</tr>
			<tr>
				<td><input type="button" value="Edit" name="btEdit" id="btEdit" onClick="ediS();"></td>
			</tr>
		</table>
		<table>
			<tr>
				<td><input type="button" value="Add" name="btAdd" id="btAdd" onClick="addS();"></td>
			</tr>
		</table>
	</form>
  </div>
</div>