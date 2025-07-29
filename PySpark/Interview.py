import findspark
import os
import tempfile
findspark.init()

from pyspark.sql import SparkSession,Window
from pyspark.sql.connect.functions import second
from pyspark.sql.functions import sum,col

spark=SparkSession.builder.appName("ReadWrite").getOrCreate()

# data=[("North","2023-01-01","700"), ("North","2024-01-02","400"), ("North","2023-01-03","800"),("South","2023-01-05","300"),("South","2023-01-04","80")]
# columns = ["region", "date", "sales"]

temp_dir= tempfile.mkdtemp()
csv_path= os.path.join(temp_dir,"input.csv")
parquet_path=os.path.join(temp_dir,"output_sales")

csv_data= """sale_id,product,amount
1,laptop,2000
2,mouse,200
3,keyboard,300
4,Monitor,4000
"""

with open(csv_path,"w") as f:
    f.write(csv_data)


df=spark.read.option("header",True).option("inferSchema",True).csv(csv_path)
print("Original DataFrame read from CSV:")
df.show()
df.printSchema()



