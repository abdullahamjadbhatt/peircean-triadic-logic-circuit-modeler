import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *This class prompts the user to open or save a design file.
 * @version 7.0
 */

public class DesignPrompt extends JDialog
{
    public DesignPrompt(MainFrame parent, int mode) 
    {
        super(parent);
        if(mode == 0)
        {
            super.setTitle("Save Design");
        }
        else
        {
            super.setTitle("Open Design");
        }
        
        super.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
        super.setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());
        
        this.fileFilter = new FileNameExtensionFilter("XML files", "xml");
        
        this.parent = parent;
        
        this.prompt = new JFileChooser();
        if(mode == 0)
        {
            this.prompt.setDialogType(JFileChooser.SAVE_DIALOG);
        }
        else
        {
            this.prompt.setDialogType(JFileChooser.OPEN_DIALOG);
        }        
        this.prompt.setBackground(new Color(255, 255, 255));
        this.prompt.setCurrentDirectory(new File("C:\\Designs"));
        this.prompt.setFileFilter(this.fileFilter);
        this.prompt.setFont(new Font("Tahoma", 0, 12));
        this.prompt.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent evt) 
            {
                if(evt.getActionCommand().contentEquals(JFileChooser.CANCEL_SELECTION))
                {
                    DesignPrompt.this.parent.fileName = null;
                    DesignPrompt.this.dispose();
                }
                else if(evt.getActionCommand().contentEquals(JFileChooser.APPROVE_SELECTION))
                {
                    DesignPrompt.this.parent.fileName = DesignPrompt.this.prompt.getSelectedFile().toString();
                    DesignPrompt.this.dispose();
                }
            }
        });

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(this.prompt, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(this.prompt, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
        );

        pack();        
        super.setLocationRelativeTo(null);
    }
    
    private final FileFilter fileFilter;
    private final JFileChooser prompt;
    
    private final MainFrame parent;
}
