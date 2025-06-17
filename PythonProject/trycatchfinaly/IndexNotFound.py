def main():
    # Create a list with 3 elements
    fruits = ["Apple", "Banana", "Cherry"]

    print("List size:", len(fruits))

    # Attempt to access an invalid index
    try:
        print("Element at index 5:", fruits[5])  # Invalid index
    except IndexError as e:
        print("Caught an IndexError:", str(e))

if __name__ == "__main__":
    main()
