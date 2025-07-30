# -*- coding: utf-8 -*-

from pyspark.sql import SparkSession
from pyspark.sql.functions import col
from pyspark.sql.types import StringType, BooleanType, DateType

# Initialize Spark session
spark = SparkSession.builder.appName('EmployeeCastingApp').getOrCreate()

# Sample data
employee_data = [
    ("Riya", 29, "2015-07-01", "true", "F", 4200.75),
    ("Karan", 31, "1988-03-15", "true", "M", 3900.60),
    ("Neha", 35, "15-08-1990", "false", "F", 5100.40)
]

# Column names
columns = ["name", "age", "joiningDate", "isCertified", "gender", "salary"]

# Create DataFrame
df = spark.createDataFrame(data=employee_data, schema=columns)
df.printSchema()
df.show(truncate=False)

# Cast columns to different types
df2 = df.withColumn("age", col("age").cast(StringType())) \
        .withColumn("isCertified", col("isCertified").cast(BooleanType())) \
        .withColumn("joiningDate", col("joiningDate").cast(DateType()))
df2.printSchema()

# Recast using selectExpr
df3 = df2.selectExpr(
    "cast(age as int) as age",
    "cast(isCertified as string) as isCertified",
    "cast(joiningDate as string) as joiningDate"
)
df3.printSchema()
df3.show(truncate=False)

# SQL casting
df3.createOrReplaceTempView("EmployeeCastView")
df4 = spark.sql("""
    SELECT STRING(age), BOOLEAN(isCertified), DATE(joiningDate)
    FROM EmployeeCastView
""")
df4.printSchema()
df4.show(truncate=False)
