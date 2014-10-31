import java.util.*;
import java.util.stream.Collectors;

public class Skylines
{
    public static void main(String... args)
    {
        Scanner stdin = new Scanner(System.in);
        String line;
        while (stdin.hasNextLine() && (line = stdin.nextLine()) != null)
        {
            String[] inputs = line.split(" ");
            List<Rectangle> rectangles = Arrays.asList(inputs).stream().map(input -> {
                String[] points = input.split(",");
                points[0] = points[0].substring(1);
                points[2] = points[2].substring(0, points[2].length() - 1);
                return new Rectangle(points);
            }).collect(Collectors.toList());

            List<Integer> heightMap = new ArrayList<>(Collections.nCopies(rectangles.stream().map(Rectangle::getRight).max(Comparator.<Integer>naturalOrder()).get() + 1, 0));
            for (Rectangle rect : rectangles)
            {
                for (int i = rect.getLeft(); i < rect.getRight(); i++)
                {
                    if (heightMap.get(i) < rect.getHeight())
                    {
                        heightMap.set(i, rect.getHeight());
                    }
                }
            }

            List<String> moves = new ArrayList<>();
            Integer last = 0;
            int heightMapSize = heightMap.size();
            for (int i = 0; i < heightMapSize; i++)
            {
                int height = heightMap.get(i);
                if (height != last)
                {
                    moves.add(String.valueOf(i));
                    moves.add(String.valueOf(height));
                }
                last = height;
            }
            System.out.println(String.join(",", moves));
        }
    }
}