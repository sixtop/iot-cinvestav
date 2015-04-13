package mx.cinvestav.gdl.iot.cloudclient;


public class Data
{
	private String data;
	private String time;
	private String image;
	private String metadata;

	public String getData()
	{
		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	public String getTime()
	{
		return time;
	}

	public void setTime(String time)
	{
		
		this.time = time;
	}
	
	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		
		this.image = image;
	}

	public String getMetadata()
	{
		return metadata;
	}

	public void setMetadata(String metadata)
	{
		this.metadata = metadata;
	}
}
