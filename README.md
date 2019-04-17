# INFSCI 2730 E-Business Final Project  Hotel Booking Application  


## Group Member
* Xinran Chen xic96@pitt.edu  
* Zhifeng Wang zhw81@pitt.edu  
* Chi-Heng Hung CHH162@pitt.edu  
* Chia-Jung Chang CHC276@pitt.edu  

## Overview
We build a hotel booking web-based application that allows user to search for available rooms for our hotel and book rooms through the web-based application.  

## Main Features  
* Book room  
  * Available Room lookup  
  * Select Idea Room  
  * Confirm Booking Information and User Information  
* Membership  
  * Sign In / Sign Up  
  * Manage Your user Information  
  * Manage Booking Information  
  * Booking History Lookup  
* Preventing Injection Attacks  
  * Server-side Input Validation for all inputs  
  * Prepared Statement for all SQL Queries  
* Protecting User’s password  
  * Using SHA-256 to hash user’s password  
* Prevent invalid credential accessing data  
  * Using JSF WebFilter to filter invalid credentials  

## System Flow Chart  
![System Flow Chart](https://raw.githubusercontent.com/Jeffreyhung/UPitts_EBusiness_HotelBookingApp/master/SystemFlowChart.png)  

## UML  
![UML](https://raw.githubusercontent.com/Jeffreyhung/UPitts_EBusiness_HotelBookingApp/master/Diagram.png)  

## Usage Instruction  
The Hotel Booking application we build can be used with or without a member account and login. Therefore, we will break down the instruction into two parts, booking part and membership part  

### Booking  
The homepage of our Hotel Booking application is the room lookup page, one this page user can select the dates that they are planning to stay at the hotel and the number of people they are planning to stay with. Once user entered the information, they can press the “Search” button and they will be direct to a new page with all available rooms that matches the condition they selected. On this page, all the available rooms will be listed as a table with the name of the room, the capacity of the room, number of beds in this room and the price of the room. User can click on the select button right next to the room they are interested in to select that room. Once they select the room they are interested in, they will be guide to a new page with order details of the dates and the room information, on this page user will be asked to enter their information which is required to complete the booking process. There are links on this page that allows user to change the dates or the room if they change their mind. After the user input their information and they click on the submit button, the page will show a confirmation information about the booking detail. Till this point, a complete booking process is finished.  

### Membership  
Our Hotel Booking application have a membership that allows user to manage their booking information and booking history. To access these features, user can click on the “MENU” button on the upper right of every page in our Hotel Booking application, where they can choose to sign in or sign up. If the user owns an account, they can sign in from the “Sign In” button, if they don’t, they can use the “Sign Up” button to sign up for an account. On the sign-up page, user will be asked to choose a username that only allows lowercase alphabets and password that should be a combination of alphabets and number which length should be between 8 and 15. Besides username and password, user will be asked to enter some basic information like their name, email address, phone number and address. When user click on the “Sign Up” button, we will perform a check that all user’s input are valid and matches our rules which should not include some invalid characters, also we will check the username that user input to see if it’s unique and never used by other user. If any input made by user is invalid, there will be red error message showing up indicate which part of input is invalid. If everything is correct and valid, then the user account will be created and stored in database, the user will be login automatically and redirected to the home page. If the user is login, they can book a hotel room just as they are not login, the only difference is that on the order details page, their member information will be automatically filled into the form. Another feature that members can manage their member information and booking information. One the member is login, there will be a option in the upper-right “MENU” button called “Account”, where they can choose to manage their member information and booking information. They can view their member information and make changes to it. They can also lookup for their booking history on the “Booking Info” page, here all their booking records will be listed as a table and user can see all the information of it, also if the booking has not happened yet, they can choose to cancel their booking by clicking on the cancel button right next to the booking information, once they cancel it the booking information will be deleted from the database and the table will be refreshed. If the booking has pasted already and should not be able to be canceled, the cancel button will be disabled.  

## Reference  
Bootstrap v3.3.7 (http://getbootstrap.com) using MIT License  
Wow Technology Website Template from Free Software Foundation (https://www.fsf.org/) using GNU v.3 License  
Font Awesome Free License (https://fontawesome.com) using CC 4.0 License  
