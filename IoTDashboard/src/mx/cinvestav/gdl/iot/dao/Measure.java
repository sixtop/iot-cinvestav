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
	private String measure;
	private Timestamp measure_date;

	@Persistent
	private SmartThing thing;

	@Persistent
	private Sensor sensor;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getMeasure()
	{
		return measure;
	}

	public void setMeasure(String measure)
	{
		this.measure = measure;
	}

	public Timestamp getMeasure_date()
	{
		return measure_date;
	}

	public void setMeasure_date(Timestamp measure_date)
	{
		this.measure_date = measure_date;
	}

	public SmartThing getThing()
	{
		return thing;
	}

	public void setThing(SmartThing thing)
	{
		this.thing = thing;
	}

	public Sensor getSensor()
	{
		return sensor;
	}

	public void setSensor(Sensor sensor)
	{
		this.sensor = sensor;
	}

}
