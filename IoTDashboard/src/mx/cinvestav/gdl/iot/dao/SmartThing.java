package mx.cinvestav.gdl.iot.dao;

import java.util.Set;

import javax.jdo.annotations.Persistent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "smart_thing")
public class SmartThing
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name;
	private String description;

	@Persistent
	private Controller controller;

	@Persistent
	private Set<Sensor> sensors;

	@Persistent
	private Set<Measure> measures;

	@Persistent
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

	public Controller getController()
	{
		return controller;
	}

	public void setController(Controller controller)
	{
		this.controller = controller;
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
