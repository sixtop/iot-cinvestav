package mx.cinvestav.gdl.iot.webpage.client;

import java.util.Date;

import mx.cinvestav.gdl.iot.webpage.dto.UserDTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EpLogin implements EntryPoint
{
	private DialogBox dbWait = new DialogBox();
	private VerticalPanel formContainer = new VerticalPanel();

	private TextBox tbUserName = new TextBox();
	private PasswordTextBox tbPassword = new PasswordTextBox();
	private Button btLogin = new Button("Login");
	private static final LoginServiceAsync loginService = GWT.create(LoginService.class);

	public static String SESSION_ID = null;

	@Override
	public void onModuleLoad()
	{
		String logout = Window.Location.getParameter("Logout");
		if (logout != null)
		{
			logout();
		}
		
		formContainer.add(new Label("User"));
		formContainer.add(tbUserName);
		formContainer.add(new Label("Password"));
		formContainer.add(tbPassword);
		
		//btLogin.addStyleName("myButton");
		
		formContainer.add(btLogin);
		formContainer.setCellHorizontalAlignment(btLogin, HasHorizontalAlignment.ALIGN_CENTER);
		
		formContainer.setSpacing(10);

		DecoratorPanel panelLogin = new DecoratorPanel();
		panelLogin.add(formContainer);

		RootPanel.get("formContainer").add(panelLogin);

		btLogin.addClickHandler(new ClickHandler()
		{

			public void onClick(ClickEvent event)
			{
				showDialogWait();
				loginService.login(tbUserName.getText(), tbPassword.getText(), new AsyncCallback<UserDTO>()
				{

					@Override
					public void onSuccess(UserDTO result)
					{
						dbWait.hide();
						if (result != null && result.getLoggedIn())
						{
							String sessionID = result.getSessionID();
							final long DURATION = 1000 * 60 * 60 * 24 * 1;
							Date expires = new Date(System.currentTimeMillis() + DURATION);
							Cookies.setCookie("sid", sessionID, expires, null, "/", false);
							Window.Location.replace("/index.jsp");
						}
						else
						{
							Window.alert("Access Denied. Check your user-name and password.");
						}
					}

					@Override
					public void onFailure(Throwable caught)
					{
						Window.alert("Error ocurred while signing on:" + caught.getMessage());
					}
				});
			}
		});
	}

	private void logout()
	{
		dbWait.show();
		loginService.logout(new AsyncCallback<Void>()
		{

			@Override
			public void onFailure(Throwable caught)
			{
				dbWait.hide();
			}

			@Override
			public void onSuccess(Void result)
			{
				dbWait.hide();
			}
		});
	}

	public void showDialogWait()
	{
		dbWait.center();
		dbWait.setAnimationEnabled(true);
		dbWait.setGlassEnabled(true);
		dbWait.setModal(true);

		VerticalPanel dialogContents = new VerticalPanel();

		dialogContents.setSpacing(4);

		Image image = new Image();

		image.setUrl(GWT.getHostPageBaseURL() + "images/loading2.gif");

		dialogContents.add(image);
		dialogContents.setCellHorizontalAlignment(image, HasHorizontalAlignment.ALIGN_CENTER);

		dbWait.setWidget(dialogContents);
		dbWait.show();

	}
}