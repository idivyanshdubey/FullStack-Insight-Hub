def main():
    try:
        result = 10 / 0  # This will raise a ZeroDivisionError
    except ZeroDivisionError:
        print("Exception caught: Division by zero.")
    finally:
        print("Finally block executed.")

if __name__ == "__main__":
    main()
