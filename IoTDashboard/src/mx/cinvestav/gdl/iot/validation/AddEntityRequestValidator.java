package mx.cinvestav.gdl.iot.validation;

import javax.servlet.http.HttpServletRequest;

import mx.cinvestav.gdl.iot.dashboard.client.ClientConstants;

public class AddEntityRequestValidator
{
	public static String validate(HttpServletRequest req)
	{
		StringBuffer errors = new StringBuffer();
		String operation = req.getParameter(ClientConstants.OPERATION);
		if (isNullOrEmpty(operation))
		{
			errors.append("Entity type is null or empty; ");
		}
		else
		{
			switch (operation)
			{
				case ClientConstants.CONTROLLER:
				{
					if (isNullOrEmpty(req.getParameter(ClientConstants.LOCATION)))
					{

						errors.append("Entity location is null or empty; ");
					}
					break;
				}
				case ClientConstants.SENSOR:
				case ClientConstants.SMART_THING:
					break;
				default:
					errors.append("Invalid entity type; ");

			}
		}
		if (isNullOrEmpty(req.getParameter(ClientConstants.NAME)))
		{
			errors.append("Entity name is null or empty; ");
		}
		if (isNullOrEmpty(req.getParameter(ClientConstants.DESCRIPTION)))
		{
			errors.append("Entity description is null or empty; ");
		}
		String[] propNames = req.getParameterValues(ClientConstants.PROP_NAMES);
		if (propNames != null && propNames.length > 0)
		{
			String[] propValues = req.getParameterValues(ClientConstants.PROP_VALUES);
			if (propValues == null || propValues.length != propNames.length)
			{
				errors.append("Property values array is not valid; ");
			}
			//TODO: validate empty names

			String[] propActive = req.getParameterValues(ClientConstants.PROP_ACTIVE);
			if (propActive == null || propActive.length != propNames.length)
			{
				errors.append("Property active array is not valid; ");
			}
		}
		return errors.toString();
	}

	private static boolean isNullOrEmpty(String string)
	{
		if (string == null || "".equals(string.trim())) return true;
		return false;
	}
}
