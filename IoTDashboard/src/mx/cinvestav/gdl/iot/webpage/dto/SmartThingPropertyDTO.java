package mx.cinvestav.gdl.iot.webpage.dto;


public class SmartThingPropertyDTO implements IoTPropertyDTO
{
	private static final long serialVersionUID = 1364448876804303998L;
	private Integer id;
	private String name;
	private String value;
	private Boolean active;
	private Integer idthing;

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

	public Boolean isActive()
	{
		return active;
	}

	public void setActive(Boolean active)
	{
		this.active = active;
	}

	public Integer getParentId()
	{
		return idthing;
	}

	public void setParentId(Integer idparent)
	{
		this.idthing = idparent;
	}
}
