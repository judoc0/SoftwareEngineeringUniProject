Repository for the Software Engineering course 2021/2022.

Faculty of Engineering - Bachelor's Degree in Information Engineering
Software Project for Exams from June 2021 to May 2022

Requirements
A large chain of furniture and home products wants to create the MyShop system to be installed on totems within their
stores to enhance the shopping experience for customers.

Guidelines for Development
The system should be developed using the Java programming language, adopting the software architecture and design patterns
covered during the course. The data should be stored in a MySQL relational database. An agile Scrum development process
should be followed, and the working procedure should be documented. Unit tests should be implemented for the classes
contained in the DAO package.
The documentation should consist of a single document with a cover page and table of contents, containing: (1) requirements analysis, (2) UML software architecture design, (3) explicit indication of the adopted design patterns, (4) conceptual and logical database design, (5) results of executed unit tests, (6) Scrum sprint backlog and burndown chart.

To view the documentation, please refer to the PDF file named "Progetto PIS.pdf".

For further information regarding the software project requirements, refer to the PDF file "Traccia_elaborato_software_valida_da_Giugno_2021_a_Maggio_2022.pdf".

--------------------------------------------------------------------------------------------------

Use the Dump.sql file to generate the database.
Remember to change 'userName' and 'pwd' in the DbUser class of the DBInterface package according to your DBMS credentials.

To use the test users for the program:

Utente acquirente

username: 1
password: 1

Manager

username: 2
password: 2

Amministratore

username: 3
password: 3


This project has been developed using IntelliJ IDEA Community Edition.

To successfully run the project using a different IDE like Eclipse, make sure to properly import the external libraries from SoftwareEngineeringUniProject/Progetto/lib/.

--------------------------------------------------------------------------------------------------

2023 Version

After about a year, I decided to upload the project to GitHub, but not before making some small updates.

•	The software used a Gmail account to automatically send any emails.
	Starting from May 30, 2022, Google disabled the "Less secure apps" feature, which resulted in the discontinuation
	of support for third-party apps or devices that requested access to the Google Account using only the username and password. Now, in order to send emails through third-party apps via a Gmail account, you need to generate an app password in your Google account.
	After following the steps to generate the app password and modifying the credentials in the MailJavaxApiClass class in the business package, the software is able to send automatic emails again.

•	Simplified the image insertion process by using the JFileChooser class from the Java Swing library to create a dialog box for selecting files or directories by the user.
	(previously, the user had to manually enter the file path)

•	In the use case of removing an article, if the article is a product and is part of a composite product,
	you will now be notified that you cannot remove it without first removing it from the composite product.
	(previously, it attempted to delete it and gave an error)

•	Moved ItemListener to be grouped with other listeners from the NewProdottoPanel class to NewProdottoPanelListener.

•	In the PDF documentation, I forgot to mention that I used the Composite Pattern for the IProdotto, Prodotto, and ProdottoComposito classes in the Model package.

