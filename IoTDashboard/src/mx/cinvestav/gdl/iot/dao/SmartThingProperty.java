package mx.cinvestav.gdl.iot.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thing_property")
public class SmartThingProperty implements IoTProperty
{
	@Id
	@GeneratedValue
	@Column(name = "idpropertything")
	private int id;
	private String name;
	private String value;
	@Column(name = "isactive")
	private boolean active;
	private int idthing;

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

	@Override
	public int getParentId()
	{
		return idthing;
	}

	@Override
	public void setParentId(int idparent)
	{
		this.idthing = idparent;
	}
}
