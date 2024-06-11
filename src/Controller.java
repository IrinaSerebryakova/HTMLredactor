import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.io.*;

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

    public void resetDocument() {
        //будет сбрасывать текущий документ
        htmlDocument.removeUndoableEditListener();   // Удалять у текущего документа document слушателя правок, которые можно отменить/вернуть (найди подходящий для этого метод, унаследованный от AbstractDocument)
        view.getUndoListener(); // Слушателя нужно запросить у представления (метод getUndoListener())
        //Не забудь проверить, что текущий документ существует (не null)

        htmlDocument = HTMLEditorKit.HTMLFactory.createDefaultDocument(); // Создавать новый документ по умолчанию и присваивать его полю document
        // Подсказка: воспользуйся подходящим методом класса HTMLEditorKit
        htmlDocument.addUndoableEditListener();      // Добавлять новому документу слушателя правок
        view.update();                           // Вызывать у представления метод update()
    }


    public void setPlainText(String text) {    //записывать переданный текст с html тегами в документ document
        resetDocument();                 //  Сбрось документ
        StringReader stringReader = new StringReader(text);    // Создай новый ридер StringReader на базе переданного текста.
        try {
            htmlDocument = HTMLEditorKit.read(stringReader);    // Вызови метод read() из класса HTMLEditorKit, который вычитает данные из ридера в документ document.
        } catch (Exception e) {
            ExceptionHandler.log(e);                      // Проследи, чтобы метод не кидал исключения. Их необходимо просто логировать.
        }

    }

    public String getPlainText() {   // Он должен получать текст из документа со всеми html тегами.
        StringWriter stringWriter = new StringWriter();
        String plainText = null;
        try {
            plainText = HTMLEditorKit.write(htmlDocument());   // Перепиши все содержимое из документа document в созданный объект с помощью метода write класса HTMLEditorKit.
        } catch (Exception e) {
            ExceptionHandler.log(e);              // Проследи, чтобы метод не кидал исключения. Их необходимо просто логировать.
        }
        return plainText;
    }

    public void createNewDocument() {
        view.selectHtmlTab();      // Выбирать html вкладку у представления.
        resetDocument();           // Сбрасывать текущий документ.

        view.setTitle("HTML редактор"); // Устанавливать новый заголовок окна, например: "HTML редактор". Воспользуйся методом, который унаследован в нашем представлении.
        currentFile = null;                           // Обнулить переменную currentFile (присвоить ей значение null).
    }

    public void openDocument(){  // Обрати внимание на имя метода для показа диалогового окна.
                                    //   Когда файл выбран, необходимо:
        try{
            FileReader fileReader = new FileReader(currentFile);             // Создать FileReader, используя currentFile.
        JFileChooser jFileChooser = new JFileChooser();        // Создавать новый объект для выбора файла JFileChooser.
        HTMLFileFilter htmlFileFilter = new HTMLFileFilter();
        jFileChooser.setFileFilter(htmlFileFilter);            // Устанавливать ему в качестве фильтра объект HTMLFileFilter.
        jFileChooser.showSaveDialog("Save File");                 // Показывать диалоговое окно "Save File" для выбора файла.
        currentFile = new File();          // Установить новое значение currentFile.
                                         // Установить имя файла в заголовок у представления.
            resetDocument();               // Сбросить документ.
        // Вычитать данные из FileReader-а в документ document с помощью объекта класса HTMLEditorKit.
        // Сбросить правки (вызвать метод resetUndo представления).
        // Если возникнут исключения - залогируй их и не пробрасывай наружу.

            }catch (Exception e) {
                ExceptionHandler.log(e);              // Проследи, чтобы метод не кидал исключения. Их необходимо просто логировать.
            }
    }

    public void saveDocument() {
        //  Метод должен работать также, как saveDocumentAs(), но не запрашивать файл у пользователя, а использовать currentFile.
        if (currentFile == null) {
            saveDocumentAs();
        }
        if (currentFile != null) {
            view.selectHtmlTab();                                  // Переключать представление на html вкладку.
            try {
                FileWriter fileWriter = new FileWriter(currentFile);  // Создавать FileWriter на базе currentFile, если currentFile != null.
                HTMLEditorKit.fileWriter.write(document);
            } catch (Exception e) {
                ExceptionHandler.log(e);              // Проследи, чтобы метод не кидал исключения. Их необходимо просто логировать.
            }
        }
    }

    public void saveDocumentAs() throws IOException {
        view.selectHtmlTab();                             // Переключать представление на html вкладку.
        JFileChooser jFileChooser = new JFileChooser();        // Создавать новый объект для выбора файла JFileChooser.
        HTMLFileFilter htmlFileFilter = new HTMLFileFilter();
        jFileChooser.setFileFilter(htmlFileFilter);            // Устанавливать ему в качестве фильтра объект HTMLFileFilter.
        jFileChooser.showSaveDialog("Save File");                 // Показывать диалоговое окно "Save File" для выбора файла.
        // Если пользователь подтвердит выбор файла:
        currentFile = new File();                     // Сохранять выбранный файл в поле currentFile.
        //  Устанавливать имя файла в качестве заголовка окна представления.
        FileWriter fileWriter = new FileWriter(currentFile);                   // Создавать FileWriter на базе currentFile.
        // Переписывать данные из документа document в объект FileWriter-а аналогично тому, как мы это делали в методе getPlainText().
        try {
            fileWriter.write(htmlDocument());   // Перепиши все содержимое из документа document в созданный объект с помощью метода write класса HTMLEditorKit.
        } catch (Exception e) {
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

    }
}


