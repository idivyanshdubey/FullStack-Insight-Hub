# -*- coding: utf-8 -*-


from pyspark.sql import SparkSession
from pyspark.sql.functions import col, expr

# Initialize Spark session
spark = SparkSession.builder.appName('DateIncrementApp').getOrCreate()

# Sample data
records = [("2022-03-15", 2), ("2022-07-10", 4), ("2022-11-05", 1)]

# Create DataFrame and apply transformation
spark.createDataFrame(records).toDF("start_date", "months_to_add") \
    .select(
        col("start_date"),
        col("months_to_add"),
        expr("add_months(to_date(start_date,'yyyy-MM-dd'), cast(months_to_add as int))").alias("new_date")
    ).show()
