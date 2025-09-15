# Define the two lists
list1 = [1, 2, 3, 4, 5, 6]
list2 = [10, 11, 12, 13, 14, 15]

# Filter odd numbers from list1
odd_numbers = [num for num in list1 if num % 2 != 0]

# Filter even numbers from list2
even_numbers = [num for num in list2 if num % 2 == 0]

# Combine the two filtered lists
new_list = odd_numbers + even_numbers

# Print the result
print("New list with odd numbers from list1 and even numbers from list2:", new_list)