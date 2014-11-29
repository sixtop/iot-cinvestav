package mx.cinvestav.gdl.iot.cloudclient;

public class SensorData
{
	private int sensorId;
	private Data[] measures;

	public int getSensorId()
	{
		return sensorId;
	}

	public void setSensorId(int sensorId)
	{
		this.sensorId = sensorId;
	}

	public Data[] getMeasures()
	{
		return measures;
	}

	public void setMeasures(Data[] measures)
	{
		this.measures = measures;
	}

}
