<div id="container">
	<div id="body">
		<script type="text/javascript">
			function addS() {
				window.location = "layout.jsp?page=addSensor";
			}

			function addP() {
				var name = prompt("Name");
				var value = prompt("Value");
			}
		</script>

		<form action="">
			<h3>Things</h3>
			<table>
				<tr>
					<td>Name:</td>
					<td><input type="text" value=""></td>
				</tr>
				<tr>
					<td>Description:</td>
					<td><input type="text" value=""></td>
				</tr>
				<tr>
					<td>Controller:</td>
					<td><select name="controllers">
							<option>A</option>
							<option>B</option>
					</select></td>
				</tr>
			</table>
			<table>
				<tr>
					<td>Property:</td>
					<td>
						<table>
							<tr>
								<th>Name</th>
								<th>Value</th>
							</tr>
							<tr>
								<td><input type="button" value="Add" name="btAdd"
									id="btAdd" onClick="addP();"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<jsp:include page="wpSensors.jsp" />


			<table>
				<tr>
					<td><input type="submit" value="Save" /></td>
					<td><input type="submit" value="Cancel" /></td>
				</tr>
			</table>

		</form>
	</div>
</div>