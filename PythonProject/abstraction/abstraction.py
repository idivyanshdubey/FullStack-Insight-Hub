import json
import logging
import sys
import os
from abc import ABC, abstractmethod

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_004"

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

# WrapperExample equivalent
def wrapper_example(numbers):
    logging.info(f"WrapperExample output: {numbers}")

# Abstract base class
class Abstraction(ABC):
    @abstractmethod
    def draw(self):
        pass

# Concrete subclass
class Abstraction2(Abstraction):
    def draw(self):
        logging.info("Drawing a circle.")

# Main function
def main():
    inputs = load_input(SCRIPT_ID)
    numbers = inputs.get("numbers", [])
    wrapper_example(numbers)
    a = Abstraction2()
    a.draw()

if __name__ == "__main__":
    main()
