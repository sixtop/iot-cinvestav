package mx.cinvestav.gdl.iot.cloudclient;

public class UpdateDataRequest
{
	private int controllerId;
	private SmartThingData[] smartThingData;

	public int getControllerId()
	{
		return controllerId;
	}

	public void setControllerId(int controllerId)
	{
		this.controllerId = controllerId;
	}

	public SmartThingData[] getSmartThingData()
	{
		return smartThingData;
	}

	public void setSmartThingData(SmartThingData[] smartThingData)
	{
		this.smartThingData = smartThingData;
	}
}
