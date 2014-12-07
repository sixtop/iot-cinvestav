package mx.cinvestav.gdl.iot.webpage.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class MeasureDTO implements Serializable
{
	private static final long serialVersionUID = 7496999784301790738L;
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
