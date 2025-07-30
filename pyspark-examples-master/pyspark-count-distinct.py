# -*- coding: utf-8 -*-


from pyspark.sql import SparkSession
from pyspark.sql.functions import countDistinct

# Initialize Spark session
spark = SparkSession.builder.appName('TeamStatsApp').getOrCreate()

# Sample data
records = [
    ("Riya", "HR", 4200),
    ("Karan", "HR", 5800),
    ("Neha", "HR", 4200),
    ("Amit", "Finance", 3100),
    ("Riya", "HR", 4200),
    ("Meera", "Finance", 3400),
    ("John", "Finance", 4000),
    ("Ali", "Support", 3100),
    ("Sara", "Support", 2100),
    ("Raj", "HR", 5800)
]

columns = ["Employee", "Team", "Income"]

# Create DataFrame
df = spark.createDataFrame(data=records, schema=columns)

# Show distinct rows
df.distinct().show()
print("Distinct Count:", df.distinct().count())

# Count distinct combinations of Team and Income
df2 = df.select(countDistinct("Team", "Income"))
df2.show()
print("Distinct Count of Team & Income:", df2.collect()[0][0])

# SQL-based distinct count
df.createOrReplaceTempView("EMPLOYEES")
spark.sql("SELECT DISTINCT(COUNT(*)) FROM EMPLOYEES").show()
