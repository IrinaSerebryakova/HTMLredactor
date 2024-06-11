import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.nio.file.Files;

public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {   //возвращал true, если переданный файл директория или содержит в конце имени ".html" или ".htm" без учета регистра.
        String fileName = f.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex);  //знаем какое расширение у файла
        if(extension.equals(".html") || extension.equals(".htm") || Files.isDirectory(f.toPath()) == true){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getDescription() {  // Чтобы в окне выбора файла в описании доступных типов файлов отображался текст "HTML и HTM файлы"
        return "HTML и HTM файлы";   // переопредели соответствующим образом метод getDescription()
    }
}
