package mx.cinvestav.gdl.iot.dao;

public interface IoTProperty
{
	public int getParentId();
	public void setParentId(int idparent);
	
	public void setName(String name);
	public void setValue(String value);
	public void setActive(boolean active);
	
}
