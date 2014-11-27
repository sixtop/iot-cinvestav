package mx.cinvestav.gdl.iot.dao;

import java.util.Set;

import javax.jdo.annotations.Persistent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "controller")
public class Controller
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private String name;
	private String description;
	private String location;
	
	@Persistent(mappedBy = "controller")
	private Set<SmartThing> thingSet;
	
	@Persistent
	private Set<ControllerProperty> properties;
}
