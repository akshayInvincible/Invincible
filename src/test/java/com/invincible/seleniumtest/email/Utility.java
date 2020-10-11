package com.invincible.seleniumtest.email;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

/**
 * Utility for interacting with an Email application
 */
public class Utility {
	private Folder emailFolder;
	private Store store;
	public Message[] getMails(String host,int port, String user,String password) 
	{
		try {

			Properties properties = new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.port", "465");
			properties.put("mail.smtp.socketFactory.port","465");
			properties.put("mail.transport.protocol","smtp");					

			Session session = Session.getDefaultInstance(properties);
			this.store = session.getStore("imaps");
			store.connect(host,port, user, password);

			this.emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			Message[] messages = emailFolder.getMessages();
			System.out.println("Total no. of emails: " + messages.length);
			return messages;
		} catch (MessagingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean checkTextInSubject(String text, Message[] msgs) {
		boolean check= false;
		if(msgs!=null) {
			for (Message msg: msgs) {
				try {
					if(msg.getSubject()!=null&&msg.getSubject().contains(text)) {
						check=true;
						System.out.println("mail subject: "+msg.getSubject()+"/n body: "+msg.getContent().toString());
						break;
					}
				} catch (MessagingException |IOException e) {
					e.printStackTrace();
				}
			}
		}
		return check;
	}
	public boolean checkTextInBody(String text, Message[] msgs) {
		boolean check= false;
		if(msgs!=null) {
			for (Message msg: msgs) {
				try {
					if(msg.getContent().toString()!=null&&msg.getContent().toString().contains(text)) {
						check=true;
						System.out.println("mail subject: "+msg.getSubject()+"/n body: "+msg.getContent().toString());
						break;
					}				
				} catch (MessagingException |IOException e) {
					e.printStackTrace();
				}
			}
		}
		return check;
	}
	public void close() {
		try {
			if(emailFolder!=null)
				emailFolder.close(false);
			if(store!=null)
				store.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
