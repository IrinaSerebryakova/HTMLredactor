package HTMLpackage;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class View extends JFrame implements ActionListener {
    static View view = new View();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void undo() {                        // отменяет последнее действие. Реализуй его используя undoManager.
        try {
            if (view.canUndo()) {
                undoManager.undo(); // Метод не должен кидать исключений, логируй их.
            }
        } catch (Exception e) {
            ExceptionHandler.log(e);              // Проследи, чтобы метод не кидал исключения. Их необходимо просто логировать.
        }
    }

    public void redo() {                       // возвращает ранее отмененное действие. Реализуй его по аналогии с предыдущим пунктом.
        try {
            if (view.canRedo()) {
                undoManager.redo();   // Метод не должен кидать исключений, логируй их.
            }
        } catch (Exception e) {
            ExceptionHandler.log(e);              // Проследи, чтобы метод не кидал исключения. Их необходимо просто логировать.
        }
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public void resetUndo() {    //должен сбрасывать все правки в менеджере undoManager.
        undoManager = new UndoManager();
    }

    public boolean isHtmlTabSelected() {   //если выбрана вкладка, отображающая html в панели вкладок (подсказка: ее индекс 0).
        if (view.tabbedPane.getTabCount() == 0) ;
        return true;
    }

    public void selectHtmlTab() {
        if (!isHtmlTabSelected()) {
            tabbedPane.setComponentAt(0, htmlTextPane);
        }
        // Выбирать html вкладку (переключаться на нее).
        resetUndo();         // Сбрасывать все правки с помощью метода, который ты реализовал ранее.
    }



       public void update(){     // должен получать документ у контроллера и устанавливать его в панель редактирования htmlTextPane.
           htmlTextPane.setDocument(controller.getHtmlDocument());
       }

       private JButton about = null;
       private JOptionPane aboutOptionPane = null;
    public void showAbout() {
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Включение в интерфейс иконки
               showMessageDialog(aboutOptionPane,
                        "Здесь выводится информация о программе",
                        "Информация", JOptionPane.INFORMATION_MESSAGE);
            }
        });

           //должен показывать диалоговое окно с информацией о программе.
           //Информацию придумай сам, а вот тип сообщения должен быть JOptionPane.INFORMATION_MESSAGE.
    }
    Controller controller = new Controller(view);

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void init(){
        initGui();
        FrameListener listener = new FrameListener(this);
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
        @Override
        public void actionPerformed(ActionEvent e) {  // будет вызваться при выборе пунктов меню, у которых наше представление указано в виде слушателя событий.
            String str = e.getActionCommand(); //Получи из события команду с помощью метода. Это будет обычная строка.
            // По этой строке ты можешь понять какой пункт меню создал данное событие.
            try {
                switch (str) {
                    case "Новый":
                        controller.createNewDocument();   //В этом пункте и далее, если необходимого метода в контроллере еще нет - создай заглушки.
                        break;
                    case "Открыть":
                        controller.openDocument();
                        break;
                    case "Сохранить":
                        controller.saveDocument();
                        break;
                    case "Сохранить как...":
                        controller.saveDocumentAs();
                        break;
                    case "Выход":
                        controller.exit();
                        break;
                    case "О программе":
                        view.showAbout();
                        break;
                }
            } catch (IOException ex) {
                ExceptionHandler.log(ex);
            }
        }
        public void initMenuBar(){  // инициализация меню
            JMenuBar menuBar = new JMenuBar();
            view.getContentPane().add(menuBar);
            menuHelper.initFileMenu(view,menuBar);
            menuHelper.initEditMenu(view,menuBar);
            menuHelper.initFontMenu(view,menuBar);
            menuHelper.initAlignMenu(view,menuBar);
            menuHelper.initColorMenu(view,menuBar);
            menuHelper.initStyleMenu(view,menuBar);
            menuHelper.initHelpMenu(view,menuBar);
        }

    public Dimension getPreferredSize() {
        return new Dimension(5000, 5000);
    }
    public void initEditor() {  // инициализация панелей редактора
        htmlTextPane.setContentType("text/html"); // Устанавливать тип контента для компонента
        JScrollPane htmlScrollPane = new JScrollPane(htmlTextPane); // локальный компонент прокрутки
        tabbedPane.addTab("HTML", htmlScrollPane); // Добавляем вкладку в панель с именем "HTML" и компонентом из предыдущего пункта.
        JScrollPane textScrollPane = new JScrollPane(plainTextPane); // новый локальный компонент на базе plainTextPane.
        tabbedPane.addTab("Текст", textScrollPane); //Добавляем еще одну вкладку в tabbedPane с именем "Текст" и компонентом из предыдущего пункта.
        tabbedPane.setPreferredSize(tabbedPane.getPreferredSize()); //Устанавливаем предпочтительный размер панели
        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
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
        if(tabbedPane.getSelectedIndex() == 0){    //проверить, какая вкладка сейчас оказалась выбранной.
            // Если выбрана вкладка с индексом 0 (html вкладка), нам нужно получить текст из plainTextPane и установить его в контроллер с помощью метода setPlainText.
            controller.setPlainText(plainTextPane.getText());
        }
        if(tabbedPane.getSelectedIndex() == 1){  //Если выбрана вкладка с html текстом, необходимо получить текст у контроллера с помощью метода getPlainText() и установить его в панель plainTextPane.
            controller.setPlainText(controller.getPlainText());
        }
       resetUndo();    //Сбросить правки (вызвать метод  представления).
    }
}
