# -*- coding: utf-8 -*-

from pyspark.sql import SparkSession, Row
from pyspark.sql.functions import col

# Initialize Spark session
spark = SparkSession.builder.appName('UserDataApp').getOrCreate()

# Sample data with dot in column name
records = [("Rohan", 28), ("Meera", 35)]
df = spark.createDataFrame(records).toDF("user.name", "age")
df.printSchema()
df.show()

# Access column with dot using backticks
df.select(col("`user.name`")).show()
df.select(df["`user.name`"]).show()
df.withColumn("initials", col("`user.name`").substr(1, 2)).show()
df.filter(col("`user.name`").startswith("R")).show()

# Rename columns to remove dot
new_columns = (column.replace('.', '_') for column in df.columns)
df2 = df.toDF(*new_columns)
df2.show()

# Accessing columns normally
df.select(df.age).show()
df.select(df["age"]).show()
df.select(col("age")).show()
df.select(col("`user.name`")).show()

# Struct column example
nested_data = [
    Row(user="Rohan", details=Row(hair="black", eye="brown")),
    Row(user="Meera", details=Row(hair="blonde", eye="blue"))
]
df_nested = spark.createDataFrame(nested_data)
df_nested.printSchema()

df_nested.select(df_nested.details.hair).show()
df_nested.select(df_nested["details.hair"]).show()
df_nested.select(col("details.hair")).show()
df_nested.select(col("details.*")).show()

# Column operations
math_data = [(150, 3, 2), (250, 5, 5), (350, 6, 4)]
df_math = spark.createDataFrame(math_data).toDF("num1", "num2", "num3")

df_math.select(df_math.num1 + df_math.num2).show()
df_math.select(df_math.num1 - df_math.num2).show()
df_math.select(df_math.num1 * df_math.num2).show()
df_math.select(df_math.num1 / df_math.num2).show()
df_math.select(df_math.num1 % df_math.num2).show()

df_math.select(df_math.num2 > df_math.num3).show()
df_math.select(df_math.num2 < df_math.num3).show()
df_math.select(df_math.num2 == df_math.num3).show()
