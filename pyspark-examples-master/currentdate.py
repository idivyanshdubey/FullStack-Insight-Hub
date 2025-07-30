# -*- coding: utf-8 -*-


import pyspark
from pyspark.sql import SparkSession
from pyspark.sql.functions import col, to_timestamp, current_timestamp
from pyspark.sql.types import StructType, StructField, StringType

# Initialize Spark session
spark = SparkSession.builder.appName('CitySequenceApp').getOrCreate()

# Define schema
custom_schema = StructType([
    StructField("id", StringType(), True)
])

# Sample data
sequence_data = ['A']

# Create DataFrame
sequence_df = spark.createDataFrame(list('A'), schema=custom_schema)

# Display DataFrame
sequence_df.show()
