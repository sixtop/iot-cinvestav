package mx.cinvestav.gdl.iot.dao;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "smart_thing")
public class SmartThing implements IoTEntity
{
	@Id
	@GeneratedValue
	@Column(name = "idthing")
	private int id;

	private String name;
	private String description;

	@OneToMany
	@JoinColumn(name = "idthing")
	@MapKey(name = "id")
	private Map<Integer, Sensor> sensors;

	@OneToMany
	@JoinColumn(name = "idthing")
	@MapKey(name = "id")
	private Map<Integer, Measure> measures;

	@OneToMany
	@JoinColumn(name = "idthing")
	@MapKey(name = "id")
	private Map<Integer, SmartThingProperty> properties;

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

}
