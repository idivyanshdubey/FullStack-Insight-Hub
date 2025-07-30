# -*- coding: utf-8 -*-

from pyspark.sql import SparkSession
from pyspark.sql.functions import col, concat_ws

# Initialize Spark session
spark = SparkSession.builder.master("local[1]") \
                    .appName('StudentLanguagesApp') \
                    .getOrCreate()

# Sample data
records = [
    ("Riya,,Sharma", ["Python", "SQL", "Java"], "TX"),
    ("Karan,Mehta,", ["Spark", "Python", "JavaScript"], "WA"),
    ("Neha,,Verma", ["Go", "Rust"], "OR")
]

# Column names
columns = ["student_name", "languages_known", "state"]

# Create DataFrame
df = spark.createDataFrame(data=records, schema=columns)
df.printSchema()
df.show(truncate=False)

# Convert array column to comma-separated string
df2 = df.withColumn("languages_known", concat_ws(",", col("languages_known")))
df2.printSchema()
df2.show(truncate=False)

# SQL transformation
df.createOrReplaceTempView("LANGUAGE_TABLE")
spark.sql("""
    SELECT student_name,
           concat_ws(',', languages_known) AS languages_known,
           state
    FROM LANGUAGE_TABLE
""").show(truncate=False)
