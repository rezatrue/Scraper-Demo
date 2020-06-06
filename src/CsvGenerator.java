

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;

public class CsvGenerator {

	public CsvGenerator() {
	}

	public int listtoCsv(LinkedList<Info> list) {
		
		int count = 0;
		
		System.out.println("list size 1: " + list);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Calendar cal = Calendar.getInstance();
		String fileName = dateFormat.format(cal.getTime());

		FileWriter writer = null;
		try {
			writer = new FileWriter("Commercialrealestate_list_" + fileName + ".csv");

			writer.append("Profile_URL");
			writer.append(",");
			writer.append("Name");
			writer.append(",");
			writer.append("Type");
			writer.append(",");
			writer.append("Area");
			writer.append(",");
			writer.append("Sale Contact");
			writer.append(",");
			writer.append("Description");
			writer.append(",");
			writer.append("latlong");
			writer.append(",");
			writer.append("Property ID");
			writer.append(",");
			writer.append("Floor Area");
			writer.append(",");
			writer.append("Land Area");
			writer.append(",");
			writer.append("Energy");
			writer.append(",");
			writer.append("Parking");
			writer.append(",");
			writer.append("Building Type");
			writer.append(",");
			writer.append("Annual Return");
			writer.append(",");
			writer.append("Availability");
			writer.append(",");
			writer.append("Category");
			writer.append(",");
			writer.append("LastUpdated");
			writer.append(",");
			writer.append("GalleryImage");
			writer.append(",");
			writer.append("Agents");
			writer.append(",");
			writer.append("\n");

			System.out.println(" -- out size-- "+ list.size());
			if(list.size() > 0) {
				Iterator<Info> it = list.iterator();
	
				while (it.hasNext()) {
					Info info = (Info) it.next();
					try {
					System.out.println(info.getBusinessProfileUrl());
					System.out.println(info.getName());
					writer.append(csvformatDevider(info.getBusinessProfileUrl()));
					writer.append(",");
					writer.append(csvformatDevider(info.getName()));
					writer.append(",");
					writer.append(csvformatDevider(info.getType()));
					writer.append(",");
					writer.append(csvformatDevider(info.getArea()));
					writer.append(",");
					writer.append(csvformatDevider(info.getSaleContact()));
					writer.append(",");
					writer.append(csvformatDevider(info.getDescription()));
					writer.append(",");
					writer.append(csvformatDevider(info.getLatlong()));
					writer.append(",");
					writer.append(csvformatDevider(info.getPropertyID()));
					writer.append(",");
					writer.append(csvformatDevider(info.getFloorArea()));
					writer.append(",");
					writer.append(csvformatDevider(info.getLandArea()));
					writer.append(",");
					writer.append(csvformatDevider(info.getEnergy()));
					writer.append(",");
					writer.append(csvformatDevider(info.getParking()));
					writer.append(",");
					writer.append(csvformatDevider(info.getBuildingType()));
					writer.append(",");
					writer.append(csvformatDevider(info.getAnnualReturn()));
					writer.append(",");
					writer.append(csvformatDevider(info.getAvailability()));
					writer.append(",");
					writer.append(csvformatDevider(info.getCategory()));
					writer.append(",");
					writer.append(csvformatDevider(info.getLastUpdated()));
					writer.append(",");
					
					StringBuilder b = new StringBuilder();
					Iterator<String> iter = info.getImageUrl().iterator();
					while(iter.hasNext()) {
						b.append( iter.next() + " | ");
					}
					writer.append("\"" + b.toString()+ "\"");
					writer.append(",");
					
					Iterator<Agent> itera = info.getAgents().iterator();
					StringBuilder sb = new StringBuilder();
					while(itera.hasNext()) {
						Agent agent = new Agent();
						agent = itera.next();
						sb.append("["+ agent.getAgentName() +" : " +	agent.getAgentPhone()+" : " + agent.getProfileUrl()+"] | ");
					}
					writer.append(csvformatDevider(sb.toString()));
					  } catch(Exception e) {}
					writer.append("\n");
					count++;
				}
			}

		} catch (IOException e) {
			System.out.println(" csv g Error : " + e.getMessage());
		}finally {
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
		return count;
	} 

	private String csvformatDevider(String text) {
		String newText; 
		try {
		newText = text.replaceAll("[\\t\\n\\r]", " | ") ;
		
		if (newText.contains(","))
			if (!newText.startsWith("\"") && !newText.endsWith("\""))
				newText = "\"" + newText + "\"";
		}catch (Exception e) {
			return "";
		}
		return newText;
	}
	
	

}
