# -*- coding: utf-8 -*-

from pyspark.sql import SparkSession
from pyspark.sql.functions import current_date, current_timestamp, date_format, to_timestamp

# Initialize Spark session
spark = SparkSession.builder.appName('DateTimeDemoApp').getOrCreate()

# Sample data
data = [["A001"]]
df = spark.createDataFrame(data, ["record_id"])

# Add current date and timestamp
df.withColumn("today", current_date()) \
  .withColumn("now", current_timestamp()) \
  .show(truncate=False)

# SQL version
spark.sql("SELECT current_date() AS today, current_timestamp() AS now").show(truncate=False)

# Format date and timestamp
df.withColumn("formatted_date", date_format(current_date(), "dd-MM-yyyy")) \
  .withColumn("formatted_timestamp", to_timestamp(current_timestamp(), "dd-MM-yyyy HH:mm:ss")) \
  .show(truncate=False)

# SQL version with formatting
spark.sql("""
    SELECT 
        date_format(current_date(), 'dd-MM-yyyy') AS formatted_date,
        to_timestamp(current_timestamp(), 'dd-MM-yyyy HH:mm:ss') AS formatted_timestamp
""").show(truncate=False)
