import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *This class displays truth table and logical expressions for the designed system.
 * @version 7.0
 */

public class TruthTable extends JFrame
{    
    public TruthTable(ArrayList<ArrayList<String>> values, ArrayList<String> expressions) 
    {
        this.values = values;
        this.model = new DefaultTableModel();
        for(int i = 0; i < this.values.get(0).size(); i++)
        {
            String Label = this.values.get(0).get(i);
            ArrayList<String> Data = new ArrayList();
            for(int j = 1; j < this.values.size(); j++)
            {
                Data.add(this.values.get(j).get(i));
            }
            this.model.addColumn(Label, Data.toArray());
        }
 
        initComponents();
        
        DefaultTableCellRenderer _header = new DefaultTableCellRenderer();
        _header.setHorizontalAlignment(SwingConstants.CENTER);
        _header.setBackground(Color.GRAY);
        _header.setForeground(Color.WHITE);
        this.truthTable.getTableHeader().setDefaultRenderer(_header);
        
        DefaultTableCellRenderer _values = new DefaultTableCellRenderer();
        _values.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < this.truthTable.getColumnCount(); i++)
        {
            this.truthTable.getColumnModel().getColumn(i).setCellRenderer(_values);
            this.truthTable.setDefaultEditor(this.truthTable.getColumnClass(i), null);
        }
         
        this.addWindowListener(new WindowListener() 
        {
            @Override
            public void windowOpened(WindowEvent e) 
            {
            }

            @Override
            public void windowClosing(WindowEvent e) 
            {
                TruthTable.this.values.clear();
                TruthTable.this.model.getDataVector().removeAllElements();
                TruthTable.this.model.setColumnCount(0);
                TruthTable.this.dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) 
            {
            }

            @Override
            public void windowIconified(WindowEvent e) 
            {
            }

            @Override
            public void windowDeiconified(WindowEvent e) 
            {
            }

            @Override
            public void windowActivated(WindowEvent e) 
            {
            }

            @Override
            public void windowDeactivated(WindowEvent e) 
            {
            }
        });
        
        for(int i = 0; i < expressions.size(); i++)
        {
            this.expressions.setText(this.expressions.getText() + "\n" + expressions.get(i));
        }
    }    

    private void initComponents()//GEN-BEGIN:initComponents
    {

        JScrollPane truthTableScroll = new JScrollPane();
        truthTable = new JTable();
        JScrollPane expressionsScroll = new JScrollPane();
        expressions = new JTextArea();

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Results");
        setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());

        truthTable.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        truthTable.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        truthTable.setModel(this.model);
        truthTable.setGridColor(new Color(0, 0, 0));
        truthTableScroll.setViewportView(truthTable);

        expressions.setEditable(false);
        expressions.setColumns(20);
        expressions.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        expressions.setRows(5);
        expressions.setText("Expressions:\n\n");
        expressions.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
        expressionsScroll.setViewportView(expressions);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(truthTableScroll, GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                    .addComponent(expressionsScroll))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(truthTableScroll, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(expressionsScroll, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextArea expressions;
    private JTable truthTable;
    // End of variables declaration//GEN-END:variables
    private DefaultTableModel model;
    private ArrayList<ArrayList<String>> values;    
}
