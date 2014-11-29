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
@Table(name = "sensor")
public class Sensor
{
	@Id
	@GeneratedValue
	@Column(name = "idsensor")
	private int id;

	@Column(name = "isactive")
	private boolean active;

	private String name;
	private String description;
	private String sensor_type;
	private String unit;

	private double latitude;
	private double longitude;
	private double altitude;

	@OneToMany
	@JoinColumn(name = "idsensor")
	@MapKey(name = "id")
	private Map<Integer, Measure> measures;

	@OneToMany
	@JoinColumn(name = "idsensor")
	@MapKey(name = "id")
	private Map<Integer, SensorProperty> properties;

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

	public String getSensor_type()
	{
		return sensor_type;
	}

	public void setSensor_type(String sensor_type)
	{
		this.sensor_type = sensor_type;
	}

	public String getUnit()
	{
		return unit;
	}

	public void setUnit(String unit)
	{
		this.unit = unit;
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}

	public double getAltitude()
	{
		return altitude;
	}

	public void setAltitude(double altitude)
	{
		this.altitude = altitude;
	}

	public Map<Integer, Measure> getMeasures()
	{
		return measures;
	}

	public void setMeasures(Map<Integer, Measure> measures)
	{
		this.measures = measures;
	}

	public Map<Integer, SensorProperty> getProperties()
	{
		return properties;
	}

	public void setProperties(Map<Integer, SensorProperty> properties)
	{
		this.properties = properties;
	}

}
