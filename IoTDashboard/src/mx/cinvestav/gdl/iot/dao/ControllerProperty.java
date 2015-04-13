package mx.cinvestav.gdl.iot.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "con_property")
public class ControllerProperty implements IoTProperty
{
	private static final long serialVersionUID = -5541762445209122374L;
	@Id
	@GeneratedValue
	@Column(name = "idpropertycon")
	private Integer id;
	private String name;
	private String value;
	@Column(name = "isactive")
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
		return idcontroller;
	}

	public void setParentId(Integer idparent)
	{
		this.idcontroller = idparent;
	}
}
