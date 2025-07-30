# -*- coding: utf-8 -*-

from pyspark.sql import SparkSession, Row
from pyspark.sql.types import StructType, StructField, StringType

# Initialize Spark session
spark = SparkSession.builder.appName('TeamInfoApp').getOrCreate()

# Using List
teams = [("Human Resources", 101),
         ("Engineering", 102),
         ("Operations", 103),
         ("Support", 104)]

team_columns = ["team_name", "team_id"]
team_df = spark.createDataFrame(data=teams, schema=team_columns)
team_df.printSchema()
team_df.show(truncate=False)

# Using mismatched schema for demonstration
employee_schema = StructType([
    StructField('first_name', StringType(), True),
    StructField('middle_name', StringType(), True),
    StructField('last_name', StringType(), True)
])

employee_df = spark.createDataFrame(data=teams, schema=employee_schema)
employee_df.printSchema()
employee_df.show(truncate=False)

# Using list of Row type
teams_row = [Row("Human Resources", 101),
             Row("Engineering", 102),
             Row("Operations", 103),
             Row("Support", 104)]

team_df2 = spark.createDataFrame(data=teams_row, schema=team_columns)
team_df2.printSchema()
team_df2.show(truncate=False)

# Convert list to RDD
rdd = spark.sparkContext.parallelize(teams)
