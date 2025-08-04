import findspark
findspark.init()

from pyspark.sql import SparkSession
from pyspark.sql.functions import col, add_months, current_date, count

spark = SparkSession.builder.appName("HandleNulls").getOrCreate()

data = [
    (101, "Bob", "2020-01-15", 75000, 201),
    (102, "Bob", "2019-03-22", 68000, 202),
    (103, "Charlie", None, 72000, None),
    (104, "David", "2021-07-01", None, 201),
    (105, "Eva", "2022-11-30", 80000, 203),
    (106, "Frank", "2020-05-18", 69000, 202),
    (107, None, "2023-02-10", 71000, 203),
    (108, "Grace", "2018-09-25", None, None),
    (109, "Hank", None, 67000, 201),
    (110, "Ivy", "2025-06-05", 73000, 202)
]

schema = ["id", "name", "joining_date", "salary", "manager_id"]
df = spark.createDataFrame(data, schema=schema)
df.show()


df_final = df.where("salary > 50000")
df_final.show()

print(df_final.rdd.getNumPartitions())

df_final.write.format("csv").save("data/output.csv")

# recent_employees = df.filter(col("joining_date") >= add_months(current_date(), -3))
#
# recent_employees.select("name", "joining_date").show()
#
# duplicate_names = df.groupBy("name").agg(count("*").alias("occurrences")).filter("occurrences > 1")
#
# duplicate_names.show()

#
# emp_mgr = df.alias("e").join(df.alias("m"), col("e.manager_id") == col("m.id"), "left") \
#             .select(col("e.name").alias("employee_name"), col("m.name").alias("manager_name"))
#
# emp_mgr.show()
#
#
