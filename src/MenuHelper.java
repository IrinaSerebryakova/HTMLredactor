import listeners.FrameListener;

import javax.swing.*;
import javax.swing.text.StyleConstants;
import java.awt.event.ActionListener;

public class MenuHelper {   //вспомогательный класс для инициализации и настройки меню
    FrameListener listener = new FrameListener();
    JMenu style = new JMenu("Стиль");
/*    JMenuItem italic = new JMenuItem("Курсив");
    JMenuItem underlined = new JMenuItem("Подчеркнутый");
    JMenuItem subscript = new JMenuItem("Подстрочный знак");
    JMenuItem superscript = new JMenuItem("Надстрочный знак");
    JMenuItem crossed = new JMenuItem("Зачеркнутый");*/

    JMenu file = new JMenu("Файл");
    JMenu edit = new JMenu("Редактировать");
    JMenu alignment = new JMenu("Выравнивание");
    JMenu color = new JMenu("Цвет");
    JMenu font = new JMenu("Шрифт");
    JMenu help = new JMenu("Помощь");

    //метод, где parent - меню в котором мы добавляем пункт, text -текст добавляемого пункта,
    // actionListener - слушатель действий добавляемого пункта меню.
    public static JMenuItem addMenuItem (JMenu parent, String text, ActionListener actionListener) {
        JMenu newMenu = new JMenu(text);      // Создавать новый пункт меню, используя text.
        newMenu.addActionListener(actionListener);  // Устанавливать этому пункту слушателя действий с помощью метода addActionListener().
        parent.add(text);                     // Добавлять в parent созданный пункт меню.
        return newMenu;                          //  Возвращать созданный пункт меню.
    }

    public static JMenuItem addMenuItem(JMenu parent, Action action){ // в качестве параметра принимает действие action, которое необходимо выполнить при выборе пункта меню.
        parent.setActionCommand(action.toString());
        return parent;
    }
    public static JMenuItem addMenuItem(JMenu parent, String text, Action action){
        JMenu newMenu = new JMenu(text);     //добавляет в parent новый пункт меню с текстом text и действием action.
        parent.add(newMenu);                   // Добавлять в parent созданный пункт меню.
        addMenuItem(newMenu, action);      // При реализации используй вызов метода из предыдущего пункта.
        return newMenu;
    }

/*    String[] menuFile = {"Новый","Открыть","Сохранить","Сохранить как...","Выход" };
    String[] menuEdit = {"Отменить","Вернуть","Вырезать","Копировать","Вставить"};
    String[] menuAlignment = {"По левому краю","По центру","По правому краю"};
    String[] menuColor = {"Красный","Оранжевый","Желтый","Зеленый","Синий","Голубой","Пурпурный","Черный"};
    String[] menuFont = {"Шрифт","Размер шрифта"};
    String[] menuFontOfFontes = {"SansSerif","Serif","Monospaced","Dialog","DialogInput"};
    String[] menuSizeOfFontes = {"6","8","10", "12", "14", "16", "20", "24", "32", "36", "48", "72"};
    String[] menuHelp = {"О программе"};*/




    public static void initHelpMenu(View view, JMenuBar menuBar) { //- инициализация меню помощи.
    }
    public static void initFontMenu(View view, JMenuBar menuBar) { // - инициализация меню выбора шрифта.
    }
    public static void initColorMenu(View view, JMenuBar menuBar) { // - инициализация меню выбора цвета.
    }
    public static void initAlignMenu(View view, JMenuBar menuBar) { // - инициализация меню выравнивания.
    }
    public static void initStyleMenu(View view, JMenuBar menuBar) { // - инициализация меню выбора стиля текста.
    }
    public static void initEditMenu(View view, JMenuBar menuBar) { // - инициализация меню редактирования текста.
    }
    public static void initFileMenu(View view, JMenuBar menuBar) { // - инициализация меню Файл.
    }


}


