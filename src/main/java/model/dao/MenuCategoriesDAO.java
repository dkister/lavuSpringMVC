package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import model.MenuCategory;
import model.MenuItem;
import model.MenuItems;
import model.OrderContent;
import model.service.GetOrderContentData;

public class MenuCategoriesDAO {

	final static Logger logger = Logger.getLogger(GetOrderContentData.class);

	static Statement statement;
	private static int counter=0;

	public static String addSingleQuotes(String in) {
		if (in.isEmpty())
			return "''";
		return "'" + (in.trim()) + "'";
	}

	public static void insertRow(MenuCategory menuCategory, Connection dbConnection) throws SQLException {

		String insertTableSQL = "Insert into doug.MENU_CATEGORIES (ID,MENU_ID,GROUP_ID,NAME,ACTIVE,FORCED_MODIFIER_GROUP,SUPER_GROUP_ID,LTG_DISPLAY) "
				+ "values ($ID,$MENU_ID,$GROUP_ID,$NAME,$ACTIVE,$FORCED_MODIFIER_GROUP,$SUPER_GROUP_ID, $LTG_DISPLAY)";

		try {
			Statement statement;
			counter++;

			statement = dbConnection.createStatement();

			insertTableSQL = insertTableSQL.replaceFirst("\\$ID", Integer.toString(menuCategory.getId()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$MENU_ID", Integer.toString(menuCategory.getMenu_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$GROUP_ID", Integer.toString(menuCategory.getGroup_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$NAME", addSingleQuotes(menuCategory.getName()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$ACTIVE", Integer.toString(menuCategory.getActive()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$FORCED_MODIFIER_GROUP", Integer.toString(menuCategory.getForced_modifier_group_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$SUPER_GROUP_ID", Integer.toString(menuCategory.getSuper_group_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$LTG_DISPLAY", Integer.toString(menuCategory.getLtg_active()));

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
