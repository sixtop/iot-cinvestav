package mx.cinvestav.gdl.iot.webpage.dto;

import java.util.Map;

public class SmartThingDTO implements IoTEntityDTO
{
	private static final long serialVersionUID = 3539643120571649894L;

	private Integer id;

	private String name;
	private String description;

	private Map<Integer, SensorDTO> sensors;

	private Map<Integer, MeasureDTO> measures;

	private Map<Integer, SmartThingPropertyDTO> properties;

	private Integer idcontroller;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
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

	public Map<Integer, SensorDTO> getSensors()
	{
		return sensors;
	}

	public void setSensors(Map<Integer, SensorDTO> sensors)
	{
		this.sensors = sensors;
	}

	public Map<Integer, MeasureDTO> getMeasures()
	{
		return measures;
	}

	public void setMeasures(Map<Integer, MeasureDTO> measures)
	{
		this.measures = measures;
	}

	public Map<Integer, SmartThingPropertyDTO> getProperties()
	{
		return properties;
	}

	public void setProperties(Map<Integer, SmartThingPropertyDTO> properties)
	{
		this.properties = properties;
	}

	public Integer getIdcontroller()
	{
		return idcontroller;
	}

	public void setIdcontroller(Integer idcontroller)
	{
		this.idcontroller = idcontroller;
	}
}
