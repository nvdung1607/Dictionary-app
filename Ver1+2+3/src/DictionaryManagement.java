import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class DictionaryManagement {
    Dictionary dic = new Dictionary();

    /**
     * setter, getter dic.
     */
    public Dictionary getDic() {
        return dic;
    }

    public void setDic(Dictionary dic) {
        this.dic = dic;
    }

    /**
     * Ham nhap lieu tu commandLine.
     */
    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);

        //Nhap gia tri cho so phan tu cua mang
        int n = sc.nextInt();
        dic.setCount_word(n);

        //Nhap gia trị cho các phần tử của mảng
        String endl = sc.nextLine();   //Đọc hàng đầu tiên ( hàng sau n)
        for (int i = 0; i < n; i++) {
            Word word = new Word();
            word.setWord_target(sc.nextLine());
            word.setWord_explain(sc.nextLine());
            dic.setWords(word, i);
        }
        sc.close();
    }

    /**
     * Ham nhap lieu tu file.
     */
    public void insertFromFile() {
        try {
            FileReader fr = new FileReader("ver3\\Data\\dictionaries.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            int count = 0;
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                count++;
                String txt[] = line.split("    ");
                Word word = new Word();
                word.setWord_target(txt[0]);
                word.setWord_explain(txt[1]);
                dic.setWords(word, count - 1);

                //System.out.println(txt[0] + " " + txt[1] + " " + count);
                //System.out.println(dic.getWord(count).getWord_target() + " " + dic.getWord(count).getWord_explain() + " " + count);
            }
            dic.setCount_word(count);
            fr.close();
            br.close();
        } catch (Exception e){}
    }

    /**
     * Tra cuu tu dien bang dong lenh.
     */
    public void dictionaryLookup() {
        //Thong bao
        System.out.println("SEARCH:");
        System.out.println("Please Enter the word:");

        //Xu Ly
        Scanner sc = new Scanner(System.in);
        String search = sc.next();
        boolean check_found = false;    //Kiểm tra có từ cần tìm kiếm không
        for (int i = 0; i < dic.getCount_word(); i++) {
            if (dic.getWord(i).getWord_target().equals(search)) {
                System.out.format("%-10s %-20s %-20s \n", "No", "| English", "| VietNamese");
                System.out.format("%-10s %-20s %-20s \n",
                        i,
                        dic.getWord(i).getWord_target(),
                        dic.getWord(i).getWord_explain());
                check_found = true;
                break;
            }
        }
        if (check_found == false) {
            System.out.println("NOT FOUND!");
        }
    }

    /**
     * Ham them du lieu tu dong lenh.
     */
    public  void addTailWord() {
        Scanner sc = new Scanner(System.in);
        //Thong bao
        System.out.println("ADD WORD:");
        System.out.println("Please Enter the word target:");
        String new_target = sc.nextLine();
        System.out.println("Please Enter the word explain:");
        String new_explain = sc.nextLine();

        dic.setCount_word(dic.getCount_word() + 1);
        Word word = new Word();
        word.setWord_target(new_target);
        word.setWord_explain(new_explain);
        dic.setWords(word, dic.getCount_word() - 1);

        System.out.println("ADD WORD SUCCESS...");
    }

    /**
     * Hàm sửa dữ liệu bằng dòng lệnh (thay đổi nghĩa của từ).
     */
    public void edit_data() {
        Scanner sc = new Scanner(System.in);

        //Thong bao
        System.out.println("CHANGE WORD:");
        System.out.println("Please Enter the word target:");
        String new_target = sc.nextLine();
        System.out.println("Please Enter the word explain:");
        String new_explain = sc.nextLine();

        boolean check = false;

        for (int i = 0; i < dic.getCount_word(); i++) {
            if (dic.getWord(i).getWord_target().equals(new_target)) {
                Word word = new Word();
                word.setWord_target(new_target);
                word.setWord_explain(new_explain);
                dic.setWords(word, i);
                check = true;
            }
        }
        if (check){
            System.out.println("COMPLETED THE CHANGE...");
        }
        else {
            System.out.println("CAN NOT FIND THE WORD...");
        }
    }

    /**
     * Hàm xóa dữ liệu bằng dòng lệnh (xóa từ);
     */
    public void deleteWord(){
        ///Thong bao
        System.out.println("DELETE WORD:");
        System.out.println("Please Enter the word:");

        Scanner sc = new Scanner(System.in);

        boolean check = false;

        String target = sc.nextLine();
        for (int i = 0; i < dic.getCount_word(); i++) {
            if (dic.getWord(i).getWord_target().equals(target)) {
                for (int j = i; j < dic.getCount_word(); j++) {
                    dic.setWords(dic.getWord(j+1), j );
                }
                dic.setCount_word(dic.getCount_word() - 1);
                check = true;
                break;
            }
        }

        if (check){
            System.out.println("COMPLETED DELETE THE WORD...");
        }
        else {
            System.out.println("CAN NOT FIND THE WORD...");
        }
    }

    /**
     * Xuất dữ liệu từ điển hiện tại ra file.
     */
    public void dictionaryExportToFile() {
        try {
            FileWriter fw = new FileWriter("ver3\\Data\\dictionaries.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < dic.getCount_word(); i++) {
                bw.write(dic.getWord(i).getWord_target() + "    " + dic.getWord(i).getWord_explain() + "\n");
            }
            bw.close();
            fw.close();
        } catch (Exception e) {}
        System.out.println("Export to file done!");
    }

}