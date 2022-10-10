/*Ability to get the Total Wage when queried by Company - Create Method in EmpWageBuilder to get total
wage by Company*/

package com.bridgelabz.employeewage;

import com.bridgelabz.employeewage.MultipleCompanies.CompanyEmpWage;
import com.bridgelabz.employeewage.UsingInterface.EmployeeWage;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;

public class CompanyQuery implements EmployeeWage {

    //ArrayList for Company Wage Object
    ArrayList<CompanyEmpWage> mulComPanyEmpWages;

    //Hashmap for storing daily wage
    HashMap<String, Integer> totalEmpWages;

    public CompanyQuery() {
        mulComPanyEmpWages = new ArrayList<>();
        totalEmpWages = new HashMap<>();
    }

    private void addCompanyEmpWages(String company, int wagePerHr, int numOfWorkingDays, int maxHrsPerMonth) {
        mulComPanyEmpWages.add(new CompanyEmpWage(company, wagePerHr, numOfWorkingDays, maxHrsPerMonth));
    }

    public void calculateEmpWage() {
        for (CompanyEmpWage companyEmpWage : mulComPanyEmpWages) {
            companyEmpWage.setTotalEmpWage(this.calculateEmpWage(companyEmpWage));
            System.out.println(companyEmpWage.toString());
        }
    }

    //Method to Calculate Employee Wage by Hours and Type
    public int calculateEmpWage(CompanyEmpWage companyEmpWage) {
        // Local Variables
        int empHrs = 0, totalEmpHrs = 0, totalWorkingDays = 0;
        //Compute Emp Wage for Month
        while (totalEmpHrs < companyEmpWage.maxHrsPerMonth && totalWorkingDays < companyEmpWage.numWorkingDays) {
            totalWorkingDays++;     //Incrementing Working Days
            double empCheck = Math.floor(Math.random() * 10) % 3;

            switch ((int) empCheck) {
                case IS_PART_TIME:
                    System.out.println("Employee is Present as PART TIME");
                    empHrs = 4;
                    break;
                case IS_FULL_TIME:
                    System.out.println("Employee is Present as FULL TIME");
                    empHrs = 8;
                    break;
                default:
                    System.out.println("Employee is Absent");
                    empHrs = 0;
                    break;
            }
            totalEmpHrs += empHrs;

            System.out.println("Day No :" + totalWorkingDays + " Emp Hrs: " + empHrs);
        }
        int totalWage = totalEmpHrs * companyEmpWage.wagePerHr;
        totalEmpWages.put(companyEmpWage.companyName, totalWage);
        return totalWage;
    }

    public int getTotalEmpWage(String companyName) {
        return totalEmpWages.get(companyName);
    }

    public static void main(String[] args) {
        //Welcome Message for Initial Purpose
        System.out.println("Welcome to Employee Wages Problem.");
        CompanyQuery employeeWageBuilder = new CompanyQuery();
        employeeWageBuilder.addCompanyEmpWages("Bajaj", 30, 5, 25);
        employeeWageBuilder.addCompanyEmpWages("HDFC", 25, 30, 30);
        employeeWageBuilder.calculateEmpWage();
        System.out.println("Enter the company name for query : ");
        Scanner s = new Scanner(System.in);
        String query = s.nextLine();
        int totalWage = employeeWageBuilder.getTotalEmpWage(query);
        System.out.println("Total Employee Wage for " + query + " company is " + totalWage);
    }

}