#################################################################################
This test is to verify that the given String is present in the Subject of mail.
#################################################################################

Requirements:
* IDE with testNg plug-in installed.
* JDK 1.7+

Steps to execute
* Enter credentials and SearchText in the "emailCredentials.xlsx" file in resources folder.
* In case any other mail server used that gmail. Please modify "host" and "port" in email.properties as per new email server.
* Run testng.xml to execute the test.
* Project contains 2 tests. 
	- Test1: to verify content in Subject of mail
	- Test2: to verify content in Body of mail
* In case of success the console will print subject and body of mail.
* In case of Failure there will be appropriate message.