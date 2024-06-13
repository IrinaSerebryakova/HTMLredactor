package HTMLpackage;

import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuHelper {   //вспомогательный класс для инициализации и настройки меню
    private JMenu jMenu;
    private JMenuItem jMenuItem;
    private static ActionListener actionListener;
    public MenuHelper() {

    }

    //метод, где parent - меню в котором мы добавляем пункт, text -текст добавляемого пункта,
    // actionListener - слушатель действий добавляемого пункта меню.
    public static JMenuItem addMenuItem (JMenu parent, String text, ActionListener actionListener) {
        JMenu newMenu = new JMenu(text);      // Создавать новый пункт меню, используя text.
        newMenu.addActionListener(actionListener);  // Устанавливать этому пункту слушателя действий с помощью метода addActionListener().
        parent.add(text);                     // Добавлять в parent созданный пункт меню.
        return newMenu;                          //  Возвращать созданный пункт меню.
    }

    public static JMenuItem addMenuItem(JMenu parent, Action action){ // в качестве параметра принимает
                                      // действие action, которое необходимо выполнить при выборе пункта меню.
        JMenuItem menuItem = new JMenuItem(action);
        parent.add(menuItem);
        return menuItem;

    }
    public static JMenuItem addMenuItem(JMenu parent, String text, Action action){
        JMenu newMenu = new JMenu(text);     //добавляет в parent новый пункт меню с текстом text и действием action.
        parent.add(newMenu);                   // Добавлять в parent созданный пункт меню.
        addMenuItem(newMenu, action);      // При реализации используй вызов метода из предыдущего пункта.
        return newMenu;
    }

    public static void initHelpMenu(View view, JMenuBar menuBar) { //- инициализация меню помощи.
        view.add(menuBar);
        JMenu help = new JMenu("Помощь");
        menuBar.add(help);
        help.add("О программе");
           }
    public static void initStyleMenu(View view, JMenuBar menuBar) { // - инициализация меню выбора шрифта.
        view.add(menuBar);
        JMenu styles = new JMenu("Шрифт");
        menuBar.add(styles);
        addMenuItem(styles,"Курсив",actionListener);
        addMenuItem(styles,"Подчеркнутый",actionListener);
        addMenuItem(styles,"Подстрочный знак",actionListener);
        addMenuItem(styles,"Надстрочный знак",actionListener);
        addMenuItem(styles,"Зачеркнутый",actionListener);
    }
    public static void initColorMenu(View view, JMenuBar menuBar) { // - инициализация меню выбора цвета.
        JMenu color = new JMenu("Цвет");
        menuBar.add(color);
                //Действие для установки цвета переднего плана.
        addMenuItem(color, new StyledEditorKit.ForegroundAction("Красный", Color.red));
        addMenuItem(color,new StyledEditorKit.ForegroundAction("Оранжевый",Color.orange));
        addMenuItem(color,new StyledEditorKit.ForegroundAction("Желтый",Color.yellow));
        addMenuItem(color,new StyledEditorKit.ForegroundAction("Зеленый",Color.green));
        addMenuItem(color,new StyledEditorKit.ForegroundAction("Синий",Color.blue));
        addMenuItem(color,new StyledEditorKit.ForegroundAction("Голубой",Color.cyan));
        addMenuItem(color,new StyledEditorKit.ForegroundAction("Пурпурный",Color.magenta));
        addMenuItem(color,new StyledEditorKit.ForegroundAction("Черный",Color.black));
        color.addMenuListener(new TextEditMenuListener(view));
    }
    public static void initAlignMenu(View view, JMenuBar menuBar) { // - инициализация меню выравнивания.
        JMenu alignment = new JMenu("Выравнивание");
        menuBar.add(alignment);
        addMenuItem(alignment,new StyledEditorKit.AlignmentAction("По левому краю",StyleConstants.ALIGN_LEFT));
        addMenuItem(alignment,new StyledEditorKit.AlignmentAction("По центру",StyleConstants.ALIGN_CENTER));
        addMenuItem(alignment,new StyledEditorKit.AlignmentAction("По правому краю",StyleConstants.ALIGN_RIGHT));
        alignment.addMenuListener(new TextEditMenuListener(view));
    }
    public static void initFontMenu(View view, JMenuBar menuBar) { // - инициализация меню выбора стиля текста.
        JMenu fontMenu = new JMenu("Шрифт");
        menuBar.add(fontMenu);

        JMenu fontTypeMenu = new JMenu("Шрифт");
        fontMenu.add(fontTypeMenu);

        String[] fontTypes = {Font.SANS_SERIF, Font.SERIF, Font.MONOSPACED, Font.DIALOG, Font.DIALOG_INPUT};
        for (String fontType : fontTypes) {
            addMenuItem(fontTypeMenu, fontType, new StyledEditorKit.FontFamilyAction(fontType, fontType));
        }

        JMenu fontSizeMenu = new JMenu("Размер шрифта");
        fontMenu.add(fontSizeMenu);

        String[] fontSizes = {"6", "8", "10", "12", "14", "16", "20", "24", "32", "36", "48", "72"};
        for (String fontSize : fontSizes) {
            addMenuItem(fontSizeMenu, fontSize, new StyledEditorKit.FontSizeAction(fontSize, Integer.parseInt(fontSize)));
        }

        fontMenu.addMenuListener(new TextEditMenuListener(view));
    }
    public static void initEditMenu(View view, JMenuBar menuBar) { // - инициализация меню редактирования текста.
        JMenu edit = new JMenu("Редактировать");
        menuBar.add(edit);
        addMenuItem(edit,"Отменить",new UndoAction(view));;
        addMenuItem(edit,"Вернуть",new RedoAction(view));;
        addMenuItem(edit,"Вырезать",new DefaultEditorKit.CutAction());
        addMenuItem(edit,"Копировать",new DefaultEditorKit.CopyAction());
        addMenuItem(edit,"Вставить",new DefaultEditorKit.PasteAction());
        edit.addMenuListener(new TextEditMenuListener(view));
    }
    public static void initFileMenu(View view, JMenuBar menuBar) { // - инициализация меню Файл.
        view.add(menuBar);
        JMenu file = new JMenu("Файл");
        menuBar.add(file);
        addMenuItem(file, "Новый", view);
        addMenuItem(file, "Открыть", view);
        addMenuItem(file, "Сохранить", view);
        addMenuItem(file, "Сохранить как...", view);
        file.addSeparator();
        addMenuItem(file, "Выход", view);
    }
}


