import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *This class displays information about Peircean triadic logic and this application.
 * @version 7.0
 */

public class About extends JFrame
{
    public About()
    {
        initComponents();
    }

    private void initComponents()//GEN-BEGIN:initComponents
    {

        JScrollPane informationScroll = new JScrollPane();
        JTextPane information = new JTextPane();
        JLabel logo = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About");
        setIconImage(new ImageIcon(getClass().getResource("images/icon.png")).getImage());

        information.setEditable(false);
        information.setContentType("text/html"); // NOI18N
        information.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        information.setText("<html>\n<body>\n<h2>Introduction:</h2>\n<p style=\"text-align:justify\">Peircean Triadic logic is a system of logic that recognizes that every proposition, Ɐ S is P, is either true, or false, or else has a lower mode of being such that it is neither determinately P, \n       nor determinately not P, but is at the limit between P and not P.</p>\n<p style=\"text-align:justify\">This system of logic provides a universal framework in digital system design and analysis. It realizes that digital systems vary in the degree of uncertainty of the environment they are deployed in. \n       It classifies the underlying environment as being of high, moderate or low degree of indeterminacy. Equipped with three distinct sets of operators, it has the ability to deliver a digital system best \n       suited for the underlying environment.</p>\n<p style=\"text-align:justify\">The motivation behind this application is the fact that Peircean gates have multiple cases. What this implies is that there are going to be multiple ways a Peircean combinational circuit can be conceived.\n       For example, a Peircean half adder has 2 Peircean-NOT gates, 3 Peircean-AND gates and 1 Peircean-OR gate. Given that Peircean-AND and Peircean-OR have 3 cases each and Peircean-NOT has \n      4 cases, there are a total of (2x4)x(3x3)x(1x3) = 216 configurations. Using this application, we will be able to deploy any number of any kind of gates, make connections between them and design \n      any possible configuration for any triadic combinational circuit.</p>\n</body>\n</html>");
        informationScroll.setViewportView(information);

        logo.setIcon(new ImageIcon(getClass().getResource("images/icon.png")));
        logo.setBackground(new Color(255, 255, 255));
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setOpaque(true);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logo, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(informationScroll, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(informationScroll, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                    .addComponent(logo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
