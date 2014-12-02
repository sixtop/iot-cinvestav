package mx.cinvestav.gdl.iot.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.cinvestav.gdl.iot.dao.Controller;
import mx.cinvestav.gdl.iot.dao.DAO;
import mx.cinvestav.gdl.iot.dao.IoTEntity;
import mx.cinvestav.gdl.iot.dao.IoTProperty;
import mx.cinvestav.gdl.iot.dao.Sensor;
import mx.cinvestav.gdl.iot.dao.SmartThing;
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
					boolean active = false;
					String sensor_type = null;
					String unit = null;
					double latitude = 0;
					double longitude = 0;
					double altitude = 0;
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
		//for()
		//{

		//}
		return null;
	}
}
