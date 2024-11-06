def fractional_knapsack():
    
    weights = list(map(int, input("Enter the weights of items separated by spaces: ").split()))
    values = list(map(int, input("Enter the values of items separated by spaces: ").split()))
    
    
    #if len(weights) != len(values):
       # print("The number of weights and values must be the same.")
       # return

    
    capacity = int(input("Enter the knapsack capacity: "))
    
    
    res = 0

    #Sort items based on value-to-weight ratio in descending order
    for pair in sorted(zip(weights, values), key=lambda x: x[1] / x[0], reverse=True):
        if capacity <= 0:  # If capacity is full, break out of the loop
            break

         #If the item's weight is more than the remaining capacity, take a fraction
        if pair[0] > capacity:
            res += capacity * (pair[1] / pair[0]) 
            capacity = 0  # Set capacity to 0 as knapsack is full

        # If the item's weight fits in the remaining capacity, take the whole item
        elif pair[0] <= capacity:
            res += pair[1]  
            capacity -= pair[0] 
    # Print the maximum value obtained
    print("The maximum value in the knapsack is:", res)

if __name__ == "__main__":
    fractional_knapsack()
