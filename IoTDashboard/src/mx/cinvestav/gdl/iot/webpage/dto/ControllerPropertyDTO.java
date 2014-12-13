package mx.cinvestav.gdl.iot.webpage.dto;


public class ControllerPropertyDTO implements IoTPropertyDTO
{
	private static final long serialVersionUID = -5541762445209122374L;
	private Integer id;
	private String name;
	private String value;
	private Boolean active;
	private Integer idcontroller;

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
		return idcontroller;
	}

	public void setParentId(Integer idparent)
	{
		this.idcontroller = idparent;
	}
}
