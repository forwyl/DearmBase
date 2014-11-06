package beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Dream {

	protected int number;
	protected int price;
	protected String type;
	protected String title;
	protected String imgUrl;
	protected String description;
	
	public Dream(){
		
	}

	public Dream(int number, int price, String type, String title, String imgUrl, String description){
		this.number = number;
		this.price = price;
		this.type = type;
		this.title = title;
		this.imgUrl = imgUrl;
		this.description = description;
	}
	
	public int getNumber() {
		return number;
	}
//	@XmlElement(name="number")
//	public void setNumber(int number) {
//		this.number = number;
//	}
	public int getPrice() {
		return price;
	}
//	@XmlElement(name="price")
//	public void setPrice(int price) {
//		this.price = price;
//	}
	public String getType() {
		return type;
	}
//	@XmlElement(name="type")
//	public void setType(String type) {
//		this.type = type;
//	}
	public String getTitle() {
		return title;
	}
//	@XmlElement(name="title")
//	public void setTitle(String title) {
//		this.title = title;
//	}
	public String getImgUrl() {
		return imgUrl;
	}
//	@XmlElement(name="imgUrl")
//	public void setImgUrl(String imgUrl) {
//		this.imgUrl = imgUrl;
//	}
	public String getDescription() {
		return description;
	}
//	@XmlElement(name="description")
//	public void setDescription(String description) {
//		this.description = description;
//	}
	
	
}
