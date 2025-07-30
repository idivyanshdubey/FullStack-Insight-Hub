# -*- coding: utf-8 -*-

from pyspark.sql import SparkSession
from pyspark.sql.functions import expr, col
from pyspark.sql.types import DoubleType

# Create SparkSession
spark = SparkSession.builder \
          .appName('EmployeeSalaryCastingApp') \
          .getOrCreate()

# Sample data
data = [
    ("Riya", "29", "true", "F", "4200.7589"),
    ("Karan", "31", "true", "M", "3900.8067"),
    ("Neha", "35", "false", "F", "5100.5034")
]

columns = ["firstname", "age", "isCertified", "gender", "salary"]

# Create DataFrame
df = spark.createDataFrame(data=data, schema=columns)
df.printSchema()
df.show(truncate=False)

# Cast salary to double using different methods
df1 = df.withColumn("salary", df.salary.cast(DoubleType()))
df1.printSchema()

# Using selectExpr for casting
df2 = df.selectExpr("firstname", "isCertified", "cast(salary as double) as salary")
df2.printSchema()

# Using SQL for casting
df.createOrReplaceTempView("EmployeeData")
df3 = spark.sql("SELECT firstname, isCertified, DOUBLE(salary) as salary FROM EmployeeData")
df3.printSchema()
df3.show(truncate=False)

# Optional: Cast age and salary together
df.select("firstname", expr("cast(age as int)"), "isCertified", col("salary").cast("float").alias("salary")).show(truncate=False)
