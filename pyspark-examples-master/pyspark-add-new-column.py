# -*- coding: utf-8 -*-

from pyspark.sql import SparkSession
from pyspark.sql.functions import col, lit, concat_ws, current_date, when

# Initialize Spark session
spark = SparkSession.builder \
                    .appName('EmployeeDataApp') \
                    .getOrCreate()

# Sample data
records = [('Riya', 'Sharma', 'F', 3500),
           ('Karan', 'Mehta', 'M', 4800),
           ('Neha', 'Verma', 'F', 6100)]

# Column names
columns = ["first_name", "last_name", "gender", "salary"]

# Create DataFrame
df = spark.createDataFrame(data=records, schema=columns)
df.show()

# Check for non-existent column
if 'salary_bonus' not in df.columns:
    print("Column 'salary_bonus' does not exist.")

# Add constant column
df.withColumn("bonus_rate", lit(0.25)).show()

# Add column from existing column
df.withColumn("bonus_amount", df.salary * 0.25).show()

# Concatenate columns
df.withColumn("full_name", concat_ws(" ", "first_name", "last_name")).show()

# Add current date
df.withColumn("record_date", current_date()).show()

# Add conditional column
df.withColumn("grade",
    when(df.salary < 4000, lit("A"))
    .when((df.salary >= 4000) & (df.salary <= 5000), lit("B"))
    .otherwise(lit("C"))
).show()

# Add columns using select
df.select("first_name", "salary", lit(0.25).alias("bonus")).show()
df.select("first_name", "salary", (df.salary * 0.25).alias("bonus_amount")).show()
df.select("first_name", "salary", current_date().alias("record_date")).show()

# Add columns using SQL
df.createOrReplaceTempView("EMPLOYEES")
spark.sql("SELECT first_name, salary, '0.25' AS bonus FROM EMPLOYEES").show()
spark.sql("SELECT first_name, salary, salary * 0.25 AS bonus_amount FROM EMPLOYEES").show()
spark.sql("SELECT first_name, salary, current_date() AS record_date FROM EMPLOYEES").show()
spark.sql("""
    SELECT first_name, salary,
    CASE
        WHEN salary < 4000 THEN 'A'
        WHEN salary BETWEEN 4000 AND 5000 THEN 'B'
        ELSE 'C'
    END AS grade
    FROM EMPLOYEES
""").show()
