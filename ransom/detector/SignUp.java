package ransom.detector;

import java.util.HashMap;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import dao.Dao;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import bean.User;

@Path("signup")
public class SignUp  {
	
	
	 @Context HttpServletRequest request;
	 @Context HttpServletResponse response;
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	// @Produces(MediaType.APPLICATION_JSON)
	 
	 public String signUp(User user) {
	    	
		    String username=user.getUsername();
	    	String password= user.getPassword();
	    	System.out.println(user.getUsername()+"______________________"+user.getPassword());
	    	HttpSession ses=request.getSession();
	    	try {
	    		
	    	if((boolean)ses.getAttribute("login_status"))
	    	{   
	    		
	    		return "Already_LoggedIn";
	    	}
	    	}catch(Exception e) {}
	    	
	    	Dao dao_Obj = new Dao();
	    	if(dao_Obj.getDataSet().containsKey(username))
	        {    
	    		
		    	return "UserName_Already_Present";
		    	
	        }
		    dao_Obj.storeData(username, password);
	    	Cookie ck= new Cookie("logged_user", username);
	    	ck.setMaxAge(60*60);
	    	//ck.setPath("/");
			response.addCookie(ck);
	    	
			System.out.println(ses.getAttribute("login_status"));
			ses.setAttribute("login_status",true);
			//response.setHeader("Set-Cookie", "alex=pandian;Path=/; SameSite=none ; Secure");
			return "SucessFully_SignedIN";
	    	 
	    	
	    }
	 }


