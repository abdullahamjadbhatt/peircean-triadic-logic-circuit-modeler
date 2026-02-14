import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 *This utility class implements UI and simulation processes for a component.
 * <p> It has been adopted from DraggableLabel class from Stanislav Lapitsky's 
 * JConnector Project that can be found on this URL: 
 * http://java-sl.com/connector.html </p>
 * @version 7.0
 */

public class Component extends JLabel
{
    public Component(MainFrame parent, String type, int index, Point p, String label)
    {
        this.dragProcessor = new DragProcessor();
        this.addMouseListener(this.dragProcessor);
        this.addMouseMotionListener(this.dragProcessor);
        
        this.dragEnable = true;
        this.inputNodes = new ArrayList();
        this.outputNode = new Point();
        this.inputWires = new ArrayList();
        this.outputWires = new ArrayList();
        
        this.parent = parent;
        this.setType(type);
        this.index = index;
        this.setUI(p, label);
        
        this.locateInputNodes();
        this.locateOutputNode();
        
        this.logicalInputs = new ArrayList();
        this.children = new ArrayList();
        this.parents = new ArrayList();
        this.visited = false;
    }
    
    public Component() 
    {
        this.dragProcessor = null;
        
        this.inputNodes = null;
        this.outputNode = null;
        this.inputWires = null;
        this.outputWires = null;
        
        this.logicalInputs = null;
        this.children = new ArrayList();
        this.parents = null;
        
        this.parent = null;
        this.type = "Root";
        this.index = 0;
    }
    
    private void setType(String type)
    {
        switch(type)
        {
            case "AND":
                this.type = "Z-" + type;
                break;
            case "OR":
                this.type = "Θ-" + type;
                break;
            case "NOT":
                this.type = "S-" + type;
                break;
            default:
                this.type = type;
                break;
        }
    }
    
    private void setUI(Point p, String label)
    {
        if(label == null)
        {
            label = this.type + this.index;
        }
        
        this.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(1, 5, 1, 1)));
        this.label = new JTextField(label);
        this.label.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.label.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        this.label.setHorizontalAlignment(SwingConstants.CENTER);
        this.label.setBackground(Color.BLACK);
        this.label.setForeground(Color.WHITE);
        this.label.setMaximumSize(this.label.getPreferredSize());
        this.label.setMinimumSize(this.label.getPreferredSize());
        this.label.setBorder(null);

        switch(this.type)
        {
            case "Input":
                this.state = new JToggleButton("V", true);
                this.state.setFont(new Font("Tahoma", Font.BOLD, 12));
                this.state.setBackground(Color.RED);
                this.state.setAlignmentX(JToggleButton.CENTER_ALIGNMENT);
                this.state.addActionListener(new ActionListener()  
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        Component.this.toggleState();
                    }
                }
                );

                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                this.add(this.label);
                this.add(this.state);
                this.setBounds(p.x, p.y, 65, 50);                
                break;
            case "Output":        
                this.state = new JToggleButton();
                this.state.setFont(new Font("Tahoma", Font.BOLD, 12));
                this.state.setAlignmentX(JToggleButton.CENTER_ALIGNMENT);
                this.state.setHorizontalAlignment(SwingConstants.CENTER);
                this.state.setBorderPainted(false);
                this.state.setFocusPainted(false);
                this.state.setContentAreaFilled(false);

                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                this.add(this.label);
                this.add(this.state);
                this.setBounds(p.x, p.y, 75, 50);
                break;
            default:
                this.label.setEditable(false);
                
                this.state = new JToggleButton(new ImageIcon(getClass().getResource("images/" + this.type + ".jpg")));
                this.state.setSelected(true);
                this.state.setAlignmentX(JToggleButton.CENTER_ALIGNMENT);
                this.state.setBackground(Color.WHITE);
                this.state.addActionListener(new ActionListener()  
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        Component.this.toggleState();
                    }
                }
                );
         
                this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                this.add(this.label);
                this.add(this.state);
                this.setBounds(p.x, p.y, 105, 105);
                break;
        }
    }
    
    private void locateInputNodes()
    {
        if(!this.type.contentEquals("Input"))
        {
            Point A = new Point(this.getX(), this.getY());
            Point B = new Point(this.getX(), this.getY() + this.getHeight());
            this.inputNodes.clear();
            
            if(this.type.contentEquals("Output") || this.type.contains("-NOT"))
            {
                Point p = new Point((A.x + B.x) / 2, (A.y + B.y) / 2);
                
                if(!this.type.contentEquals("Output"))
                {
                    p.x += 13;
                    p.y += 10;
                }
                
                this.inputNodes.add(p);
            }
            else
            {
                int k1 = 1;
                int k2 = 2;
                for(int i = 0; i < 2; i++)
                {
                    Point p = new Point((k1*B.x + k2*A.x) / (k1+k2), (k1*B.y + k2*A.y) / (k1+k2));
                    
                    if(i == 0)
                    {
                        p.x += 13;
                        p.y += 10;
                    }
                    else
                    {
                       p.x += 13;
                       p.y += 9;
                    }
                    
                    this.inputNodes.add(p);
                    k1++;
                    k2--;
                }                
            }
            
            for(int i = 0; i < this.inputWires.size(); i++)
            {
                this.inputWires.get(i).line.p2 = this.inputNodes.get(i);
            }            
        }
    }
    
    private void locateOutputNode()
    {
        if(!this.type.contentEquals("Output"))
        {
            Point A = new Point(this.getX() + this.getWidth() , this.getY());
            Point B = new Point(this.getX() + this.getWidth() , this.getY() + this.getHeight());

            this.outputNode.x = (A.x + B.x) / 2;
            this.outputNode.y = (A.y + B.y) / 2;
            
            if(!this.type.contentEquals("Input"))
            {
                this.outputNode.x -= 11;
                this.outputNode.y += 10;
            }

            for(int i = 0; i < this.outputWires.size(); i++)
            {
                this.outputWires.get(i).line.p1 = this.outputNode;
            }
        }
    }
    
    protected Point getVacantInputNode()
    {
        for(int i = 0; i < this.inputNodes.size(); i++)
        {
            boolean found = false;
            for(int j = 0; j < this.inputWires.size(); j++)
            {
                if(this.inputWires.get(j).line != null)
                {
                    if(this.inputNodes.get(i) == this.inputWires.get(j).line.p2)
                    {
                        found = true;
                        break;
                    }
                }
            }
            
            if(!found)
            {
                return this.inputNodes.get(i);
            }
        }
        return null;
    }
    
    public boolean isCycle(ArrayList<Component> stack) 
    {
        stack.add(this);
        if(stack.size() > 1)
        {
            if(stack.get(0).index == stack.get(stack.size()-1).index)
            {
                return true;
            }
        }
        
        if(!this.visited)
        {
            this.visited = true;
            for(int i = 0; i < this.children.size(); i++)
            {
                if(this.children.get(i).isCycle(stack))
                {
                    return true;
                }
            }
        }
        
        stack.remove(this);
        return false;
    }
    
    public void evaluate() 
    {
        switch (this.type) 
        {
            case "Input":
                this.logicalOutput = this.state.getText();
                break;
            case "Output":
                if(!this.logicalInputs.isEmpty())
                {
                    this.logicalOutput = this.logicalInputs.get(0);
                    this.state.setText(this.logicalOutput);
                    switch(this.logicalOutput)
                    {
                        case "V":
                            this.state.setBackground(Color.RED);
                            break;
                        case "L":
                            this.state.setBackground(Color.GREEN);
                            break;
                        case "F":
                            this.state.setBackground(Color.BLUE);
                            break;
                        default:
                            this.state.setBackground(Color.WHITE);
                            break;
                    }
                }
                break;
            case "S-NOT":
                if(!this.logicalInputs.isEmpty())
                {
                    switch(this.logicalInputs.get(0)) 
                    {
                        case "V":
                            this.logicalOutput = "F";
                            break;
                        case "L":
                            this.logicalOutput = "L";
                            break;
                        case "F":
                            this.logicalOutput = "V";
                            break;
                    }
                }
                break;
            case "P-NOT":
                if(!this.logicalInputs.isEmpty())
                {
                    this.logicalOutput = "L";
                }

                break;
            case "C1-NOT":
                if(!this.logicalInputs.isEmpty())
                {
                    switch(this.logicalInputs.get(0)) 
                    {
                        case "V":
                            this.logicalOutput = "F";
                            break;
                        case "L":
                            this.logicalOutput = "V";
                            break;
                        case "F":
                            this.logicalOutput = "L";
                            break;
                    }
                }
                break;
            case "C2-NOT":
                if(!this.logicalInputs.isEmpty())
                {
                    switch(this.logicalInputs.get(0)) 
                    {
                        case "V":
                            this.logicalOutput = "L";
                            break;
                        case "L":
                            this.logicalOutput = "F";
                            break;
                        case "F":
                            this.logicalOutput = "V";
                            break;
                    }
                }
                break;
            default:
                if(this.logicalInputs.size() == 2)
                {
                    String p = this.logicalInputs.get(0);
                    String q = this.logicalInputs.get(1);
                    switch (this.type)
                    {
                        case "Z-AND":
                            if("F".equals(p) || "F".equals(q))
                            {
                                this.logicalOutput = "F";
                            }
                            else if("L".equals(p) || "L".equals(q))
                            {
                                this.logicalOutput = "L";
                            }
                            else
                            {
                                this.logicalOutput = "V";
                            }
                            break;
                        case "Ω-AND":
                            if("L".equals(p) || "L".equals(q))
                            {
                                this.logicalOutput = "L";
                            }
                            else if("F".equals(p) || "F".equals(q))
                            {
                                this.logicalOutput = "F";
                            }
                            else
                            {
                                this.logicalOutput = "V";
                            }
                            break;
                        case "Ψ-AND":
                            if("F".equals(p) || "F".equals(q))
                            {
                                this.logicalOutput = "F";
                            }
                            else if("V".equals(p) || "V".equals(q))
                            {
                                this.logicalOutput = "V";
                            }
                            else
                            {
                                this.logicalOutput = "L";
                            }
                            break;
                        case "Θ-OR":
                            if("V".equals(p) || "V".equals(q))
                            {
                                this.logicalOutput = "V";
                            }
                            else if("L".equals(p) || "L".equals(q))
                            {
                                this.logicalOutput = "L";
                            }
                            else
                            {
                                this.logicalOutput = "F";
                            }
                            break;
                        case "Y-OR":
                            if("L".equals(p) || "L".equals(q))
                            {
                                this.logicalOutput = "L";
                            }
                            else if("V".equals(p) || "V".equals(q))
                            {
                                this.logicalOutput = "V";
                            }
                            else
                            {
                                this.logicalOutput = "F";
                            }
                            break;
                        case "Φ-OR":
                            if("V".equals(p) || "V".equals(q))
                            {
                                this.logicalOutput = "V";
                            }
                            else if("F".equals(p) || "F".equals(q))
                            {
                                this.logicalOutput = "F";
                            }
                            else
                            {
                                this.logicalOutput = "L";
                            }
                        default:
                            break;
                    }                    
                }
                break;
        }
        
        if(this.logicalOutput != null)
        {
            Color c;
            switch (this.logicalOutput) 
            {
                case "V":
                    c = Color.RED;
                    break;
                case "L":
                    c = Color.GREEN;
                    break;
                case "F":
                    c = Color.BLUE;
                    break;
                default:
                    c = Color.BLACK;
                    break;
            }
            
            for(int i = 0; i < this.outputWires.size(); i++)
            {
                this.outputWires.get(i).line.color = c;
            }

            for(int i = 0; i < this.children.size(); i++)
            {
                this.children.get(i).logicalInputs.add(this.logicalOutput);
                this.children.get(i).evaluate();
            }
        }
    }
    
    public String generateExpression() 
    {
        if(this.type.contentEquals("Input"))
        {
            return this.label.getText();
        }
        
        ArrayList<String> s = new ArrayList();
        for(int i = 0; i < this.parents.size(); i++)
        {
            s.add(this.parents.get(i).generateExpression());
        }
        
        if(this.type.contentEquals("Output"))
        {
            StringBuilder e = new StringBuilder(s.get(0));
            if(e.charAt(0) == '[')
            {
                e.deleteCharAt(0);
            }
            
            if(e.charAt(e.length()-1) == ']')
            {
                e.deleteCharAt(e.length()-1);
            }
            
            return this.label.getText() + " = " + e;
        }
        else if(this.type.contains("-NOT"))
        {
            return this.type.substring(0, this.type.indexOf('-')) + "(" + s.get(0) + ")";                
        }
        else
        {
            return "[" + s.get(0) + " " + this.type.charAt(0) + " " + s.get(1) + "]";
        }
    }
    
    protected void toggleState() 
    {
        switch(this.type)
        {
            case "Input":
                switch(this.state.getText())
                {
                    case "V":
                        this.state.setSelected(true);
                        this.state.setText("L");
                        this.state.setBackground(Color.GREEN);
                        break;
                    case "L":
                        this.state.setSelected(true);
                        this.state.setText("F");                                
                        this.state.setBackground(Color.BLUE);
                        break;
                    case "F":
                        this.state.setSelected(true);
                        this.state.setText("V");                                
                        this.state.setBackground(Color.RED);
                        break;
                }
                break;
            default:
                String oldType = this.type;
                switch(oldType)
                {
                    case "Z-AND":
                        this.state.setSelected(true);
                        this.type = "Ω-AND"; 
                        break;
                    case "Ω-AND":
                        this.state.setSelected(true);
                        this.type = "Ψ-AND";
                        break;
                    case "Ψ-AND":
                        this.state.setSelected(true);
                        this.type = "Z-AND";
                        break;

                    case "Θ-OR":
                        this.state.setSelected(true);
                        this.type = "Y-OR";
                        break;
                    case "Y-OR":
                        this.state.setSelected(true);
                        this.type = "Φ-OR";
                        break;
                    case "Φ-OR":
                        this.state.setSelected(true);
                        this.type = "Θ-OR";
                        break;

                    case "S-NOT":
                        this.state.setSelected(true);
                        this.type = "P-NOT";
                        break;
                    case "P-NOT":
                        this.state.setSelected(true);
                        this.type = "C1-NOT";
                        break;
                    case "C1-NOT":
                        this.state.setSelected(true);
                        this.type = "C2-NOT";
                        break;
                    case "C2-NOT":
                        this.state.setSelected(true);
                        this.type = "S-NOT";
                        break;
                }
                this.state.setIcon(new ImageIcon(getClass().getResource("images/" + this.type + ".jpg")));
                this.label.setText(this.type + this.index);
                this.parent.notify("Message: Component " + oldType + this.index + " changed to " + this.type + this.index);
                break;
        }

        if(this.parent.environmentMenu.getSelectedItem().toString().contentEquals("Simulate"))
        {
            this.parent.graph.evaluate();
            this.parent.update();
        }  
    }    
    
    private final MainFrame parent;
    protected String type;
    protected int index;
    
    protected JTextField label;
    protected JToggleButton state;
    
    protected boolean dragEnable;
    private final ArrayList<Point> inputNodes;
    protected Point outputNode;
    protected ArrayList<Wire> inputWires;
    protected ArrayList<Wire> outputWires;
    
    protected ArrayList<String> logicalInputs;
    protected String logicalOutput;
    protected ArrayList<Component> children;
    protected ArrayList<Component> parents;
    protected boolean visited;
    
    protected class DragProcessor extends MouseAdapter implements MouseListener, MouseMotionListener 
    {
        Window dragWindow = new JWindow() 
        {
            @Override
            public void paint(Graphics g) 
            {
                super.paint(g);
                Component.this.paint(g);
            }
        };
      
        @Override
        public void mouseDragged(MouseEvent e) 
        {
            if(Component.this.dragEnable)
            {
                Point dragPoint = e.getPoint();
                int xDiff = Component.this.pressPoint.x - dragPoint.x;
                int yDiff = Component.this.pressPoint.y - dragPoint.y; 
     
                Rectangle b = e.getComponent().getBounds();
                Point p = b.getLocation();
                SwingUtilities.convertPointToScreen(p, e.getComponent().getParent());
                p.x -= xDiff;
                p.y -= yDiff;
                this.dragWindow.setLocation(p);
            }
        }
          
        @Override
        public void mouseClicked(MouseEvent e) 
        {
            e.getComponent().getParent().dispatchEvent(e);
        }      

        @Override
        public void mouseMoved(MouseEvent e) 
        {
        }    

        @Override
        public void mousePressed(MouseEvent e) 
        {
            if(Component.this.dragEnable)
            {
                Component.this.pressPoint = e.getPoint();
                Rectangle b = e.getComponent().getBounds();
                Point p = b.getLocation();
                SwingUtilities.convertPointToScreen(p, e.getComponent().getParent());
                this.dragWindow.setBounds(b);
                this.dragWindow.setLocation(p);
                this.dragWindow.setVisible(true);
            }
        }   

        @Override
        public void mouseReleased(MouseEvent e) 
        {
            if(Component.this.dragEnable)
            {
                Component.this.releasePoint = e.getPoint();
                this.dragWindow.setVisible(false);        

                int xDiff = Component.this.pressPoint.x - Component.this.releasePoint.x;
                int yDiff = Component.this.pressPoint.y - Component.this.releasePoint.y;      

                Rectangle b = e.getComponent().getBounds();
                Point p = b.getLocation();
                SwingUtilities.convertPointToScreen(p, e.getComponent().getParent());
                p.x -= xDiff;
                p.y -= yDiff;      

                SwingUtilities.convertPointFromScreen(p, Component.this.getParent());
                if(p.x <= 0) 
                {
                    p.x = 1;
                }
                if(p.x > Component.this.getParent().getWidth() - b.width) 
                {
                    p.x = Component.this.getParent().getWidth() - b.width;
                }
                if(p.y <= 0) 
                {
                    p.y = 1;
                }
                if(p.y > Component.this.getParent().getHeight() - b.height) 
                {
                    p.y = Component.this.getParent().getHeight() - b.height;
                }
                Component.this.setLocation(p);
                Component.this.locateInputNodes();
                Component.this.locateOutputNode();
                Component.this.parent.update();
            }
        }  
    }
      
    private Point pressPoint;
    private Point releasePoint;
    private final DragProcessor dragProcessor;
}
