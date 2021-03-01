package es.unex.pi.resources;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import es.unex.pi.dao.*;
import es.unex.pi.model.*;

@Path("/users")
public class UsersResources {
	@Context
	ServletContext sc;
	@Context
	UriInfo uriInfo;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(User newUser, @Context HttpServletRequest request) throws Exception {
		Connection conn = (Connection) sc.getAttribute("dbConn");

		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);

		Response res = null;
		if (newUser.getEmail() != null && newUser.getUsername() != null && newUser.getPassword() != null) {
			userDao.add(newUser);
			res = Response // return 201 and Location:
					.created(uriInfo.getAbsolutePathBuilder().build())
					.contentLocation(uriInfo.getAbsolutePathBuilder().build()).build();
		}

		return res;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response put(User userUpdate, @Context HttpServletRequest request) throws Exception {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);

		Response response = null;

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		user.setUsername(userUpdate.getUsername());

		user.setEmail(userUpdate.getEmail());

		user.setPassword(userUpdate.getPassword());

		userDao.save(user);
		return response;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserJSON(@Context HttpServletRequest request) { 
		Connection conn = (Connection) sc.getAttribute("dbConn");
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		User returnUser = userDao.get(user.getId());

		if (returnUser != null) {
			return returnUser;
		} else
			return null; 
	}
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(@Context HttpServletRequest request) throws Exception {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);

		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);

		List<Route> listRoute = new ArrayList<Route>();
		listRoute = routeDao.getAllByUser(user.getId());

		if (user != null) {
			for (int i = 0; i < listRoute.size(); i++) {
				routeDao.delete(listRoute.get(i).getId());
			}
			userDao.delete(user.getId());
			session.invalidate();
		}

		Response res;
		res = Response // return 201 and Location:
				.created(uriInfo.getAbsolutePathBuilder().build())
				.contentLocation(uriInfo.getAbsolutePathBuilder().build()).build();

		return res;
	}
	
}
