class RunPoly:
    def sound(self):
        print("Vehicle sound.")

class RunP(RunPoly):
    def sound(self):
        print("Car honks.")

def main():
    vehicle = RunP()
    vehicle.sound()

if __name__ == "__main__":
    main()
