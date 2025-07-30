# -*- coding: utf-8 -*-


from pyspark.sql import SparkSession
from pyspark.sql.functions import expr, col, when
from pyspark.sql.types import StructType, StructField, StringType, ArrayType, MapType

# Initialize Spark session
spark = SparkSession.builder.appName('EmployeeInfoApp').getOrCreate()

# Sample data
records = [("Rohan", "Shah", "101", None),
           ("Meera", "Joshi", "202", "F"),
           ("Amit Kumar", "XXX", "303", ""),
           ("Neel Patel", None, "303", "M")]

columns = ["first_name", "last_name", "emp_id", "gender"]
df = spark.createDataFrame(records, columns)

# Alias and expression
df.select(df.first_name.alias("fname"),
          df.last_name.alias("lname"),
          expr("first_name || ' ' || last_name").alias("full_name")).show()

# Sorting
df.sort(df.first_name.asc()).show()
df.sort(df.first_name.desc()).show()

# Casting
df.select(df.first_name, df.emp_id.cast("int")).printSchema()

# Between
df.filter(df.emp_id.between(100, 250)).show()

# Contains
df.filter(df.first_name.contains("Kumar")).show()

# Startswith / Endswith
df.filter(df.first_name.startswith("N")).show()
df.filter(df.first_name.endswith("Kumar")).show()

# isNull / isNotNull
df.filter(df.last_name.isNull()).show()
df.filter(df.last_name.isNotNull()).show()

# Like
df.select(df.first_name, df.last_name, df.emp_id).filter(df.first_name.like("%it")).show()

# Substring
df.select(df.first_name.substr(1, 3).alias("initials")).show()

# When / Otherwise
df.select(df.first_name, df.last_name,
          when(df.gender == "M", "Male")
          .when(df.gender == "F", "Female")
          .when(df.gender == None, "")
          .otherwise(df.gender).alias("gender_label")).show()

# isin
id_list = ["101", "202"]
df.select("first_name", "last_name", "emp_id").filter(df.emp_id.isin(id_list)).show()

# Complex types
nested_data = [
    (("Rohan", "Shah"), ["Python", "Java"], {"hair": "black", "eye": "brown"}),
    (("Meera", "Joshi"), [".NET", "SQL"], {"hair": "brown", "eye": "black"}),
    (("Amit Kumar", ""), ["Scala", "Go"], {"hair": "red", "eye": "grey"}),
    (("Neel Patel", None), ["Ruby", "Perl"], {"hair": "black", "eye": "blue"})
]

schema = StructType([
    StructField("full_name", StructType([
        StructField("first", StringType(), True),
        StructField("last", StringType(), True)
    ])),
    StructField("skills", ArrayType(StringType()), True),
    StructField("traits", MapType(StringType(), StringType()), True)
])

df_nested = spark.createDataFrame(nested_data, schema)
df_nested.printSchema()

# Access array and map elements
df_nested.select(df_nested.skills.getItem(1)).show()
df_nested.select(df_nested.traits.getItem("hair")).show()
df_nested.select(df_nested.traits.getField("hair")).show()
df_nested.select(df_nested.full_name.getField("first")).show()
