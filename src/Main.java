import java.util.LinkedList;

public class Main {
	
	final static String URL = "https://www.commercialrealestate.com.au/for-sale/australia/industrial-warehouse/?r=5%2C19";
	final static int lastpageNumber = 46;
	final static int driverDefreshAfter = 5;
	
	public static void main(String[] args) {
		int currentPage = 0;
		FireFoxHandaler fireFoxHandaler = new FireFoxHandaler();
		LinkedList<Info> infos = new LinkedList<>();
		CsvGenerator csvGenerator = new CsvGenerator();
		int driverDefreshAfterPage = driverDefreshAfter;
		String targetURL = URL;
				
		fireFoxHandaler.loadPage(targetURL);
		currentPage = fireFoxHandaler.currentPageNumber();
		System.out.println("pageNumber : " + currentPage);
		
		fireFoxHandaler.createSubTab();
		
		while(currentPage != -1 || !(currentPage > lastpageNumber)) {
		
		// For avoid memory leaks	
		driverDefreshAfterPage--;
		if(driverDefreshAfterPage == 0 ) {
			targetURL = fireFoxHandaler.pageUrl();
			fireFoxHandaler.refreshDriver(targetURL);
			fireFoxHandaler.createSubTab();
			driverDefreshAfterPage = driverDefreshAfter;;
		}
		
		LinkedList<String> companies = fireFoxHandaler.takeCompanyList();
		
		fireFoxHandaler.switchSubTab();
		for(int i= 0; i < companies.size(); i++) {
			System.out.println(" Page url :: " + companies.get(i));
			infos.add(fireFoxHandaler.openpage(companies.get(i)));
		}
		
		
		if(currentPage == lastpageNumber) {
			System.out.println("COMPLITED \n CURRENT PAGE : " + currentPage); 
			break;}
		fireFoxHandaler.switchMainTab();
		if(!fireFoxHandaler.openNextPage()) {
			System.out.println("PAGE DIDN'T LOAD WITH IN TIME LIIMITS \n BREAKING AT PAGE : " + currentPage); 
			break;}

		int page = fireFoxHandaler.currentPageNumber();
		if(currentPage == page) {
			System.out.println("UNABLE TO LOAD NEXT PAGE \n BREAKING AT PAGE : " + page); 
			break;}		
		currentPage = page;	
		
		}
		
		csvGenerator.listtoCsv(infos);
		
	}

}
