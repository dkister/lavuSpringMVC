package model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class OrderContent {
	
	
	private int id;
	private int loc_id;
	private String order_id;
	private String item;
	private double price;
	private int quanity;
	private String options;
	private String special;
	private int modify_price;
	private int print;
	private int check;
	private int seat;
	private int item_id;
	private int printer;
	private String apply_taxrate;
	private String custom_taxrate;
	private int modifier_list_id;
	private int forced_modifier_group_id;
	private double forced_modifiers_price;
	private int course;
	private int print_order;
	private int open_item;
	private double subtotal;
	private int allow_deposit;
	private String deposit_info;
	private double discount_amount;
	private double discount_value;
	private String discount_type;
	private double after_discount;
	private double subtotal_with_mods;
	private double tax_amount;
	private String notes;
	private double total_with_tax;
	private double tax_rate1;
	private double tax1;
	private double tax_subtotal1;
	private double after_gratuity;
	private int voided;
	private int discount_id;
	private String server_time;
	private String device_time;
	private double split_factor;
	private String tax_name1;
	private int exemption_id;
	private String exemption_name;
	private int checked_out;
	private double price_override;
	private double original_price;
	private int override_id;
	private String idiscount_info;
	private int category_id;
	private String ioid;
	private String icid;
	private double last_mod_ts;
	private double last_change_ts;
	private String product_code;
	private String tax_code;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLoc_id() {
		return loc_id;
	}
	public void setLoc_id(int loc_id) {
		this.loc_id = loc_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuanity() {
		return quanity;
	}
	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public int getModify_price() {
		return modify_price;
	}
	public void setModify_price(int modify_price) {
		this.modify_price = modify_price;
	}
	public int getPrint() {
		return print;
	}
	public void setPrint(int print) {
		this.print = print;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	public int getSeat() {
		return seat;
	}
	public void setSeat(int seat) {
		this.seat = seat;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public int getPrinter() {
		return printer;
	}
	public void setPrinter(int printer) {
		this.printer = printer;
	}
	public String getApply_taxrate() {
		return apply_taxrate;
	}
	public void setApply_taxrate(String apply_taxrate) {
		this.apply_taxrate = apply_taxrate;
	}
	public String getCustom_taxrate() {
		return custom_taxrate;
	}
	public void setCustom_taxrate(String custom_taxrate) {
		this.custom_taxrate = custom_taxrate;
	}
	public int getModifier_list_id() {
		return modifier_list_id;
	}
	public void setModifier_list_id(int modifier_list_id) {
		this.modifier_list_id = modifier_list_id;
	}
	public int getForced_modifier_group_id() {
		return forced_modifier_group_id;
	}
	public void setForced_modifier_group_id(int forced_modifier_group_id) {
		this.forced_modifier_group_id = forced_modifier_group_id;
	}
	public double getForced_modifiers_price() {
		return forced_modifiers_price;
	}
	public void setForced_modifiers_price(double forced_modifiers_price) {
		this.forced_modifiers_price = forced_modifiers_price;
	}
	public int getCourse() {
		return course;
	}
	public void setCourse(int course) {
		this.course = course;
	}
	public int getPrint_order() {
		return print_order;
	}
	public void setPrint_order(int print_order) {
		this.print_order = print_order;
	}
	public int getOpen_item() {
		return open_item;
	}
	public void setOpen_item(int open_item) {
		this.open_item = open_item;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public int getAllow_deposit() {
		return allow_deposit;
	}
	public void setAllow_deposit(int allow_deposit) {
		this.allow_deposit = allow_deposit;
	}
	public String getDeposit_info() {
		return deposit_info;
	}
	public void setDeposit_info(String deposit_info) {
		this.deposit_info = deposit_info;
	}
	public double getDiscount_amount() {
		return discount_amount;
	}
	public void setDiscount_amount(double discount_amount) {
		this.discount_amount = discount_amount;
	}
	public double getDiscount_value() {
		return discount_value;
	}
	public void setDiscount_value(double discount_value) {
		this.discount_value = discount_value;
	}
	public String getDiscount_type() {
		return discount_type;
	}
	public void setDiscount_type(String discount_type) {
		this.discount_type = discount_type;
	}
	public double getAfter_discount() {
		return after_discount;
	}
	public void setAfter_discount(double after_discount) {
		this.after_discount = after_discount;
	}
	public double getSubtotal_with_mods() {
		return subtotal_with_mods;
	}
	public void setSubtotal_with_mods(double subtotal_with_mods) {
		this.subtotal_with_mods = subtotal_with_mods;
	}
	public double getTax_amount() {
		return tax_amount;
	}
	public void setTax_amount(double tax_amount) {
		this.tax_amount = tax_amount;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public double getTotal_with_tax() {
		return total_with_tax;
	}
	public void setTotal_with_tax(double total_with_tax) {
		this.total_with_tax = total_with_tax;
	}
	public double getTax_rate1() {
		return tax_rate1;
	}
	public void setTax_rate1(double tax_rate1) {
		this.tax_rate1 = tax_rate1;
	}
	public double getTax1() {
		return tax1;
	}
	public void setTax1(double tax1) {
		this.tax1 = tax1;
	}
	public double getTax_subtotal1() {
		return tax_subtotal1;
	}
	public void setTax_subtotal1(double tax_subtotal1) {
		this.tax_subtotal1 = tax_subtotal1;
	}
	public double getAfter_gratuity() {
		return after_gratuity;
	}
	public void setAfter_gratuity(double after_gratuity) {
		this.after_gratuity = after_gratuity;
	}
	public int getVoided() {
		return voided;
	}
	public void setVoided(int voided) {
		this.voided = voided;
	}
	public int getDiscount_id() {
		return discount_id;
	}
	public void setDiscount_id(int discount_id) {
		this.discount_id = discount_id;
	}
	public String getServer_time() {
		return server_time;
	}
	public void setServer_time(String server_time) {
		this.server_time = server_time;
	}
	public String getDevice_time() {
		return device_time;
	}
	public void setDevice_time(String device_time) {
		this.device_time = device_time;
	}
	public double getSplit_factor() {
		return split_factor;
	}
	public void setSplit_factor(double split_factor) {
		this.split_factor = split_factor;
	}
	public String getTax_name1() {
		return tax_name1;
	}
	public void setTax_name1(String tax_name1) {
		this.tax_name1 = tax_name1;
	}
	public int getExemption_id() {
		return exemption_id;
	}
	public void setExemption_id(int exemption_id) {
		this.exemption_id = exemption_id;
	}
	public String getExemption_name() {
		return exemption_name;
	}
	public void setExemption_name(String exemption_name) {
		this.exemption_name = exemption_name;
	}
	public int getChecked_out() {
		return checked_out;
	}
	public void setChecked_out(int checked_out) {
		this.checked_out = checked_out;
	}
	public double getPrice_override() {
		return price_override;
	}
	public void setPrice_override(double price_override) {
		this.price_override = price_override;
	}
	public double getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(double original_price) {
		this.original_price = original_price;
	}
	public int getOverride_id() {
		return override_id;
	}
	public void setOverride_id(int override_id) {
		this.override_id = override_id;
	}
	public String getIdiscount_info() {
		return idiscount_info;
	}
	public void setIdiscount_info(String idiscount_info) {
		this.idiscount_info = idiscount_info;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getIoid() {
		return ioid;
	}
	public void setIoid(String ioid) {
		this.ioid = ioid;
	}
	public String getIcid() {
		return icid;
	}
	public void setIcid(String icid) {
		this.icid = icid;
	}
	public double getLast_mod_ts() {
		return last_mod_ts;
	}
	public void setLast_mod_ts(double last_mod_ts) {
		this.last_mod_ts = last_mod_ts;
	}
	public double getLast_change_ts() {
		return last_change_ts;
	}
	public void setLast_change_ts(double last_change_ts) {
		this.last_change_ts = last_change_ts;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getTax_code() {
		return tax_code;
	}
	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}
	@Override
	public String toString() {
		return "OrderContent [id=" + id + ", loc_id=" + loc_id + ", order_id=" + order_id + ", item=" + item
				+ ", price=" + price + ", quanity=" + quanity + ", options=" + options + ", special=" + special
				+ ", modify_price=" + modify_price + ", print=" + print + ", check=" + check + ", seat=" + seat
				+ ", item_id=" + item_id + ", printer=" + printer + ", apply_taxrate=" + apply_taxrate
				+ ", custom_taxrate=" + custom_taxrate + ", modifier_list_id=" + modifier_list_id
				+ ", forced_modifier_group_id=" + forced_modifier_group_id + ", forced_modifiers_price="
				+ forced_modifiers_price + ", course=" + course + ", print_order=" + print_order + ", open_item="
				+ open_item + ", subtotal=" + subtotal + ", allow_deposit=" + allow_deposit + ", deposit_info="
				+ deposit_info + ", discount_amount=" + discount_amount + ", discount_value=" + discount_value
				+ ", discount_type=" + discount_type + ", after_discount=" + after_discount + ", subtotal_with_mods="
				+ subtotal_with_mods + ", tax_amount=" + tax_amount + ", notes=" + notes + ", total_with_tax="
				+ total_with_tax + ", tax_rate1=" + tax_rate1 + ", tax1=" + tax1 + ", tax_subtotal1=" + tax_subtotal1
				+ ", after_gratuity=" + after_gratuity + ", voided=" + voided + ", discount_id=" + discount_id
				+ ", server_time=" + server_time + ", device_time=" + device_time + ", split_factor=" + split_factor
				+ ", tax_name1=" + tax_name1 + ", exemption_id=" + exemption_id + ", exemption_name=" + exemption_name
				+ ", checked_out=" + checked_out + ", price_override=" + price_override + ", original_price="
				+ original_price + ", override_id=" + override_id + ", idiscount_info=" + idiscount_info
				+ ", category_id=" + category_id + ", ioid=" + ioid + ", icid=" + icid + ", last_mod_ts=" + last_mod_ts
				+ ", last_change_ts=" + last_change_ts + ", product_code=" + product_code + ", tax_code=" + tax_code
				+ "]";
	}

	
	
}
