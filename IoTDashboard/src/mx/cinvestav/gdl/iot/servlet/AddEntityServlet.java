package mx.cinvestav.gdl.iot.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.cinvestav.gdl.iot.dao.Controller;
import mx.cinvestav.gdl.iot.dao.ControllerProperty;
import mx.cinvestav.gdl.iot.dao.DAO;
import mx.cinvestav.gdl.iot.dao.IoTEntity;
import mx.cinvestav.gdl.iot.dao.IoTProperty;
import mx.cinvestav.gdl.iot.dao.Sensor;
import mx.cinvestav.gdl.iot.dao.SensorProperty;
import mx.cinvestav.gdl.iot.dao.SmartThing;
import mx.cinvestav.gdl.iot.dao.SmartThingProperty;
import mx.cinvestav.gdl.iot.dashboard.client.ClientConstants;
import mx.cinvestav.gdl.iot.exception.DatabaseException;
import mx.cinvestav.gdl.iot.validation.AddEntityRequestValidator;

/**
 * Servlet implementation class AddControllerServlet
 */
public class AddEntityServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(DAO.class.getName());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEntityServlet()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String errors = AddEntityRequestValidator.validate(request);
		if (errors == null || "".equals(errors))
		{
			String operation = request.getParameter(ClientConstants.OPERATION).trim();
			IoTEntity entity = null;
			switch (operation)
			{
				case ClientConstants.CONTROLLER:
				{
					String location = request.getParameter(ClientConstants.LOCATION);
					entity = new Controller(location);
					break;
				}
				case ClientConstants.SENSOR:
				{
					boolean active = Boolean.valueOf(request.getParameter(ClientConstants.ACTIVE));
					String sensor_type = request.getParameter(ClientConstants.SENSOR_TYPE);
					String unit = request.getParameter(ClientConstants.UNIT);
					double latitude = Double.parseDouble(request.getParameter(ClientConstants.LATITUDE));
					double longitude = Double.parseDouble(request.getParameter(ClientConstants.LONGITUDE));
					double altitude = Double.parseDouble(request.getParameter(ClientConstants.ALTITUDE));
					entity = new Sensor(active, sensor_type, unit, latitude, longitude, altitude);
					break;
				}
				case ClientConstants.SMART_THING:
				{
					entity = new SmartThing();
					break;
				}
			}
			entity.setName(request.getParameter(ClientConstants.NAME).trim());
			entity.setDescription(request.getParameter(ClientConstants.DESCRIPTION).trim());

			Collection<? extends IoTProperty> props = extractProperties(operation, request);
			try
			{
				DAO.insertEntity(entity, props);
			}
			catch (DatabaseException e)
			{
				String message = "Cannot store in DB: " + e.getMessage();
				logger.log(Level.SEVERE, message, e);
				response.sendError(400, message);
			}
		}
		else
		{
			response.sendError(400, "AddEntity Validation exception: " + errors);
		}
	}

	private Collection<? extends IoTProperty> extractProperties(String operation,
			HttpServletRequest request)
	{
		String[] propNames = request.getParameterValues(ClientConstants.PROP_NAMES);
		String[] propValues = request.getParameterValues(ClientConstants.PROP_VALUES);
		String[] propActive = request.getParameterValues(ClientConstants.PROP_ACTIVE);
		Collection<IoTProperty> props = new ArrayList<>();

		for (int i = 0; i < propNames.length; i++)
		{
			IoTProperty prop = null;
			if (ClientConstants.CONTROLLER.equals(operation))
			{
				prop = new ControllerProperty();
			}
			else if (ClientConstants.SENSOR.equals(operation))
			{
				prop = new SensorProperty();
			}
			else if (ClientConstants.SMART_THING.equals(operation))
			{
				prop = new SmartThingProperty();
			}
			prop.setName(propNames[i]);
			prop.setValue(propValues[i]);
			prop.setActive(Boolean.parseBoolean(propActive[i]));
			props.add(prop);
		}

		return props;
	}
}
