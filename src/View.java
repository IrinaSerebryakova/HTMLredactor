import listeners.*;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Position;
import javax.swing.text.StyleConstants;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    static View view = new View();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public static boolean canUndo(){

        return false;
    }
    public static boolean canRedo(){

        return false;
    }
    public void undo(){                        // отменяет последнее действие. Реализуй его используя undoManager.
        if(undoManager.canUndo() == true){     // Метод не должен кидать исключений, логируй их.

        }
    }

    public void redo() {                       // возвращает ранее отмененное действие. Реализуй его по аналогии с предыдущим пунктом.
        if(undoManager.canRedo() == true){     // Метод не должен кидать исключений, логируй их.

        }
    }
    public UndoListener getUndoListener() {
        return undoListener;
    }
     public void resetUndo(){    //должен сбрасывать все правки в менеджере undoManager.

     }

    public boolean isHtmlTabSelected(){   //если выбрана вкладка, отображающая html в панели вкладок (подсказка: ее индекс 0).
        if (view.tabbedPane.getTabCount() == 0);
          return true;
    }

    public selectHtmlTab() {
                         // Выбирать html вкладку (переключаться на нее).
                            // Сбрасывать все правки с помощью метода, который ты реализовал ранее.
    }

       public void update(){     // должен получать документ у контроллера и устанавливать его в панель редактирования htmlTextPane.
           htmlTextPane.setDocument(controller.getHtmlDocument());
       }

    public void showAbout() {
        public JOptionPane.INFORMATION_MESSAGE = "Использование изображения в окне сообщений";   //должен показывать диалоговое окно с информацией о программе.
           //Информацию придумай сам, а вот тип сообщения должен быть JOptionPane.INFORMATION_MESSAGE.
    }
    Controller controller = new Controller(view);

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void actionPerformed(ActionEvent e){

    }

    public void init(){
        initGui();
        FrameListener listener = new FrameListener();
        view.addWindowListener(listener);
        setVisible(true);
    }
    public void exit(){
        controller.exit();
    }

    JTabbedPane tabbedPane = new JTabbedPane(); // панель с вкладками
    JTextPane htmlTextPane = new JTextPane();  // панель для визуального редактирования html
   JEditorPane plainTextPane = new JEditorPane(); // будет отображать код html (теги и их содержимое)

    MenuHelper menuHelper = new MenuHelper();
    private ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {  // будет вызваться при выборе пунктов меню, у которых наше представление указано в виде слушателя событий.
            String str = e.getActionCommand(); //Получи из события команду с помощью метода. Это будет обычная строка.
            // По этой строке ты можешь понять какой пункт меню создал данное событие.
            switch (str) {
                case "Новый":
                    controller.createNewDocument();   //В этом пункте и далее, если необходимого метода в контроллере еще нет - создай заглушки.
                case "Открыть":
                    controller.openDocument();
                case "Сохранить":
                    controller.saveDocument();
                case "Сохранить как...":
                    controller.saveDocumentAs();
                case "Выход":
                    controller.exit();
                case "О программе":
                    view.showAbout();
            }
        }
    public void initMenuBar(){  // инициализация меню
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(menuHelper.file);
        MenuHelper.addMenuItem(menuHelper.file, "Новый", actionListener);
        MenuHelper.addMenuItem(menuHelper.file, "Открыть", actionListener);
        MenuHelper.addMenuItem(menuHelper.file, "Сохранить", actionListener);
        MenuHelper.addMenuItem(menuHelper.file, "Сохранить как...", actionListener);
        MenuHelper.addMenuItem(menuHelper.file, "Выход", actionListener);

        menuBar.add(menuHelper.edit);
        MenuHelper.addMenuItem(menuHelper.edit, "Отменить", actionListener);
        MenuHelper.addMenuItem(menuHelper.edit, "Вернуть", actionListener);
        MenuHelper.addMenuItem(menuHelper.edit, "Вырезать", actionListener);
        MenuHelper.addMenuItem(menuHelper.edit, "Копировать", actionListener);
        MenuHelper.addMenuItem(menuHelper.edit, "Вставить", actionListener);

        menuBar.add(menuHelper.style);
        // javax.swing.text.StyleConstants
        ALIGN_CENTER, ALIGN_JUSTIFIED, ALIGN_LEFT, ALIGN_RIGHT, Alignment, Background, BidiLevel, Bold,
                ComponentAttribute, ComponentElementName, ComposedTextAttribute, Family, FirstLineIndent, FontFamily,
                FontSize, Foreground, IconAttribute, IconElementName, Italic, LeftIndent, LineSpacing, ModelAttribute,
                NameAttribute, Orientation, ResolveAttribute, RightIndent, Size, SpaceAbove, SpaceBelow, StrikeThrough, Subscript, Superscript, TabSet, Underline
        getAlignment, getBackground, getBidiLevel, getComponent, getFirstLineIndent, getFontFamily, getFontSize, getForeground, getIcon, getLeftIndent, getLineSpacing, getRightIndent, getSpaceAbove, getSpaceBelow, getTabSet, isBold, isItalic, isStrikeThrough, isSubscript, isSuperscript, isUnderline, setAlignment, setBackground, setBidiLevel, setBold, setComponent, setFirstLineIndent, setFontFamily, setFontSize, setForeground, setIcon, setItalic, setLeftIndent, setLineSpacing, setRightIndent, setSpaceAbove, setSpaceBelow, setStrikeThrough, setSubscript, setSuperscript, setTabSet, setUnderline, toString
        MenuHelper.addMenuItem(menuHelper.style, "Курсив", actionListener);
        MenuHelper.addMenuItem(menuHelper.style, "Подчеркнутый", actionListener);
        MenuHelper.addMenuItem(menuHelper.style, "Подстрочный знак", actionListener);
        MenuHelper.addMenuItem(menuHelper.style, "Надстрочный знак", actionListener);
        MenuHelper.addMenuItem(menuHelper.style, "Зачеркнутый", actionListener);



        menuHelper.alignment.add(String.valueOf(StyleConstants.ALIGN_CENTER));
        menuHelper.alignment.add(String.valueOf(StyleConstants.ALIGN_LEFT));
        menuHelper.alignment.add(String.valueOf(StyleConstants.ALIGN_RIGHT));
        MenuHelper.addMenuItem(menuHelper.alignment, "По левому краю", actionListener);
        MenuHelper.addMenuItem(menuHelper.alignment, "По центру", actionListener);
        MenuHelper.addMenuItem(menuHelper.alignment, "По правому краю", actionListener);
        menuBar.add(menuHelper.alignment);

        MenuHelper.addMenuItem(menuHelper.color, "Красный", actionListener);
        MenuHelper.addMenuItem(menuHelper.color, "Оранжевый", actionListener);
        MenuHelper.addMenuItem(menuHelper.color, "Желтый", actionListener);
        MenuHelper.addMenuItem(menuHelper.color, "Зеленый", actionListener);
        MenuHelper.addMenuItem(menuHelper.color, "Синий", actionListener);
        MenuHelper.addMenuItem(menuHelper.color, "Голубой", actionListener);
        MenuHelper.addMenuItem(menuHelper.color, "Пурпурный", actionListener);
        MenuHelper.addMenuItem(menuHelper.color, "Черный", actionListener);
        menuBar.add(menuHelper.color);

        MenuHelper.addMenuItem(menuHelper.font, "Шрифт", actionListener);
        MenuHelper.addMenuItem(menuHelper.font, "Размер шрифта", actionListener);
        menuBar.add(menuHelper.font);

        MenuHelper.addMenuItem(menuHelper.font,"SansSerif", actionListener);


      /*  String[] menuFontOfFontes = {"SansSerif","Serif","Monospaced","Dialog","DialogInput"};
        String[] menuSizeOfFontes = {"6","8","10", "12", "14", "16", "20", "24", "32", "36", "48", "72"};
*/

        MenuHelper.addMenuItem(menuHelper.help, "О программе", actionListener);
        menuBar.add(menuHelper.help);

        view.getContentPane().add(menuBar);

    }


    public Dimension getPreferredSize() {
        return new Dimension(1000, 1000);
    }
    public void initEditor(){  // инициализация панелей редактора
        htmlTextPane.setContentType("text/html"); // Устанавливать тип контента для компонента
        JScrollPane htmlScrollPane = new JScrollPane(htmlTextPane); // локальный компонент прокрутки
        tabbedPane.addTab("HTML", htmlScrollPane); // Добавляем вкладку в панель с именем "HTML" и компонентом из предыдущего пункта.
        JScrollPane textScrollPane = new JScrollPane(plainTextPane); // новый локальный компонент на базе plainTextPane.
        tabbedPane.addTab("Текст", textScrollPane); //Добавляем еще одну вкладку в tabbedPane с именем "Текст" и компонентом из предыдущего пункта.
        tabbedPane.setPreferredSize(tabbedPane.getPreferredSize()); //Устанавливаем предпочтительный размер панели
        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener();
        tabbedPane.addChangeListener(tabbedPaneChangeListener); // создаем слушателя и устанавливаем его в качестве слушателя изменений в tabbedPane.
        view.getContentPane().add(tabbedPane); // Получили панель контента текущего фрейма с помощью метода, его реализация унаследовалась от JFrame.
                                                // Добавляем по центру панели контента текущего фрейма нашу панель с вкладками
        // После запуска приложения можно будет увидеть текущие результаты: две независимые закладки (HTML и Текст), в каждой из которых можно набирать свой текст.


    }

    public void initGui(){  // будет инициализировать графический интерфейс
        view.initMenuBar();
        view.initEditor();
        view.setDefaultLookAndFeelDecorated(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.pack();   //	Позволяет «упаковать» имеющиеся в окне компоненты, так чтобы они занимали столько места, сколько им необходимо.
        view.setVisible(true);          // Компоненты при вызове этого метода переходят в «видимое» состояние, хотя и не появляются на экране до вызова одного из следующих методов
    }


        public void selectedTabChanged(){    // Этот метод вызывается, когда произошла смена выбранной вкладки
        if(view.tabbedPane.getTabCount() == 0){    //проверить, какая вкладка сейчас оказалась выбранной.
            // Если выбрана вкладка с индексом 0 (html вкладка), нам нужно получить текст из plainTextPane и установить его в контроллер с помощью метода setPlainText.
            controller.setPlainText(String.valueOf(plainTextPane));
        }
        if(view.tabbedPane.getTabCount() == 1){  //Если выбрана вкладка с html текстом, необходимо получить текст у контроллера с помощью метода getPlainText() и установить его в панель plainTextPane.
            controller.setPlainText(String.valueOf(controller.getPlainText());
        }
        view.resetUndo();    //Сбросить правки (вызвать метод  представления).
    }
}
