package uk.co.whitbread.core.models;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.felix.scr.annotations.Activate ;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
//Sling Imports
import org.apache.sling.api.resource.ResourceResolverFactory ;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService; 
 
 
 
//This is a component so it can provide or consume services
@Component(metatype=true, label="Jose mailservice",description="Jose mailservice",configurationFactory=true)
   
@Service
public class MailServiceImpl implements MailService {
     
     
     @Reference
     private MessageGatewayService messageGatewayService;
      
     @Reference
     private ResourceResolverFactory resolverFactory;
     
     
    /** Default log. */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
            
    private static final String DEFAULT_ADDRESS="jose.berciano@whitbread.com";
    private static final String DEFAULT_FROMADDRESS="jmanuelbr@gmail.com";
    private String address;
    private String fromaddress;
     
     
    @Property(description="adress to whom email is sent",value=DEFAULT_ADDRESS)
    private static final String ADDRESS = "mailservice.address";
    
    
    @Property(description="address uses to represent from address",value=DEFAULT_FROMADDRESS)
    private static final String FROMADDRESS = "mailservice.username";
    
    @Property(description="Label for this SMTP service")
    private static final String NAME = "mailservice.label" ; 
 
    
 
    @Activate
    protected void activate (ComponentContext ctx) {
      address = PropertiesUtil.toString(ctx.getProperties().get(ADDRESS),DEFAULT_ADDRESS);
      fromaddress = PropertiesUtil.toString(ctx.getProperties().get(FROMADDRESS),DEFAULT_FROMADDRESS);
       
      log.info("THE from address is " + fromaddress);
       
       
       
    }
     
    public void sendMail (String message)
    {
     
        try
        {
             //Declare a MessageGateway service
             MessageGateway<Email> messageGateway; 
                    
             //Set up the Email message
             Email email = new SimpleEmail();
                            
             email.addTo(address);
             email.setSubject("AEM Service");
             email.setFrom(fromaddress); 
             email.setMsg(message);
                
             //Inject a MessageGateway Service and send the message
             messageGateway = messageGatewayService.getGateway(Email.class);
            
             // Check the logs to see that messageGateway is not null
             messageGateway.send((Email) email);
          
        }
            catch (Exception e)
            {
                  
                 log.info(e.getMessage())  ; 
            }
   }    
         
         
     
     
 
}