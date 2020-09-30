class Person {
    protected String name;
    protected int yearOfBirth;
    protected String address;
}

class Employee extends Person {
    protected String startDate;
    protected Long salary;
}

class Doctor extends Employee {

}

class Patient extends Person {
    protected String contractNumber;
    protected boolean gold;
}
