def main():
    # Create a set and add elements
    tree_set = {"Banana", "Apple", "Cherry"}  # Python sets do not allow null (None) in sorting either

    # Sort the set to mimic TreeSet behavior
    sorted_set = sorted(tree_set)

    print("TreeSet (sorted):", sorted_set)

    # Access first and last elements
    print("First element:", sorted_set[0])
    print("Last element:", sorted_set[-1])

if __name__ == "__main__":
    main()
