package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParenthesis {
    public List<Integer> solve(String exp){
        List<List<List<Integer>>> dp = new ArrayList<>();
        for(int i = 0; i<= exp.length(); i++){
            dp.add(i, new ArrayList<>());
            for(int j = 0; j<= exp.length(); j++){
                dp.get(i).add(j, new ArrayList<>());
            }
        }
        return getResultOfExpression(exp, 0, exp.length(), dp);
    }


    private List<Integer> getResultOfExpression(String exp, int start, int end, List<List<List<Integer>>> dp){
        if((!dp.get(start).get(end).isEmpty())) return dp.get(start).get(end);

        int num = 0;
        boolean isOperatorPresent = false;
        List<Integer> res = new ArrayList<>();

        for(int i = start; i< end; i++){
            char ch = exp.charAt(i);

            if(!isOperator(ch)){
                num = num*10 + (ch - '0');
                continue;
            }

            isOperatorPresent = true;

            List<Integer> left = getResultOfExpression(exp, start, i, dp);
            List<Integer> right = getResultOfExpression(exp, i+1, end, dp);

            for(int a : left){
                for(int b : right){
                    res.add(applyOperator(a, b, ch));
                }
            }

        }

        if(!isOperatorPresent) res.add(num);

        // System.out.println(dp.size() + " start"+ start + " end"+end);

        dp.get(start).get(end).addAll(res);

        return res;
    }

    private boolean isOperator(char ch){
        return ch == '+' || ch == '-' || ch == '*';
    }

    private int applyOperator(int a, int b, char op){
        switch(op){
            case '+': return a+b;
            case '-': return a-b;
            case '*': return a*b;
        }
        return 0;
    }
}
