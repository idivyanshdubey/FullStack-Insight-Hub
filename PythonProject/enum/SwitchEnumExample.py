from enum import Enum

# Define the Day1 enum
class Day1(Enum):
    MONDAY = 1
    TUESDAY = 2
    WEDNESDAY = 3
    THURSDAY = 4
    FRIDAY = 5
    SATURDAY = 6
    SUNDAY = 7

# Main logic using match-case (Python 3.10+)
if __name__ == "__main__":
    today = Day1.WEDNESDAY

    match today:
        case Day1.MONDAY:
            print("Start of the work week!")
        case Day1.FRIDAY:
            print("Weekend is coming!")
        case Day1.SUNDAY:
            print("Rest day!")
        case _:
            print("A regular day.")
