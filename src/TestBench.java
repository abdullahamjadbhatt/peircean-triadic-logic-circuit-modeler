import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *This class implements test bench creation and timing diagram output for the MainFrame.
 *<p>It uses WaveDrom JavaScript API to create timing diagrams. The embedding html
 * code for WaveDrom API can be found here: https://github.com/drom/wavedrom</p>
 * @version 7.0
 */
public class TestBench extends JFrame
{
    public TestBench(MainFrame parent, int numInputs, ArrayList<ArrayList<String>> table)
    {
        initComponents();
        this.setExtendedState(Frame.MAXIMIZED_HORIZ);
        this.parent = parent;
        this.truthTable = new ArrayList();
        for(int i = 0; i < table.get(0).size(); i++)
        {
            ArrayList<String> data = new ArrayList();
            data.add(table.get(0).get(i));
            for(int j = 1; j < table.size(); j++)
            {
                data.add(table.get(j).get(i));
            }
            this.truthTable.add(data);
        }
        this.numInputs = numInputs;
        this.indexMap = new HashMap();
        
        for(int i = 1; i < this.truthTable.get(0).size(); i++)
        {
            StringBuilder s = new StringBuilder();
            for(int j = 0; j < this.numInputs; j++)
            {
                s.append(this.truthTable.get(j).get(i));
            }
            this.indexMap.put(s.toString(), i);
        }
        
        for(int i = 0; i < this.numInputs; i++)
        {
            JPanel Panel = new JPanel();
            Panel.setLayout(new BorderLayout());            
            JLabel Label = new JLabel(table.get(0).get(i));
            Label.setFont(new Font("Tahoma", 1, 12));
            Label.setHorizontalAlignment(SwingConstants.TRAILING);
            JTextField Field = new JTextField(10);
            Panel.add(Label, BorderLayout.WEST);
            Panel.add(Field, BorderLayout.EAST);
            Panel.setMaximumSize(Panel.getPreferredSize());
            this.inputPanel.add(Panel);
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
                TestBench.this.setVisible(false);
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
    }

    @SuppressWarnings("unchecked")
    private void initComponents()//GEN-BEGIN:initComponents
    {

        inputScroll = new JScrollPane();
        inputPanel = new JPanel();
        run = new JButton();
        errorDisplay = new JLabel();
        JLabel inputPanelLabel = new JLabel();
        JLabel outputPanelLabel = new JLabel();
        outputPanel = new WaveFormPanel();
        autoGenerate = new JButton();
        saveWaveForm = new JButton();

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Test Bench");
        setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());

        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputScroll.setViewportView(inputPanel);

        run.setText("Run");
        run.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                runActionPerformed(evt);
            }
        });

        errorDisplay.setForeground(Color.RED);

        inputPanelLabel.setFont(new Font("Tahoma", 1, 12)); // NOI18N
        inputPanelLabel.setText("Input Streams");

        outputPanelLabel.setFont(new Font("Tahoma", 1, 12)); // NOI18N
        outputPanelLabel.setText("Timing Diagram");

        autoGenerate.setText("Auto-Generate");
        autoGenerate.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                autoGenerateActionPerformed(evt);
            }
        });

        saveWaveForm.setText("Save WaveForm");
        saveWaveForm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                saveWaveFormActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(inputScroll, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(run)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(autoGenerate))
                    .addComponent(inputPanelLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(outputPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(outputPanelLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 574, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(errorDisplay, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveWaveForm)))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(inputPanelLabel)
                    .addComponent(outputPanelLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(inputScroll, GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                    .addComponent(outputPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(errorDisplay, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveWaveForm, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(autoGenerate)
                        .addComponent(run)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }//GEN-END:initComponents

    private void runActionPerformed(ActionEvent evt)//GEN-FIRST:event_runActionPerformed
    {//GEN-HEADEREND:event_runActionPerformed
        int streamLength = 0;
        boolean valid = true;
        
        for(int i = 0; i < this.inputPanel.getComponentCount() && valid; i++)
        {
            JPanel ithPanel = (JPanel) this.inputPanel.getComponent(i);
            JTextField ithField = (JTextField) ithPanel.getComponent(1);
            ithField.setText(ithField.getText().toUpperCase());
            StringBuilder Str = new StringBuilder(ithField.getText());
            
            if(i == 0)
            {
                streamLength = Str.length(); 
            }
            
            if(Str.length() != streamLength)
            {
                this.errorDisplay.setText("Streams should be of equal length.");
                valid = false;
                break;
            }

            for(int j = 0; j < Str.length(); j++)
            {
                if(Str.charAt(j) != 'v' && Str.charAt(j) != 'l' && Str.charAt(j) != 'f' &&
                   Str.charAt(j) != 'V' && Str.charAt(j) != 'L' && Str.charAt(j) != 'F')
                {
                    this.errorDisplay.setText("Undefined values were found.");
                    valid = false;
                    break;
                }
            }
        }
        
        if(valid)
        {
            this.errorDisplay.setText("");
            this.evaluate();
        }
    }//GEN-LAST:event_runActionPerformed

    private void autoGenerateActionPerformed(ActionEvent evt)//GEN-FIRST:event_autoGenerateActionPerformed
    {//GEN-HEADEREND:event_autoGenerateActionPerformed
        Random rand = new Random();
        int min = 5;
        int max = 20;
        int streamLength = rand.nextInt((max - min) + 1) + min;
        
        for(int i = 0; i < this.inputPanel.getComponentCount(); i++)
        {
            JPanel ithPanel = (JPanel) this.inputPanel.getComponent(i);
            JTextField ithField = (JTextField) ithPanel.getComponent(1);
            StringBuilder sequence = new StringBuilder();
            
            for(int j = 0; j < streamLength; j++)
            {
                int value = rand.nextInt(3);
                sequence.append((value == 0) ? 'V' : (value == 1) ? 'L' : (value == 2) ? 'F' : '0');
            }
            
            ithField.setText(sequence.toString());
        }
        
        this.runActionPerformed(evt);
    }//GEN-LAST:event_autoGenerateActionPerformed

    private void saveWaveFormActionPerformed(ActionEvent evt)//GEN-FIRST:event_saveWaveFormActionPerformed
    {//GEN-HEADEREND:event_saveWaveFormActionPerformed
        WaveFormPrompt prompt = new WaveFormPrompt(this);
        prompt.setVisible(true);
        
        if(this.fileName != null)
        {
            if(!this.outputPanel.toHTML(this.parent, this.fileName))
            {
                this.errorDisplay.setText("Convert waveform to SVG first by right clicking on the image.");
            }
            else
            {
                this.errorDisplay.setText("");
            }
        }
    }//GEN-LAST:event_saveWaveFormActionPerformed
    
    private void evaluate()
    {
        StringBuilder executive = new StringBuilder();
        executive.append("<header>\n" +
                        " <script src=\"http://wavedrom.com/skins/default.js\"></script>\n" +
                        " <script src=\"http://wavedrom.com/WaveDrom.js\"></script>\n" +
                        "</header>\n" +
                        "<body onload=\"WaveDrom.ProcessAll()\">\n" +
                        "\n" +
                        "<script type=\"WaveDrom\">\n" +
                        "{ signal : [");
        
        for(int i = 0; i < this.inputPanel.getComponentCount(); i++)
        {
            JPanel ithPanel = (JPanel) this.inputPanel.getComponent(i);
            JLabel ithLabel = (JLabel) ithPanel.getComponent(0);
            JTextField ithField = (JTextField) ithPanel.getComponent(1);
            StringBuilder sequence = new StringBuilder(ithField.getText());
            StringBuilder wavedromSequence = new StringBuilder(ithField.getText());
            
            for(int j = 0; j < sequence.length(); j++)
            {
                char c = (sequence.charAt(j) == 'V') ? 'h' : (sequence.charAt(j) == 'L') ? 'x' : (sequence.charAt(j) == 'F') ? 'l' : '0';
                
                if(j == 0)
                {
                    wavedromSequence.setCharAt(j, c);
                }
                else
                {
                    if(sequence.charAt(j) == sequence.charAt(j-1))
                    {
                        wavedromSequence.setCharAt(j, '.');
                    }
                    else
                    {
                        wavedromSequence.setCharAt(j, c);
                    }
                }                
            }
            
            executive.append("{ name: \"").append(ithLabel.getText()).append("\", wave: \"").append(wavedromSequence.toString()).append("\" },");
        }
        
        ArrayList<String> inputStreams = new ArrayList();

        for(int i = 0; i < this.inputPanel.getComponentCount(); i++)
        {
            JPanel ithPanel = (JPanel) this.inputPanel.getComponent(i);
            JTextField ithField = (JTextField) ithPanel.getComponent(1);
            inputStreams.add(ithField.getText());
        }
        
        for(int i = this.numInputs; i < this.truthTable.size(); i++)
        {
            String outputName = this.truthTable.get(i).get(0);
            StringBuilder outputStream = new StringBuilder();
            for(int j = 0; j < inputStreams.get(0).length(); j++)
            {
                StringBuilder s = new StringBuilder();
                for(int k = 0; k < inputStreams.size(); k++)
                {
                    s.append(inputStreams.get(k).charAt(j));
                }
                outputStream.append(this.truthTable.get(i).get(this.indexMap.get(s.toString())));
            }

            StringBuilder wavedromSequence = new StringBuilder(outputStream);
            for(int l = 0; l < outputStream.length(); l++)
            {
                char c = (outputStream.charAt(l) == 'V') ? 'h' : (outputStream.charAt(l) == 'L') ? 'x' : (outputStream.charAt(l) == 'F') ? 'l' : '0';
                
                if(l == 0)
                {
                    wavedromSequence.setCharAt(l, c);
                }
                else
                {
                    if(outputStream.charAt(l) == outputStream.charAt(l-1))
                    {
                        wavedromSequence.setCharAt(l, '.');
                    }
                    else
                    {
                        wavedromSequence.setCharAt(l, c);
                    }
                }                
            }
            
            executive.append("{ name: \"").append(outputName).append("\", wave: \"").append(wavedromSequence.toString()).append("\" },");
        }
        
        executive.append("], head:{\n" + "tick:0,\n" + " }}");
        executive.append("</script>\n");
        executive.append("</body>");        
        
        outputPanel.load(executive.toString());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton autoGenerate;
    private JLabel errorDisplay;
    private JPanel inputPanel;
    private JScrollPane inputScroll;
    private WaveFormPanel outputPanel;
    private JButton run;
    private JButton saveWaveForm;
    // End of variables declaration//GEN-END:variables
    private final ArrayList<ArrayList<String>> truthTable;
    private final HashMap<String, Integer> indexMap;
    private final int numInputs;
    public String fileName;
    private final MainFrame parent;
}
