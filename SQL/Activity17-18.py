import pandas as pd
import os
# Create the dataset
data = {
  "Usernames": ["sri", "pandu", "maa"],
  "Passwords": ["password", "Charl13", "AllMight"]
}

# Convert the dataset into a dataframe
df = pd.DataFrame(data)
#here pd is nothing but pandas

# write dataframe into CSV file
df.to_csv("./activities/sample.csv", index=False)
# . represents current folder and .. represents Parent folder
# for reading CSV file
input_data = pd.read_csv("./activities/sample.csv")
# to print only usernames column
print(input_data["Usernames"])
# print username and password of second row
print(input_data["Usernames"][1], input_data["Passwords"][1])

# sort the user names colums data in ascending order
print(input_data.sort_values("Usernames"))
print(input_data.sort_values('Passwords', ascending=False))

