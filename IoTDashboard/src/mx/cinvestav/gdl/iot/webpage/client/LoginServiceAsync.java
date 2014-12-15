package mx.cinvestav.gdl.iot.webpage.client;

import mx.cinvestav.gdl.iot.webpage.dto.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync
{
	public void login(String username, String password, AsyncCallback<UserDTO> callback);

	public void loginFromSession(String sessionID, AsyncCallback<UserDTO> callback);

	public void logout(AsyncCallback<Void> callback);
	
	public void insertUser(UserDTO user, AsyncCallback<Void> callback) throws DatabaseException;
}
