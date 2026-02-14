import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D.Float;

/**
 *This utility class implements visual interface for wires between components.
 * <p> It has been adopted from Stanislav Lapitsky's JConnector Project that 
 * can be found on this URL: http://java-sl.com/connector.html </p>
 * @version 7.0
 */

public class ConnectLine 
{   
    public ConnectLine(Point p1, Point p2) 
    {
        this(p1, p2, LINE_TYPE_RECT_2BREAK, LINE_START_HORIZONTAL, LINE_ARROW_NONE, Color.BLACK);
    }

    public ConnectLine(Point p1, Point p2, int lineType, int lineStart, int lineArrow) 
    {
        this(p1, p2, lineType, lineStart, lineArrow, Color.BLACK);
    }
    
    public ConnectLine(Point p1, Point p2, int lineType, int lineStart, int lineArrow, Color c) 
    {
        this.p1 = p1;
        this.p2 = p2;
        this.lineType = lineType;
        this.lineStart = lineStart;
        this.lineArrow = lineArrow;
        this.color = c;
    }

    public void paint(Graphics2D g2d) 
    {
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(this.color);
        switch(lineType) 
        {
            case LINE_TYPE_SIMPLE:
                paintSimple(g2d);
                break;
            case LINE_TYPE_RECT_1BREAK:
                paint1Break(g2d);
                break;
            case LINE_TYPE_RECT_2BREAK:
                paint2Breaks(g2d);
                break;
        }
    }

    protected void paintSimple(Graphics2D g2d) 
    {
        g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        switch(lineArrow) 
        {
            case LINE_ARROW_DEST:
                paintArrow(g2d, p1, p2);
                break;
            case LINE_ARROW_SOURCE:
                paintArrow(g2d, p2, p1);
                break;
            case LINE_ARROW_BOTH:
                paintArrow(g2d, p1, p2);
                paintArrow(g2d, p2, p1);
                break;
        }
    }

    protected void paintArrow(Graphics2D g2d, Point p1, Point p2) 
    {
        paintArrow(g2d, p1, p2, getRestrictedArrowWidth(p1, p2));
    }

    protected void paintArrow(Graphics2D g2d, Point p1, Point p2, int width) 
    {
        Float pp1 = new Float(p1.x, p1.y);
        Float pp2 = new Float(p2.x, p2.y);
        Float left = getLeftArrowPoint(pp1, pp2, width);
        Float right = getRightArrowPoint(pp1, pp2, width);

        g2d.drawLine(p2.x, p2.y, Math.round(left.x), Math.round(left.y));
        g2d.drawLine(p2.x, p2.y, Math.round(right.x), Math.round(right.y));
    }

    protected void paint1Break(Graphics2D g2d) 
    {
        if(lineStart == LINE_START_HORIZONTAL) 
        {
            g2d.drawLine(p1.x, p1.y, p2.x, p1.y);
            g2d.drawLine(p2.x, p1.y, p2.x, p2.y);
            switch(lineArrow) 
            {
                case LINE_ARROW_DEST:
                    paintArrow(g2d, new Point(p2.x, p1.y), p2);
                    break;
                case LINE_ARROW_SOURCE:
                    paintArrow(g2d, new Point(p2.x, p1.y), p1);
                    break;
                case LINE_ARROW_BOTH:
                    paintArrow(g2d, new Point(p2.x, p1.y), p2);
                    paintArrow(g2d, new Point(p2.x, p1.y), p1);
                    break;
            }
        }
        else if(lineStart == LINE_START_VERTICAL) 
        {
            g2d.drawLine(p1.x, p1.y, p1.x, p2.y);
            g2d.drawLine(p1.x, p2.y, p2.x, p2.y);
            switch(lineArrow) 
            {
                case LINE_ARROW_DEST:
                    paintArrow(g2d, new Point(p1.x, p2.y), p2);
                    break;
                case LINE_ARROW_SOURCE:
                    paintArrow(g2d, new Point(p1.x, p2.y), p1);
                    break;
                case LINE_ARROW_BOTH:
                    paintArrow(g2d, new Point(p1.x, p2.y), p2);
                    paintArrow(g2d, new Point(p1.x, p2.y), p1);
                    break;
            }
        }
    }

    protected void paint2Breaks(Graphics2D g2d) 
    {
        if(lineStart == LINE_START_HORIZONTAL) 
        {
            g2d.drawLine(p1.x, p1.y, p1.x + (p2.x - p1.x) / 2, p1.y);
            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p1.y, p1.x + (p2.x - p1.x) / 2, p2.y);
            g2d.drawLine(p1.x + (p2.x - p1.x) / 2, p2.y, p2.x, p2.y);
            switch(lineArrow) 
            {
                case LINE_ARROW_DEST:
                    paintArrow(g2d, new Point(p1.x + (p2.x - p1.x) / 2, p2.y), p2);
                    break;
                case LINE_ARROW_SOURCE:
                    paintArrow(g2d, new Point(p1.x + (p2.x - p1.x) / 2, p1.y), p1);
                    break;
                case LINE_ARROW_BOTH:
                    paintArrow(g2d, new Point(p1.x + (p2.x - p1.x) / 2, p2.y), p2);
                    paintArrow(g2d, new Point(p1.x + (p2.x - p1.x) / 2, p1.y), p1);
                    break;
            }
        }
        else if(lineStart == LINE_START_VERTICAL) 
        {
            g2d.drawLine(p1.x, p1.y, p1.x, p1.y + (p2.y - p1.y) / 2);
            g2d.drawLine(p1.x, p1.y + (p2.y - p1.y) / 2, p2.x, p1.y + (p2.y - p1.y) / 2);
            g2d.drawLine(p2.x, p1.y + (p2.y - p1.y) / 2, p2.x, p2.y);
            switch(lineArrow) 
            {
                case LINE_ARROW_DEST:
                    paintArrow(g2d, new Point(p2.x, p1.y + (p2.y - p1.y) / 2), p2);
                    break;
                case LINE_ARROW_SOURCE:
                    paintArrow(g2d, new Point(p1.x, p1.y + (p2.y - p1.y) / 2), p1);
                    break;
                case LINE_ARROW_BOTH:
                    paintArrow(g2d, new Point(p2.x, p1.y + (p2.y - p1.y) / 2), p2);
                    paintArrow(g2d, new Point(p1.x, p1.y + (p2.y - p1.y) / 2), p1);
                    break;
            }
        }
    }

    protected static Float getMidArrowPoint(Float p1, Float p2, float w) 
    {
        Float res = new Float();
        float d = Math.round(Math.sqrt( (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)));

        if(p1.x < p2.x) 
        {
            res.x = p2.x - w * Math.abs(p1.x - p2.x) / d;
        }
        else 
        {
            res.x = p2.x + w * Math.abs(p1.x - p2.x) / d;
        }

        if(p1.y < p2.y) 
        {
            res.y = p2.y - w * Math.abs(p1.y - p2.y) / d;
        }
        else 
        {
            res.y = p2.y + w * Math.abs(p1.y - p2.y) / d;
        }
        return res;
    }

    protected static Float getLeftArrowPoint(Float p1, Float p2) 
    {
        return getLeftArrowPoint(p1, p2, LINE_ARROW_WIDTH);
    }

    protected static Float getLeftArrowPoint(Float p1, Float p2, float w) 
    {
        Float res = new Float();
        double alpha = Math.PI / 2;
        if(p2.x != p1.x) 
        {
            alpha = Math.atan( (p2.y - p1.y) / (p2.x - p1.x));
        }
        alpha += Math.PI / 10;
        float xShift = Math.abs(Math.round(Math.cos(alpha) * w));
        float yShift = Math.abs(Math.round(Math.sin(alpha) * w));
        if(p1.x <= p2.x) 
        {
            res.x = p2.x - xShift;
        }
        else 
        {
            res.x = p2.x + xShift;
        }
        if(p1.y < p2.y) 
        {
            res.y = p2.y - yShift;
        }
        else 
        {
            res.y = p2.y + yShift;
        }
        return res;
    }

    protected static Float getRightArrowPoint(Float p1, Float p2) 
    {
        return getRightArrowPoint(p1, p2, LINE_ARROW_WIDTH);
    }

    protected static Float getRightArrowPoint(Float p1, Float p2, float w) 
    {
        Float res = new Float();
        double alpha = Math.PI / 2;
        if(p2.x != p1.x) 
        {
            alpha = Math.atan( (p2.y - p1.y) / (p2.x - p1.x));
        }
        alpha -= Math.PI / 10;
        float xShift = Math.abs(Math.round(Math.cos(alpha) * w));
        float yShift = Math.abs(Math.round(Math.sin(alpha) * w));
        if(p1.x < p2.x) 
        {
            res.x = p2.x - xShift;
        }
        else 
        {
            res.x = p2.x + xShift;
        }
        if(p1.y <= p2.y) 
        {
            res.y = p2.y - yShift;
        }
        else 
        {
            res.y = p2.y + yShift;
        }
        return res;
    }

    protected int getRestrictedArrowWidth(Point p1, Point p2) 
    {
        return Math.min(LINE_ARROW_WIDTH, (int) Math.sqrt( (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y)));
    }
    
    public static final int LINE_TYPE_SIMPLE = 0;
    public static final int LINE_TYPE_RECT_1BREAK = 1;
    public static final int LINE_TYPE_RECT_2BREAK = 2;

    public static final int LINE_START_HORIZONTAL = 0;
    public static final int LINE_START_VERTICAL = 1;

    public static final int LINE_ARROW_NONE = 0;
    public static final int LINE_ARROW_SOURCE = 1;
    public static final int LINE_ARROW_DEST = 2;
    public static final int LINE_ARROW_BOTH = 3;

    public static int LINE_ARROW_WIDTH = 10;    
    
    protected Point p1;
    protected Point p2;
    private int lineType = LINE_TYPE_SIMPLE;
    private int lineStart = LINE_START_HORIZONTAL;
    private int lineArrow = LINE_ARROW_NONE;
    protected Color color;
}
