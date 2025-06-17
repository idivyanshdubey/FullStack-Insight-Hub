import json
import logging
import sys
import os
import inspect
from functools import wraps

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_006"

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

# Define a custom decorator to mimic Java's annotation
def my_annotation(value):
    def decorator(func):
        @wraps(func)
        def wrapper(*args, **kwargs):
            return func(*args, **kwargs)
        wrapper._annotation_value = value  # Attach custom attribute
        return wrapper
    return decorator

# Class using the custom annotation
class CustomAnnotationExample:
    @my_annotation("Custom Message")
    def display(self):
        logging.info("Custom Annotation applied.")

def main():
    inputs = load_input(SCRIPT_ID)
    annotation_value = inputs.get("annotation_value", "Custom Message")

    obj = CustomAnnotationExample()
    obj.display()

    # Accessing annotation value using reflection
    method = getattr(obj, 'display')
    annotation_value = getattr(method, '_annotation_value', None)

    if annotation_value:
        logging.info(f"Annotation value: {annotation_value}")

if __name__ == "__main__":
    main()
