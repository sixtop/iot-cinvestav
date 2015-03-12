/*
 * Helper code to graph smart city data using nvd3
 * */
function generateNVD3(xaxis, yaxis, data) {
	nv.addGraph(function() {
		var chart = nv.models.lineChart().margin({
			left : 100
		}).useInteractiveGuideline(true).showLegend(true).showYAxis(true)
				.showXAxis(true);

		chart.xAxis.axisLabel(yaxis).tickFormat(function(d) {
			return d3.time.format('%m/%d %H:%M:%S')(new Date(d))
		});

		chart.yAxis.axisLabel(xaxis).tickFormat(d3.format(',r'));
		// .axisLabel('Voltage (v)').tickFormat(d3.time.format('%m/%d/%Y')(new
		// Date(d)));

		// var myData = sinAndCos();
		var myData = JSON.parse(data, JSON.dateParser);

		d3.select('#chart svg').datum(myData).transition().duration(500).call(
				chart);
		nv.utils.windowResize(function() {
			chart.update()
		});
		return chart;
	});

	document.getElementById("chart").style.display = 'block';
}

/* JSON Date Parser */
var reISO = /^(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2}(?:\.\d*))(?:Z|(\+|-)([\d|:]*))?$/;
var reMsAjax = /^\/Date\((d|-|.*)\)[\/|\\]$/;
JSON.dateParser = function(key, value) {
	if (typeof value === 'string') {
		var a = reISO.exec(value);
		if (a)
			return new Date(value);
		a = reMsAjax.exec(value);
		if (a) {
			var b = a[1].split(/[-+,.]/);
			return new Date(b[0] ? +b[0] : 0 - +b[1]);
		}
	}
	return value;
};