package ransom.detector;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bean.Event;
import dao.Dao;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.GET;

/*import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule; */

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Path("report") 
public class RansomReport{

	
	//private static final long serialVersionUID = 1L;
	ResultSet rs = null;
	Event evt  = null;
	List<Event> evtList = null;
	Dao dao = new Dao();
	
	
	@Context private HttpServletRequest request;
	@Context private HttpServletResponse response;
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> execute()  {
	
      try {
    	  
    	  
		    evtList = new ArrayList<Event>();
			rs = dao.getReport();
			int i = 0;
			if (rs != null) {
				while (rs.next()) {
					i++;
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					LocalDateTime dateTime = LocalDateTime.parse(rs.getString(1), formatter);
					evt=new Event(dateTime,rs.getString(2),rs.getString(3),rs.getString(4),rs.getBoolean(5));
					evtList.add(evt);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return  evtList;
	}


	public List<Event> getEvtList() {
		return evtList;
	}

	
}