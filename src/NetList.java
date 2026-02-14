import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *This class displays information about wires between components.
 * @version 7.0
 */

public class NetList extends JFrame
{   
    public NetList(ArrayList<Wire> connections)
    {
        this.connections = connections;
        this.model = new DefaultTableModel(new String[] {"Source", "Sink"}, 0);
        
        initComponents();
        
        for(int i = 0; i < this.connections.size(); i++)
        {
            this.model.addRow(new Object[] {this.connections.get(i).source.label.getText(), 
                                            this.connections.get(i).sink.label.getText()});
        }
        
        DefaultTableCellRenderer _header = new DefaultTableCellRenderer();
        _header.setHorizontalAlignment(SwingConstants.CENTER);
        _header.setBackground(Color.BLACK);
        _header.setForeground(Color.WHITE);
        this.connectionsTable.getTableHeader().setDefaultRenderer(_header);
        
        DefaultTableCellRenderer _values = new DefaultTableCellRenderer();
        _values.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < this.connectionsTable.getColumnCount(); i++)
        {
            this.connectionsTable.getColumnModel().getColumn(i).setCellRenderer(_values);
            this.connectionsTable.setDefaultEditor(this.connectionsTable.getColumnClass(i), null);
        }        
    }

    private void initComponents()//GEN-BEGIN:initComponents
    {

        JScrollPane connectionsTableScroll = new JScrollPane();
        connectionsTable = new JTable();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Net List");
        setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());

        connectionsTable.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        connectionsTable.setModel(this.model);
        connectionsTableScroll.setViewportView(connectionsTable);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(connectionsTableScroll, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(connectionsTableScroll, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTable connectionsTable;
    // End of variables declaration//GEN-END:variables
    private final DefaultTableModel model;
    private final ArrayList<Wire> connections;
}
