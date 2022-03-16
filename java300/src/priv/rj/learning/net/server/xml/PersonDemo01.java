package priv.rj.learning.net.server.xml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class PersonDemo01 {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        //获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //从解析工厂例获取解析器
        SAXParser parser = factory.newSAXParser();
        //加载文档Docume注册处理器
        //编写处理器
        PersonHandler handler = new PersonHandler();

        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("priv/rj/learning/net/server/xml/person.xml"), handler);

        List<Person> persons = handler.getPersons();
        for (Person p: persons){
            System.out.println(p.getName() + "---->" + p.getAge());
        }
    }
}
