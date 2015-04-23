package mx.cinvestav.gdl.iot.cloudclient;


public class Data
{
	private String data;
	private String time;
	private String image;
	private Integer charted = 0;
	private Integer idexperiment;
	

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

	public Integer getCharted()
	{
		return charted;
	}

	public void setCharted(Integer charted)
	{
		this.charted = charted;
	}

	public Integer getIdexperiment()
	{
		return idexperiment;
	}

	public void setIdexperiment(Integer idexperiment)
	{
		this.idexperiment = idexperiment;
	}
}
