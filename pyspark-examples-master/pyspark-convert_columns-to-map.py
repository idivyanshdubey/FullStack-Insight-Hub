# -*- coding: utf-8 -*-


from pyspark.sql import SparkSession
from pyspark.sql.types import StructType, StructField, StringType, IntegerType
from pyspark.sql.functions import col, lit, create_map

# Initialize Spark session
spark = SparkSession.builder.appName('EmployeeMapApp').getOrCreate()

# Sample data
records = [
    ("E101", "HR", 4200, "UK"),
    ("E102", "Engineering", 5800, "IND"),
    ("E103", "Sales", 3900, "USA"),
    ("E104", "Support", 2700, "CAN"),
    ("E105", "Sales", 6100, "USA")
]

# Define schema
schema = StructType([
    StructField("emp_id", StringType(), True),
    StructField("department", StringType(), True),
    StructField("income", IntegerType(), True),
    StructField("country", StringType(), True)
])

# Create DataFrame
df = spark.createDataFrame(data=records, schema=schema)
df.printSchema()
df.show(truncate=False)

# Convert selected columns to a map
df_mapped = df.withColumn("infoMap", create_map(
    lit("income"), col("income"),
    lit("country"), col("country")
)).drop("income", "country")

df_mapped.printSchema()
df_mapped.show(truncate=False)
