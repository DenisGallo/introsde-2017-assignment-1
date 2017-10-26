package people.classes;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "activitypreference")	
@XmlType(propOrder = { "name", "description", "place", "startdate" })

//Activity class to store the information of an Activity with them getters and setters
public class Activity {
	private String name;
	private String description;
	private String place;
	private Date startdate;
	@XmlAttribute(name="id")
	private long activityId;

	public Activity(String name, String description, String place, Date startdate) {
		this.name = name;
		this.description = description;
		this.place=place;
		this.startdate=startdate;
	}

	public Activity() {
		this.name = "generic";
		this.description = "doSomething";
		this.place="somePlace";
		this.startdate=new Date();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public long getActivityId() {
		return activityId;
	}

	public void setActivityId(long activityId) {
		this.activityId = activityId;
	}

}
