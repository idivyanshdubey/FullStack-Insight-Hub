class Variables:
    shared_var = 10  # Class variable (shared across all instances)

    def __init__(self):
        self.instance_var = 20  # Instance variable
        self.__instance = 30    # Private instance variable

    def display(self):
        local_var = 30
        Variables.shared_var = 1
        print(local_var)

    def get_instance(self):
        return self.__instance


# Equivalent of the Java 'main' method
if __name__ == "__main__":
    v = Variables()
    v.display()
    print(Variables.shared_var)
    print(v.instance_var)
    print(v.get_instance())
