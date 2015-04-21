package mx.cinvestav.gdl.iot.webpage.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class MeasureDTO implements Serializable
{
	private static final long serialVersionUID = 7496999784301790738L;
	private Integer id;
	private String measure;
	private Timestamp measure_date;
	private Integer idsensor;
	private Integer idthing;
	private byte[] image;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
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

	public Integer getIdsensor()
	{
		return idsensor;
	}

	public void setIdsensor(Integer idsensor)
	{
		this.idsensor = idsensor;
	}

	public Integer getIdthing()
	{
		return idthing;
	}

	public void setIdthing(Integer idthing)
	{
		this.idthing = idthing;
	}
	
	public byte[] getImage()
	{
		return image;
	}
	
	public void setImage(byte[] image)
	{
		this.image = image;
	}
}
