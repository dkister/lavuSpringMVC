package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import model.OrderContent;
import model.service.GetOrderContentData;

public class OrderContentsDAO {

	final static Logger logger = Logger.getLogger(GetOrderContentData.class);

	static Statement statement;
	private static int counter=0;

	public static String addSingleQuotes(String in) {
		if (in.isEmpty())
			return "''";
		return "'" + (in.trim()) + "'";
	}

	public static void insertRow(OrderContent orderContent, Connection dbConnection) throws SQLException {

		String insertTableSQL = "Insert into doug.LAVU_ORDER_CONTENTS (ID,LOC_ID,ORDER_ID,ITEM,PRICE,QUANITY,OPTIONS,SPECIAL,MODIFY_PRICE,PRINT,CHECKD,SEAT,ITEM_ID,PRINTER,APPLY_TAXRATE,CUSTOM_TAXRATE,MODIFIER_LIST_ID,FORCED_MODIFIER_GROUP_ID,FORCED_MODIFIERS_PRICE,COURSE,PRINT_ORDER,OPEN_ITEM,SUBTOTAL,ALLOW_DEPOSIT,DEPOSIT_INFO,DISCOUNT_AMOUNT,DISCOUNT_VALUE,DISCOUNT_TYPE,AFTER_DISCOUNT,SUBTOTAL_WITH_MODS,TAX_AMOUNT,NOTES,TOTAL_WITH_TAX,TAX_RATE1,TAX1,TAX_SUBTOTAL1,AFTER_GRATUITY,VOIDED,DISCOUNT_ID,SERVER_TIME,DEVICE_TIME,SPLIT_FACTOR,TAX_NAME1,EXEMPTION_ID,EXEMPTION_NAME,CHECKED_OUT,PRICE_OVERRIDE,ORIGINAL_PRICE,OVERRIDE_ID,IDISCOUNT_INFO,CATEGORY_ID,IOID,ICID,LAST_MOD_TS,LAST_CHANGE_TS,PRODUCT_CODE,TAX_CODE) "
				+ "values ($ID,$LOC_ID,$ORDER_ID,$ITEM,$PRICE,$QUANITY,$OPTIONS,$SPECIAL,$MODIFY_PRICE,$PRINT,$CHECKD,$SEAT,$ITEM_ID,$PRINTER,$APPLY_TAXRATE,$CUSTOM_TAXRATE,$MODIFIER_LIST_ID,$FORCED_MODIFIER_GROUP_ID,$FORCED_MODIFIERS_PRICE,$COURSE,$PRINT_ORDER,$OPEN_ITEM,$ASUBTOTAL,$ALLOW_DEPOSIT,$DEPOSIT_INFO,$DISCOUNT_AMOUNT,$DISCOUNT_VALUE,$DISCOUNT_TYPE,$AFTER_DISCOUNT,$SUBTOTAL_WITH_MODS,$TAX_AMOUNT,$NOTES,$TOTAL_WITH_TAX,$TAX_RATE1,$TAX1,$TAX_SUBTOTAL1,$AFTER_GRATUITY,$VOIDED,$DISCOUNT_ID,$SERVER_TIME,$DEVICE_TIME,$SPLIT_FACTOR,$TAX_NAME1,$EXEMPTION_ID,$EXEMPTION_NAME,$CHECKED_OUT,$PRICE_OVERRIDE,$ORIGINAL_PRICE,$OVERRIDE_ID,$IDISCOUNT_INFO,$CATEGORY_ID,$IOID,$ICID,$LAST_MOD_TS,$LAST_CHANGE_TS,$PRODUCT_CODE,$TAX_CODE)";

		try {
			Statement statement;
			counter++;

			statement = dbConnection.createStatement();

			insertTableSQL = insertTableSQL.replaceFirst("\\$ID", Integer.toString(orderContent.getId()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$LOC_ID", Integer.toString(orderContent.getLoc_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$ORDER_ID", addSingleQuotes(orderContent.getOrder_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$ITEM", addSingleQuotes(orderContent.getItem()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$PRICE", Double.toString(orderContent.getPrice()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$QUANITY", Integer.toString(orderContent.getQuanity()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$OPTIONS", addSingleQuotes(orderContent.getOptions()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$SPECIAL", addSingleQuotes(orderContent.getSpecial()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$MODIFY_PRICE",
					Integer.toString(orderContent.getModify_price()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$PRINT", Integer.toString(orderContent.getPrint()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$CHECKD", Integer.toString(orderContent.getCheck())); // ??
			insertTableSQL = insertTableSQL.replaceFirst("\\$SEAT", Integer.toString(orderContent.getSeat()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$ITEM_ID", Integer.toString(orderContent.getItem_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$PRINTER", Integer.toString(orderContent.getPrinter()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$APPLY_TAXRATE",
					addSingleQuotes(orderContent.getApply_taxrate()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$CUSTOM_TAXRATE",
					addSingleQuotes(orderContent.getCustom_taxrate()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$MODIFIER_LIST_ID",
					Integer.toString(orderContent.getModifier_list_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$FORCED_MODIFIER_GROUP_ID",
					Integer.toString(orderContent.getForced_modifier_group_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$FORCED_MODIFIERS_PRICE",
					Double.toString(orderContent.getForced_modifiers_price()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$COURSE", Integer.toString(orderContent.getCourse()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$PRINT_ORDER",
					Integer.toString(orderContent.getPrint_order()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$OPEN_ITEM", Integer.toString(orderContent.getOpen_item()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$ASUBTOTAL", Double.toString(orderContent.getSubtotal()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$ALLOW_DEPOSIT",
					Integer.toString(orderContent.getAllow_deposit()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$DEPOSIT_INFO",
					addSingleQuotes(orderContent.getDeposit_info()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$DISCOUNT_AMOUNT",
					Double.toString(orderContent.getDiscount_amount()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$DISCOUNT_VALUE",
					Double.toString(orderContent.getDiscount_value()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$DISCOUNT_TYPE",
					addSingleQuotes(orderContent.getDiscount_type()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$AFTER_DISCOUNT",
					Double.toString(orderContent.getAfter_discount()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$SUBTOTAL_WITH_MODS",
					Double.toString(orderContent.getSubtotal_with_mods()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$TAX_AMOUNT",
					Double.toString(orderContent.getTax_amount()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$NOTES", addSingleQuotes(orderContent.getNotes()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$TOTAL_WITH_TAX",
					Double.toString(orderContent.getTotal_with_tax()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$TAX_RATE1", Double.toString(orderContent.getTax_rate1()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$TAX1", Double.toString(orderContent.getTax1()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$TAX_SUBTOTAL1",
					Double.toString(orderContent.getTax_subtotal1()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$AFTER_GRATUITY",
					Double.toString(orderContent.getAfter_gratuity()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$VOIDED", Integer.toString(orderContent.getVoided()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$DISCOUNT_ID",
					Double.toString(orderContent.getDiscount_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$SERVER_TIME",
					"TO_DATE('" + orderContent.getServer_time().substring(0, orderContent.getServer_time().length() - 1)
							+ "', 'yyyy-mm-dd hh24:mi:ss')");
			insertTableSQL = insertTableSQL.replaceFirst("\\$DEVICE_TIME",
					"TO_DATE('" + orderContent.getDevice_time() + "', 'yyyy-mm-dd hh24:mi:ss')");
			insertTableSQL = insertTableSQL.replaceFirst("\\$SPLIT_FACTOR",
					Double.toString(orderContent.getSplit_factor()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$TAX_NAME1", addSingleQuotes(orderContent.getTax_name1()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$EXEMPTION_ID",
					Integer.toString(orderContent.getExemption_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$EXEMPTION_NAME",
					addSingleQuotes(orderContent.getExemption_name()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$CHECKED_OUT",
					Integer.toString(orderContent.getChecked_out()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$PRICE_OVERRIDE",
					Double.toString(orderContent.getPrice_override()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$ORIGINAL_PRICE",
					Double.toString(orderContent.getOriginal_price()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$OVERRIDE_ID",
					Integer.toString(orderContent.getOverride_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$IDISCOUNT_INFO",
					addSingleQuotes(orderContent.getIdiscount_info()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$CATEGORY_ID",
					Integer.toString(orderContent.getCategory_id()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$IOID", addSingleQuotes(orderContent.getIoid()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$ICID", addSingleQuotes(orderContent.getIcid()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$LAST_MOD_TS",
					Double.toString(orderContent.getLast_mod_ts()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$LAST_CHANGE_TS",
					Double.toString(orderContent.getLast_change_ts()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$PRODUCT_CODE",
					addSingleQuotes(orderContent.getProduct_code()));
			insertTableSQL = insertTableSQL.replaceFirst("\\$TAX_CODE", addSingleQuotes(orderContent.getTax_code()));
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
