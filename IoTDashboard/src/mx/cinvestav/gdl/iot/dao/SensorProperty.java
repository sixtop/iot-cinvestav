package mx.cinvestav.gdl.iot.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sensor_property")
public class SensorProperty implements IoTProperty
{
	private static final long serialVersionUID = 6750305088263892973L;
	@Id
	@GeneratedValue
	@Column(name = "idpropertysensor")
	private Integer id;
	private String name;
	private String value;
	@Column(name = "isactive")
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
		return idsensor;
	}

	public void setParentId(Integer parentId)
	{
		this.idsensor = parentId;
	}
}
