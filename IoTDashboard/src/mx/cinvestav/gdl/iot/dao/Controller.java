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
@Table(name = "controller")
public class Controller
{
	@Id
	@GeneratedValue
	@Column(name = "idcontroller")
	private int id;
	private String name;
	private String description;
	private String location;

	@OneToMany
	@JoinColumn(name = "idcontroller")
	@MapKey(name = "id")
	private Map<Integer, SmartThing> things;

	@OneToMany
	@JoinColumn(name = "idcontroller")
	@MapKey(name = "id")
	private Map<Integer, ControllerProperty> properties;

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

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public Map<Integer, SmartThing> getThings()
	{
		return things;
	}

	public void setThings(Map<Integer, SmartThing> things)
	{
		this.things = things;
	}

	public Map<Integer, ControllerProperty> getProperties()
	{
		return properties;
	}

	public void setProperties(Map<Integer, ControllerProperty> properties)
	{
		this.properties = properties;
	}

}