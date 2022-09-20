package com.bridgelabz.employeewage;

public class EmpCheck {
    public static void main(String[] args) {
        int IS_PRESENT = 1;
        int employeeCheck = (int)Math.floor(Math.random() * 10) % 2;
        if ( employeeCheck == 1)
            System.out.println("Employee is present");
        else
            System.out.println("Employee is absent");
    }
}
