examples = gets().to_i

def string_array_to_int_array(input)
  input.split(',').map { |n| n.to_i }
end

def bubble_sort_one_step(input)
  numbers = string_array_to_int_array(input)
  i = 0
  while i < numbers.count - 1 do
    if numbers[i] > numbers[i + 1]
      current = numbers[i]
      numbers[i] = numbers[i + 1]
      numbers[i + 1] = current
    end
    i = i + 1
  end

  numbers.join(',')
end

def comb_sort_one_step(input, gap)
  numbers = string_array_to_int_array(input)
  i = 0
  while i + gap < numbers.count do
    if numbers[i] > numbers[i + gap]
      current = numbers[i]
      numbers[i] = numbers[i + gap]
      numbers[i + gap] = current
    end
    i = i + 1
  end

  numbers.join(',')
end

def odd_even_sort_one_step(input)
  numbers = string_array_to_int_array(input)
  i = 1
  while i < numbers.count - 1 do
    if numbers[i] > numbers[i + 1]
      current = numbers[i]
      numbers[i] = numbers[i + 1]
      numbers[i + 1] = current
    end
    i = i + 2
  end

  i = 0
  while i < numbers.count - 1 do
    if numbers[i] > numbers[i + 1]
      current = numbers[i]
      numbers[i] = numbers[i + 1]
      numbers[i + 1] = current
    end
    i = i + 2
  end

  numbers.join(',')
end

i = 0
while i < examples do
  lines = []
  input = gets()
  until input.nil? || input == '' || input == "\n" do
    lines << input.strip
    input = gets()
  end

  if bubble_sort_one_step(lines[0]) == lines[1]
    puts "BUBBLE"
  end

  gap = string_array_to_int_array(lines[0]).count
  gap = gap / 1.3
  if comb_sort_one_step(lines[0], gap.floor) == lines[1]
    puts "COMB"
  end

  if odd_even_sort_one_step(lines[0]) == lines[1]
    puts "ODDEVEN"
  end

  i = i + 1
end