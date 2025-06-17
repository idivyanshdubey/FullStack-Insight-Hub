import threading
import time

class SharedResource:
    def __init__(self):
        self.condition = threading.Condition()

    def produce(self):
        with self.condition:
            print("Producer is producing...")
            self.condition.wait()  # Releases the lock and waits
            print("Producer resumed.")

    def consume(self):
        with self.condition:
            print("Consumer is consuming...")
            self.condition.notify()  # Wakes up the waiting thread

def main():
    resource = SharedResource()

    producer = threading.Thread(target=resource.produce)
    consumer = threading.Thread(target=resource.consume)

    producer.start()
    time.sleep(1)  # Ensure producer gets a chance to wait
    consumer.start()

    producer.join()
    consumer.join()

if __name__ == "__main__":
    main()
