import threading

class MyThread(threading.Thread):
    def run(self):
        print("Thread is running.")

def main():
    thread = MyThread()  # Create a thread
    thread.start()       # Start the thread; it will call run() internally

if __name__ == "__main__":
    main()
