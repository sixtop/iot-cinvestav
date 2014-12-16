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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EpLogin implements EntryPoint
{
	private DialogBox dbWait = new DialogBox();
	private VerticalPanel formContainer = new VerticalPanel();
	private FlexTable tableLogin = new FlexTable();
	private TextBox tbUserName = new TextBox();
	private PasswordTextBox tbPassword = new PasswordTextBox();
	private Button btLogin = new Button("Login");
	private static final LoginServiceAsync loginService = GWT.create(LoginService.class);

	public static String SESSION_ID = null;

	@Override
	public void onModuleLoad()
	{
		tableLogin.setText(0, 0, "User");
		tableLogin.setWidget(0, 1, tbUserName);

		tableLogin.setText(1, 0, "Password");
		tableLogin.setWidget(1, 1, tbPassword);

		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.add(tableLogin);
		verticalPanel.add(btLogin);
		verticalPanel.setCellHorizontalAlignment(btLogin, HasHorizontalAlignment.ALIGN_CENTER);

		DecoratorPanel panelLogin = new DecoratorPanel();
		panelLogin.add(verticalPanel);
		formContainer.add(panelLogin);

		RootPanel.get("formContainer").add(formContainer);

		btLogin.addClickHandler(new ClickHandler()
		{

			public void onClick(ClickEvent event)
			{
				dbWait.show();
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

		String logout = Window.Location.getParameter("Logout");
		if (logout != null)
		{
			logout();
		}

	}

	private void logout()
	{
		dbWait.show();

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