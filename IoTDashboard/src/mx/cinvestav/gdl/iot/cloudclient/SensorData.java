package mx.cinvestav.gdl.iot.cloudclient;

public class SensorData
{
	private String sensorId;
	private Measure[] measures;

	public String getSensorId()
	{
		return sensorId;
	}

	public void setSensorId(String sensorId)
	{
		this.sensorId = sensorId;
	}

	public Measure[] getMeasures()
	{
		return measures;
	}

	public void setMeasures(Measure[] measures)
	{
		this.measures = measures;
	}

}
