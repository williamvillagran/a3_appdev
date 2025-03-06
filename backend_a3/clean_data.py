import pandas as pd
from datetime import datetime
import os

def preprocess_swipe_data(data):
    records = []

    for user_id, events in data.items():
        event_count = 1

        for event_id, details in events.items():
            details['user_id'] = user_id

            details['user_id'] = details['user_id'].replace('User:', '')


            if 'timestamp:' in details:
                # Convert timestamp to proper format
                timestamp_ms = int(details['timestamp:'])
                details['timestamp'] = datetime.utcfromtimestamp(timestamp_ms / 1000).strftime('%Y-%m-%d %H:%M:%S')  # Format to standard datetime
                del details['timestamp:']
            elif 'timestamp' in details:
                timestamp_ms = int(details['timestamp'])
                details['timestamp'] = datetime.utcfromtimestamp(timestamp_ms / 1000).strftime('%Y-%m-%d %H:%M:%S')
            else:
                details['timestamp'] = "No timestamp"  #If timestamp is null



            # Clean data to leave only the data is helpful
            cleaned_details = {
                'user_id': details['user_id'],
                'event_number': event_count,
                'distance': round(details.get('distance', 0), 2),
                'duration': round(details.get('duration', 0), 2),
                'swipe_direction': details.get('swipe direction', 'N/A'),
                'timestamp': details['timestamp'],
                'velocity': round(details.get('velocity', 0), 2),
                'x_axis_start': round(details.get('x axis start', 0), 2),
                'x_axis_end': round(details.get('x axis end', 0), 2)
            }

            # Append clean data to list
            records.append(cleaned_details)

            event_count += 1

    df = pd.DataFrame(records)

    df.columns = df.columns.str.strip()

    df = df.sort_values(by='timestamp')

    # Set order of data for csv
    column_order = ['user_id', 'event_number', 'distance', 'duration', 'swipe_direction', 'timestamp', 'velocity',
                    'x_axis_start', 'x_axis_end']


    df = df[column_order]

    # Export to a csv
    export_to_csv(df, "clean_data.csv")

    return df



# Creating a csv of cleaned data
def export_to_csv(df, filename):
    export_dir = './exported_data'
    if not os.path.exists(export_dir):
        os.makedirs(export_dir)

    file_path = os.path.join(export_dir, filename)

    df.to_csv(file_path, index=False)


