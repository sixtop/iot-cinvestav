package mx.cinvestav.gdl.iot.cloudclient;

import javax.xml.bind.annotation.XmlType;

@XmlType(name="UpdateDataResponse_type")
public class UpdateDataResponse
{
	private int status;
	private String message;

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

}
