class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age

    def __str__(self):
        return f"Person{{name='{self.name}', age={self.age}}}"

def main():
    person = Person("Alice", 25)
    print(person)  # Calls __str__() implicitly

if __name__ == "__main__":
    main()
