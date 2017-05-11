package model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
public class MenuItem {
	
	private int id;
	private int category_id;
	private int menu_id;
	private String name;
	private double price;
	private int description;
	private int super_group_id;
	private int forced_modifier_group_id;
	private int modifier_list_id;
	private String happyhour;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDescription() {
		return description;
	}
	public void setDescription(int description) {
		this.description = description;
	}
	public int getSuper_group_id() {
		return super_group_id;
	}
	public void setSuper_group_id(int super_group_id) {
		this.super_group_id = super_group_id;
	}
	public int getForced_modifier_group_id() {
		return forced_modifier_group_id;
	}
	public void setForced_modifier_group_id(int forced_modifier_group_id) {
		this.forced_modifier_group_id = forced_modifier_group_id;
	}
	public int getModifier_list_id() {
		return modifier_list_id;
	}
	public void setModifier_list_id(int modifier_list_id) {
		this.modifier_list_id = modifier_list_id;
	}
	public String getHappyhour() {
		return happyhour;
	}
	public void setHappyhour(String happyhour) {
		this.happyhour = happyhour;
	}
	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", category_id=" + category_id + ", menu_id=" + menu_id + ", name=" + name
				+ ", price=" + price + ", description=" + description + ", super_group_id=" + super_group_id
				+ ", forced_modifier_group_id=" + forced_modifier_group_id + ", modifier_list_id=" + modifier_list_id
				+ ", happyhour=" + happyhour + "]";
	}

	
	
}
