import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.swing.plaf.synth.Region;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class xmlTool {
    public xmlTool() {

    }

    public static Document getDocument(String path) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File(path));
        return document;
    }

    //region read
    public static List<Student> getStudentList(String elementName, Document document, List<Student> studentList) {
        Element root = document.getRootElement();
        for (Iterator<Element> it = root.elementIterator("student"); it.hasNext(); ) {
            Element student = it.next();
            Student stu = new Student();
            //获取Name值
            String Name = student.element("Name").getText();
            //对象赋值
            stu.setName(Name);
            //获取Age值
            int Age = Integer.parseInt(student.element("Age").getText());
            //对象赋值
            stu.setAge(Age);
            //获取Major值
            String Major = student.element("Major").getText();
            //对象赋值
            stu.setMajor(Major);
            //region 输出展示
            System.out.println("student");
            System.out.println("Name:" + Name);
            System.out.println("Age:" + Age);
            System.out.println("Major:" + Major);
            //endregion
            //添加进list
            studentList.add(stu);
        }
        return studentList;
    }

    public static List<Teacher> getTeacherList(String elementName, Document document, List<Teacher> teacherList) {
        Element root = document.getRootElement();
        for (Iterator<Element> it = root.elementIterator("teacher"); it.hasNext(); ) {
            Element student = it.next();
            Teacher tea = new Teacher();
            //获取Name值
            String Name = student.element("Name").getText();
            //对象赋值
            tea.setName(Name);
            //获取Age值
            int Age = Integer.parseInt(student.element("Age").getText());
            //对象赋值
            tea.setAge(Age);
            //region 输出展示
            System.out.println("teacher");
            System.out.println("Name:" + Name);
            System.out.println("Age:" + Age);
            //endregion
            //添加进list
            teacherList.add(tea);
        }
        return teacherList;
    }

    //endregion
    //region add
    public static void addStudent(Document document, String Name, int Age, String Major, String path) {
        DocumentFactory documentFactory = DocumentFactory.getInstance();
        Element root = document.getRootElement();
        //创建一级子节点
        Element oneElement = documentFactory.createElement("student");
        //创建一级子节点下的Name节点
        Element eName = documentFactory.createElement("Name");
        //为Name节点设置文本值
        eName.setText(Name);
        //添加eName至父节点
        oneElement.add(eName);
        //创建一级子节点下的Age节点
        Element eAge = documentFactory.createElement("Age");
        //为Name节点设置文本值
        eAge.setText(String.valueOf(Age));
        //添加eAge至父节点
        oneElement.add(eAge);
        //创建一级子节点下的Major节点
        Element eMajor = documentFactory.createElement("Major");
        //为Major节点设置文本值
        eMajor.setText(Major);
        //添加eMajor至父节点
        oneElement.add(eMajor);

        root.add(oneElement);
        saveXML(path, document);
    }
    //endregion

    //region update
    public static void updateStudentName(String elementName, Document document, String name,String updateName, String path) {
        Element root = document.getRootElement();
        int id = 0;
        for (Iterator it = root.elementIterator(elementName); it.hasNext(); ) {
            Element ele = (Element) it.next();
            if (ele.element("Name").getText().equals(name)) {
                ele.element("Name").setText(updateName);
                break;
            }
        }
        saveXML(path, document);
    }

    //endregion
    //region update
    public static void deleteStudent(String elementName, Document document, String name, String path) {

        //获取根节点
        Element root = document.getRootElement();
        int id = 0;
        for (Iterator it = root.elementIterator(elementName); it.hasNext(); ) {
            Element ele = (Element) it.next();
            if (ele.element("Name").getText().equals(name)) {
                ele.getParent().remove(ele);
                break;
            }
        }
        saveXML(path, document);
    }

    //endregion
    //Save XML
    public static void saveXML(String path, Document document) {
        try {
            OutputFormat format = OutputFormat.createCompactFormat(); //build format
            format.setEncoding("utf-8"); //set format
            XMLWriter writer = new XMLWriter(new FileWriter(path), format);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void treeWalk(Element element) {
        for (int i = 0, size = element.nodeCount(); i < size; i++) {
            Node node = element.node(i);
            if (node instanceof Element) {
                treeWalk((Element) node);
            } else {
                // do something…
                System.out.println(node.getText());
            }
        }
    }
}
