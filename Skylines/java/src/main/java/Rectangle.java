public class Rectangle
{
    private int left;
    private int right;
    private int height;

    public Rectangle(String[] specs)
    {
        this.left = Integer.valueOf(specs[0]);
        this.right = Integer.valueOf(specs[2]);
        this.height = Integer.valueOf(specs[1]);
    }

    public int getLeft()
    {
        return left;
    }

    public int getRight()
    {
        return right;
    }

    public int getHeight()
    {
        return height;
    }
}