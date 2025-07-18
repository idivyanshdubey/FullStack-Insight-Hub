import findspark
findspark.init()

from pyspark.sql import SparkSession
from pyspark.sql.functions import explode, split, trim
# Step 1: Initialize Spark Session
spark = SparkSession.builder \
    .appName("BasicWordCount") \
    .getOrCreate()
# Step 2: Create a DataFrame from a list of strings
data = [("Hello world",), ("Hello Spark",), ("Spark is fast",)]
df = spark.createDataFrame(data, ["text"])
# Step 3: Split lines into words and trim them
words_df = df.select(explode(split(df.text, " ")).alias("word")) \
             .select(trim("word").alias("word"))
# Step 4: Count occurrences of each word
word_counts = words_df.groupBy("word").count()
# Step 5: Show the result
word_counts.show()
# Stop the Spark session
spark.stop()