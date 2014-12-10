package mx.cinvestav.gdl.iot.webpage.dto;


public class ControllerPropertyDTO implements IoTPropertyDTO
{
	private static final long serialVersionUID = -5541762445209122374L;
	private int id;
	private String name;
	private String value;
	private boolean active;
	private Integer idcontroller;

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

	public Integer getParentId()
	{
		return idcontroller;
	}

	public void setParentId(Integer idparent)
	{
		this.idcontroller = idparent;
	}
}
