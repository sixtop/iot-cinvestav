package mx.cinvestav.gdl.iot.dao;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "smart_thing")
public class SmartThing
{
	@Id
	@GeneratedValue
	@Column(name = "idthing")
	private int id;

	private String name;
	private String description;

	@OneToMany
	@JoinColumn(name = "idthing")
	private Set<Sensor> sensors;

	@OneToMany
	@JoinColumn(name = "idthing")
	private Set<Measure> measures;

	@OneToMany
	@JoinColumn(name = "idthing")
	private Set<SmartThingProperty> properties;

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

	public Set<Sensor> getSensors()
	{
		return sensors;
	}

	public void setSensors(Set<Sensor> sensors)
	{
		this.sensors = sensors;
	}

	public Set<Measure> getMeasures()
	{
		return measures;
	}

	public void setMeasures(Set<Measure> measures)
	{
		this.measures = measures;
	}

	public Set<SmartThingProperty> getProperties()
	{
		return properties;
	}

	public void setProperties(Set<SmartThingProperty> properties)
	{
		this.properties = properties;
	}

}
