package org.javaee7.jaxrs.fileupload;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

/**
 * @author Xavier Coulon
 */
@Path("/endpoint")
public class MyResource {

    @POST
    @Path("/upload")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postOctetStream(InputStream content) {
        try (Reader reader = new InputStreamReader(content)) {
            int totalsize = 0;
            int count = 0;
            final char[] buffer = new char[256];
            while ((count = reader.read(buffer)) != -1) {
                totalsize += count;
            }
            
            return Response.ok(totalsize).build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
    
    
    @POST
    @Path("/upload3")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.TEXT_PLAIN)
    public String postOctetStream3(InputStream content) {
//        try (Reader reader = new InputStreamReader(content)) {
//            int totalsize = 0;
//            int count = 0;
//            final char[] buffer = new char[256];
//            while ((count = reader.read(buffer)) != -1) {
//                totalsize += count;
//            }
//            
           
          
           
          

       BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        	  
           StringBuilder out = new StringBuilder();
           String line;
           try {
			while ((line = reader.readLine()) != null) {
			       out.append(line);
			   }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           
     return out.toString();
      
    }

    @POST
    @Path("/upload2")
    @Consumes({ MediaType.APPLICATION_OCTET_STREAM, "image/png" })
    @Produces(MediaType.TEXT_PLAIN)
    public Response postImageFile(File file) {
        try (Reader reader = new FileReader(file)) {
            int totalsize = 0;
            int count = 0;
            final char[] buffer = new char[256];
            while ((count = reader.read(buffer)) != -1) {
                totalsize += count;
            }
            return Response.ok(totalsize).build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

}
