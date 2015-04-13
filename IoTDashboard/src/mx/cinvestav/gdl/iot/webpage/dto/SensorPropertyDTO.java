package mx.cinvestav.gdl.iot.webpage.dto;

public class SensorPropertyDTO implements IoTPropertyDTO
{
	private static final long serialVersionUID = 6750305088263892973L;
	private Integer id;
	private String name;
	private String value;
	private Boolean active;
	private Integer idsensor;

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

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public Boolean getActive()
	{
		return active;
	}

	public void setActive(Boolean active)
	{
		this.active = active;
	}

	public Integer getParentId()
	{
		return idsensor;
	}

	public void setParentId(Integer parentId)
	{
		this.idsensor = parentId;
	}
}
