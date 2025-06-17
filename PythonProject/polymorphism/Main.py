class Polymorphism:
    def add(self, *args):
        return sum(args)

def main():
    ph = Polymorphism()
    result = ph.add(12, 12)
    result1 = ph.add(12, 12, 12)
    print("result", result)
    print("result1", result1)

if __name__ == "__main__":
    main()
