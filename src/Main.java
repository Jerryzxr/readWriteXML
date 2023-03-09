import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Main {
    static List<Student> studentList = new ArrayList<>();
    static List<Teacher> teacherList = new ArrayList<>();
    static Document document;
    static String path = "src/schoolList.xml";

    public static void main(String[] args) throws DocumentException {
        document = xmlTool.getDocument(path);
        //快速遍历
        //treeWalk(document.getRootElement());

        //region read
        studentList = xmlTool.getStudentList("student", document, studentList);
        System.out.println("");
        teacherList = xmlTool.getTeacherList("teacher", document, teacherList);
        //endregion

        //region add
        xmlTool.addStudent(document, "甲乙", 22, "语文", path);
        //endregion

        //region update
        xmlTool.updateStudentName("student", document, "张三", "张四", path);
        //endregion

        //region delete
        xmlTool.deleteStudent("student", document, "甲乙", path);
        //endregion
    }


}