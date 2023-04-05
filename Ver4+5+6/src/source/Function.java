package source;

import java.util.ArrayList;
public class Function {
    // sort.
    public ArrayList<Word> sortDic(ArrayList<Word> dicMM) {
        ArrayList<Word> dicSort = new ArrayList<Word>();
        dicSort = dicMM;
        dicSort.sort((d1,d2) -> {
            if (d1.getWord_target_lowcase().compareTo(d2.getWord_target_lowcase()) > 0) {
                return 1;
            } else {
                return -1;
            }
        });
        return dicSort;
    }

    // compare.
    public int compare(String s1, String s2) {
        int result = s1.indexOf(s2);
        if (result == -1 || result > 0) {
            if (s1.compareTo(s2) > 0) {
                return 1;
            }
            if (s1.compareTo(s2) < 0) {
                return -1;
            }
        }
        return 0;
    }

    // binary Search.
    public int binarySearcher(ArrayList<Word> dicMM, String search) {
        Function comp = new Function();
        int d = 0;
        int c = dicMM.size() - 1;
        while (d < c) {
            int m = (d + c) / 2;
            if (comp.compare(dicMM.get(m).getWord_target_lowcase(), search) >= 0) {
                c = m;
            }
            if (comp.compare(dicMM.get(m).getWord_target_lowcase(), search) < 0) {
                d = m + 1;
            }
        }

        if (d == 0 && comp.compare(dicMM.get(0).getWord_target_lowcase(), search) != 0) {
            d = -1;
        }
        return d;
    }

    // binary Lookup.
    public int binaryLookup(ArrayList<Word> dicMM, String search) {
        int d = 0;
        int c = dicMM.size() - 1;
        while (d <= c) {
            int m = (d + c) / 2;
            if (dicMM.get(m).getWord_target_lowcase().compareTo(search) == 0) {
                return m;
            }
            if (dicMM.get(m).getWord_target_lowcase().compareTo(search) > 0) {
                c = m - 1;
            }
            if (dicMM.get(m).getWord_target_lowcase().compareTo(search) < 0) {
                d = m + 1;
            }
        }
        return -1;
    }
}
