package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import model.MenuItem;
import model.MenuItems;
import model.OrderContent;
import model.service.GetOrderContentData;

public class MenuItemsDAO {

	final static Logger logger = Logger.getLogger(GetOrderContentData.class);

	static Statement statement;
	private static int counter=0;

	public static String addSingleQuotes(String in) {
		if (in.isEmpty())
			return "''";
		return "'" + (in.trim()) + "'";
	}

	public static void insertRow(MenuItem menuItem, Connection dbConnection) throws SQLException {

		String insertTableSQL = "Insert into doug.MENU_ITEMS (ID,CATEGORY_ID,MENU_ID,NAME,PRICE,ACTIVE,SUPER_GROUP_ID) "
				+ "values ($ID,$CATEGORY_ID,$MENU_ID,$NAME,$PRICE,$ACTIVE,$SUPER_GROUP_ID)";

		try {
			Statement statement;
			counter++;

			statement = dbConnection.createStatement();

			insertTableSQL = insertTableSQL.replaceFirst("\\$ID", Integer.toString(menuItem.getId()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$CATEGORY_ID", Integer.toString(menuItem.getCategory_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$MENU_ID", Integer.toString(menuItem.getMenu_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$NAME", addSingleQuotes(menuItem.getName()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$PRICE", Double.toString(menuItem.getPrice()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$ACTIVE", Integer.toString(menuItem.getActive()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$SUPER_GROUP_ID", Integer.toString(menuItem.getSuper_group_id()));
			
			//System.out.println(insertTableSQL);
			// System.out.print(".");
			statement.executeUpdate(insertTableSQL);
			if (counter == 100) {
				counter=0;
				System.out.println("$");
			} else {
				System.out.print("$");
			}

			statement.close();				
			} catch (SQLException e) {
				if (counter == 100) {
					counter=0;
					System.out.println(".");
				} else {
					System.out.print(".");
				}

				// TODO Auto-generated catch block
				//System.out.println(e.getMessage());
				//e.printStackTrace();
			}		
	}
}
