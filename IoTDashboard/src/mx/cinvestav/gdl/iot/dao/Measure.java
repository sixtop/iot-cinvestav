package mx.cinvestav.gdl.iot.dao;

import java.sql.Timestamp;

import javax.jdo.annotations.Persistent;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data")
public class Measure
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private Timestamp time;
	private String value;

	@Persistent
	private SmartThing thing;
	
	@Persistent
	private Sensor sensor;
}
