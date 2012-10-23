package com.ventalandia.api;

import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.log.AppLogLine;
import com.google.appengine.api.log.LogQuery;
import com.google.appengine.api.log.LogServiceFactory;
import com.google.appengine.api.log.RequestLogs;

@Path("/logs")
public class LogginServlet{

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String doGet(@QueryParam("offset") String offset){

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<html><body>");
        
        
        // We use this to break out of our iteration loop, limiting record
        // display to 5 request logs at a time.
        int limit = 5;

        // We want the App logs for each request log
        LogQuery query = LogQuery.Builder.withDefaults();
        query.includeAppLogs(false);

        // Set the offset value retrieved from the Next link click.
        if (offset != null) {
            query.offset(offset);
        }

        // This gets filled from the last request log in the iteration
        String lastOffset = null;
        
        for (RequestLogs record : LogServiceFactory.getLogService().fetch(query)) {
            
            stringBuffer.append("<b>REQUEST LOG </b>");
            
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(record.getStartTimeUsec() / 1000);

            stringBuffer.append("IP: " + record.getIp() + "<br/>");
            stringBuffer.append("Method: " + record.getMethod() + "<br/>");
            stringBuffer.append("Resource " + record.getResource() + "<br/>");
            stringBuffer.append(String.format("Date: %s", cal.getTime().toString())+"<br/>");

            lastOffset = record.getOffset();

            for (AppLogLine appLog : record.getAppLogLines()) {
                
                Calendar appCal = Calendar.getInstance();
                appCal.setTimeInMillis(appLog.getTimeUsec() / 1000);
                stringBuffer.append(String.format("Date: %s", appCal.getTime().toString())+"<br/>");
                stringBuffer.append(appLog.getLogLevel() + "<br/>");
                stringBuffer.append(appLog.getLogMessage() + "<br/>");
            } 

        }

        if(lastOffset !=null){
            stringBuffer.append("<br><a href=\"/logs\">Current</a>&nbsp;|&nbsp;<a href=\"/logs?offset="+lastOffset+"\">Previous</a>");
        }
        stringBuffer.append("</body></html>");
        return stringBuffer.toString();
    }
}
