package cn.com.cloudstar.rightcloud.framework.test.t003util;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.com.cloudstar.rightcloud.system.entity.system.User;

/**
 * @author Hong.Wu
 * @date: 20:59 2020/02/14
 */
public class Test18Spel {

    public static void main(String[] args) {


        List<Integer> primes = new ArrayList<Integer>();
        primes.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17));

        //解析表达式需要的上下文，解析时有一个默认的上下文
        StandardEvaluationContext context = new StandardEvaluationContext();
        //在上下文中设置变量，变量名为primes，内容为primes对象
        context.setVariable("primes",primes);

        //创建SpEL表达式的解析器
        ExpressionParser parser = new SpelExpressionParser();

        //从用户对象中获得id并+1900，获得解析后的值在ctx上下文中
        List<Integer> primesGreaterThanTen = (List<Integer>) parser.parseExpression("#primes.?[#this>10]").getValue(context);
        System.out.println(primesGreaterThanTen);


        // 变量：变量可以在表达式中使用语法#’变量名’引用
        context.setVariable("name", "Hello");
        System.out.println(parser.parseExpression("#name").getValue(context));
    }


}
