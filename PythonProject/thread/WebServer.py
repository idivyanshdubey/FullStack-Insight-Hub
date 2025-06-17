import threading
import time

class RequestHandler(threading.Thread):
    def __init__(self, request_name):
        super().__init__()
        self.request_name = request_name

    def run(self):
        print(f"Processing request: {self.request_name}")
        try:
            time.sleep(2)  # Simulate processing time
        except Exception as e:
            print(f"Error: {e}")
        print(f"Request processed: {self.request_name}")

def main():
    # Simulate multiple user requests
    request1 = RequestHandler("Load Homepage")
    request2 = RequestHandler("Submit Form")
    request3 = RequestHandler("Download File")

    # Start threads to handle requests concurrently
    request1.start()
    request2.start()
    request3.start()

    # Optionally wait for all threads to finish
    request1.join()
    request2.join()
    request3.join()

if __name__ == "__main__":
    main()
