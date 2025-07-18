from pyspark.sql import SparkSession
from pyspark.sql.functions import col

# Step 1: Create a SparkSession
spark = SparkSession.builder \
    .appName("Simple PySpark Program") \
    .getOrCreate()

# Step 2: Read a CSV file
# Replace 'sample.csv' with your actual file path
df = spark.read.csv("sample.csv", header=True, inferSchema=True)

# Step 3: Show the first few rows
df.show()

# Step 4: Select a column and perform a transformation
# Let's say the CSV has a column named 'name'
df_transformed = df.select(col("name")).distinct()

# Step 5: Show the transformed data
df_transformed.show()

# Step 6: Stop the SparkSession
spark.stop()
