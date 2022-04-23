package com;

import model.Complaint; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 


@Path("/Items")
public class ComplaintService {
	Complaint complainObj = new Complaint(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readComplaints() 
	 { 
	 return "Hello"; 
	 } 
}
