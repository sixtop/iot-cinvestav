package mx.cinvestav.gdl.iot.webpage.dto;


public class SensorTypeDTO implements IoTTypeSensorDTO
{
	private static final long serialVersionUID = 3539643120571649894L;

	private Integer id;
	private String name;
	
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
