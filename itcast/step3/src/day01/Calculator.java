package day01;

public class Calculator {
    public int add(int a, int b){
        return a + b;
    }

    public int sub(int a, int b){
        return a - b;
    }

    @Check
    public void div(){
        System.out.println("1 / 0" + (1 / 0));
    }
}
