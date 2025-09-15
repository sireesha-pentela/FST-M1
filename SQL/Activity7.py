# Define the list of numbers
#numbers = [10, 20, 30, 40, 50]

# Calculate the sum using the built-in sum() function
#total = sum(numbers)

# Print the result
#print("The sum of all elements in the list is:", total)
	
numbers = input("Enter a sequence of comma separated values: ").split(", ")
 
sum = 0
for number in numbers:
  sum += int(number)
 
print(sum)