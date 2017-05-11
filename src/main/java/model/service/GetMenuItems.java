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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import database.OracleJDBC;
import model.MenuItem;
import model.MenuItems;
import model.OrderContent;
import model.OrderContents;

public class GetMenuItems {
	
	final static Logger logger = Logger.getLogger(GetMenuItems.class);

	static Connection dbConnection;
	static Statement statement;
	public static void main(String[] args) throws SQLException {

		try {
			
			if (args.length == 1) {
				System.out.println("Usage : GetMenuItems");
				System.exit(1);
			}
			
			String date = new SimpleDateFormat("MM-dd-yyyy").format(new Date());
			String fileName = "GetMenuItems-"+date;
			
			logger.debug("Begin Import Process");
			String httpsURL = "http://api.poslavu.com/cp/reqserv/?";
			String dataname = "finnegans_gril";
			String token = "34Kp24H7y2A3tZ5AvJsU";
			String key = "nfYxYJW7UivXnGNqKexI";
			String postvars = "dataname=" + dataname + "&key=" + key + "&token=" + token;
			postvars += "&table=menu_items&limit=0,1000&valid_xml=1";

			
			
			logger.debug("REST Service call to LAVU is: "+postvars);

			String urlParameters = postvars;
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
		    	 
		        JAXBContext jaxbContext = JAXBContext.newInstance(MenuItems.class);  
 			    Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
			    MenuItems menuItems = (MenuItems) jaxbUnmarshaller.unmarshal( new File(fileName) );
		     
			    for(MenuItem menuItem : menuItems.getMenuContents())
			    {
			    	logger.debug(menuItem.toString());
			    	//insertRow(orderContent);

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
	
	public static String addSingleQuotes(String in) {
		if (in.isEmpty()) return "''";
		return "'"+(in.trim())+"'";
	}
	public static void insertRow(OrderContent orderContent) throws SQLException {
		
		String insertTableSQL = "Insert into doug.LAVU_ORDER_CONTENTS (ID,LOC_ID,ORDER_ID,ITEM,PRICE,QUANITY,OPTIONS,SPECIAL,MODIFY_PRICE,PRINT,CHECKD,SEAT,ITEM_ID,PRINTER,APPLY_TAXRATE,CUSTOM_TAXRATE,MODIFIER_LIST_ID,FORCED_MODIFIER_GROUP_ID,FORCED_MODIFIERS_PRICE,COURSE,PRINT_ORDER,OPEN_ITEM,SUBTOTAL,ALLOW_DEPOSIT,DEPOSIT_INFO,DISCOUNT_AMOUNT,DISCOUNT_VALUE,DISCOUNT_TYPE,AFTER_DISCOUNT,SUBTOTAL_WITH_MODS,TAX_AMOUNT,NOTES,TOTAL_WITH_TAX,TAX_RATE1,TAX1,TAX_SUBTOTAL1,AFTER_GRATUITY,VOIDED,DISCOUNT_ID,SERVER_TIME,DEVICE_TIME,SPLIT_FACTOR,TAX_NAME1,EXEMPTION_ID,EXEMPTION_NAME,CHECKED_OUT,PRICE_OVERRIDE,ORIGINAL_PRICE,OVERRIDE_ID,IDISCOUNT_INFO,CATEGORY_ID,IOID,ICID,LAST_MOD_TS,LAST_CHANGE_TS,PRODUCT_CODE,TAX_CODE) "+ 
			"values ($ID,$LOC_ID,$ORDER_ID,$ITEM,$PRICE,$QUANITY,$OPTIONS,$SPECIAL,$MODIFY_PRICE,$PRINT,$CHECKD,$SEAT,$ITEM_ID,$PRINTER,$APPLY_TAXRATE,$CUSTOM_TAXRATE,$MODIFIER_LIST_ID,$FORCED_MODIFIER_GROUP_ID,$FORCED_MODIFIERS_PRICE,$COURSE,$PRINT_ORDER,$OPEN_ITEM,$ASUBTOTAL,$ALLOW_DEPOSIT,$DEPOSIT_INFO,$DISCOUNT_AMOUNT,$DISCOUNT_VALUE,$DISCOUNT_TYPE,$AFTER_DISCOUNT,$SUBTOTAL_WITH_MODS,$TAX_AMOUNT,$NOTES,$TOTAL_WITH_TAX,$TAX_RATE1,$TAX1,$TAX_SUBTOTAL1,$AFTER_GRATUITY,$VOIDED,$DISCOUNT_ID,$SERVER_TIME,$DEVICE_TIME,$SPLIT_FACTOR,$TAX_NAME1,$EXEMPTION_ID,$EXEMPTION_NAME,$CHECKED_OUT,$PRICE_OVERRIDE,$ORIGINAL_PRICE,$OVERRIDE_ID,$IDISCOUNT_INFO,$CATEGORY_ID,$IOID,$ICID,$LAST_MOD_TS,$LAST_CHANGE_TS,$PRODUCT_CODE,$TAX_CODE)"; 


		try {
			Statement statement;

			statement = dbConnection.createStatement();
				
		insertTableSQL = insertTableSQL.replaceFirst("\\$ID", Integer.toString(orderContent.getId()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$LOC_ID", Integer.toString(orderContent.getLoc_id()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$ORDER_ID", addSingleQuotes(orderContent.getOrder_id()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$ITEM", addSingleQuotes(orderContent.getItem()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$PRICE", Double.toString(orderContent.getPrice()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$QUANITY", Integer.toString(orderContent.getQuanity()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$OPTIONS", addSingleQuotes(orderContent.getOptions()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$SPECIAL", addSingleQuotes(orderContent.getSpecial()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$MODIFY_PRICE", Integer.toString(orderContent.getModify_price()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$PRINT", Integer.toString(orderContent.getPrint()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$CHECKD", Integer.toString(orderContent.getCheck()));   //??
		insertTableSQL = insertTableSQL.replaceFirst("\\$SEAT", Integer.toString(orderContent.getSeat()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$ITEM_ID", Integer.toString(orderContent.getItem_id()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$PRINTER", Integer.toString(orderContent.getPrinter()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$APPLY_TAXRATE", addSingleQuotes(orderContent.getApply_taxrate()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$CUSTOM_TAXRATE", addSingleQuotes(orderContent.getCustom_taxrate()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$MODIFIER_LIST_ID", Integer.toString(orderContent.getModifier_list_id()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$FORCED_MODIFIER_GROUP_ID", Integer.toString(orderContent.getForced_modifier_group_id()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$FORCED_MODIFIERS_PRICE", Double.toString(orderContent.getForced_modifiers_price()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$COURSE", Integer.toString(orderContent.getCourse()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$PRINT_ORDER", Integer.toString(orderContent.getPrint_order()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$OPEN_ITEM", Integer.toString(orderContent.getOpen_item()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$ASUBTOTAL", Double.toString(orderContent.getSubtotal()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$ALLOW_DEPOSIT", Integer.toString(orderContent.getAllow_deposit()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$DEPOSIT_INFO", addSingleQuotes(orderContent.getDeposit_info()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$DISCOUNT_AMOUNT", Double.toString(orderContent.getDiscount_amount()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$DISCOUNT_VALUE", Double.toString(orderContent.getDiscount_value()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$DISCOUNT_TYPE", addSingleQuotes(orderContent.getDiscount_type()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$AFTER_DISCOUNT", Double.toString(orderContent.getAfter_discount()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$SUBTOTAL_WITH_MODS", Double.toString(orderContent.getSubtotal_with_mods()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$TAX_AMOUNT", Double.toString(orderContent.getTax_amount()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$NOTES", addSingleQuotes(orderContent.getNotes()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$TOTAL_WITH_TAX", Double.toString(orderContent.getTotal_with_tax()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$TAX_RATE1", Double.toString(orderContent.getTax_rate1()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$TAX1", Double.toString(orderContent.getTax1()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$TAX_SUBTOTAL1", Double.toString(orderContent.getTax_subtotal1()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$AFTER_GRATUITY", Double.toString(orderContent.getAfter_gratuity()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$VOIDED", Integer.toString(orderContent.getVoided()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$DISCOUNT_ID", Double.toString(orderContent.getDiscount_id()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$SERVER_TIME", "TO_DATE('"+orderContent.getServer_time().substring(0, orderContent.getServer_time().length()-1)+"', 'yyyy-mm-dd hh24:mi:ss')");
		insertTableSQL = insertTableSQL.replaceFirst("\\$DEVICE_TIME", "TO_DATE('"+orderContent.getDevice_time()+"', 'yyyy-mm-dd hh24:mi:ss')");
		insertTableSQL = insertTableSQL.replaceFirst("\\$SPLIT_FACTOR", Double.toString(orderContent.getSplit_factor()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$TAX_NAME1", addSingleQuotes(orderContent.getTax_name1()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$EXEMPTION_ID", Integer.toString(orderContent.getExemption_id()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$EXEMPTION_NAME", addSingleQuotes(orderContent.getExemption_name()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$CHECKED_OUT", Integer.toString(orderContent.getChecked_out()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$PRICE_OVERRIDE", Double.toString(orderContent.getPrice_override()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$ORIGINAL_PRICE", Double.toString(orderContent.getOriginal_price()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$OVERRIDE_ID", Integer.toString(orderContent.getOverride_id()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$IDISCOUNT_INFO", addSingleQuotes(orderContent.getIdiscount_info()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$CATEGORY_ID", Integer.toString(orderContent.getCategory_id()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$IOID", addSingleQuotes(orderContent.getIoid()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$ICID", addSingleQuotes(orderContent.getIcid()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$LAST_MOD_TS",Double.toString(orderContent.getLast_mod_ts()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$LAST_CHANGE_TS",Double.toString(orderContent.getLast_change_ts()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$PRODUCT_CODE", addSingleQuotes(orderContent.getProduct_code()));
		insertTableSQL = insertTableSQL.replaceFirst("\\$TAX_CODE", addSingleQuotes(orderContent.getTax_code()));
		System.out.println(insertTableSQL);
		//System.out.print(".");
		statement.executeUpdate(insertTableSQL);
		statement.close();				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			statement.close();				

			e.printStackTrace();
		}		


		
	}
}