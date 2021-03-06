package mx.cinvestav.gdl.iot.dao;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "smart_thing")
public class SmartThing implements IoTEntity
{
	private static final long serialVersionUID = 3539643120571649894L;

	@Id
	@GeneratedValue
	@Column(name = "idthing")
	private Integer id;
	private String name;
	private String description;
	@Transient
	private Map<Integer, Sensor> sensors;
	@Transient
	transient private Map<Integer, Measure> measures;
	@Transient
	private Map<Integer, SmartThingProperty> properties;

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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Map<Integer, Sensor> getSensors()
	{
		return sensors;
	}

	public void setSensors(Map<Integer, Sensor> sensors)
	{
		this.sensors = sensors;
	}

	public Map<Integer, Measure> getMeasures()
	{
		return measures;
	}

	public void setMeasures(Map<Integer, Measure> measures)
	{
		this.measures = measures;
	}

	public Map<Integer, SmartThingProperty> getProperties()
	{
		return properties;
	}

	public void setProperties(Map<Integer, SmartThingProperty> properties)
	{
		this.properties = properties;
	}

	public Integer getIdcontroller()
	{
		return idcontroller;
	}

	public void setIdcontroller(Integer idcontroller)
	{
		this.idcontroller = idcontroller;
	}
}
