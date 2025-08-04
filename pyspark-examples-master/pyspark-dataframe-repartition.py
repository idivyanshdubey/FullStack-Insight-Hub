# -*- coding: utf-8 -*-
from pyspark.sql import SparkSession

# Initialize Spark session
spark = SparkSession.builder.appName('PartitionDemoApp') \
        .master("local[4]").getOrCreate()

# Create initial DataFrame
data_frame = spark.range(0, 30)
print("Initial partitions:", data_frame.rdd.getNumPartitions())

# Write to CSV
data_frame.write.mode("overwrite").csv("c:/tmp/partition_output.csv")

# Write to JSON
data_frame.write.mode("overwrite").json("c:/tmp/partition_output.json")

# Read from JSON
json_df = spark.read.json("c:/tmp/partition_output.json")
print("Read from JSON - Schema:")
json_df.printSchema()

# Repartition to increase number of partitions
repartitioned_df = data_frame.repartition(8)
print("After repartition:", repartitioned_df.rdd.getNumPartitions())

# Coalesce to reduce number of partitions
coalesced_df = data_frame.coalesce(3)
print("After coalesce:", coalesced_df.rdd.getNumPartitions())

# GroupBy operation
grouped_df = data_frame.groupBy("id").count()
print("Partitions after groupBy:", grouped_df.rdd.getNumPartitions())

# Write grouped data to JSON
grouped_df.write.mode("overwrite").json("c:/tmp/grouped_output.json")
