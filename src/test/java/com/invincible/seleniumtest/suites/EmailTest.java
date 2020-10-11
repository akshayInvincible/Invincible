package com.invincible.seleniumtest.suites;

import com.invincible.seleniumtest.email.Utility;
import com.invincible.seleniumtest.testdata.EmailCredentials;

import java.io.FileReader;
import java.util.Properties;

import javax.mail.Message;
import org.testng.Assert;
import org.testng.annotations.*;

public class EmailTest{

	private Message[] mails;
	private Utility emailUtil=new Utility();
	
	@Test(dataProvider="Authenticator")
	public void testTextContained(String username, String password,String searchText) {
		try{
			FileReader reader=new FileReader("src/test/resources/email.properties");
			Properties p=new Properties();
			p.load(reader);
			mails=emailUtil.getMails(p.getProperty("host"),Integer.parseInt(p.getProperty("port")), username, password);
			Assert.assertTrue(emailUtil.checkTextInSubject(searchText, mails), "Approval message is not in email");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	@AfterClass
	public void testClose() {
		emailUtil.close();
	}
	@DataProvider(name="Authenticator")
	public Object[][] credentials(){
		return new EmailCredentials().getCredentials();	
	}
	
}