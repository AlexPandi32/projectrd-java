package ransom.detector;

import java.util.HashMap;
import dao.Dao;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import bean.User;
@Path("login")
public class Login  {
 
 @Context HttpServletRequest request;
 @Context HttpServletResponse response;
 @POST
 @Consumes(MediaType.APPLICATION_JSON)
 //@Produces(MediaType.APPLICATION_JSON)
    public String login(User user) throws Exception {
    	
	 String username=user.getUsername();
 	 String password= user.getPassword();
 	 System.out.println(user.getUsername()+"______________________"+user.getPassword());
 
  	    Dao dao_Obj = new Dao();
    	HashMap<String,String> dataset =new HashMap<String,String>();
    	dataset=dao_Obj.getDataSet();
    	
    	if(dataset.containsKey(username))
    	{
    		if(dataset.get(username).equals(password))
    		{  
    			Cookie ck= new Cookie("logged_user", username);
    	    	ck.setMaxAge(60*60);
    	    	//ck.setPath("/");
    			response.addCookie(ck);
    		    //	Cookie[] c =request.getCookies();
    			//System.out.println("C[0]"+c[0].getValue());
    	    	HttpSession session=request.getSession();
    			System.out.println(session.getAttribute("login_status"));
    			session.setAttribute("login_status",true);
    		
    			return "Success";
    		}
    	}
    	
    	return "Failure" ;
    	
    }	      
  
}
