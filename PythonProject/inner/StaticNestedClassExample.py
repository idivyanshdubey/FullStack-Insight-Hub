# Outer class
class OuterClass1:
    message = "Hello from Static Nested Class"  # Static-like class variable

    # Static nested class
    class StaticNestedClass:
        def display(self):
            print(OuterClass1.message)  # Access outer class static field

# Main logic
if __name__ == "__main__":
    nested = OuterClass1.StaticNestedClass()  # No need to instantiate OuterClass1
    nested.display()
