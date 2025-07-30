# -*- coding: utf-8 -*-


from pyspark.sql import SparkSession
from pyspark.sql.types import StringType, ArrayType, StructType, StructField
from pyspark.sql.functions import explode, split, array, array_contains

# Initialize Spark session
spark = SparkSession.builder \
                    .appName('EmployeeSkillsApp') \
                    .getOrCreate()

# Sample data
records = [
    ("Riya,,Sharma", ["Python", "SQL", "Java"], ["Spark", "Python"], "TX", "CA"),
    ("Karan,Mehta,", ["Spark", "Python", "JavaScript"], ["Spark", "Java"], "WA", "NY"),
    ("Neha,,Verma", ["Go", "Rust"], ["Spark", "Scala"], "OR", "NV")
]

# Define schema
schema = StructType([
    StructField("employee_name", StringType(), True),
    StructField("skills_at_school", ArrayType(StringType()), True),
    StructField("skills_at_work", ArrayType(StringType()), True),
    StructField("current_location", StringType(), True),
    StructField("previous_location", StringType(), True)
])

# Create DataFrame
df = spark.createDataFrame(data=records, schema=schema)
df.printSchema()
df.show()

# Explode array column
df.select(df.employee_name, explode(df.skills_at_school)).show()

# Split string column
df.select(split(df.employee_name, ",").alias("name_parts")).show()

# Combine columns into array
df.select(df.employee_name, array(df.current_location, df.previous_location).alias("locations")).show()

# Check if array contains a specific value
df.select(df.employee_name, array_contains(df.skills_at_school, "Python").alias("knows_python")).show()
