package priv.rj.learning.que;



public class StackDemo {
    public static void main(String[] args) {
        DIYStack<String> backHistory = new DIYStack<String>(3);
        backHistory.push("www.baidu.com");
        backHistory.push("www.google.com");
        backHistory.push("www.sina.com");
        backHistory.push("www.swpu.com");
        System.out.println("大小： " + backHistory.size());

        //遍历
        String item = null;
        while ((item = backHistory.pop()) != null){
            System.out.println(item);
        }
    }


}
