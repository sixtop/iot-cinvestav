package mx.cinvestav.gdl.iot.webpage.client;

import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dto.UserDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EpUser  extends IoTEntryPoint
{
	private DialogBox dbWait = new DialogBox();

	private String idUser;
	private DialogBox dialogBox = new DialogBox();
	private Button btDialogClose = new Button("Close");
	private Button btDialogError = new Button("Close");
	private VerticalPanel dialogPanel = new VerticalPanel();
	private Label lbDialogBox = new Label();

	final DecoratedPopupPanel popup = new DecoratedPopupPanel(true);

	private VerticalPanel formPanel = new VerticalPanel();

	private Button btSaveUser = new Button("Save User");
	private Button btCancelUser = new Button("Cancel");
	private HorizontalPanel buttonsPanel = new HorizontalPanel();

	private Button btNewPassword = new Button("New Password");
	private FlexTable tableFields = new FlexTable();
	private TextBox tbId = new TextBox();
	private TextBox tbName = new TextBox();
	private TextBox tbUserName = new TextBox();
	private TextBox tbEmail = new TextBox();
	private PasswordTextBox tbPassword = new PasswordTextBox();
	private PasswordTextBox tbPassword2 = new PasswordTextBox();

	private static final LoginServiceAsync loginService = GWT.create(LoginService.class);

	@Override
	public void continueModuleLoad()
	{
		tableFields.setText(0, 0, "Id: ");
		tableFields.setWidget(0, 1, tbId);
		tableFields.setText(1, 0, "Name: ");
		tableFields.setWidget(1, 1, tbName);
		tableFields.setText(2, 0, "User name: ");
		tableFields.setWidget(2, 1, tbUserName);
		tableFields.setText(3, 0, "Email: ");
		tableFields.setWidget(3, 1, tbEmail);
		tableFields.setWidget(4, 0, btNewPassword);
		tableFields.setWidget(4, 1, tbPassword);
		tableFields.setText(5, 0,"Confirm the password:");
		tableFields.setWidget(5, 1, tbPassword2);
		
		
		 tbPassword.setEnabled(false);
		 tbPassword2.setEnabled(false);

		tbId.setEnabled(false);
		formPanel.add(tableFields);

		buttonsPanel.add(btSaveUser);
		buttonsPanel.add(btCancelUser);
		formPanel.add(buttonsPanel);
		formPanel.setCellHorizontalAlignment(buttonsPanel, HasHorizontalAlignment.ALIGN_RIGHT);

		RootPanel.get("formContainer").add(formPanel);

		btDialogClose.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				dialogBox.hide();
				Window.Location.replace("wpUsers.jsp");
			}
		});

		btDialogError.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				dialogBox.hide();
			}
		});
		
		btNewPassword.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				 tbPassword.setEnabled(true);
				 tbPassword2.setEnabled(true);
			}
		});


		btSaveUser.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				saveUserAction();
			}
		});

		btCancelUser.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				Window.Location.replace("wpUsers.jsp");
			}
		});

		// We need to retrieve the id User to update
		idUser = Window.Location.getParameter("idUser");
		if (idUser != null)
		{
			updateUserAction();
		}
	}

	private void updateUserAction()
	{
		int id = Integer.parseInt(idUser);
		showDialogWait();
		loginService.getUser(id, new AsyncCallback<List<UserDTO>>()
		{
			@Override
			public void onFailure(Throwable caught)
			{
				dbWait.hide();
				//TODO:
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(List<UserDTO> result)
			{
				dbWait.hide();
				fillUserData(result);
			}
		});
	}

	private void fillUserData(List<UserDTO> result)
	{
		UserDTO c = result.get(0);
		tbId.setText(c.getId() + "");
		tbName.setText(c.getName());
		tbUserName.setText(c.getUsername());
		tbEmail.setText(c.getEmail());
		tbPassword.setEnabled(false);
	
	}

	private void saveUserAction()
	{
		UserDTO c = new UserDTO();
		if (idUser != null)
		{
			c.setId(Integer.parseInt(idUser));
		}
		c.setName(tbName.getText());
		c.setUsername(tbUserName.getText());
		c.setEmail(tbEmail.getText());
		
		if(tbPassword.isEnabled()){
			if(!tbPassword.getText().equals(tbPassword2.getText())){
				showInformationDialog("Error", "Password missmatch", btDialogError);
			}
		}
		
		c.setHash(tbPassword.getText());
		
		loginService.insertUser(c, new AsyncCallback<Void>()
		{
			@Override
			public void onSuccess(Void result)
			{
				dbWait.hide();
				showInformationDialog("Information", "User succesfully stored", btDialogClose);
			}

			@Override
			public void onFailure(Throwable caught)
			{
				//TODO
				dbWait.hide();
				Window.alert(caught.getMessage());
			}
		});
	}

	private void showInformationDialog(String title, String message, Button btDialogClose)
	{
		dialogBox.setAnimationEnabled(true);
		dialogBox.center();
		dialogBox.setGlassEnabled(true);
		dialogBox.setText(title);
		lbDialogBox.setText(message);
		dialogPanel.add(lbDialogBox);
		dialogPanel.setCellHorizontalAlignment(lbDialogBox, HasHorizontalAlignment.ALIGN_CENTER);
		dialogPanel.add(btDialogClose);
		dialogPanel.setCellHorizontalAlignment(btDialogClose, HasHorizontalAlignment.ALIGN_CENTER);
		dialogBox.add(dialogPanel);
		dialogBox.show();
	}

	public void showDialogWait()
	{

		dbWait.setAnimationEnabled(true);
		dbWait.setGlassEnabled(true);
		dbWait.setModal(true);
		dbWait.center();

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
