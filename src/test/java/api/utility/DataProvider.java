package api.utility;

import java.io.IOException;

public class DataProvider {
	
	@org.testng.annotations.DataProvider(name="Data")
	public String[][] getAllData() throws IOException{
		
		String path=System.getProperty("user.dir")+"//TestData//UserTestData.xlsx";
		XLUtility xlutility = new XLUtility();
		
		int rownum = XLUtility.getRowCount(path, "Sheet1");
		int columcount = XLUtility.getCellCount(path, "Sheet1", 1);
	
		String[][] apidata= new String[rownum][columcount];
		
		for(int i=1; i<=rownum;i++) {
			for(int j=0;j<columcount;j++) {
				apidata[i-1][j]=XLUtility.getCellData(path, "Sheet1", i, j);
			};
			
		}
		return apidata;
		
	}
	
	@org.testng.annotations.DataProvider(name="UserData")
	public String[] getuserData() throws IOException{
		
			
			String path=System.getProperty("user.dir")+"//TestData//UserTestData.xlsx";
					
			int rownum = XLUtility.getRowCount(path, "Sheet1");
		
			String[] userName= new String[rownum];
			
			for(int i=1; i<=rownum;i++) {
				
				userName[i-1]=XLUtility.getCellData(path, "Sheet1", i, 1);
			
				
			}
			return userName;
			
		
	}

}
