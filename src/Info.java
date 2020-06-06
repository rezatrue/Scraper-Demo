import java.util.LinkedList;

public class Info {
	String businessProfileUrl;
	String name;
	String type;
	String area;
	String saleContact;
	String description;
	String latlong;

	String propertyID;
	String floorArea;
	String landArea;
	String Energy;
	String parking;
	String buildingType;
	String annualReturn;
	String availability;
	String category;
	String LastUpdated;
	LinkedList<Agent> agents;
	LinkedList<String> imageUrl;
	
	public Info(){
		super();
	}

	public Info(String businessProfileUrl, String name, String type, String area, String saleContact, String description, String latlong,
			String propertyID, String floorArea, String landArea, String energy, String parking, String buildingType,
			String annualReturn, String availability, String category, String lastUpdated, LinkedList<Agent> agents,
			LinkedList<String> imageUrl) {
		super();
		this.businessProfileUrl = businessProfileUrl;
		this.name = name;
		this.type = type;
		this.area = area;
		this.saleContact = saleContact;
		this.description = description;
		this.latlong = latlong;
		this.propertyID = propertyID;
		this.floorArea = floorArea;
		this.landArea = landArea;
		Energy = energy;
		this.parking = parking;
		this.buildingType = buildingType;
		this.annualReturn = annualReturn;
		this.availability = availability;
		this.category = category;
		LastUpdated = lastUpdated;
		this.agents = agents;
		this.imageUrl = imageUrl;
	}

	
	
	
	public String getBusinessProfileUrl() {
		return businessProfileUrl;
	}

	public void setBusinessProfileUrl(String businessProfileUrl) {
		this.businessProfileUrl = businessProfileUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSaleContact() {
		return saleContact;
	}

	public void setSaleContact(String saleContact) {
		this.saleContact = saleContact;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLatlong() {
		return latlong;
	}

	public void setLatlong(String latlong) {
		this.latlong = latlong;
	}

	public String getPropertyID() {
		return propertyID;
	}

	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}

	public String getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(String floorArea) {
		this.floorArea = floorArea;
	}

	public String getLandArea() {
		return landArea;
	}

	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}

	public String getEnergy() {
		return Energy;
	}

	public void setEnergy(String energy) {
		Energy = energy;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	public String getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(String buildingType) {
		this.buildingType = buildingType;
	}

	public String getAnnualReturn() {
		return annualReturn;
	}

	public void setAnnualReturn(String annualReturn) {
		this.annualReturn = annualReturn;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLastUpdated() {
		return LastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		LastUpdated = lastUpdated;
	}

	public LinkedList<Agent> getAgents() {
		return agents;
	}

	public void setAgents(LinkedList<Agent> agents) {
		this.agents = agents;
	}

	public LinkedList<String> getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(LinkedList<String> imageUrl) {
		this.imageUrl = imageUrl;
	}


	
	
	
}
