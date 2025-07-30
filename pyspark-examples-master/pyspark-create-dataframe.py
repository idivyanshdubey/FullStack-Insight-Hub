# -*- coding: utf-8 -*-


from pyspark.sql import SparkSession, Row
from pyspark.sql.functions import *
from pyspark.sql.types import StructType, StructField, StringType, IntegerType

# Initialize Spark session
spark = SparkSession.builder.appName('TechUsageApp').getOrCreate()

# Sample data
tech_data = [("HTML", "15000"), ("CSS", "8000"), ("JavaScript", "120000")]

# Define column names
columns = ["tech_name", "user_count"]

# Create RDD
rdd = spark.sparkContext.parallelize(tech_data)

# Convert RDD to DataFrame (default column names)
df_rdd1 = rdd.toDF()
df_rdd1.printSchema()

# Convert RDD to DataFrame with column names
df_rdd2 = rdd.toDF(columns)
df_rdd2.printSchema()

# Create DataFrame using SparkSession and unpack column names
df_rdd3 = spark.createDataFrame(rdd).toDF(*columns)
df_rdd3.printSchema()

# Create DataFrame directly from data
df_data1 = spark.createDataFrame(tech_data).toDF(*columns)
df_data1.printSchema()

# Create DataFrame using Row mapping
row_data = map(lambda x: Row(*x), tech_data)
df_data2 = spark.createDataFrame(row_data, columns)
df_data2.printSchema()
