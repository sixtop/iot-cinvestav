package mx.cinvestav.gdl.iot.webpage.dao;

import java.io.Serializable;

public interface IoTEntity extends Serializable
{
	public Integer getId();

	public void setId(Integer id);

	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);
}
