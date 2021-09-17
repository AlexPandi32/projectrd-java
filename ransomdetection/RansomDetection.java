package ransomdetection;

import dao.Dao;
public class RansomDetection{  
   
	public static String lastTime;
 
	static {  
           System.loadLibrary("bmfrommysql"); 
           }
    
 private void callback(String xml)
    {   
	     System.out.println("FROM CALL BACK");
	    Checker checker = new Checker();
        checker.add(xml);
    }
 
  private native void getEvents(String query);
  public  static String  collectEvents() {
	      
	        RansomDetection  rd = new RansomDetection();
	        Dao dao = new Dao();
	        String query =new String();
	        //String time= "2021-07-17T17:08:30.804531800Z";
	        System.out.println("BEFORE GET TIME");
	        String time =dao.getTime();
            query="Event/System[(EventID=4663) and TimeCreated[@SystemTime >='"+time+"']]";
	        System.out.println(query);
	        rd.getEvents(query);
	        System.out.println("AFter Get Events");
	       // dao.updateTime(lastTime);
	 
	        
	   return "input";
		  
   }
   
}