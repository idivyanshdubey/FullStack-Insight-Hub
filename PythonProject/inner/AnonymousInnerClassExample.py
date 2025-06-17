import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_029"

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

# Define the interface-like structure
class Greeting:
    def say_hello(self):
        raise NotImplementedError("Subclasses should implement this!")

def main():
    inputs = load_input(SCRIPT_ID)
    message = inputs.get("message", "Hello from Anonymous Inner Class")

    # Create an anonymous-like class instance using a subclass
    greeting = type('AnonymousGreeting', (Greeting,), {
        'say_hello': lambda self: print(message)
    })()

    logging.info("Calling say_hello on anonymous class instance.")
    greeting.say_hello()

if __name__ == "__main__":
    main()
