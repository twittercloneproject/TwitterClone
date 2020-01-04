# TwitterClone
CS 340 project
Frontend code for twitterclone android app. the actually source code is found in app/src/main
Used the Model View Presenter design pattern. 
Each different screen has its own activity class and a fragment is used for tabs on the main screen. 
The app interacts with the backend by the presenter objects using the model class logic. This class has a proxy that gives it services that call the backend api, which is connected to lambda functions.  
