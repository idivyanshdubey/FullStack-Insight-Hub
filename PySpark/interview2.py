import findspark
import os
import tempfile
findspark.init()

from pyspark.sql import SparkSession,Window
from pyspark.sql.connect.functions import second
from pyspark.sql.functions import sum,col,udf,broadcast
from pyspark.sql.types import StringType

spark=SparkSession.builder.appName("Interview").getOrCreate()

# data=[("North","2023-01-01","700"), ("North","2024-01-02","400"), ("North","2023-01-03","800"),("South","2023-01-05","300"),("South","2023-01-04","80")]
# columns = ["region", "date", "sales"]

data_employees = [
    (1, "Alice Johnson", "2020-05-21", 85000.0, 101),
    (2, "Bob Smith", "2019-03-15", 65000.0, 102),
    (3, "Charlie Lee", "2021-07-10", 90000.0, 103),
    (4, "Diana Prince", "2022-01-05", 87000.0, 101),
    (5, "Ethan Hunt", "2018-11-30", 72000.0, 104)
]
data_products= [(101,"Electronics"),(2,"Furniture"),(3,"Clothing")]

df = spark.createDataFrame(data_employees,["id","name","joining_date","salary","department_id"])
df_products = spark.createDataFrame((data_products),["department_id","category"])

def mask_name(name):
    if len(name) <=2:
        return "*" * len(name)
    return name[0] + "*" * (len(name) -2) + name[-1]

mask_name_udf = udf(mask_name,StringType())

df_with_masked = df.withColumn("masked_name", mask_name_udf(col("name")))
df_with_masked.select("name","masked_name").show()

df_joined = df.join(broadcast(df_products), on = "department_id", how="inner")
df_joined.select("name","salary","category").show()



