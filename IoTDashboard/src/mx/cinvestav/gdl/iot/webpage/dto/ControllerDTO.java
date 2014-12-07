package mx.cinvestav.gdl.iot.webpage.dto;

import java.util.Map;

public class ControllerDTO implements IoTEntityDTO
{
	private static final long serialVersionUID = 6343944649241458664L;
	
	private int id;
	private String name;
	private String description;
	private String location;
	private Map<Integer, SmartThingDTO> things;
	private Map<Integer, ControllerPropertyDTO> properties;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public Map<Integer, SmartThingDTO> getThings()
	{
		return things;
	}

	public void setThings(Map<Integer, SmartThingDTO> things)
	{
		this.things = things;
	}

	public Map<Integer, ControllerPropertyDTO> getProperties()
	{
		return properties;
	}

	public void setProperties(Map<Integer, ControllerPropertyDTO> properties)
	{
		this.properties = properties;
	}
}
