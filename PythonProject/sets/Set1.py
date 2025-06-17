def main():
    # Creating a set
    fruit_set = {"Apple", "Banana", "Cherry", "Apple"}  # Duplicate "Apple" will be ignored

    print("Set:", fruit_set)
    print("Contains 'Banana'?", "Banana" in fruit_set)

    # Removing an element
    fruit_set.discard("Cherry")
    print("Updated Set:", fruit_set)

    # Iterating through the set
    for item in fruit_set:
        print("Printing with iterator:", item)
        if item == "Apple":
            print("equals")

if __name__ == "__main__":
    main()
