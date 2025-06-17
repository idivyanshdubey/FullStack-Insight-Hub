class PrivateCons:
    _instance = None

    def __new__(cls, value):
        if cls._instance is None:
            cls._instance = super(PrivateCons, cls).__new__(cls)
            cls._instance.value = value
            print(f"Creating new instance with value: {value}")
        else:
            print(f"Using existing instance with value: {cls._instance.value}")
        return cls._instance

def main():
    p1 = PrivateCons(10)
    p2 = PrivateCons(20)  # Will not create a new instance

if __name__ == "__main__":
    main()
