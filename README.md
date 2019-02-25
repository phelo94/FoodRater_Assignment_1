# FoodRater_Assignment1
Android Studio App



Name: Shawn Phelan

Student Number: 20066555

App Description

The app is called FoodRater, and the purpose of the app is to offer any user the possibility to track any of their favourite takeouts or restaurants. The app lets the user add a food item and a price of payed and the location. The food item can then be added the favourites and then be able to search for previously added food items. 

The the user first starts the app, they will need to sign up for the app with their email address and confirm the email address through their emails to verify their details. The user can then update their profile page by uploading a profile picture to firebase which will be saved to the users profile pics. 

The login and profile setup are setup with cloud persistence with firebase, and once inside the app, the user can leave a personal note of any takeouts or restaurants they have eaten from. The note will be for a specific food item that the user might have entered in as a favourites, or to never eat in that location again, as you can leave a 1 out of 10 Score on a possible return. This personal review is also cloud based when the details are stored and can be deleted as well when needed, using a swipe action since its stored in a Recycler-view.

The personal Journal is for how the user experienced the takeout or restaurant overall, including the location and how it was served, it gives the user to enter more details on their personal experience. This option is setup through SQLite and is ruined locally from the app. When a user adds a review, the details will be saved in JSON file setup in a local schema, these details entered can also be deleted or edited to add more or less to the review and then saved again once it’s updated. 

The user will then be able to log out and then can log back in with the same details when they set up their account as it has the Authentication persistence through firebase. 


GITHUB LINK: 
https://github.com/phelo94/FoodRater_Assignment1


YOUTUBE LINK:
https://www.youtube.com/watch?v=4-ch0Jnj_KM&feature=youtu.be


References

Card view in Recycler view
https://www.youtube.com/watch?v=RFFu3dP5JDk&list=PLrnPJCHvNZuAXdWxOzsN5rgG2M4uJ8bH1


Android Auth and login and upload image 
https://www.youtube.com/watch?v=qVaBY92sOSI&lc=z23sihjhxwvcxf5t1acdp432yrycdyfqvhvvm20z04tw03c010c.1550521913447097


Google Docs for Image Upload 
https://firebase.google.com/docs/storage/web/upload-files


Resolution for Deprecated code - .getDownloadUrl();
https://firebase.google.com/docs/storage/android/download-files


David Drohan: Labs And Source Code:


Lab 1
https://ddrohan.github.io/wit-mad-2-2019/topic01-overview-and-tools/unit-1/book-coffeemate-lab-01/index.html


Lab 2
https://ddrohan.github.io/wit-mad-2-2019/topic02-ui-design/unit-1/book-coffeemate-lab-02/index.html


Lab 3
https://ddrohan.github.io/wit-mad-2-2019/topic02-ui-design/unit-1/book-coffeemate-lab-03/index.html


Lab 4
https://ddrohan.github.io/wit-mad-2-2019/topic02-ui-design/unit-2/book-coffeemate-lab-04/index.html


Adding Notes with SQL
https://codingwithmitch.com/courses/sqlite-room-persistence-android/


Gif Logo 
https://www.pinterest.ie/pin/87890630203265466/


Stye-able Toast 
https://www.codingdemos.com/android-toast-message-tutorial/
