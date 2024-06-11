package listeners;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.text.View;

public class UndoMenuListener implements MenuListener { //Этот слушатель будет следить за меню, конкретно за моментом, когда меню редактирования будет выбрано пользователем.
    //В этот момент он будет запрашивать у представления можем ли мы сейчас отменить или вернуть какое-то действие,
    // и в зависимости от этого делать доступными или не доступными пункты меню "Отменить" и "Вернуть"
    private View view;
    private JMenuItem undoMenuItem = new JMenuItem("Отменить");
    private JMenuItem redoMenuItem = new JMenuItem("Вернуть");

    @Override
    public void menuSelected(MenuEvent e) {
        if(view.canUndo() == true) {
            undoMenuItem.setEnabled(true);
        }
        if(view.canRedo()== true) {
            redoMenuItem.setEnabled(true);
        }
    }

    @Override
    public void menuDeselected(MenuEvent e) {
    }
    @Override
    public void menuCanceled(MenuEvent e) {
    }
    public UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem){
        this.view = view;
        this.undoMenuItem = undoMenuItem;
        this.redoMenuItem = redoMenuItem;
    }

}


