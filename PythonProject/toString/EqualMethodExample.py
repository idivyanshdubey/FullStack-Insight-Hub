class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def __eq__(self, other):
        if isinstance(other, Person):
            return self.name == other.name and self.age == other.age
        return False

def main():
    # Create two Person objects
    person1 = Person("Alice", 25)
    person2 = Person("Alice", 25)
    person3 = Person("Bob", 30)

    # Compare objects using ==
    print("person1 equals person2:", person1 == person2)  # True
    print("person1 equals person3:", person1 == person3)  # False

if __name__ == "__main__":
    main()
