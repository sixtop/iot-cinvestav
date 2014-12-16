package mx.cinvestav.gdl.iot.webpage.client;

import mx.cinvestav.gdl.iot.webpage.dto.UserDTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class IoTEntryPoint implements EntryPoint
{
	private static final LoginServiceAsync loginService = GWT.create(LoginService.class);

	@Override
	public void onModuleLoad()
	{
		String sessionID = Cookies.getCookie("sid");
		if (sessionID == null)
		{
			Window.Location.replace("login.jsp");
		}
		else
		{
			loginService.loginFromSession(sessionID, new AsyncCallback<UserDTO>()
			{

				@Override
				public void onSuccess(UserDTO result)
				{
					if (result == null)
					{
						Window.Location.replace("login.jsp");
					}
					else if (result.getLoggedIn())
					{
						DOM.getElementById("root").getStyle().setDisplay(Display.BLOCK);
						continueModuleLoad();
					}
					else
					{
						Window.Location.replace("login.jsp");
					}
				}

				@Override
				public void onFailure(Throwable caught)
				{
					Window.Location.replace("login.jsp");
				}
			});
		}
	}

	public abstract void continueModuleLoad();
}