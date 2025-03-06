from flask import Flask, jsonify
import firebase_admin
from firebase_admin import credentials, db
from datetime import datetime
import firebase_setup
from clean_data import preprocess_swipe_data

app = Flask(__name__)

@app.route('/')
def home():
    return jsonify({"message" : "Flask & Firebase are connected!"})

# Recieve swipe data from Firebase
@app.route('/get_data', methods=['GET'])
def get_swipes():
    ref = db.reference("swipeLog")
    data = ref.get()
    return data if data else {}

@app.route('/get_data_clean', methods=['GET'])
def get_clean_swipes():
    try:
        ref = db.reference("swipeLog")
        data = ref.get()

        if data:
            # Preprocess the swipe data
            processed_data = preprocess_swipe_data(data)

            # Convert the processed data to a dictionary for easy response
            processed_data_dict = processed_data.to_dict(orient='records')
            return jsonify({"clean_data": processed_data_dict})
        else:
            return jsonify({"message": "No swipe data found"}), 404
    except Exception as e:
        return jsonify({"error": str(e)}), 500




if __name__ == '__main__':
    app.run(debug=False)
