package es.unex.pi.resources;

import java.sql.Connection;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import es.unex.pi.resources.exceptions.CustomBadRequestException;
import es.unex.pi.dao.*;
import es.unex.pi.model.*;
import es.unex.pi.resources.exceptions.CustomNotFoundException;

@Path("/routes")
public class RoutesResource {
	@Context
	ServletContext sc;
	@Context
	UriInfo uriInfo;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Route> getAllRoutesJSON(@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);
		
		List<Route> routes = routeDao.getAll();

		return routes;
	}
	
	@GET
	@Path("/{routeid: [0-9]+}") 
	@Produces(MediaType.APPLICATION_JSON)
	public Route getRouteJSON(@PathParam("routeid") long routeid, @Context HttpServletRequest request) { // Se usa
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);

		Route route = routeDao.get(routeid);

		if ((route != null)) {
			return route;
		} else
			throw new CustomNotFoundException("route (" + routeid + ") is not found");
	}
	@GET
	@Path("/myRoutes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Route> getRoutesByUserJSON(@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);

		 HttpSession session = request.getSession();
		 User user = (User) session.getAttribute("user");
		List<Route> routes;

		routes = routeDao.getAllByUser(user.getId());

		return routes;
	}
	@GET
	@Path("/sortRoutes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Route> ordenarRoutes(@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);
		List<Route> routes;

		routes = routeDao.getByKudos();

		return routes;
	}
	@GET
	@Path("/filterRoutes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Route> filtrarRoutes(@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);
		List<Route> routes;

		routes = routeDao.getFilterByKudos();

		return routes;
	}
	@GET
	@Path("/availableRoutes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Route> disponiblesRoutes(@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);
		List<Route> routes;

		routes = routeDao.getAvailableRoutes();

		return routes;
	}
	@GET
	@Path("/searchRoutes/{routeid: [a-zA-Z]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Route> getRutaBuscar(@PathParam("routeid") String routeid, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeao = new JDBCRouteDAOImpl();
		routeao.setConnection(conn);
		
		List<Route> routes;

		routes = routeao.getAllBySearchAll(routeid);

		return routes;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON) // indica que para ser ejecutado necesita un objeto json
	public Response addRoute(Route newRoute, @Context HttpServletRequest request) throws Exception {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO RouteDao = new JDBCRouteDAOImpl();
		RouteDao.setConnection(conn);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		Response res;

		if (newRoute != null && newRoute.getDescription()!=null && newRoute.getDate() != null && newRoute.getBlocked()<2 &&
				newRoute.getDifficulty()!= null && newRoute.getDistance()>=0 && newRoute.getElevation()>=0 && newRoute.getKudos()>=0 && newRoute.getTitle()!=null
				&& newRoute.getTime()!=null ) {
			newRoute.setIdu(user.getId());
			long id = RouteDao.add(newRoute); 
			res = Response 
					.created(uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build())
					.contentLocation(uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build()).build();
			return res;
		} else
			throw new CustomBadRequestException("Errors in parameters");


	}
	
	@PUT
	@Path("/{routeid: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response put(Route routeUpdate, @PathParam("routeid") long routeid, @Context HttpServletRequest request)
			throws Exception {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);
		
		  HttpSession session = request.getSession();
		  User user = (User) session.getAttribute("user");
		 
		Response response = null;
		
		Route route = routeDao.get(routeUpdate.getId());
		if (route.getIdu()==user.getId()&&route != null && routeUpdate.getDescription()!=null && routeUpdate.getDate() != null && routeUpdate.getBlocked()<2 &&
				routeUpdate.getDifficulty()!= null && routeUpdate.getDistance()>=0 && routeUpdate.getElevation()>=0 && routeUpdate.getKudos()>=0 && routeUpdate.getTitle()!=null
				&& routeUpdate.getTime()!=null) {
			if (route.getId() != routeid)
				throw new CustomBadRequestException("Error in id");
			else {
				long id=route.getId();
				routeDao.save(routeUpdate);
				response = Response // return 201 and Location: /orders/newid
						.created(uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build())
						.contentLocation(uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build()).build();

			}
		}

		else
			throw new WebApplicationException(Response.Status.NOT_FOUND);

		return response;
	}
	@PUT
	@Path("/addKudos/{routeid: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON) 
	public Response plusKudos( @PathParam("routeid") long routeid,
			@Context HttpServletRequest request) throws Exception {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);

		Response res = null;

		Route route = routeDao.get(routeid);
		if ((route != null)) {
			if (route.getId() != routeid)
				throw new CustomBadRequestException("Error in id");
			else {
				int kudos_before = route.getKudos();
				route.setKudos(kudos_before + 1);
				routeDao.save(route);
			}
		} else
			throw new CustomBadRequestException("Errors in parameters");
		res = Response 
				.created(uriInfo.getAbsolutePathBuilder().build())
				.contentLocation(uriInfo.getAbsolutePathBuilder().build()).build();
		return res;

	}
	@PUT
	@Path("/lessKudos/{routeid: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON) 
	public Response minusKudos( @PathParam("routeid") long routeid,
			@Context HttpServletRequest request) throws Exception {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);

		Response res = null;

		Route route = routeDao.get(routeid);
		if ((route != null)) {
			if (route.getId() != routeid)
				throw new CustomBadRequestException("Error in id");
			else {
				int kudos_before = route.getKudos();
				route.setKudos(kudos_before - 1);
				routeDao.save(route);
			}
		} else
			throw new CustomBadRequestException("Errors in parameters");
		res = Response 
				.created(uriInfo.getAbsolutePathBuilder().build())
				.contentLocation(uriInfo.getAbsolutePathBuilder().build()).build();
		return res;

	}
	@PUT
	@Path("/state/{routeid: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON) 
	public Response cambiarEstadoRuta(@PathParam("routeid") long routeid, @Context HttpServletRequest request)
			throws Exception {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO routeDao = new JDBCRouteDAOImpl();
		routeDao.setConnection(conn);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);

		Response res = null;

		Route route = routeDao.get(routeid);
		User user_aux = userDao.get(route.getIdu());
		if ((route != null) && ((user.getUsername().equals(user_aux.getUsername())))) {
			if (route.getId() != routeid)
				throw new CustomBadRequestException("Error in id");
			else {
				if (route.getTitle() != "") {
					if (route.getBlocked() == 0) {
						route.setBlocked(1);
						routeDao.save(route);
					} else {
						route.setBlocked(0);
						routeDao.save(route);
					}
				} else
					throw new CustomBadRequestException("Errors in parameters");
			}
		} else
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		res = Response 
				.created(uriInfo.getAbsolutePathBuilder().build())
				.contentLocation(uriInfo.getAbsolutePathBuilder().build()).build();
		return res;

	}
	@DELETE
	  @Path("/{routeid: [0-9]+}")	  
	  public Response deleteRoute(@PathParam("routeid") long routeid,
			  					  @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		RouteDAO RouteDao = new JDBCRouteDAOImpl();
		RouteDao.setConnection(conn);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		Route Route = RouteDao.get(routeid);
		if ((Route != null)&&Route.getIdu()==user.getId()){
					RouteDao.delete(routeid);
					return Response.noContent().build(); 
		}
		else throw new CustomBadRequestException("Error in user or id");		
			
	  }
}
