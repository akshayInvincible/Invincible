package com.invincible.seleniumtest.testdata;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;

public class EmailCredentials {
	private XSSFWorkbook workbook;
	private XSSFSheet worksheet;
	private DataFormatter formatter= new DataFormatter();

	public Object[][] getCredentials(){
		try {
			FileInputStream fileInputStream;
			fileInputStream = new FileInputStream("src/test/resources/emailCredentials.xlsx");		
			workbook = new XSSFWorkbook (fileInputStream); 
			worksheet=workbook.getSheet("Sheet1");
			XSSFRow Row=worksheet.getRow(0);   
			int RowNum = worksheet.getPhysicalNumberOfRows();

			int ColNum= Row.getLastCellNum();        
			Object Data[][]= new Object[RowNum-1][ColNum];

			for(int i=0; i<RowNum-1; i++) //skip the first row as it is just a header
			{  
				XSSFRow row= worksheet.getRow(i+1);
				for (int j=0; j<ColNum; j++)
				{
					if(row==null)
						Data[i][j]= "";
					else
					{
						XSSFCell cell= row.getCell(j);
						if(cell!=null)
						{
							String value=formatter.formatCellValue(cell);
							Data[i][j]=value;
						}
					}
				}
			}
			return Data;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}