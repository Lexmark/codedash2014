# Ruby 2
class Rectangle
    attr_accessor :left, :right, :height

    def initialize(specs)
        @left = specs[0].to_i
        @right = specs[2].to_i
        @height = specs[1].to_i
    end
end

if __FILE__ == $PROGRAM_NAME
    lines = STDIN.read
    lines = lines.split(/\r?\n/)
    for line in lines do
        inputs = line.chomp.split(' ')
        rectangles = Array.new
        for input in inputs do
            input = input[1..-2]
            specs = input.split(',')
            rectangles.push(Rectangle.new(specs))
        end
        height_map = Array.new((rectangles.map { |r| r.right }).max + 1, 0)
        for rect in rectangles do
            for i in (rect.left .. (rect.right - 1)).to_a do
                if height_map[i] < rect.height
                    height_map[i] = rect.height
                end
            end
        end
        moves = Array.new
        last = 0
        for i in (0..(height_map.size - 1))
            if height_map[i] != last
                moves.push(i)
                moves.push(height_map[i])
            end
            last = height_map[i]
        end
        puts (moves.map { |m| m.to_s }).join(",")
    end
end