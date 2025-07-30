# -*- coding: utf-8 -*-

from pyspark.sql import SparkSession

# Initialize Spark session
spark = SparkSession.builder.appName('LocationMappingApp').getOrCreate()

# Dictionary to broadcast
location_map = {"TX": "Texas", "WA": "Washington", "OR": "Oregon"}
broadcast_locations = spark.sparkContext.broadcast(location_map)

# Sample data
records = [
    ("Riya", "Sharma", "USA", "TX"),
    ("Karan", "Mehta", "USA", "WA"),
    ("Neha", "Verma", "USA", "TX"),
    ("Amit", "Patel", "USA", "OR")
]

# Column names
columns = ["first_name", "last_name", "country", "state_code"]

# Create DataFrame
df = spark.createDataFrame(data=records, schema=columns)
df.printSchema()
df.show(truncate=False)

# Function to convert state code to full name using broadcast variable
def convert_state(code):
    return broadcast_locations.value.get(code, "Unknown")

# Apply transformation using RDD
converted_df = df.rdd.map(lambda row: (row[0], row[1], row[2], convert_state(row[3]))).toDF(columns)
converted_df.show(truncate=False)

# Filter using broadcast variable
filtered_df = df.where(df['state_code'].isin(broadcast_locations.value))
filtered_df.show(truncate=False)
