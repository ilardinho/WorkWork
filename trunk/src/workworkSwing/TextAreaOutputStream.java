package workworkSwing;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;
/**
 * Joopajoo, en saanut tuota tulostushommelia mitenk‰‰n toimimaan ilman t‰t‰ mallia
 * @author ilardinho
 * versivo 10.2.2012
 */
public final class TextAreaOutputStream extends OutputStream {

    private final JTextArea textArea;
    
    /**
     * @param textArea
     */
    public TextAreaOutputStream(final JTextArea textArea) {
        this.textArea = textArea;
    }


    @Override
    public void write(int b) throws IOException {
        
        write(new byte[] {(byte) b}, 0, 1);
    }

    @Override
    public void write(byte b[], int off, int len) throws IOException {  
        textArea.append(new String(b, off, len));
    }


    /**
     * @param textArea
     * @return textArean tekstit
     */
    public static PrintStream getTextPrintStream(JTextArea textArea) {
        return new PrintStream(new TextAreaOutputStream(textArea)); 
    }

}

