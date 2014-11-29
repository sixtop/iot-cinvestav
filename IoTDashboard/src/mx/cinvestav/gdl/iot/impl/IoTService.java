package mx.cinvestav.gdl.iot.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import mx.cinvestav.gdl.iot.cloudclient.UpdateDataRequest;
import mx.cinvestav.gdl.iot.cloudclient.UpdateDataResponse;
import mx.cinvestav.gdl.iot.dao.Controller;
import mx.cinvestav.gdl.iot.dao.DAO;
import mx.cinvestav.gdl.iot.validation.UpdateRequestValidator;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.NotFoundException;

/**
 * Defines v1 of a IoT API, which provides simple update methods.
 */
@Api(name = "iotService", version = "v1")
public class IoTService
{
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@ApiMethod(name = "updateData", httpMethod = "post")
	public UpdateDataResponse updateData(UpdateDataRequest request) throws NotFoundException
	{
		UpdateDataResponse res = new UpdateDataResponse();
		try
		{
			if (request != null)
			{
				String status = "fail";
				String validationResult = UpdateRequestValidator.validate(request);
				if (validationResult == null || "".equals(validationResult))
				{
					int controllerId = Integer.valueOf(request.getControllerId());
					String smartId = request.getSmartThingId();
					res.setMessage("OK" + status);
					res.setStatus(200);
					
					EntityManager em = null;
					try
					{
						em = DAO.getEntityManager();
						Controller c = em.find(Controller.class, controllerId);					
					
						
						if(c!=null) 
						{
							status = c.getName();
							res.setMessage("OK" + status);
						}
						
//						SensorData[] sensorData = request.getSensorData();
//						for (SensorData data : sensorData)
//						{
//							String sensorId = data.getSensorId();
//							//validar sensor-cotrolador
//							Measure[] measures = data.getMeasures();
//							for (Measure m : measures)
//							{
//								String data2 = m.getData();
//								String time = m.getTime();
//
//								//String statement = "INSERT INTO sensor (idcontrolador, ) VALUES( ? , ? )";
//								//PreparedStatement stmt = conn.prepareStatement(statement);
//								//stmt.setString(1, fname);
//								//stmt.setString(2, content);
//								int success = 2;
//								//success = stmt.executeUpdate();
//							}
//						}
//						tx.commit();
					}
					catch (Exception e)
					{
						e.printStackTrace();
						logger.log(Level.SEVERE, "Unexpected exception executing query", e);
						res.setMessage(e.getMessage());
						res.setStatus(500);
					}
					finally
					{
						if(em!=null)
						em.close();
					}

					
				}
				else
				{
					res.setMessage("Invalid request:" + validationResult);
					res.setStatus(400);
				}
			}
			else
			{
				res.setMessage("Empty request");
				res.setStatus(404);
			}
		}
		catch (Exception e)
		{
			logger.log(Level.SEVERE, "Unexpected exception in update data", e);
			res.setMessage(e.getMessage());
			res.setStatus(500);
		}
		return res;
	}
}
