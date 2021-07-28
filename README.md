# Water Amount Calculator Application 
## Water is an essential component of life; however, most people do not drink enough water. Consequently, I sought to develop an easy way for users to calculate the amount of water that they should be striving to drink each day.
### Components 
#### User Data/Login Feature 
##### This application allows users to save the input data that they provide through the likes of an SQL database; in the app, users are prompted to create a username and password. If the user's username and password are deemed as valid (i.e. no matching usernames already exist in the database), the user is directed to a page that allows them to fill in demographic information such as weight, sex, age, and physical activity level. Once they submit this information, it is stored in the SQL database with the user's username and password; consequently, should the user attempt to login in using their credentials in the future, they are not required to fill in their demographic information a second time (although they may update it).
#### GUI
##### This application uses a series of JFrames in order to display the login screen, demographic information screen, and results screen to the user. Depending upon which of the various buttons are clicked, the user can navigate between each of the three pages and log out/update information/view results again as necessary.
#### Calculations 
##### This application uses several methods to perform the requisite calculations which are required in order to provide the user with an accurate depiction of the amount of water that they should be drinking daily. Depending upon the values that the user inputs, the conditions executed within each of the functions varies, causing the application to be customizable to each individual user.
#### Future Additions/Updates
##### In the future, I hope to be able to add more features that allow their user to keep track of their fitness levels, whether that be in the form of diet patterns, amount of exercise, or sleep, in order to be able to provide more personalized recommendations to the users to enable them to set more specific and tailored fitness goals.
#### Acknowledgements 
##### The code used to connect the SQL database to the Java application was found in the video "Java JDBC - Connect to MySQL Database in IntelliJ with Java" by user WittCode at youtube.com. Link to video: https://www.youtube.com/watch?v=e8g9eNnFpHQ&t=245s
##### The code used to construct the JFrame GUI was found in the videos "Java GUI ☕ (𝙁𝙧𝙚𝙚)" by user Bro Code at youtube.com at https://www.youtube.com/watch?v=Kmgo00avvEw&t=1775s and "Java GUI Tutorial - Make a Login GUI" by user Alex Lee at youtube.com at https://www.youtube.com/watch?v=iE8tZ0hn2Ws
