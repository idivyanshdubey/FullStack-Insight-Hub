import threading

class MyThread(threading.Thread):
    def run(self):
        print("Thread is in running state.")

def main():
    thread = MyThread()  # New state
    thread.start()       # Moves to Runnable and Running
    thread.join()        # Waits for thread to finish
    print("Thread has terminated.")

if __name__ == "__main__":
    main()
