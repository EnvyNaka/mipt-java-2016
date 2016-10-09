package ru.miptr.java2016.homework.g594.pyrkin.task1;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import com.sun.org.apache.xerces.internal.impl.xs.identity.Selector;
import com.sun.org.apache.xml.internal.serializer.utils.SystemIDResolver;
import ru.mipt.java2016.homework.base.task1.Calculator;
import ru.mipt.java2016.homework.base.task1.ParsingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.regex.Pattern;

/**
 * 2-stack Calculator
 * Created by randan on 10/9/16.
 */
public class CalculatorImplementation implements Calculator{
    private boolean badSymbolsCheck(String expression){
        for(char symbol : expression.toCharArray())
            if(!Character.isDigit(symbol) && symbol != '.' && symbol != '(' && symbol != ')'
                && symbol != '+' && symbol != '-' && symbol != '*' && symbol !='/')
                return false;
        return true;
    }

    private boolean bracketsCheck(String expression){
        int balance = 0;
        char previousSymbol = '#';
        for(char symbol : expression.toCharArray()){
            if(symbol == '(')
                ++balance;
            else if(symbol == ')') {
                --balance;
                if(previousSymbol == '(')
                    return false;
            }

            if(balance < 0)
                return false;

            previousSymbol = symbol;
        }
        return balance == 0;
    }

    public void removeExtraOperands(String expression){
        Pattern pattern = Pattern.compile("([\\d)])\\+*-(\\+*-\\+*-)*\\+*([\\d(])");
        Matcher matcher = pattern.matcher(expression);
        expression = matcher.replaceAll("$1-$3");

        pattern = Pattern.compile("([\\d)])(\\+*-\\+*-)*\\+*([\\d(])");
        matcher = pattern.matcher(expression);
        expression = matcher.replaceAll("$1+$3");

        pattern = Pattern.compile("^\\+*-(\\+*-\\+*-)*\\+*([\\d(])");
        matcher = pattern.matcher(expression);
        expression = matcher.replaceAll("-$2");

        pattern = Pattern.compile("^(\\+*-\\+*-)*\\+*([\\d(])");
        matcher = pattern.matcher(expression);
        expression = matcher.replaceAll("+$2");

        System.out.print(expression);
    }

    @Override
    public double calculate (String expression) throws ParsingException{
        if(expression == null)
            throw  new ParsingException("Null expression");
        expression = expression.replaceAll(" ", "");
        expression = expression.replaceAll("\n", "");
        if(expression.isEmpty())
            throw new ParsingException("Invalid expression");
        if(!badSymbolsCheck(expression))
            throw  new ParsingException("Invalid expression");
        if(!bracketsCheck(expression))
            throw  new ParsingException("Invalid expression");
        removeExtraOperands(expression);


        return 0;
    }

    public static void main(String[] args) {
        String s = "11";
        CalculatorImplementation calc = new CalculatorImplementation();
        calc.removeExtraOperands(s);

    }
}


