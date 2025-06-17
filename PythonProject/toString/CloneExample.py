import copy

class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def clone(self):
        return copy.copy(self)

def main():
    # Create an original object
    original = Person("Alice", 25)

    # Create a clone of the original object
    cloned = original.clone()

    # Display original and cloned object details
    print(f"Original: {original.name}, {original.age}")
    print(f"Cloned: {cloned.name}, {cloned.age}")

if __name__ == "__main__":
    main()
