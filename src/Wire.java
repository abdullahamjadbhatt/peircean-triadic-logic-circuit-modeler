/**
 *This class records wires between the components.
 * @version 7.0
 */

public class Wire
{
    public Wire(Component source, Component sink, ConnectLine line)
    {
        this.source = source;
        this.sink = sink;
        this.line = line;
    }
    
    protected Component source;
    protected Component sink;
    protected ConnectLine line;
}
