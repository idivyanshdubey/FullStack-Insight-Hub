import argparse


class ArithmeticRelationalLogicalBitwise:
    def __init__(self, a, b, x, y):
        self.a = a
        self.b = b
        self.x = x
        self.y = y

    def show_arithmetic_operators(self):
        try:
            print(f"Addition: {self.a + self.b}")
            print(f"Subtraction: {self.a - self.b}")
            print(f"Multiplication: {self.a * self.b}")
            print(f"Division: {self.a / self.b if self.b != 0 else 'Undefined (division by zero)'}")
            print(f"Modulus: {self.a % self.b if self.b != 0 else 'Undefined (modulus by zero)'}")
        except Exception as e:
            print(f"Error in arithmetic operations: {e}")

    def show_relational_operators(self):
        try:
            print(f"a == b: {self.a == self.b}")
            print(f"a != b: {self.a != self.b}")
            print(f"a > b: {self.a > self.b}")
            print(f"a < b: {self.a < self.b}")
            print(f"a >= b: {self.a >= self.b}")
            print(f"a <= b: {self.a <= self.b}")
        except Exception as e:
            print(f"Error in relational operations: {e}")

    def show_logical_operators(self):
        try:
            print(f"x and y: {self.x and self.y}")
            print(f"x or y: {self.x or self.y}")
            print(f"not x: {not self.x}")
        except Exception as e:
            print(f"Error in logical operations: {e}")

    def show_bitwise_operators(self):
        try:
            print(f"a & b: {self.a & self.b}")
            print(f"a | b: {self.a | self.b}")
            print(f"a ^ b: {self.a ^ self.b}")
            print(f"~a: {~self.a}")
            print(f"a << 1: {self.a << 1}")
            print(f"a >> 1: {self.a >> 1}")
        except Exception as e:
            print(f"Error in bitwise operations: {e}")


def main():
    parser = argparse.ArgumentParser(description="Demonstrate arithmetic, relational, logical, and bitwise operations.")
    parser.add_argument('-a', type=int, default=10, help="First number for arithmetic and bitwise operations")
    parser.add_argument('-b', type=int, default=5, help="Second number for arithmetic and bitwise operations")
    parser.add_argument('-x', type=lambda x: x.lower() == 'true', default=True,
                        help="First boolean value for logical operations (true/false)")
    parser.add_argument('-y', type=lambda y: y.lower() == 'true', default=False,
                        help="Second boolean value for logical operations (true/false)")
    parser.add_argument('--skip-arithmetic', action='store_true', help="Skip arithmetic operations")
    parser.add_argument('--skip-relational', action='store_true', help="Skip relational operations")
    parser.add_argument('--skip-logical', action='store_true', help="Skip logical operations")
    parser.add_argument('--skip-bitwise', action='store_true', help="Skip bitwise operations")

    args = parser.parse_args()

    demo = ArithmeticRelationalLogicalBitwise(args.a, args.b, args.x, args.y)

    if not args.skip_arithmetic:
        demo.show_arithmetic_operators()
    if not args.skip_relational:
        demo.show_relational_operators()
    if not args.skip_logical:
        demo.show_logical_operators()
    if not args.skip_bitwise:
        demo.show_bitwise_operators()


if __name__ == "__main__":
    try:
        main()
    except Exception as e:
        print(f"An unexpected error occurred: {e}")