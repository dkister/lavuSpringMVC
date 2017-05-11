package model;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "results")
@XmlAccessorType (XmlAccessType.FIELD)
public class OrderContents 
{
    @XmlElement(name = "row")
    private List<OrderContent> orderContents = null;
 
    public List<OrderContent> getOrderContents() {
        return orderContents;
    }
 
    public void setOrderContents(List<OrderContent> orderContents) {
        this.orderContents = orderContents;
    }
}

