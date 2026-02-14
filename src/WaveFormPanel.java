import com.sun.javafx.application.PlatformImpl;
import java.awt.BorderLayout;
import java.io.File;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JPanel;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *This utility class implements timing diagram generator in TestBench frame.
 * <p> It has been adopted from Andy Jackson's projects on GitHub titled . 
 * "Embedding a JavaFX WebView in a Swing panel" on this URL: https://gist.github.com/anjackson/1640654 </p>
 * @version 7.0
 */
public class WaveFormPanel extends JPanel 
{
    public WaveFormPanel()
    {  
        initComponents();  
    }  
     
    private void initComponents()
    {       
        jfxPanel = new JFXPanel();  
        createScene();  
         
        setLayout(new BorderLayout());  
        add(jfxPanel, BorderLayout.CENTER);
        isSVG = false;
    }     
     
    private void createScene() 
    {  
        PlatformImpl.startup(new Runnable() 
        {
            @Override
            public void run() 
            {   
                stage = new Stage();  
                stage.setResizable(true);  
   
                Group root = new Group();  
                Scene scene = new Scene(root,80,20);  
                stage.setScene(scene);  
                 
                browser = new WebView();
                webEngine = browser.getEngine();
                webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() 
                {
                    @Override
                    public void changed(ObservableValue ov, State oldState, State newState) 
                    {
                        if (newState == State.SUCCEEDED) 
                        {
                            Document doc = webEngine.getDocument();
                            NodeList nodeList = doc.getElementsByTagName("HTML");
                            if(nodeList.getLength() == 0)
                            {
                                isSVG = true;
                            }
                        }
                    }
                });                
                webEngine.loadContent("Enter streams.....");

                
                ObservableList<Node> children = root.getChildren();
                children.add(browser);                     
                 
                jfxPanel.setScene(scene); 
            }  
        });
    }
    
    protected void load(final String code) 
    {
        Platform.runLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                webEngine.loadContent(code);
            }
        });
    }
    
    protected boolean toHTML(MainFrame parent, String fileName)
    {
        if(isSVG)
        {
            try 
            {
                Document doc = webEngine.getDocument();
                TransformerFactory transFactory = TransformerFactory.newInstance();
                Transformer transformer = transFactory.newTransformer();
                DOMSource docSource = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(fileName + ".html"));
                transformer.transform(docSource, result);
                parent.notify("Message: " + fileName + ".html" + " saved.");
            } 
            catch(TransformerException ex) 
            {
                parent.notify("Error: " + ex.getMessage());
            }
        }
        return isSVG;
    }
    
    private Stage stage;  
    private WebView browser;  
    private JFXPanel jfxPanel;  
    private WebEngine webEngine;
    private boolean isSVG;
}
