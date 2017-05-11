package model;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "results")
@XmlAccessorType (XmlAccessType.FIELD)
public class MenuItems 
{
    @XmlElement(name = "row")
    private List<MenuItem> menuContents = null;

	public List<MenuItem> getMenuContents() {
		return menuContents;
	}

	public void setMenuContents(List<MenuItem> menuContents) {
		this.menuContents = menuContents;
	}

    
}

