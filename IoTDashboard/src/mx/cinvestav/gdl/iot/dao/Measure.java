package mx.cinvestav.gdl.iot.dao;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data")
public class Measure
{
	@Id
	@GeneratedValue
	@Column(name = "iddata")
	private int id;
	private String measure;
	private Timestamp measure_date;
	private int idsensor;
	private int idthing;

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

	public int getIdsensor()
	{
		return idsensor;
	}

	public void setIdsensor(int idsensor)
	{
		this.idsensor = idsensor;
	}

	public int getIdthing()
	{
		return idthing;
	}

	public void setIdthing(int idthing)
	{
		this.idthing = idthing;
	}

}