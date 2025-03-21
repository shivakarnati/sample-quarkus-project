
public class example2 {

    static void traverse(String str){
        char[] ch = str.toCharArray();
        for (int i=0; i<ch.length; i++){
            System.out.println(ch[i]+ " ");
        }
    }

    public  static  void main(String[] args){
        String str = "Shivakarnati";
        traverse(str);
    }
}
