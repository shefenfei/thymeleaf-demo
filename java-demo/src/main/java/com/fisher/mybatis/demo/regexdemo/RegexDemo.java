package com.fisher.mybatis.demo.regexdemo;

public class RegexDemo {

    public static void main(String[] args) {
//        test01();
//        test02();
//        test03();
//        test04();

        String reg = "salex.";
        String[] resources = {"salex.xls", "salex1.xls", "salex0.xls", "salex1"};
        for (int i = 0; i < resources.length; i++) {
            if (resources[i].matches(reg)) {
                System.out.println(resources[i]);
            }
        }


    }

    /**
     * \b 只匹配一个位置
     */
    private static void test01() {
        //language=RegExp
        String reg1 = "\\bhi\\b";

        String[] sources = {"dhidemo", "hi12demo", "hi world", "hi"};
        for (int i = 0; i < sources.length; i++) {
            if (sources[i].matches(reg1)) {
                System.out.println(sources[i]);
            }
        }
        // 结果 : hi
    }

    private static void test02() {
        //language=RegExp
//        String reg = "\\bhi\\b.*\\bdemo\\b";
//        String reg = "\\bhi\\b.*\\b";
        String reg = "\\bhi\\b.*\\b";

        String[] sources = {"dhidemo", "hi12demo", "hi world", "hi"};
        for (int i = 0; i < sources.length; i++) {
            if (sources[i].matches(reg)) {
                System.out.println(sources[i]);
            }
        }
        // 结果 : "hi world", "hi"
    }

    private static void test03() {
        //language=RegExp
        String reg = "0\\d{2}-\\d{8}";

        String[] sources = {"010-38765546"};
        for (int i = 0; i < sources.length; i++) {
            if (sources[i].matches(reg)) {
                System.out.println(sources[i]);
            }
        }
        // 结果 : "hi world", "hi"
    }


    private static void test04() {
        //language=RegExp
//        String reg = "\\bparking\\w*\\b";
        String reg = "parking/client/1";

        String[] sources = {"parking/client/1", "parking/demo/aaa"};
        for (int i = 0; i < sources.length; i++) {
            if (sources[i].matches(reg)) {
                System.out.println(sources[i]);
            }
        }

        //language=RegExp
        String reg1 = "\\ba\\w*";
        String sou1 = "a1212ffsdfv";
        if (sou1.matches(reg1)) {
            System.out.println(sou1);
        }
        // 结果 : "hi world", "hi"

        //language=RegExp
        String reg2 = "\\d+";
        String sou2 = "1212a";
        if (sou2.matches(reg2)) {
            System.out.println(sou2);
        }

        //language=RegExp
        String reg3 = "\\b\\w{6}\\b";
        String[] words = {"123456", "abcded", "1222cddd"};
        for (int i = 0; i < words.length; i++) {
            if (words[i].matches(reg3)) {
                System.out.println(words[i]);
            }
        }

        //language=RegExp
        String reg4 = "^\\d{5,12}$";
        String[] qqs = {"123456", "abcded", "1222cddd", "3983431235"};
        for (int i = 0; i < qqs.length; i++) {
            if (qqs[i].matches(reg4)) {
                System.out.println(qqs[i]);
            }
        }

        //language=RegExp
        String reg5 = "[aeiou]";
        String[] chars = {"a", "ae", "e"};
        for (int i = 0; i < chars.length; i++) {
            if (chars[i].matches(reg5)) {
                System.out.println(chars[i]);
            }
        }

        //language=RegExp
        String reg6 = "\\(?0\\d{2}[) -]+\\d{8}";
        String[] nums = {"010-12348888", "(010)-12348888", "010-123488880", "(000-12121212"};
        for (int i = 0; i < nums.length; i++) {
            if (nums[i].matches(reg6)) {
                System.out.println(nums[i]);
            }
        }

        // 分支条件的正则表达式 正则1 |  正则2 满足其1就可以
     }

}
