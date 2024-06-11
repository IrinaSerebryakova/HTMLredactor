package actions;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.event.ActionEvent;

public class UndoAction extends AbstractAction {
    private View view;

    public UndoAction(View view){
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            view.undo();
    }
}
