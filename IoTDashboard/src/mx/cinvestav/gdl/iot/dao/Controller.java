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
	@JoinColumn(name="idcontroller")
	private Set<SmartThing> thingSet;

	@OneToMany
	@JoinColumn(name="idcontroller")
	private Set<ControllerProperty> properties;

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

	public Set<SmartThing> getThingSet()
	{
		return thingSet;
	}

	public void setThingSet(Set<SmartThing> thingSet)
	{
		this.thingSet = thingSet;
	}

	public Set<ControllerProperty> getProperties()
	{
		return properties;
	}

	public void setProperties(Set<ControllerProperty> properties)
	{
		this.properties = properties;
	}

}
