# -*- coding: utf-8 -*-

import pandas as pd
from pyspark.sql import SparkSession
from pyspark.sql.types import StructType, StructField, StringType, IntegerType

# Sample data
data = [['Scott', 50], ['Jeff', 45], ['Thomas', 54], ['Ann', 34]]

# Create Pandas DataFrame
pandas_df = pd.DataFrame(data, columns=['Name', 'Age'])
print("Pandas DataFrame:")
print(pandas_df)

# Initialize Spark session
spark = SparkSession.builder \
    .master("local[1]") \
    .appName("PandasToSparkExample") \
    .getOrCreate()

# Convert Pandas DataFrame to Spark DataFrame
spark_df = spark.createDataFrame(pandas_df)
print("\nSpark DataFrame (inferred schema):")
spark_df.printSchema()
spark_df.show()

# Define custom schema
custom_schema = StructType([
    StructField("First Name", StringType(), True),
    StructField("Age", IntegerType(), True)
])

# Apply schema during conversion
spark_df2 = spark.createDataFrame(pandas_df, schema=custom_schema)
print("\nSpark DataFrame (custom schema):")
spark_df2.printSchema()
spark_df2.show()

# Enable Arrow optimization for faster conversion
spark.conf.set("spark.sql.execution.arrow.enabled", "true")
spark.conf.set("spark.sql.execution.arrow.pyspark.fallback.enabled", "true")

# Convert Spark DataFrame back to Pandas
pandas_df2 = spark_df2.select("*").toPandas()
print("\nConverted back to Pandas DataFrame:")
print(pandas_df2)

# Check Spark configuration
arrow_enabled = spark.conf.get("spark.sql.execution.arrow.enabled")
arrow_fallback = spark.conf.get("spark.sql.execution.arrow.pyspark.fallback.enabled")

print("\nArrow Enabled:", arrow_enabled)
print("Arrow Fallback Enabled:", arrow_fallback)
