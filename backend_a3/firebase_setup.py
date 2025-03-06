import firebase_admin
from firebase_admin import credentials, db

# Load Firebase credentials
cred = credentials.Certificate("backend-a3-firebase-adminsdk-fbsvc-7ee1534fe3.json")
firebase_admin.initialize_app(cred, {
    'databaseURL': 'https://backend-a3-default-rtdb.firebaseio.com/'
})

print("Firebase Initialized!")
