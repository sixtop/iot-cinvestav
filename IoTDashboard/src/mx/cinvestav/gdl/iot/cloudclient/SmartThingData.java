package mx.cinvestav.gdl.iot.cloudclient;

public class SmartThingData
{
	private int smartThingId;
	private SensorData[] sensorData;

	public int getSmartThingId()
	{
		return smartThingId;
	}

	public void setSmartThingId(int smartThingId)
	{
		this.smartThingId = smartThingId;
	}

	public SensorData[] getSensorData()
	{
		return sensorData;
	}

	public void setSensorData(SensorData[] sensorData)
	{
		this.sensorData = sensorData;
	}

}
