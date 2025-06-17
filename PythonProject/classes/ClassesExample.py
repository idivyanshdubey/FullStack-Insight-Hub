# Define the Opps class
class Opps:
    def __init__(self):
        self.color = None  # Initialize color attribute

    def display(self):
        print(f"The car color is: {self.color}")

# Main logic
if __name__ == "__main__":
    my_car = Opps()       # Create an object
    my_car.color = "Red"  # Set property
    my_car.display()      # Call method
