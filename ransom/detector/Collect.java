package ransom.detector;

import ransomdetection.Checker;
import ransomdetection.RansomDetection;
import ransomdetection.VirtualDb;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import dao.Dao;
@Path("collect")
public class Collect {

    @GET
	@Produces(MediaType.TEXT_PLAIN)
	public String collectEvents(@QueryParam("time") int time,@QueryParam("count_limit") int count_limit)
	{
    	
        Checker.MAX_COUNT_LIMIT=count_limit;
        VirtualDb.TIME_GAP_IN_SEC= (time*60);
		RansomDetection rd=new RansomDetection();
		rd.collectEvents();
		return "collected";
	}
    
    @GET
    @Path("/collectedtime")
	@Produces(MediaType.TEXT_PLAIN)
    public String getLastCollectedTime()
    {     
    	  System.out.println("From Collected Time");
    	  Dao dao = new   Dao();
    	  String time =dao.getTime();
    	  LocalDateTime t=  LocalDateTime.parse(time.substring(0,time.length()-1));
    	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
    	  time=t.format(formatter);
    	  System.out.println(time);
    	  return time;
    }
    
}
