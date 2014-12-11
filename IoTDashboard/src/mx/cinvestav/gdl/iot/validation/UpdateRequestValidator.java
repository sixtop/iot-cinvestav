package mx.cinvestav.gdl.iot.validation;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import mx.cinvestav.gdl.iot.cloudclient.SensorData;
import mx.cinvestav.gdl.iot.cloudclient.SmartThingData;
import mx.cinvestav.gdl.iot.cloudclient.UpdateDataRequest;
import mx.cinvestav.gdl.iot.webpage.dao.Controller;
import mx.cinvestav.gdl.iot.webpage.dao.DAO;
import mx.cinvestav.gdl.iot.webpage.dao.Sensor;
import mx.cinvestav.gdl.iot.webpage.dao.SmartThing;

public class UpdateRequestValidator
{
	public static String validate(UpdateDataRequest req)
	{
		StringBuffer sb = new StringBuffer();
		EntityManager em = null;
		try
		{
			em = DAO.getEntityManager();
			int c_id = req.getControllerId();
			Controller c = em.find(Controller.class, c_id);
			if (c == null)
			{
				sb.append("The controller id is invalid; ");
			}
			else
			{				
				for (SmartThingData std : req.getSmartThingData())
				{
					int s_id = std.getSmartThingId();
					SmartThing sm = em.find(SmartThing.class, s_id);
					if(sm==null || sm.getIdcontroller()!=c_id)
					{
						sb.append("Controller id " + c_id + " is not associated with Smarthing "
								+ s_id + "; ");						
					}
					else 
					{
						for (SensorData sd : std.getSensorData())
						{
							int id = sd.getSensorId();
							Sensor s = em.find(Sensor.class, id);
							if(s==null || s.getIdthing()!=s_id)
							{
								sb.append("SmartThing id " + s_id
										+ " is not associated with sensorid " + id + "; ");								
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			Logger logger = Logger.getLogger(UpdateRequestValidator.class.getName());
			logger.log(Level.SEVERE, "Unexpected exception validating API request", e);
		}
		finally
		{
			if (em != null) em.close();
		}
		return sb.toString();
	}
}
