

from pyspark.sql import SparkSession

# Initialize Spark session
session = SparkSession.builder.appName('LanguageMappingApp').getOrCreate()

# Define schema and data
fields = ["full_name", "school_languages", "region"]
records = [("Riya,,Sharma", ["Python", "SQL", "Java"], "TX"),
           ("Karan,Mehta,", ["Spark", "Python", "JavaScript"], "WA"),
           ("Neha,,Verma", ["Go", "Rust"], "OR")]

# Create DataFrame
df_lang = session.createDataFrame(data=records, schema=fields)
df_lang.printSchema()
df_lang.show(truncate=False)

# FlatMap logic using RDD
flattened_rdd = df_lang.rdd.flatMap(lambda row: [(row['full_name'], lang, row['region']) for lang in row['school_languages']])
flattened_df = flattened_rdd.toDF(["full_name", "language", "region"])
flattened_df.show(truncate=False)
