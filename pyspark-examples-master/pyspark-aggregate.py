# -*- coding: utf-8 -*-

from pyspark.sql import SparkSession
from pyspark.sql.functions import (
    approx_count_distinct, collect_list, collect_set,
    sum, avg, max, min, mean, countDistinct, count,
    first, last, kurtosis, skewness,
    stddev, stddev_samp, stddev_pop,
    sumDistinct, variance, var_samp, var_pop
)

# Initialize Spark session
spark = SparkSession.builder.appName('DepartmentStatsApp').getOrCreate()

# Sample data
employee_data = [
    ("Riya", "HR", 3200),
    ("Karan", "HR", 4700),
    ("Neha", "HR", 4200),
    ("Amit", "Finance", 3100),
    ("Riya", "HR", 3200),
    ("Meera", "Finance", 3400),
    ("John", "Finance", 4000),
    ("Ali", "Marketing", 3100),
    ("Sara", "Marketing", 2100),
    ("Raj", "HR", 4200)
]

schema = ["employee_name", "department", "salary"]

# Create DataFrame
df = spark.createDataFrame(data=employee_data, schema=schema)
df.printSchema()
df.show(truncate=False)

# Aggregations
print("Approx Distinct Salaries:", df.select(approx_count_distinct("salary")).collect()[0][0])
print("Average Salary:", df.select(avg("salary")).collect()[0][0])

df.select(collect_list("salary")).show(truncate=False)
df.select(collect_set("salary")).show(truncate=False)

# Count distinct combinations
df2 = df.select(countDistinct("department", "salary"))
df2.show(truncate=False)
print("Distinct Count of Department & Salary:", df2.collect()[0][0])

# Basic stats
print("Total Count:", df.select(count("salary")).collect()[0])
df.select(first("salary")).show(truncate=False)
df.select(last("salary")).show(truncate=False)
df.select(kurtosis("salary")).show(truncate=False)
df.select(max("salary")).show(truncate=False)
df.select(min("salary")).show(truncate=False)
df.select(mean("salary")).show(truncate=False)
df.select(skewness("salary")).show(truncate=False)

# Standard deviation
df.select(stddev("salary"), stddev_samp("salary"), stddev_pop("salary")).show(truncate=False)

# Sum and distinct sum
df.select(sum("salary")).show(truncate=False)
df.select(sumDistinct("salary")).show(truncate=False)

# Variance
df.select(variance("salary"), var_samp("salary"), var_pop("salary")).show(truncate=False)
