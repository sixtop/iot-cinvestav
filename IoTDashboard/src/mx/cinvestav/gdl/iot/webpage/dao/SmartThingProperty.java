package mx.cinvestav.gdl.iot.webpage.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thing_property")
public class SmartThingProperty implements IoTProperty
{
	private static final long serialVersionUID = 1364448876804303998L;
	@Id
	@GeneratedValue
	@Column(name = "idpropertything")
	private Integer id;
	private String name;
	private String value;
	@Column(name = "isactive")
	private boolean active;
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

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	@Override
	public Integer getParentId()
	{
		return idthing;
	}

	@Override
	public void setParentId(Integer idparent)
	{
		this.idthing = idparent;
	}
}
