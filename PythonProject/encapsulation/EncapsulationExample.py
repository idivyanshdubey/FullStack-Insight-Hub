# Person class with encapsulation
class Person:
    def __init__(self):
        self.__name = None  # Private attribute using name mangling

    def get_name(self):
        return self.__name

    def set_name(self, name):
        self.__name = name

# Main logic
if __name__ == "__main__":
    person = Person()
    person.set_name("John")
    print("Name:", person.get_name())
