package mx.cinvestav.gdl.iot.webpage.client;

import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dto.UserDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("iot.login")
public interface LoginService extends RemoteService
{
	public UserDTO login(String username, String password) throws DatabaseException;

	public UserDTO loginFromSession(String sessionID);

	public void logout();
	
	public void insertUser(UserDTO user) throws DatabaseException;
	
	public List<UserDTO> getUser(Integer id) throws DatabaseException;
}
