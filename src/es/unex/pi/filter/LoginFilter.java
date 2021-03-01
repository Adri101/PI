package es.unex.pi.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/UploadRouteServlet.do", "/MyRoutesServlet.do", "/AddKudosServlet.do",
		"/DecreaseKudosServlet.do", "/RouteStarsServlet.do", "/FilterRouteServlet.do", "/AvailableRoutesServlet.do",
		"/SearchServlet.do","/ShowRouteServlet.do"  })
public class LoginFilter implements Filter {

	public void init(FilterConfig fc) {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		if (session.getAttribute("user") == null) {
			res.sendRedirect(req.getContextPath() + "/IniciarSesionServlet.do");
		} else
			chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
