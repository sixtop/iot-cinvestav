package mx.cinvestav.gdl.iot.dao;

import java.util.Set;

import javax.jdo.annotations.Persistent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sensor")
public class Sensor
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private boolean active;
	private String name;
	private String description;
	private String type;
	private String unit;

	private String latitude;
	private String longitude;
	private String altitude;

	@Persistent
	private SmartThing things;

	@Persistent
	private Set<Measure> measures;

	@Persistent
	private Set<SensorProperty> properties;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
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

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public String getLatitude()
	{
		return latitude;
	}

	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}

	public String getLongitude()
	{
		return longitude;
	}

	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}

	public String getAltitude()
	{
		return altitude;
	}

	public void setAltitude(String altitude)
	{
		this.altitude = altitude;
	}

	public SmartThing getThings()
	{
		return things;
	}

	public void setThings(SmartThing things)
	{
		this.things = things;
	}

	public Set<Measure> getMeasures()
	{
		return measures;
	}

	public void setMeasures(Set<Measure> measures)
	{
		this.measures = measures;
	}

	public Set<SensorProperty> getProperties()
	{
		return properties;
	}

	public void setProperties(Set<SensorProperty> properties)
	{
		this.properties = properties;
	}

}
