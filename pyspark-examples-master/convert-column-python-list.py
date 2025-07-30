# -*- coding: utf-8 -*-

from pyspark.sql import SparkSession

# Create Spark session
spark = SparkSession.builder.master("local[1]") \
                    .appName('ExampleApp') \
                    .getOrCreate()

# Sample data
records = [("Alice", "Brown", "UK", "London"),
           ("John", "Doe", "UK", "Manchester"),
           ("Emma", "Wilson", "UK", "London"),
           ("Liam", "Taylor", "UK", "Bristol")]

# Column names
fields = ["first_name", "last_name", "nation", "city"]

# Create DataFrame
dataframe = spark.createDataFrame(data=records, schema=fields)
dataframe.show()
print(dataframe.collect())

# Extract city using RDD map
cities1 = dataframe.rdd.map(lambda row: row[3]).collect()
print(cities1)
# ['London', 'Manchester', 'London', 'Bristol']

# Remove duplicates while preserving order
from collections import OrderedDict
unique_cities = list(OrderedDict.fromkeys(cities1))
print(unique_cities)
# ['London', 'Manchester', 'Bristol']

# Example 2: Access using attribute
cities2 = dataframe.rdd.map(lambda row: row.city).collect()
print(cities2)
# ['London', 'Manchester', 'London', 'Bristol']

# Example 3: Select column
cities3 = dataframe.select(dataframe.city).collect()
print(cities3)
# [Row(city='London'), Row(city='Manchester'), Row(city='London'), Row(city='Bristol')]

# Example 4: FlatMap to extract values
cities4 = dataframe.select(dataframe.city).rdd.flatMap(lambda row: row).collect()
print(cities4)
# ['London', 'Manchester', 'London', 'Bristol']

# Example 5: Convert to Pandas
cities_pd = dataframe.select(dataframe.city).toPandas()['city']
cities_list = list(cities_pd)
print(cities_list)
# ['London', 'Manchester', 'London', 'Bristol']

