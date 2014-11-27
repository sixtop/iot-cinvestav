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
	private String name;
	private String description;
	private String unit;

	@Persistent
	private Set<SmartThing> things;
	
	@Persistent
	private Set<Measure> measures;
	
	@Persistent
	private Set<SensorProperty> properties;
}
