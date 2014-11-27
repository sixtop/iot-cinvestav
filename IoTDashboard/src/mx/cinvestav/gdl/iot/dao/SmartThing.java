package mx.cinvestav.gdl.iot.dao;

import java.util.Set;

import javax.jdo.annotations.Persistent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thing")
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
}
