class Animal:
    def sound(self):
        print("Some generic animal sound")

class RunP(Animal):
    def sound(self):
        print("RunP makes a specific sound")

def main():
    p = RunP()
    p.sound()

if __name__ == "__main__":
    main()
