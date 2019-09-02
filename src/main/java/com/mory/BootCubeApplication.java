package com.mory;

import com.mory.biz.ResultCompose;
import com.mory.biz.StatCombiner;
import com.mory.common.CombinationContext;
import com.mory.entity.DigitCompose;
import com.mory.interfaces.impl.MyDigitsComposeCalculator;
import com.mory.interfaces.impl.MyParallelLettersComposeCalculator;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class BootCubeApplication  implements ApplicationRunner {

    /**
     * main入口
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(BootCubeApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            System.out.println("请选择: 1开始, 0结束退出");
            String inputs = scanner.nextLine();

            if ("0".equals(inputs)) {//结束退出
                flag = false;
                continue;
            }

            if (!"1".equals(inputs)) {//非法字符
                System.out.println("!输入包含非法字符!");
                continue;
            }

            System.out.println("麻烦输入数字序列,以英文逗号分割:");
            String[] digits = scanner.nextLine().split(",");
            if (digits == null || digits.length == 0) {
                System.out.println("非法字符!");
                continue;
            }

            //注入定制的MyDigitsComposeCalculator 和MyParallelLettersComposeCalculator对象
            StatCombiner statCombiner = new StatCombiner(new MyDigitsComposeCalculator(), new MyParallelLettersComposeCalculator());
            try {
                List<Integer> digitsList = new LinkedList<>();
                for (int i = 0; i < digits.length; i++) {
                    digitsList.add(Integer.valueOf(digits[i].trim()));
                }

                //开始计算
                ResultCompose resultCompose = statCombiner.startCount(digitsList);
                Set<DigitCompose> digitComposeSet = resultCompose.getDigitComposeSet();
                Map<DigitCompose, Set> composeResultMap = resultCompose.getComposeResultMap();
                //打印结果
                for (DigitCompose digitCompose : digitComposeSet) {
                    System.out.println("  "+digitCompose + ": ");
                    Set<String> vSet = composeResultMap.get(digitCompose);
                    if (vSet == null) {
                        System.out.println("    此分类没有字母组合\r\n");
                        continue;
                    }

                    for (String rs : vSet) {
                        System.out.print("    " + rs);
                    }

                    System.out.println();
                    System.out.println();
                }
                System.out.println();

            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
        System.out.println("byebye!");
    }

}
