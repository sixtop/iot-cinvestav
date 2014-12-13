package mx.cinvestav.gdl.iot.impl;

import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import mx.cinvestav.gdl.iot.cloudclient.Data;
import mx.cinvestav.gdl.iot.cloudclient.SensorData;
import mx.cinvestav.gdl.iot.cloudclient.SmartThingData;
import mx.cinvestav.gdl.iot.cloudclient.UpdateDataRequest;
import mx.cinvestav.gdl.iot.cloudclient.UpdateDataResponse;
import mx.cinvestav.gdl.iot.validation.UpdateRequestValidator;
import mx.cinvestav.gdl.iot.webpage.dao.DAO;
import mx.cinvestav.gdl.iot.webpage.dao.Measure;

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
				String validationResult = UpdateRequestValidator.validate(request);
				if (validationResult == null || "".equals(validationResult))
				{
					SmartThingData[] thing_data_array = request.getSmartThingData();
					EntityManager em = null;
					EntityTransaction tx = null;
					try
					{
						em = DAO.getEntityManager();
						tx = em.getTransaction();
						tx.begin();
						for (SmartThingData thing_data : thing_data_array)
						{
							SensorData[] sensor_data_array = thing_data.getSensorData();
							for (SensorData sensor_data : sensor_data_array)
							{
								Data[] measures = sensor_data.getMeasures();
								for (Data m : measures)
								{
									// create and persist the new measure
									Measure measureEntity = new Measure();
									measureEntity.setMeasure(m.getData());
									measureEntity.setMeasure_date(Timestamp.valueOf(m.getTime()));
									measureEntity.setIdsensor(sensor_data.getSensorId());
									measureEntity.setIdthing(thing_data.getSmartThingId());
									em.persist(measureEntity);
								}
							}
							em.flush();
						}
						tx.commit();
						res.setMessage("ok");
						res.setStatus(200);
					}
					catch (Exception e)
					{
						if (tx != null && tx.isActive())
						{
							tx.rollback();
						}
						e.printStackTrace();
						logger.log(Level.SEVERE, "Unexpected exception executing query", e);
						res.setMessage(e.getMessage());
						res.setStatus(500);
					}
					finally
					{
						if (em != null)
						{
							em.close();
						}
					}
				}
				else
				{
					res.setMessage("Invalid request: " + validationResult);
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
