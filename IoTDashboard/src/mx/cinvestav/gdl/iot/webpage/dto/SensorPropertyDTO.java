package mx.cinvestav.gdl.iot.webpage.dto;

import java.io.Serializable;

public class SensorPropertyDTO implements IoTPropertyDTO
{
	private static final long serialVersionUID = 6750305088263892973L;
	private int id;
	private String name;
	private String value;
	private boolean active;
	private int idsensor;

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

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public int getParentId()
	{
		return idsensor;
	}

	public void setParentId(int parentId)
	{
		this.idsensor = parentId;
	}
}
