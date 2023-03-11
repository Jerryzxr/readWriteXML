//import org.dom4j.Document;
//import org.dom4j.DocumentException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.dom4j.DocumentException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.*;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Entity;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Main {
    static List<Student> studentList = new ArrayList<>();
    static List<Teacher> teacherList = new ArrayList<>();
    static Document document;
    static String path = "src/schoolList.xml";
    static Boolean isEnd = false;

    public static void main(String[] args) throws DocumentException, ParserConfigurationException, IOException, SAXException, TransformerException {

        //document = dom4jXmlTool.getDocument(path);
        document = XMLTool.getDocument(path);
        //快速遍历
        //treeWalk(document.getRootElement());
//region dom4j
        //region read
        //studentList = dom4jXmlTool.getStudentList("student", document, studentList);
        //System.out.println("");
        //teacherList = dom4jXmlTool.getTeacherList("teacher", document, teacherList);
        //endregion

        //region add
        //dom4jXmlTool.addStudent(document, "甲乙", 22, "语文", path);
        //endregion

        //region update
        //dom4jXmlTool.updateStudentName("student", document, "张三", "张四", path);
        //endregion

        //region delete
        //dom4jXmlTool.deleteStudent("student", document, "甲乙", path);
        //endregion
//endregion
//region javax
        //read
        studentList = XMLTool.getStudentList(document, "student");

        //search
        XMLTool.getStudent(document, "student", "张三");
        //add
        XMLTool.addStudent(document,"item","甲乙丙丁",222,"天文",path);
        //delete
        XMLTool.deleteNode(document, "student", "张三", path);
        //update
        XMLTool.updateNode(document, "student", "张三", "张四", path);

//endregion
    }

    public static void while1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            if (i == 5) {
                isEnd = true;
            }
        }
    }


}