import threading

class MyRunnable:
    def run(self):
        print("Runnable thread is running.")

def main():
    runnable = MyRunnable()
    thread = threading.Thread(target=runnable.run)  # Pass method to Thread
    thread.start()  # Start the thread

if __name__ == "__main__":
    main()
