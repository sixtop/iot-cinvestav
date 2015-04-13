package mx.cinvestav.gdl.iot.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import mx.cinvestav.gdl.iot.cloudclient.Data;
import mx.cinvestav.gdl.iot.cloudclient.SensorData;
import mx.cinvestav.gdl.iot.cloudclient.SmartThingData;
import mx.cinvestav.gdl.iot.cloudclient.UpdateDataRequest;
import mx.cinvestav.gdl.iot.cloudclient.UpdateDataResponse;
import mx.cinvestav.gdl.iot.dao.DAO;
import mx.cinvestav.gdl.iot.dao.Measure;
import mx.cinvestav.gdl.iot.validation.UpdateRequestValidator;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.NotFoundException;

import org.apache.commons.codec.binary.Base64;

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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssssZ");
		SimpleDateFormat gmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssss");
		gmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		
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
									Date parse = dateFormat.parse(m.getTime());
									measureEntity.setMeasure_date(Timestamp.valueOf(gmt.format(parse)));
									measureEntity.setIdsensor(sensor_data.getSensorId());
									measureEntity.setIdthing(thing_data.getSmartThingId());
									measureEntity.setImage(Base64.decodeBase64(m.getImage()));
									measureEntity.setMetadata(m.getMetadata());
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
	
	public static void main(String[] args) throws ParseException
	{
		SimpleDateFormat local = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssssZ");
		Date parsedDate = local.parse("2015-03-11 15:13:0011-0600");
		
		SimpleDateFormat gmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssss");
		gmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		
	    Timestamp timestamp = Timestamp.valueOf(gmt.format(parsedDate));
		System.out.println(timestamp);
	}
}
