package mx.cinvestav.gdl.iot.webpage.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.cinvestav.gdl.iot.dao.DAO;
import mx.cinvestav.gdl.iot.dao.User;
import mx.cinvestav.gdl.iot.util.BCrypt;
import mx.cinvestav.gdl.iot.webpage.client.DatabaseException;
import mx.cinvestav.gdl.iot.webpage.client.LoginService;
import mx.cinvestav.gdl.iot.webpage.dto.UserDTO;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService
{
	private static final long serialVersionUID = -8306702743270115220L;
	Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());
	private static Mapper mapper = new DozerBeanMapper();

	public UserDTO login(String username, String password) throws DatabaseException
	{
		UserDTO ret = null;
		try
		{
			User user = DAO.getUser(username);
			if (user != null)
			{
				if (BCrypt.checkpw(password, user.getHash()))
				{
					ret = new UserDTO();
					ret.setEmail(user.getEmail());
					ret.setId(user.getId());
					ret.setName(user.getName());
					ret.setLoggedIn(true);
					ret.setSessionID(this.getThreadLocalRequest().getSession().getId());
					ret.setUsername(username);
					storeUserInSession(ret);
				}
			}
			return ret;
		}
		catch (DatabaseException e)
		{
			String message = "Exception in deleteEntity: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}

	private void storeUserInSession(UserDTO user)
	{
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
		HttpSession session = httpServletRequest.getSession(true);
		session.setAttribute("user", user);
	}

	@Override
	public UserDTO loginFromSession(String sessionID)
	{
		UserDTO user = null;
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
		HttpSession session = httpServletRequest.getSession();
		Object userObj = session.getAttribute("user");
		if (userObj != null && userObj instanceof UserDTO)
		{
			user = (UserDTO) userObj;
			return user;
		}
		return user;
	}

	@Override
	public void logout()
	{
		HttpServletRequest httpServletRequest = this.getThreadLocalRequest();
		HttpSession session = httpServletRequest.getSession();
		session.removeAttribute("user");
	}

	@Override
	public void insertUser(UserDTO userDTO) throws DatabaseException
	{
		//map to non DTO object
		User user = mapper.map(userDTO, User.class);
		try
		{
			if(!"".equals(userDTO.getHash()))
			{
				String hash = BCrypt.hashpw(user.getHash(), BCrypt.gensalt());				
				user.setHash(hash);
			}
			else user.setHash("");
			DAO.storeUser(user);
		}
		catch (DatabaseException e)
		{
			String message = "Exception in deleteEntity: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}

	@Override
	public List<UserDTO> getUser(Integer id) throws DatabaseException
	{
		try
		{
			List<User> users = DAO.getUser(id);
			List<UserDTO> res = new ArrayList<UserDTO>();
			for(User u : users)
			{
				UserDTO dto = mapper.map(u, UserDTO.class);
				dto.setHash("");
				res.add(dto);
			}
			return res;
		}
		catch (DatabaseException e)
		{
			String message = "Exception in deleteEntity: " + e.getMessage();
			logger.log(Level.SEVERE, message, e);
			throw e;
		}
	}
}