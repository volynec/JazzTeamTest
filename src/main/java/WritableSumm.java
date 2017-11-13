
class WritableSumm {

    final String[][] stringsFromOneToNine = {
            {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
            {"", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
    };
    final String[] stringsHundreds = {"", "сто", "двести", "триста", "четыреста", "пятьсот",
            "шестьсот", "семьсот", "восемьсот", "девятьсот"};
    String[] stringsFromElevenToNineteen = {"", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
            "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
    String[] stringsTens = {"", "десять", "двадцать", "тридцать", "сорок", "пятьдесят",
            "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    final String[][] forms = {
            {"", "", "", "0"},
            {"", "", "", "0"},
            {"тысяча", "тысячи", "тысяч", "1"},
            {"миллион", "миллиона", "миллионов", "0"},
            {"миллиард", "миллиарда", "миллиардов", "0"},
            {"триллион", "триллиона", "триллионов", "0"},
            {"квадриллион", "квадриллиона", "квадриллионов", "0"},
            {"квинтиллион", "квинтиллиона", "квинтиллионов", "0"},
            {"секстиллион", "секстиллиона", "секстиллионов", "0"},
    };

    protected String getStringsFromOneToNine(int n, int gender) {
        return stringsFromOneToNine[gender][n];
    }

    protected String getStringsFromElevenToNineteen(int n) {
        return stringsFromElevenToNineteen[n];
    }

    protected String getStringsTens(int n) {
        return stringsTens[n];
    }

    protected String getStringsHundreds(int n) {
        return stringsHundreds[n];
    }

    protected int getUnitGender(int idx) {
        return new Integer(forms[idx][3]);
    }

    protected String getUnit(int idx, int cnt) {
        if (cnt % 100 > 4 && cnt % 100 < 21)
            return forms[idx][2];
        switch  (cnt % 10) {
            case 1:
                return forms[idx][0];
            case 2:
            case 3:
            case 4:
                return forms[idx][1];
            default:
                return forms[idx][2];
        }
    }

    protected String triadToString(int n, int gender, boolean acceptZero) {
        if (!acceptZero && n == 0) return "";
        String res = "";
        if (n % 1000 > 99) {
            res += getStringsHundreds(n % 1000 / 100) + " ";
        }
        if (n % 100 > 10 && n % 100 < 20) {
            return res + getStringsFromElevenToNineteen(n % 10) + " ";
        }
        if (n % 100 > 9) {
            res += getStringsTens(n % 100 / 10) + " ";
        }
        if (res.length() == 0 || n % 10 > 0) {
            res = res + getStringsFromOneToNine(n % 10, gender) + " ";
        }
        return res;
    }

    public String numberToString(long num) {

        String res = "";
        if (num == 0 ) {
            res = getStringsFromOneToNine(0, 0) + " " + getUnit(1, 0);
        }
        int idx = 1;
        while (num > 0) {
            String triad = triadToString((int) (num % 1000), getUnitGender(idx), idx < 1);
            res = triad + getUnit(idx, (int)(num % 1000)) + " " + res;
            num = num / 1000;
            idx++;
        }
        return res.trim();
    }
}