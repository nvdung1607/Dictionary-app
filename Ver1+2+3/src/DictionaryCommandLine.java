import java.sql.SQLOutput;
import java.util.Scanner;

public class DictionaryCommandLine {

    DictionaryManagement dicM = new DictionaryManagement();

    /**
     * Contructor.
     */
    public DictionaryCommandLine() {
    }

    /**
     * getter, setter DicM
     */
    public DictionaryManagement getDicM() {
        return dicM;
    }

    public void setDicM(DictionaryManagement dicM) {
        this.dicM = dicM;
    }

    /**
     * Tim kiem cac tu.
     */
    public void dictionarySearcher(Dictionary dic) {
        System.out.println("SEARCH:");
        System.out.println("Enter the word:");

        Scanner sc = new Scanner(System.in);
        String search = sc.nextLine();

        boolean check_found = false;
        for (int i = 0; i < dic.getCount_word(); i++) {
            if (dic.getWord(i).getWord_target().indexOf(search) == 0) {
                if (check_found == false) {
                    System.out.format("%-10s %-20s %-20s \n", "No", "| English", "| VietNamese");
                }
                check_found = true;
                System.out.format("%-10s %-20s %-20s \n",
                        i,
                        dic.getWord(i).getWord_target(),
                        dic.getWord(i).getWord_explain());
            }
        }
        if (check_found == false) {
            System.out.println("NOT FOUND!");
        }
    }

    /**
     * Ham in ra commandline.
     */
    public void showAllWords(Dictionary dic) {
        //System.out.println(dic.getCount_word());
        System.out.format("%-10s %-20s %-20s \n", "No", "| English", "| VietNamese");
        for (int i = 0; i < dic.getCount_word(); i++) {
            System.out.format("%-10s %-20s %-20s \n",
                    i,
                    dic.getWord(i).getWord_target(),
                    dic.getWord(i).getWord_explain());
        }

    }

    /**
     * Hàm có chức năng gọi hàm insertFromCommandline() và showAllWords().
     */
    public void dictionaryBasic() {
        dicM.insertFromCommandline();
        showAllWords(dicM.getDic());
    }

    /**
     * Hàm có chức năng gọi hàm insertFromFile, showAllWords() và dictionaryLookup().
     */
    public void dictionaryAdvanced() {
        dicM.insertFromFile();
        showAllWords(getDicM().dic);
        dicM.dictionaryLookup();
    }

    /**
     * Ham test.
     */
    public  void test_dictionary() {
        //dicM.insertFromCommandline();
        dicM.insertFromFile();
        dicM.addTailWord();
        //dicM.edit_data();
        //dicM.deleteWord();
        dictionarySearcher(getDicM().dic);
        //dictionarySearcher(getDicM().dic);
        //dicM.dictionaryExportToFile();
        //showAllWords(getDicM().dic);
    }

    /**
     * Menu.
     */
    public void menu() {
        dicM.insertFromFile();
        System.out.println("ENGLISH - VIETNAMESE DISCIONARY");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("MENU");
            System.out.format("%-20s %-20s %-20s \n", "1. Search", "2. Add new word", "3.Change word ");
            System.out.format("%-20s %-20s %-20s \n", "4. Delete word", "5. Show all word", "6. Export dictionary to file");
            System.out.println("0. Exit");

            System.out.println("Your choice:");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    dictionarySearcher(getDicM().dic);
                    break;
                case 2:
                    dicM.addTailWord();
                    break;
                case 3:
                    dicM.edit_data();
                    break;
                case 4:
                    dicM.deleteWord();
                    break;
                case 5:
                    showAllWords(getDicM().dic);
                    break;
                case 6:
                    dicM.dictionaryExportToFile();
                    break;
                default:
                    System.out.println("THANK YOU!!!");
                    return;
            }
        }
    }
}