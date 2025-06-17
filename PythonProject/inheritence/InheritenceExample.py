# Parent class
class Vehicle:
    def __init__(self):
        self.speed = 50

    def get_speed(self):
        return self.speed

# Child class
class Car1(Vehicle):
    def __init__(self):
        super().__init__()
        self.speed = 100

    def display(self):
        print("Parent Speed:", super().get_speed())  # Access via method
        print("Child Speed:", self.speed)

# Main logic
def main():
    c = Car1()
    c.display()

if __name__ == "__main__":
    print("=== Car1 Inheritance Example ===")
    main()
