package listeners;

import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameListener extends WindowAdapter { //класс для удобства создания объектов-прослушивателей
    private View view;

   public FrameListener(){

   }
    @Override
    public void windowClosing(WindowEvent e){ // Вызывается, когда окно находится в процессе закрытия.

          }
    @Override
    public void windowClosed(WindowEvent e) {

    }
    @Override
    public void windowOpened(WindowEvent e) {

    }
    @Override
    public void windowIconified(WindowEvent e) {

    }
    @Override
    public void windowDeiconified(WindowEvent e) {

    }
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
    @Override
    public void windowStateChanged(WindowEvent e){

    }
}
