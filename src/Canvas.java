import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

/**
 *This class implements the drawing area for the application.
 * @version 7.0
 */

public class Canvas extends JPanel
{
    public Canvas()
    {
        initComponents();
        this.wireList = new ArrayList();
    }

    private void initComponents()//GEN-BEGIN:initComponents
    {

        setBackground(new Color(255, 255, 255));
        setBorder(new SoftBevelBorder(BevelBorder.LOWERED));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 294, Short.MAX_VALUE)
        );
    }//GEN-END:initComponents

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        for(int i = 0; i < this.wireList.size(); i++)
        {
            this.wireList.get(i).line.paint((Graphics2D) g);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    protected ArrayList<Wire> wireList;
}
