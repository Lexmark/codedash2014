# Python 3
import sys

class Rectangle:
    def __init__(self, specs):
        self.left = int(specs[0])
        self.right = int(specs[2])
        self.height = int(specs[1])

def main():
    for line in sys.stdin:
        rectangles = []
        inputs = line.split()
        for i in inputs:
            i = i[1:-1]
            specs = i.split(",")
            rectangles.append(Rectangle(specs))
        height_map = [0] * (max(map(lambda r: r.right, rectangles)) + 1)
        for rect in rectangles:
            for i in range(rect.left, rect.right):
                if height_map[i] < rect.height:
                    height_map[i] = rect.height
        moves = []
        last = 0
        for i in range(0, len(height_map)):
            if height_map[i] != last:
                moves.append(i)
                moves.append(height_map[i])
            last = height_map[i]
        print(",".join(map(lambda x: str(x), moves)))


if __name__ == '__main__':
    main()
