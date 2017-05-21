package model;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "results")
@XmlAccessorType (XmlAccessType.FIELD)
public class MenuCategories 
{
    @XmlElement(name = "row")
    private List<MenuCategory> menuCategories = null;

	public List<MenuCategory> getMenuContents() {
		return menuCategories;
	}

	public void setMenuContents(List<MenuCategory> menuCategories) {
		this.menuCategories = menuCategories;
	}

    
}

