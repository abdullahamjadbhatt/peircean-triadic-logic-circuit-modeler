import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *This class implements main interface for the Modeler application.
 * @version 7.0
 */

public class MainFrame extends JFrame
{
    public MainFrame()
    {
        this.setExtendedState(Frame.MAXIMIZED_BOTH);        
        initComponents();

        this.componentIndex = 0;
        this.componentList = new ArrayList();
        
        this.clickCount = 0;
        
        this.graph = new Graph();
        
        new File("C:\\Designs").mkdir();
        new File("C:\\WaveForms").mkdir();
    }

    private void initComponents()//GEN-BEGIN:initComponents
    {

        JToolBar toolBar = new JToolBar();
        JLabel componentMenuDescriptor = new JLabel();
        componentMenu = new JComboBox<>();
        JLabel environmentMenuDescriptor = new JLabel();
        environmentMenu = new JComboBox<>();
        JLabel reportsDescriptor = new JLabel();
        JToolBar.Separator toolBarLogicalSeparator = new JToolBar.Separator();
        JToolBar.Separator toolBarLogicalSeparator1 = new JToolBar.Separator();
        JToolBar.Separator toolBarLogicalSeparator2 = new JToolBar.Separator();
        JButton netList = new JButton();
        JToolBar.Separator buttonSeparator1 = new JToolBar.Separator();
        JButton truthTable = new JButton();
        JToolBar.Separator buttonSeparator2 = new JToolBar.Separator();
        JButton testBench = new JButton();
        JScrollPane logScroll = new JScrollPane();
        log = new JTextArea();
        JScrollPane canvasScroll = new JScrollPane();
        canvas = new Canvas();
        JMenuBar menus = new JMenuBar();
        JMenu fileMenu = new JMenu();
        JMenuItem newDesign = new JMenuItem();
        JMenuItem openDesign = new JMenuItem();
        JMenuItem saveDesign = new JMenuItem();
        JMenu helpMenu = new JMenu();
        JMenuItem about = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Peircean Triadic Logic Circuit Modeler");
        setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());

        toolBar.setFloatable(false);
        toolBar.setRollover(true);
        toolBar.setMinimumSize(new Dimension(800, 30));
        toolBar.setPreferredSize(new Dimension(800, 30));

        componentMenuDescriptor.setFont(new Font("Tahoma", 1, 12)); // NOI18N
        componentMenuDescriptor.setText(" Components:  ");
        toolBar.add(componentMenuDescriptor);

        componentMenu.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        componentMenu.setModel(new DefaultComboBoxModel<>(new String[] { "AND", "OR", "NOT", "Input", "Output" }));
        componentMenu.setMinimumSize(new Dimension(100, 25));
        componentMenu.setPreferredSize(new Dimension(100, 25));
        toolBar.add(componentMenu);

        environmentMenuDescriptor.setFont(new Font("Tahoma", 1, 12)); // NOI18N
        environmentMenuDescriptor.setText(" Environment:  ");
        toolBar.add(environmentMenuDescriptor);

        environmentMenu.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        environmentMenu.setModel(new DefaultComboBoxModel<>(new String[] { "Design", "Delete", "Connect", "Simulate" }));
        environmentMenu.setMinimumSize(new Dimension(100, 25));
        environmentMenu.setPreferredSize(new Dimension(100, 25));
        environmentMenu.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                environmentMenuActionPerformed(evt);
            }
        });
        toolBar.add(environmentMenu);

        reportsDescriptor.setFont(new Font("Tahoma", 1, 12)); // NOI18N
        reportsDescriptor.setText(" Reports:  ");
        toolBar.add(reportsDescriptor);

        toolBarLogicalSeparator.setMinimumSize(new Dimension(10, 0));
        toolBarLogicalSeparator.setPreferredSize(new Dimension(10, 0));
        toolBar.add(toolBarLogicalSeparator);

        toolBarLogicalSeparator1.setMinimumSize(new Dimension(10, 0));
        toolBarLogicalSeparator1.setPreferredSize(new Dimension(10, 0));
        toolBar.add(toolBarLogicalSeparator1);

        toolBarLogicalSeparator2.setMinimumSize(new Dimension(10, 0));
        toolBarLogicalSeparator2.setPreferredSize(new Dimension(10, 0));
        toolBar.add(toolBarLogicalSeparator2);

        netList.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        netList.setText("Net List");
        netList.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        netList.setHorizontalTextPosition(SwingConstants.CENTER);
        netList.setMinimumSize(new Dimension(80, 20));
        netList.setPreferredSize(new Dimension(80, 20));
        netList.setVerticalTextPosition(SwingConstants.BOTTOM);
        netList.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                netListActionPerformed(evt);
            }
        });
        toolBar.add(netList);

        buttonSeparator1.setMinimumSize(new Dimension(10, 0));
        buttonSeparator1.setPreferredSize(new Dimension(10, 0));
        toolBar.add(buttonSeparator1);

        truthTable.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        truthTable.setText("Truth Table");
        truthTable.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        truthTable.setFocusable(false);
        truthTable.setHorizontalTextPosition(SwingConstants.CENTER);
        truthTable.setMinimumSize(new Dimension(80, 20));
        truthTable.setPreferredSize(new Dimension(80, 20));
        truthTable.setVerticalTextPosition(SwingConstants.BOTTOM);
        truthTable.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                truthTableActionPerformed(evt);
            }
        });
        toolBar.add(truthTable);

        buttonSeparator2.setMinimumSize(new Dimension(10, 0));
        buttonSeparator2.setPreferredSize(new Dimension(10, 0));
        toolBar.add(buttonSeparator2);

        testBench.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        testBench.setText("Test Bench");
        testBench.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        testBench.setFocusable(false);
        testBench.setHorizontalTextPosition(SwingConstants.CENTER);
        testBench.setPreferredSize(new Dimension(95, 20));
        testBench.setVerticalTextPosition(SwingConstants.BOTTOM);
        testBench.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                testBenchActionPerformed(evt);
            }
        });
        toolBar.add(testBench);

        log.setEditable(false);
        log.setColumns(20);
        log.setFont(new Font("Monospaced", 0, 12)); // NOI18N
        log.setRows(5);
        log.setText("Messages Log:\n\nMessage: Design variables loaded.");
        logScroll.setViewportView(log);

        canvasScroll.setPreferredSize(new Dimension(800, 300));

        canvas.setPreferredSize(new Dimension(5000, 5000));
        canvas.addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent evt)
            {
                canvasMouseClicked(evt);
            }
        });

        GroupLayout canvasLayout = new GroupLayout(canvas);
        canvas.setLayout(canvasLayout);
        canvasLayout.setHorizontalGroup(canvasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 4994, Short.MAX_VALUE)
        );
        canvasLayout.setVerticalGroup(canvasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 4994, Short.MAX_VALUE)
        );

        canvasScroll.setViewportView(canvas);

        fileMenu.setText("File");

        newDesign.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        newDesign.setText("New Design");
        newDesign.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                newDesignActionPerformed(evt);
            }
        });
        fileMenu.add(newDesign);

        openDesign.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        openDesign.setText("Open Design");
        openDesign.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                openDesignActionPerformed(evt);
            }
        });
        fileMenu.add(openDesign);

        saveDesign.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        saveDesign.setText("Save Design");
        saveDesign.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                saveDesignActionPerformed(evt);
            }
        });
        fileMenu.add(saveDesign);

        menus.add(fileMenu);

        helpMenu.setText("Help");

        about.setText("About");
        about.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                aboutActionPerformed(evt);
            }
        });
        helpMenu.add(about);

        menus.add(helpMenu);

        setJMenuBar(menus);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(logScroll)
                    .addComponent(canvasScroll, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(canvasScroll, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logScroll, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }//GEN-END:initComponents
   
    protected void update()
    {
        this.canvas.revalidate();
        this.canvas.repaint();
    }    
    
    private void clear()
    {
        this.canvas.removeAll();
        this.componentIndex = 0;
        this.componentList.clear();
        
        this.canvas.wireList.clear();
        
        this.graph = null;
        this.graph = new Graph();
        
        this.environmentMenu.setSelectedItem("Design");
        this.log.setText("Messages Log:\n\nMessage: Design variables loaded.");
        
        this.update();
    }
    
    protected void notify(String message)
    {
        this.log.setText(this.log.getText() + "\n" + message);
    }

    private void addComponent(String type, int index, Point p, String label)
    {
        Component component = new Component(this, type, index, p, label);
        this.componentList.add(component);
        this.canvas.add(component);
        this.update();
        this.notify("Message: Component " + component.type + component.index + " placed.");        
    }

    private void addWire()
    {
        Point sourcePoint = this.source.outputNode;
        Point sinkPoint = this.sink.getVacantInputNode();
        
        if(sinkPoint == null)
        {
            this.notify("Error: Cannot allow any more wires to Component " + this.sink.type + this.sink.index);
        }
        else
        {
            if(this.graph.insert(this.source, this.sink))
            {
                Wire w = new Wire(this.source, this.sink, new ConnectLine(sourcePoint, sinkPoint));
                this.source.outputWires.add(w);
                this.sink.inputWires.add(w);
                this.canvas.wireList.add(w);
                this.update();            
                this.notify("Message: Component " + this.sink.type + this.sink.index + " selected as sink.");
            }
            else
            {
                this.notify("Error: Cannot allow feedback connections.");
            }
        }
    }    
    
    private void openDesign()
    {
        try 
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(this.fileName));
            doc.getDocumentElement().normalize();

            NodeList comList = doc.getElementsByTagName("Component");
            for(int i = 0; i < comList.getLength(); i++)
            {
                Element com = (Element) comList.item(i);
                this.addComponent(com.getElementsByTagName("Type").item(0).getTextContent(), 
                                  Integer.parseInt(com.getElementsByTagName("Index").item(0).getTextContent()), 
                                  new Point(Integer.parseInt(com.getElementsByTagName("X").item(0).getTextContent()), 
                                            Integer.parseInt(com.getElementsByTagName("Y").item(0).getTextContent())), 
                                  com.getElementsByTagName("Label").item(0).getTextContent());
                this.componentIndex++;
            }

            NodeList conList = doc.getElementsByTagName("Wire");
            for(int i = 0; i < conList.getLength(); i++)
            {
                Element con = (Element) conList.item(i);

                for(int j = 0; j < this.componentList.size(); j++)
                {
                    if(this.componentList.get(j).index == Integer.parseInt(con.getElementsByTagName("Source-Index").item(0).getTextContent()))
                    {
                        this.source = this.componentList.get(j);
                    }
                    if(this.componentList.get(j).index == Integer.parseInt(con.getElementsByTagName("Sink-Index").item(0).getTextContent()))
                    {
                        this.sink = this.componentList.get(j);
                    }
                }

                this.addWire();
            }
            this.notify("Message: " + this.fileName + " loaded.");
        } 
        catch(IOException | ParserConfigurationException | SAXException ex)
        {
            this.notify("Error: " + ex.getMessage());
        }        
    }   
    
    private void saveDesign()
    {
        try 
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element root = doc.createElement("Configuration");
            Element components = doc.createElement("ComponentList");
            Element wires = doc.createElement("WireList");

            for(int i = 0; i < this.componentList.size(); i++)
            {
                Element component = doc.createElement("Component");

                Element type = doc.createElement("Type");
                type.appendChild(doc.createTextNode(this.componentList.get(i).type));                
                component.appendChild(type);

                Element index = doc.createElement("Index");
                index.appendChild(doc.createTextNode(((Integer)this.componentList.get(i).index).toString()));
                component.appendChild(index);

                Element x = doc.createElement("X");
                x.appendChild(doc.createTextNode(((Integer)this.componentList.get(i).getX()).toString()));
                component.appendChild(x);

                Element y = doc.createElement("Y");
                y.appendChild(doc.createTextNode(((Integer)this.componentList.get(i).getY()).toString()));
                component.appendChild(y);
                
                Element label = doc.createElement("Label");
                label.appendChild(doc.createTextNode(this.componentList.get(i).label.getText()));
                component.appendChild(label);

                components.appendChild(component);
            }

            root.appendChild(components);

            for(int i = 0; i < this.canvas.wireList.size(); i++)
            {
                Element wire = doc.createElement("Wire");

                Element sourceIndex = doc.createElement("Source-Index");
                sourceIndex.appendChild(doc.createTextNode(((Integer)this.canvas.wireList.get(i).source.index).toString()));
                wire.appendChild(sourceIndex);

                Element sinkIndex = doc.createElement("Sink-Index");
                sinkIndex.appendChild(doc.createTextNode(((Integer)this.canvas.wireList.get(i).sink.index).toString()));
                wire.appendChild(sinkIndex);                

                wires.appendChild(wire);                
            }

            root.appendChild(wires);
            doc.appendChild(root);

            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource docSource = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(this.fileName + ".xml"));
            transformer.transform(docSource, result);
            this.notify("Message: " + this.fileName + ".xml" + " saved.");
        } 
        catch(TransformerException | ParserConfigurationException ex) 
        {
            this.notify("Error: " + ex.getMessage());
        }        
    }

    private void iterator(ArrayList<Component> nodes, int index, boolean init) 
    {
        if(index < nodes.size())
        {
            for(int i = 0; i < 3; i++)
            {
                if(!init)
                {
                   nodes.get(index).toggleState();
                }

                this.iterator(nodes, index + 1, init);

                if(init)
                {
                   init = false;
                }
            }
        }
    }    
    
    private void newDesignActionPerformed(ActionEvent evt)//GEN-FIRST:event_newDesignActionPerformed
    {//GEN-HEADEREND:event_newDesignActionPerformed
        this.clear();
    }//GEN-LAST:event_newDesignActionPerformed

    private void openDesignActionPerformed(ActionEvent evt)//GEN-FIRST:event_openDesignActionPerformed
    {//GEN-HEADEREND:event_openDesignActionPerformed
        this.clear();
        
        DesignPrompt openDialog = new DesignPrompt(this, 1);
        openDialog.setVisible(true);

        if(this.fileName != null)
        {
            this.openDesign();
        }
    }//GEN-LAST:event_openDesignActionPerformed

    private void saveDesignActionPerformed(ActionEvent evt)//GEN-FIRST:event_saveDesignActionPerformed
    {//GEN-HEADEREND:event_saveDesignActionPerformed
        DesignPrompt saveDialog = new DesignPrompt(this,0);
        saveDialog.setVisible(true);
        
        if(this.fileName != null)
        {
            this.saveDesign();
        }
    }//GEN-LAST:event_saveDesignActionPerformed

    private void aboutActionPerformed(ActionEvent evt)//GEN-FIRST:event_aboutActionPerformed
    {//GEN-HEADEREND:event_aboutActionPerformed
        new About().setVisible(true);
    }//GEN-LAST:event_aboutActionPerformed

    private void environmentMenuActionPerformed(ActionEvent evt)//GEN-FIRST:event_environmentMenuActionPerformed
    {//GEN-HEADEREND:event_environmentMenuActionPerformed
        switch(this.environmentMenu.getSelectedItem().toString())
        {
            case "Design":
                for(int i = 0; i < this.canvas.wireList.size(); i++)
                {
                    this.canvas.wireList.get(i).line.color = Color.BLACK;
                }

                for(int i = 0; i < this.componentList.size(); i++)
                {
                    if(this.componentList.get(i).type.contentEquals("Output"))
                    {
                        this.componentList.get(i).state.setText(" ");
                        this.componentList.get(i).state.setContentAreaFilled(false);                      
                    }
                    this.componentList.get(i).dragEnable = true;
                }                
                
                this.clickCount = 0;
                
                this.notify("Message: Design variables loaded.");
                break;
            case "Delete":
                for(int i = 0; i < this.canvas.wireList.size(); i++)
                {
                    this.canvas.wireList.get(i).line.color = Color.BLACK;
                }

                for(int i = 0; i < this.componentList.size(); i++)
                {
                    if(this.componentList.get(i).type.contentEquals("Output"))
                    {
                        this.componentList.get(i).state.setText(" ");
                        this.componentList.get(i).state.setContentAreaFilled(false);                      
                    } 
                    this.componentList.get(i).dragEnable = false;
                }
                
                this.clickCount = 0;
                
                this.notify("Message: Delete variables loaded.");
                break;
            case "Connect":
                for(int i = 0; i < this.canvas.wireList.size(); i++)
                {
                    this.canvas.wireList.get(i).line.color = Color.BLACK;
                }

                for(int i = 0; i < this.componentList.size(); i++)
                {
                    if(this.componentList.get(i).type.contentEquals("Output"))
                    {
                        this.componentList.get(i).state.setText(" ");
                        this.componentList.get(i).state.setContentAreaFilled(false);                      
                    }
                    this.componentList.get(i).dragEnable = false;
                }
                
                this.notify("Message: Connect variables loaded.");
                break;
            case "Simulate":
                this.graph.evaluate();
                for(int i = 0; i < this.componentList.size(); i++)
                {
                    if(this.componentList.get(i).type.contentEquals("Output"))
                    {
                        this.componentList.get(i).state.setContentAreaFilled(true);                        
                    }
                    this.componentList.get(i).dragEnable = true;
                }
                this.clickCount = 0;
                
                this.notify("Message: Simulate variables loaded.");
                this.update();
                break;                
            default:
                break;
        }
    }//GEN-LAST:event_environmentMenuActionPerformed

    private void netListActionPerformed(ActionEvent evt)//GEN-FIRST:event_netListActionPerformed
    {//GEN-HEADEREND:event_netListActionPerformed
        new NetList(this.canvas.wireList).setVisible(true);
    }//GEN-LAST:event_netListActionPerformed

    private void truthTableActionPerformed(ActionEvent evt)//GEN-FIRST:event_truthTableActionPerformed
    {//GEN-HEADEREND:event_truthTableActionPerformed
        this.environmentMenu.setSelectedItem("Simulate");
        
        ArrayList<Component> nodes = new ArrayList();
        for(int i = 0; i < this.graph.nodes.size(); i++)
        {
            if(this.graph.nodes.get(i).type.contentEquals("Input"))
            {
                nodes.add(this.graph.nodes.get(i));
            }
        }

        for(int i = 0; i < nodes.size(); i++)
        {
            while(!nodes.get(i).state.getText().contentEquals("F"))
            {
                nodes.get(i).toggleState();
            }

            if(i == nodes.size()-1)
            {
                this.graph.construct = true;
            }

            nodes.get(i).toggleState();
        }

        this.iterator(nodes, 0, true);
        this.graph.construct = false;

        if(!this.graph.orderedPairs.isEmpty())
        {
            new TruthTable(this.graph.orderedPairs, this.graph.generateExpressions()).setVisible(true);
        }

        this.graph.orderedPairs.clear();
    }//GEN-LAST:event_truthTableActionPerformed

    private void testBenchActionPerformed(ActionEvent evt)//GEN-FIRST:event_testBenchActionPerformed
    {//GEN-HEADEREND:event_testBenchActionPerformed
        this.environmentMenu.setSelectedItem("Simulate");
        
        ArrayList<Component> nodes = new ArrayList();
        for(int i = 0; i < this.graph.nodes.size(); i++)
        {
            if(this.graph.nodes.get(i).type.contentEquals("Input"))
            {
                nodes.add(this.graph.nodes.get(i));
            }
        }

        for(int i = 0; i < nodes.size(); i++)
        {
            while(!nodes.get(i).state.getText().contentEquals("F"))
            {
                nodes.get(i).toggleState();
            }

            if(i == nodes.size()-1)
            {
                this.graph.construct = true;
            }

            nodes.get(i).toggleState();
        }

        this.iterator(nodes, 0, true);
        this.graph.construct = false;

        if(!this.graph.orderedPairs.isEmpty())
        {
            new TestBench(this, nodes.size(), this.graph.orderedPairs).setVisible(true);
        }

        this.graph.orderedPairs.clear();        
    }//GEN-LAST:event_testBenchActionPerformed

    private void canvasMouseClicked(MouseEvent evt)//GEN-FIRST:event_canvasMouseClicked
    {//GEN-HEADEREND:event_canvasMouseClicked
        switch(this.environmentMenu.getSelectedItem().toString())
        {
            case "Design":
                if(evt.getComponent().getClass() == this.canvas.getClass())
                {
                    this.addComponent(this.componentMenu.getSelectedItem().toString(), 
                                      ++this.componentIndex, 
                                      new Point(evt.getX(), evt.getY()), 
                                      null);
                }
                break;
            case "Delete":
                if(evt.getComponent().getClass() != this.canvas.getClass())
                {
                    int deleteIndex = -1;
                    for(int i = 0; i < this.componentList.size(); i++)
                    {
                        if(evt.getComponent() == this.componentList.get(i))
                        {
                            deleteIndex = i;
                            break;
                        }
                    }
                    
                    for(int i = 0; i < this.componentList.get(deleteIndex).inputWires.size(); i++)
                    {
                        this.canvas.wireList.remove(this.componentList.get(deleteIndex).inputWires.get(i));
                        this.componentList.get(deleteIndex).inputWires.get(i).source.outputWires.remove(this.componentList.get(deleteIndex).inputWires.get(i));
                    }
                    
                    for(int i = 0; i < this.componentList.get(deleteIndex).outputWires.size(); i++)
                    {
                        this.canvas.wireList.remove(this.componentList.get(deleteIndex).outputWires.get(i));
                        this.componentList.get(deleteIndex).outputWires.get(i).sink.inputWires.remove(this.componentList.get(deleteIndex).outputWires.get(i));                        
                    }
                    
                    this.notify("Message: Component " + this.componentList.get(deleteIndex).type + this.componentList.get(deleteIndex).index + " removed.");
                    this.canvas.remove(this.componentList.get(deleteIndex));
                    this.graph.remove(this.componentList.get(deleteIndex));
                    this.componentList.remove(deleteIndex);
                    this.update();
                }
                break;
            case "Connect":
                if(evt.getComponent().getClass() != this.canvas.getClass())
                {
                    switch(this.clickCount)
                    {
                        case 0:
                            for(int i = 0; i < this.componentList.size(); i++)
                            {
                                if(evt.getComponent() == this.componentList.get(i))
                                {
                                    this.source = this.componentList.get(i);
                                    break;
                                }
                            }
                            if(this.source.type.contentEquals("Output"))
                            {
                                this.notify("Error: Cannot allow this connection.");
                            }
                            else
                            {
                                this.notify("Message: Component " + this.source.type + this.source.index + " selected as source.");
                                this.clickCount++;
                            }
                            break;
                        case 1:
                            if(evt.getComponent() == this.source)
                            {
                                this.notify("Error: Source and sink cannot be the same.");
                            }
                            else
                            {
                                for(int i = 0; i < this.componentList.size(); i++)
                                {
                                    if(evt.getComponent() == this.componentList.get(i))
                                    {
                                        this.sink = this.componentList.get(i);
                                    }
                                }
                                if(this.sink.type.contentEquals("Input"))
                                {
                                    this.notify("Error: Cannot allow this connection.");
                                }
                                else
                                {
                                    this.addWire();
                                }
                            }
                            this.clickCount--;
                            break;
                        default:
                            break;
                    }
                }
                break;                
            default:
                break;
        }
    }//GEN-LAST:event_canvasMouseClicked

    public static void main(String args[])
    {
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Canvas canvas;
    private JComboBox<String> componentMenu;
    protected JComboBox<String> environmentMenu;
    private JTextArea log;
    // End of variables declaration//GEN-END:variables

    private int componentIndex;
    private final ArrayList<Component> componentList;
    
    private int clickCount;
    private Component source;
    private Component sink;    
    
    protected Graph graph; 
    
    protected String fileName;    
}
