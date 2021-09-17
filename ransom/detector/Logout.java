package ransom.detector;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("logout")
public class Logout {
	
	@Context HttpServletRequest request;
	@Context HttpServletResponse response;
    @POST
   // @Produces(MediaType.APPLICATION_JSON)
	public String logout()
	{
    	try {
    	Cookie[] cookies = request.getCookies();
        
        if (cookies != null) {
            for (Cookie aCookie : cookies) {
                aCookie.setMaxAge(0);
                response.addCookie(aCookie);
            }
        HttpSession session=request.getSession();
        session.invalidate();
		System.out.println("LoggedOut");
			}
    	}catch(Exception e)
    	{e.printStackTrace();}     

    	return "Logged_Out";
}
}
