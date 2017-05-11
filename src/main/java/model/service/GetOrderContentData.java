package model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import database.OracleJDBC;
import model.OrderContent;
import model.OrderContents;
import model.dao.OrderContentsDAO;

public class GetOrderContentData {
	
	final static Logger logger = Logger.getLogger(GetOrderContentData.class);

	static Connection dbConnection;
	static Statement statement;
	public static void main(String[] args) throws SQLException {

		
		// this standalone program writes a file with the rows fetched from REST call to Lavu for the table order contents.
		// after the file is written - the program will insert the fetched data into the databse table LAVU_ORDER_CONTENTS
		try {
			
			if (args.length < 2) {
				System.out.println("Usage : GetOrderContents startRow numberOfRowsToFetch");
				System.exit(1);
			}
			
			String startRow = args[0];
			String numberOfRowsToFetch = args[1];
			String fileName = "GetOrderContents."+startRow+"-"+numberOfRowsToFetch;
			
			logger.debug("Begin Import Process");
			String httpsURL = "http://api.poslavu.com/cp/reqserv/?";
			String dataname = "finnegans_gril";
			String token = "34Kp24H7y2A3tZ5AvJsU";
			String key = "nfYxYJW7UivXnGNqKexI";
			//String whole = "dataname=finnegans_gril&key=nfYxYJW7UivXnGNqKexI&token=34Kp24H7y2A3tZ5AvJsU&table=menu_groups";
			String postvars = "dataname=" + dataname + "&key=" + key + "&token=" + token;
			postvars += "&table=menu_items&column=category_id&value=1";

			
			String basicFilter = "dataname=finnegans_gril&key=nfYxYJW7UivXnGNqKexI&token=34Kp24H7y2A3tZ5AvJsU&table=order_contents&limit="+startRow+","+numberOfRowsToFetch+"&valid_xml=1";
			//String basicFilter = "dataname=finnegans_gril&key=nfYxYJW7UivXnGNqKexI&token=34Kp24H7y2A3tZ5AvJsU&table=order_contents&limit=12000,3000&valid_xml=1";
			
			logger.debug("REST Service call to LAVU is: "+basicFilter);

			String urlParameters = basicFilter;
			URL url = new URL( httpsURL );

			
			logger.debug("Creating Rest call");
			byte[] postData = urlParameters.getBytes( StandardCharsets.UTF_8 );
			int postDataLength = postData.length;
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
			conn.setRequestProperty("charset", "utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString(postDataLength ));
			conn.setUseCaches(false);
			 
			logger.debug("Ready to read output stream and write to a file for later consumption.");
			try(DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
				   wr.write( postData );
				}

			InputStream ins = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(ins);
			BufferedReader in = new BufferedReader(isr);
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					inputLine = inputLine.replace("void", "voided");
					
					inputLine = inputLine.replace("'", "");
					inputLine = inputLine.replaceAll("&apos;", "");
					
					inputLine = inputLine.replaceAll("[$\\\\]", "\\\\$0");
					
					bw.write(inputLine);
				}

				// no need to close it.
				bw.close();

				logger.debug("Done fetching data and writing XML file "+fileName);

			} catch (IOException e) {

				e.printStackTrace();

			}
			
			dbConnection  = OracleJDBC.getDBConnection();

		     try {
		    	 
		        JAXBContext jaxbContext = JAXBContext.newInstance(OrderContents.class);  
 			    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			    OrderContents orderContents = (OrderContents) jaxbUnmarshaller.unmarshal( new File(fileName) );
		     
			    for(OrderContent orderContent : orderContents.getOrderContents())
			    {
			    	//System.out.println(orderContent.toPipes());
			    	//logger.debug(orderContent.toString());
			    	OrderContentsDAO.insertRow(orderContent, dbConnection);

			    }

		      } catch (JAXBException e) {  
		        e.printStackTrace();  
		      }  

			in.close();
			try {
				
				dbConnection.rollback();
				dbConnection.close();
				
			} catch (SQLException e) {
				logger.debug("sql exception "+e.getMessage());
				logger.debug("sql exception "+e.getStackTrace());
			}

		} catch (MalformedURLException e) {
			logger.debug("MalformedURLException"+e.getMessage());
			logger.debug("MalformedURLException "+e.getStackTrace());
		} catch (IOException e) {
			logger.debug("IOException"+e.getMessage());
			logger.debug("IOException "+e.getStackTrace());		}
	}
	

}