# -*- coding: utf-8 -*-

from pyspark.sql import SparkSession
from pyspark.sql.types import StructField, StructType, StringType, MapType
from pyspark.sql.functions import explode, map_keys, col

# Initialize Spark session
spark = SparkSession.builder.appName('UserAttributesApp').getOrCreate()

# Sample data
user_data = [
    ('Riya', {'height': '5.6', 'weight': '60'}),
    ('Karan', {'height': '5.9', 'weight': None}),
    ('Neha', {'height': '5.5', 'weight': '55'}),
    ('Amit', {'height': '6.0', 'weight': '70'}),
    ('Sara', {'height': '5.7', 'weight': ''})
]

# Create DataFrame with inferred schema
df = spark.createDataFrame(data=user_data, schema=['username', 'attributes'])
df.printSchema()
df.show(truncate=False)

# Using StructType schema explicitly
schema = StructType([
    StructField('username', StringType(), True),
    StructField('attributes', MapType(StringType(), StringType()), True)
])
df2 = spark.createDataFrame(data=user_data, schema=schema)
df2.printSchema()
df2.show(truncate=False)

# Convert map values to individual columns using RDD
df3 = df.rdd.map(lambda x: (x.username, x.attributes["height"], x.attributes["weight"])) \
            .toDF(["username", "height", "weight"])
df3.printSchema()
df3.show()

# Extract map values using getItem
df.withColumn("height", df.attributes.getItem("height")) \
  .withColumn("weight", df.attributes.getItem("weight")) \
  .drop("attributes") \
  .show()

# Extract map values using dictionary-style access
df.withColumn("height", df.attributes["height"]) \
  .withColumn("weight", df.attributes["weight"]) \
  .drop("attributes") \
  .show()

# Dynamically extract all keys from map
keys_df = df.select(explode(map_keys(df.attributes))).distinct()
keys_list = keys_df.rdd.map(lambda x: x[0]).collect()
key_columns = [col("attributes").getItem(k).alias(k) for k in keys_list]
df.select(df.username, *key_columns).show()
