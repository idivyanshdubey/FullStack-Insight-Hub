import threading

class Counter:
    def __init__(self):
        self.count = 0
        self.lock = threading.Lock()

    def increment(self):
        with self.lock:
            self.count += 1

    def get_count(self):
        return self.count

def main():
    counter = Counter()

    def task():
        for _ in range(1000):
            counter.increment()

    t1 = threading.Thread(target=task)
    t2 = threading.Thread(target=task)

    t1.start()
    t2.start()
    t1.join()
    t2.join()

    print("Final count:", counter.get_count())

if __name__ == "__main__":
    main()
