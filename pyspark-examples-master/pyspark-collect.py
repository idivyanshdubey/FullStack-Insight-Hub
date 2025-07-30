# -*- coding: utf-8 -*-

from pyspark.sql import SparkSession

# Initialize Spark session
spark = SparkSession.builder.appName('DepartmentInfoApp').getOrCreate()

# Sample data
departments = [
    ("Human Resources", 101),
    ("Engineering", 102),
    ("Operations", 103),
    ("Support", 104)
]

# Column names
dept_columns = ["department_name", "department_id"]

# Create DataFrame
dept_df = spark.createDataFrame(data=departments, schema=dept_columns)
dept_df.printSchema()
dept_df.show(truncate=False)

# Collect full rows
collected_data = dept_df.collect()
print(collected_data)

# Collect specific column
collected_names = dept_df.select("department_name").collect()
print(collected_names)

# Iterate and print formatted output
for row in collected_data:
    print(f"{row['department_name']}, {row['department_id']}")
