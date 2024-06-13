package HTMLpackage;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.io.*;

import static javax.swing.text.html.HTMLEditorKit.*;

public class Controller {

    //- Добавить панель инструментов, повторяющую функционал меню.
    //- Добавить подсветку html тегов на второй вкладке.
    //- Добавить возможность загрузки документа из Интернет.
    //- Расширить возможности редактора (вставка картинки, ссылки и т.д.)

    private View view;
    private HTMLDocument htmlDocument;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public HTMLDocument getHtmlDocument() {
        return htmlDocument;
    }
    Component saveFile = new Component() {
        @Override
        public void setName(String name) {
            super.setName("Save File");
        }
    };

    HTMLEditorKit kit = new HTMLEditorKit();
    HTMLDocument document = null;
    UndoManager undoManager = new UndoManager();
    UndoListener undoListener = new UndoListener(undoManager);
    public void resetDocument() {
        if (document != null) {
                               //будет сбрасывать текущий документ
            htmlDocument.removeUndoableEditListener(view.getUndoListener());   // Удалять у текущего документа document слушателя правок,
                            // которые можно отменить/вернуть (найди подходящий для этого метод, унаследованный от AbstractDocument)
                     // Слушателя нужно запросить у представления (метод getUndoListener())
                               //Не забудь проверить, что текущий документ существует (не null)
            document = (HTMLDocument) kit.createDefaultDocument(); // Создавать новый документ по умолчанию и присваивать его полю document
                    // Подсказка: воспользуйся подходящим методом класса HTMLEditorKit
            document.addUndoableEditListener(view.getUndoListener());      // Добавлять новому документу слушателя правок
            view.update();                           // Вызывать у представления метод update()
        }
    }
    public void setPlainText (String text) {    //записывать переданный текст с html тегами в документ document
        resetDocument();                 //  Сбрось документ
        try {
            StringReader stringReader = new StringReader(text);    // Создай новый ридер StringReader на базе переданного текста.
            kit.read(stringReader, document, 0); // read() из класса HTMLEditorKit вычитает данные из ридера в документ document.
            stringReader.close();
        } catch (Exception e) {
            ExceptionHandler.log(e);                      // Проследи, чтобы метод не кидал исключения. Их необходимо просто логировать.
        }
    }
    public String getPlainText() {   // Он должен получать текст из документа со всеми html тегами.
        StringWriter stringWriter = new StringWriter();
        HTMLDocument document = (HTMLDocument) kit.createDefaultDocument();
        try {
            // Перепиши все содержимое из документа document в созданный объект с помощью метода write класса HTMLEditorKit.
            kit.write(stringWriter, document, 0, document.getLength());
            stringWriter.close();
        } catch (Exception e) {
            ExceptionHandler.log(e);              // Проследи, чтобы метод не кидал исключения. Их необходимо просто логировать.
        }
        return document.toString();
    }
    public void createNewDocument() {
        view.selectHtmlTab();      // Выбирать html вкладку у представления.
        resetDocument();           // Сбрасывать текущий документ.
        setPlainText("HTML редактор"); // Устанавливать новый заголовок окна, например: "HTML редактор". Воспользуйся методом, который унаследован в нашем представлении.
        currentFile = null;                           // Обнулить переменную currentFile (присвоить ей значение null).
    }

    public void openDocument(){  // Обрати внимание на имя метода для показа диалогового окна.
                                    //   Когда файл выбран, необходимо:
        try{
            FileReader fileReader = new FileReader(currentFile);             // Создать FileReader, используя currentFile.
        JFileChooser jFileChooser = new JFileChooser();        // Создавать новый объект для выбора файла JFileChooser.
        HTMLFileFilter htmlFileFilter = new HTMLFileFilter();
        jFileChooser.setFileFilter(htmlFileFilter);            // Устанавливать ему в качестве фильтра объект HTMLFileFilter.
        jFileChooser.showSaveDialog(saveFile);
                                             // Показывать диалоговое окно "Save File" для выбора файла.
        currentFile = new File(jFileChooser.getSelectedFile().toURI());          // Установить новое значение currentFile.
            view.setTitle(currentFile.getName());        // Установить имя файла в заголовок у представления.
            FileWriter fileWriter = new FileWriter(currentFile);
            resetDocument();               // Сбросить документ.
            new HTMLEditorKit().write(fileWriter,document,0,document.getLength());           // Вычитать данные из FileReader-а в документ document с помощью объекта класса HTMLEditorKit.
        // Сбросить правки (вызвать метод resetUndo представления).
        // Если возникнут исключения - залогируй их и не пробрасывай наружу.

            }catch (Exception e) {
                ExceptionHandler.log(e);              // Проследи, чтобы метод не кидал исключения. Их необходимо просто логировать.
            }
    }

    public void saveDocument() {
        //  Метод должен работать также, как saveDocumentAs(), но не запрашивать файл у пользователя, а использовать currentFile.
        try {
            if (currentFile == null) {
                saveDocumentAs();
            } else {
                view.selectHtmlTab();                                  // Переключать представление на html вкладку.
                FileWriter fileWriter = new FileWriter(currentFile);  // Создавать FileWriter на базе currentFile, если currentFile != null.
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
                fileWriter.close();
            }
        }catch (Exception e) {
                ExceptionHandler.log(e);              // Проследи, чтобы метод не кидал исключения. Их необходимо просто логировать.
            }
        }


    public void saveDocumentAs() throws IOException {
        view.selectHtmlTab();                             // Переключать представление на html вкладку.
        JFileChooser jFileChooser = new JFileChooser();        // Создавать новый объект для выбора файла JFileChooser.
        HTMLFileFilter htmlFileFilter = new HTMLFileFilter();
        jFileChooser.setFileFilter(htmlFileFilter);            // Устанавливать ему в качестве фильтра объект HTMLFileFilter.
        jFileChooser.showSaveDialog(saveFile);                 // Показывать диалоговое окно "Save File" для выбора файла.
        // Если пользователь подтвердит выбор файла:
        try {
            if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
                currentFile = jFileChooser.getSelectedFile();               // Сохранять выбранный файл в поле currentFile.
                view.setTitle(currentFile.getName());              //  Устанавливать имя файла в качестве заголовка окна представления.
                FileWriter fileWriter = new FileWriter(currentFile);                   // Создавать FileWriter на базе currentFile.
                // Переписывать данные из документа document в объект FileWriter-а аналогично тому, как мы это делали в методе getPlainText().
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
                // Перепиши все содержимое из документа document в созданный объект с помощью метода write класса HTMLEditorKit.
                fileWriter.close();
            }
        }catch (Exception e) {
            ExceptionHandler.log(e);              // Проследи, чтобы метод не кидал исключения. Их необходимо просто логировать.
        }
    }


    public void init(){
        createNewDocument();
    }

    public void exit(){
        System.exit(0);
        //Метод используется для завершения программы на вход принимает значение типа int,
        // обычно это 0, что говорит о том, что программа завершается без ошибок.
    }
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
        view.setVisible(true);
    }
}


