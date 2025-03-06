# BrowsingApp
William Villagran (ayv137)
Victor Garcia (bfo155)

Assignment 3 - BrowsingApp is a android application that incorporates the functionalities of a gallery app allowing the user to browse through a collection of images and swipe through them.

## Swipe Gesture and Data Logging
The BrowsingApp contains a viewPager with a horizontal linear layout that allows users to swipe through the collection. The app uses MotionEvent to detect swipe gestures tracking data of swipe direction, velocity, distance, and duration, using Firebase for data storage. 

## Front-end (Android App)
On Android studio make sure the gradle is synced and run the application.
Select button and slide through pictures to record and send data to Realtime Database

## Back-end (Python App)
Either trough an IDE or terminal run app.py. You will want to browse to 127.0.0.1:5000 to see the python application running. 

127.0.0.1:5000/get_data         - uncleaned data
127.0.0.1:5000/get_data_clean   - cleaned data


