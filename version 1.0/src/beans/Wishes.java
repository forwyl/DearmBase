package beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="wishes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Wishes {

	@XmlElement(name="dream")
	List<Dream> allDreams;

	public List<Dream> getAllDreams() {
		return allDreams;
	}

	public void setAllDreams(List<Dream> allDreams) {
		this.allDreams = allDreams;
	}
	
}
