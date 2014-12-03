package mx.cinvestav.gdl.iot.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import mx.cinvestav.gdl.iot.cloudclient.Data;
import mx.cinvestav.gdl.iot.cloudclient.SensorData;
import mx.cinvestav.gdl.iot.cloudclient.SmartThingData;
import mx.cinvestav.gdl.iot.cloudclient.UpdateDataRequest;
import mx.cinvestav.gdl.iot.cloudclient.UpdateDataResponse;
import mx.cinvestav.gdl.iot.dao.Controller;
import mx.cinvestav.gdl.iot.dao.ControllerProperty;
import mx.cinvestav.gdl.iot.dao.DAO;
import mx.cinvestav.gdl.iot.dao.Measure;
import mx.cinvestav.gdl.iot.dao.Sensor;
import mx.cinvestav.gdl.iot.dao.SmartThing;
import mx.cinvestav.gdl.iot.exception.DatabaseException;
import mx.cinvestav.gdl.iot.validation.UpdateRequestValidator;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.NotFoundException;

/**
 * Defines v1 of a IoT API, which provides simple update methods.
 */
@Api(name = "iotService", version = "v2")
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
					//int c_id = request.getControllerId();
					SmartThingData[] thing_data_array = request.getSmartThingData();
					EntityManager em = null;
					EntityTransaction tx = null;
					try
					{
						em = DAO.createEntityManager();
						tx = em.getTransaction();
						tx.begin();
						for (SmartThingData thing_data : thing_data_array)
						{
							int t_id = thing_data.getSmartThingId();
							SmartThing smartThingEntity = em.find(SmartThing.class, t_id);

							SensorData[] sensor_data_array = thing_data.getSensorData();
							for (SensorData sensor_data : sensor_data_array)
							{
								int s_id = sensor_data.getSensorId();
								Sensor sensorEntity = smartThingEntity.getSensors().get(s_id);

								Data[] measures = sensor_data.getMeasures();
								for (Data m : measures)
								{
									// create and persiste the new measure
									Measure measureEntity = new Measure();
									measureEntity.setMeasure(m.getData());
									measureEntity.setMeasure_date(Timestamp.valueOf(m.getTime()));
									measureEntity.setIdsensor(sensorEntity.getId());
									measureEntity.setIdthing(smartThingEntity.getId());
									em.persist(measureEntity);

									// update associated entities
									sensorEntity.getMeasures().put(measureEntity.getId(),
											measureEntity);
									smartThingEntity.getMeasures().put(measureEntity.getId(),
											measureEntity);
								}
							}
							em.flush();
						}
						tx.commit();
					}
					catch (Exception e)
					{
						if (tx != null)
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

					res.setMessage("ok");
					res.setStatus(200);

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
	
	/*@ApiMethod(name = "createController", httpMethod = "post")
	public UpdateDataResponse createController(UpdateDataRequest request) throws NotFoundException
	{
		
		UpdateDataResponse res = new UpdateDataResponse();
		
		Controller c = new  Controller();
		c.setDescription("desc");
		c.setLocation("Loc");
		c.setName("name");
		List<ControllerProperty> props = new ArrayList<>();
		
		
		for(int i= 0; i < 10 ; i++)
		{
			ControllerProperty prop = new ControllerProperty();
			prop.setActive(false);
			prop.setName("namep");
			prop.setValue("valp");
			props.add(prop);
		}
		
		try
		{
			DAO.insertEntity(c, props);
		}
		catch (DatabaseException e1)
		{
			e1.printStackTrace();
		}
		
		res.setStatus(200);
		res.setMessage("OK");
		return res ; 
	}*/
}
