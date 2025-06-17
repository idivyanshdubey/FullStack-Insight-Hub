# Custom exception class
class CustomException(Exception):
    def __init__(self, message):
        super().__init__(message)

# Function that may raise the custom exception
def check_age(age):
    if age < 18:
        raise CustomException("Age must be 18 or above.")

# Main logic
def main():
    try:
        check_age(15)  # This will raise CustomException
    except CustomException as e:
        print("Caught exception:", str(e))

if __name__ == "__main__":
    main()
