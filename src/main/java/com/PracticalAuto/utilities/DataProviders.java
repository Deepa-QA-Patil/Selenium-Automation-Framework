package com.PracticalAuto.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {
	private static final String FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/testdata/Testdatas.xlsx";
	
	@DataProvider(name="validLoginData")
	public static Object[][] validLoginData() throws FileNotFoundException, IOException{
	    return getSheetData("validdata");
	}
	@DataProvider(name="invalidata")
	public static Object[][] inValidLoginData() throws FileNotFoundException, IOException{
	    return getSheetData("invalidata");
	}

			private static Object[][] getSheetData(String sheetName) throws FileNotFoundException, IOException {
			    List<String[]> sheetData = ExcelReaderUtility.getSheetData(FILE_PATH,sheetName);

			    Object[][] data = new Object[sheetData.size()][sheetData.get(0).length];

			    for(int i=0; i<sheetData.size(); i++) {
			        data[i] = sheetData.get(i);
			    }

			    return data;
			}


}
