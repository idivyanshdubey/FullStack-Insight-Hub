class Polymorphism:
    def add(self, *args):
        # Simulate method overloading by summing all arguments
        return sum(args)

def main():
    ph = Polymorphism()
    result = ph.add(12, 12)          # Equivalent to add(int, int)
    result1 = ph.add(12, 12, 12)     # Equivalent to add(int, int, int)

    print("result", result)
    print("result1", result1)

if __name__ == "__main__":
    main()
