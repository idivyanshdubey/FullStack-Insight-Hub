import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_034"

# Locate the input file in the parent directory under 'inputs/inputs.json'
INPUT_FILE = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), 'inputs', 'inputs.json')

def load_input(script_id):
    try:
        with open(INPUT_FILE, 'r') as f:
            data = json.load(f)
        return data.get(script_id, {})
    except Exception as e:
        logging.error(f"Failed to load input: {e}")
        sys.exit(1)

# Base class
class Shape:
    def draw(self):
        pass

# Derived classes
class Circle(Shape):
    def draw(self):
        logging.info("Drawing a Circle")
        print("Drawing a Circle")

class Rectangle(Shape):
    def draw(self):
        logging.info("Drawing a Rectangle")
        print("Drawing a Rectangle")

def main():
    inputs = load_input(SCRIPT_ID)
    shapes = inputs.get("shapes", [])

    shape_objects = []
    for shape_name in shapes:
        if shape_name.lower() == "circle":
            shape_objects.append(Circle())
        elif shape_name.lower() == "rectangle":
            shape_objects.append(Rectangle())
        else:
            logging.warning(f"Unknown shape: {shape_name}")

    for shape in shape_objects:
        shape.draw()

if __name__ == "__main__":
    main()
