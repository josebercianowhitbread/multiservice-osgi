package uk.co.whitbread.core.servlets;

import java.io.IOException;
import java.rmi.ServerError;
import java.rmi.ServerException;

import javax.servlet.ServletException;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.whitbread.core.models.MailService;
 

 
    
@SlingServlet(paths="/bin/joseMailServlet", methods = "POST", metatype=true)
public class MailServlet extends org.apache.sling.api.servlets.SlingAllMethodsServlet {
     private static final long serialVersionUID = 2598426539166789515L;
    
     @Reference(target = "(mailservice.label=InternetA)")
     MailService mailServiceA; 
      
     @Reference(target = "(mailservice.label=InternetB)")
     MailService mailServiceB;      
//       
     /** Default log. */
     protected final Logger log = LoggerFactory.getLogger(this.getClass());
             
              
     @Override
     protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException, ServerError {
          
      try {
          String provider;
         //Get the submitted data from the HTL front end
          String TopicSubject = request.getParameter("TopicSubject");
          String message = request.getParameter("message"); 
                      
          log.info("*** SUBJECT: "+TopicSubject); 
       
            
        if (TopicSubject.equals("InternetA")==true) {
            log.info("*** MULTISERVICE: Mail sent to InternetA");
            provider = "InternetA";
            mailServiceA.sendMail(message);
        }
            
        else {
            log.info("*** MULTISERVICE: Mail sent to InternetB"); 
            provider = "InternetB";
            mailServiceB.sendMail(message);
        }
           
         //Return the JSON formatted data
         response.getWriter().write("EMAIL GONE with " + provider);   
           
      } catch(ServerError e) {
    	  log.info("*** MULTISERVICE SERVER ERROR: "+ e.getMessage());
          e.printStackTrace();
      } catch(Exception e) {
    	  log.info("*** MULTISERVICE EXCEPTION: "+ e.getMessage());
          e.printStackTrace();
      }
    }
}