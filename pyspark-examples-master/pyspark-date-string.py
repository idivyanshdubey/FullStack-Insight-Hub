# -*- coding: utf-8 -*-

from pyspark.sql import SparkSession
from pyspark.sql.functions import current_date, current_timestamp, date_format

# Initialize Spark session
spark = SparkSession.builder.appName('DateFormatDemoApp').getOrCreate()

# Sample data
data = [["A001"]]
df = spark.createDataFrame(data, ["record_id"])

# Format current date and timestamp
df.select(
    current_date().alias("today"),
    date_format(current_date(), "dd-MM-yyyy").alias("formatted_date"),
    date_format(current_timestamp(), "dd/MM/yyyy HH:mm").alias("formatted_timestamp"),
    date_format(current_timestamp(), "yyyy MMM dd").alias("month_day_format"),
    date_format(current_timestamp(), "yyyy MMMM dd E").alias("full_format")
).show(truncate=False)

# SQL version
spark.sql("""
    SELECT 
        current_date() AS today,
        date_format(current_timestamp(), 'dd-MM-yyyy') AS formatted_date,
        date_format(current_timestamp(), 'dd/MM/yyyy HH:mm') AS formatted_timestamp,
        date_format(current_timestamp(), 'yyyy MMM dd') AS month_day_format,
        date_format(current_timestamp(), 'yyyy MMMM dd E') AS full_format
""").show(truncate=False)
