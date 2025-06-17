from enum import Enum

# Define the Day enum
class Day(Enum):
    MONDAY = 1
    TUESDAY = 2
    WEDNESDAY = 3
    THURSDAY = 4
    FRIDAY = 5
    SATURDAY = 6
    SUNDAY = 7

# Main logic
if __name__ == "__main__":
    today = Day.FRIDAY
    print("Today is:", today.name)
