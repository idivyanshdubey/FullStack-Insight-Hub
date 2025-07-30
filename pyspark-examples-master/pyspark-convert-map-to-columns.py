# -*- coding: utf-8 -*-

from pyspark.sql import SparkSession
from pyspark.sql.functions import explode, map_keys, col

# Initialize Spark session
spark = SparkSession.builder.appName('UserTraitsApp').getOrCreate()

# Sample data
user_traits = [
    ('Riya', {'height': '5.6', 'weight': '60'}),
    ('Karan', {'height': '5.9', 'weight': None}),
    ('Neha', {'height': '5.5', 'weight': '55'}),
    ('Amit', {'height': '6.0', 'weight': '70'}),
    ('Sara', {'height': '5.7', 'weight': ''})
]

# Create DataFrame
df = spark.createDataFrame(data=user_traits, schema=['username', 'traits'])
df.printSchema()
df.show(truncate=False)

# Convert map column to individual columns using RDD
df_rdd = df.rdd.map(lambda x: (x.username, x.traits["height"], x.traits["weight"]))
df_rdd_df = df_rdd.toDF(["username", "height", "weight"])
df_rdd_df.printSchema()
df_rdd_df.show()

# Extract map values using getItem
df.withColumn("height", df.traits.getItem("height")) \
  .withColumn("weight", df.traits.getItem("weight")) \
  .drop("traits") \
  .show()

# Extract map values using dictionary-style access
df.withColumn("height", df.traits["height"]) \
  .withColumn("weight", df.traits["weight"]) \
  .drop("traits") \
  .show()

# Dynamically extract all keys from map
keys_df = df.select(explode(map_keys(df.traits))).distinct()
keys_list = keys_df.rdd.map(lambda x: x[0]).collect()
key_columns = [col("traits").getItem(k).alias(k) for k in keys_list]
df.select(df.username, *key_columns).show()
