////////////////////////////////////////////////////////////////////////////
//
//     Header File Inclusion
//
///////////////////////////////////////////////////////////////////////////
import java.io.*;          // For serialization (FileOutputStream, ObjectOutputStream, FileInputStream, ObjectInputStream)
import java.util.*;        // For LinkedList and Scanner

////////////////////////////////////////////////////////////////////////////
//
//     Class Name : Employee
//     Description: This class holds the information of a single employee
//
///////////////////////////////////////////////////////////////////////////
class Employee implements Serializable  // Serializable allows object to be saved/restored
{
    public int EmpID;                  // Employee ID
    public String EmpName;             // Employee Name
    public int EmpAge;                 // Employee Age
    public String EmpAdress;           // Employee Address
    public int EmpSalary;              // Employee Salary

    private static int Counter;        // Static counter to auto-generate unique Employee IDs

    static 
    {
        Counter = 1;                   // Initialize counter
    }

    // Constructor : Initialize employee object with given data
    public Employee(String b, int c, String d, int e)
    {
        this.EmpID = Counter++;         // Auto-increment ID
        this.EmpName = b;
        this.EmpAge = c;
        this.EmpAdress = d;
        this.EmpSalary = e;
    }

    // Display information of employee on console
    public void DisplayInformatrion()
    {
        System.out.println(" Employee ID : " + this.EmpID + 
                           " Employee Name : " + this.EmpName + 
                           " Age : " + this.EmpAge +
                           " Address : " + this.EmpAdress + 
                           " Salaray : " + this.EmpSalary);
    }

    // Convert employee object to String format (used by System.out.println)
    public String toString()
    {
        return " Employee ID : " + this.EmpID + 
               " Employee Name : " + this.EmpName + 
               " Age : " + this.EmpAge +
               " Address : " + this.EmpAdress + 
               " Salaray : " + this.EmpSalary;
    }
}

////////////////////////////////////////////////////////////////////////////
//
//     Class Name : MarvellousDBMS
//     Description: Simulates a small database using a LinkedList for employee table
//
///////////////////////////////////////////////////////////////////////////
class MarvellousDBMS implements Serializable
{
    private LinkedList<Employee> Table;  // Table stores Employee records

    // Constructor : Initialize the table
    public MarvellousDBMS()
    {
        System.out.println("Marvellous DBMS started successfully....");
        Table = new LinkedList();         // Create empty linked list
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : InsertIntoTable
    //    Description   : Insert new employee record into the table
    //    Input         : name, age, address, salary
    //    Output        : None
    //
    ////////////////////////////////////////////////////////////////////////////
    public void InsertIntoTable(String name, int Age, String Address, int Salary)
    {
        Employee eobj = new Employee(name, Age, Address, Salary);  // Create new Employee
        Table.add(eobj);                                          // Add employee to table
        System.out.println("Marvellous DBMS : > New record inserted successfully");
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : SelectStarFrom
    //    Description   : Display all employee records
    //    Input         : None
    //    Output        : Prints all records to console
    //
    ////////////////////////////////////////////////////////////////////////////
    public void SelectStarFrom()
    {
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("Data from employee table : ");
        System.out.println("---------------------------------------------------------------------------------------------------");

        for(Employee eref : Table)              // Loop through all employees
        {
            System.out.println(eref);          // Print employee info using toString()
        }
        System.out.println("---------------------------------------------------------------------------------------------------");
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : TakeBackup
    //    Description   : Serialize the DBMS object to a file for backup
    //    Input         : None
    //    Output        : Creates "MarvellousDBMS.ser" file
    //
    ////////////////////////////////////////////////////////////////////////////
    public void TakeBackup()
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("MarvellousDBMS.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);           // Serialize entire DBMS object
        }
        catch(Exception eobj)
        {
            System.out.println("Exception occured during backup");
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : RestoreBackUp
    //    Description   : Deserialize a DBMS object from a file
    //    Input         : File path
    //    Output        : Returns restored DBMS object
    //
    ////////////////////////////////////////////////////////////////////////////
    public static MarvellousDBMS RestoreBackUp(String path)
    {
        MarvellousDBMS ret = null;
        try
        {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);

            ret = (MarvellousDBMS) ois.readObject();  // Read object from file
            return ret;
        }
        catch(Exception eobj)
        {
            System.out.println("Exception occured during restore");
            return null; 
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : SelectSpecificID
    //    Description   : Search employee by ID and display
    //    Input         : Employee ID
    //    Output        : Prints employee info if found
    //
    ////////////////////////////////////////////////////////////////////////////
    public void SelectSpecificID(int id)
    {
        boolean Found = false;

        for(Employee eref : Table)
        {
            if(eref.EmpID == id)
            {
                Found = true;
                System.out.println(eref);      // Print record
                break;
            }
        }

        if(!Found)
        {
            System.out.println("There is no such record");
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : SelectSpecificName
    //    Description   : Search employee by Name and display
    //    Input         : Employee Name
    //    Output        : Prints employee info if found
    //
    ////////////////////////////////////////////////////////////////////////////
    public void SelectSpecificName(String name)
    {
        boolean Found = false;

        for(Employee eref : Table)
        {
            if(name.equals(eref.EmpName))
            {
                Found = true;
                System.out.println(eref);      // Print matching record
            }
        }

        if(!Found)
        {
            System.out.println("There is no such record");
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : SelectSpecificAddress
    //    Description   : Search employee by Address and display
    //    Input         : Employee Address
    //    Output        : Prints employee info if found
    //
    ////////////////////////////////////////////////////////////////////////////
    public void SelectSpecificAddress(String Addr)
    {
        boolean Found = false;

        for(Employee eref : Table)
        {
            if(Addr.equals(eref.EmpAdress))
            {
                Found = true;
                System.out.println(eref);      // Print matching record
            }
        }

        if(!Found)
        {
            System.out.println("There is no such record");
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : DeleteSpecificID
    //    Description   : Delete employee record by ID
    //    Input         : Employee ID
    //    Output        : Updates table and prints success/error
    //
    ////////////////////////////////////////////////////////////////////////////
    public void DeleteSpecificID(int id)
    {
        boolean Found = false;
        int index = 0;

        for(Employee eref : Table)
        {
            if(eref.EmpID == id)
            {
                Found = true;
                break;
            }
            index++;
        }

        if(!Found)
        {
            System.out.println("There is no such record");
        }
        else
        {
            Table.remove(index);                  // Remove employee
            System.out.println("Record Successfully deleted");
        }

   
    }

    ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : DeleteSpecificname
    //    Description   : Delete employee record by name
    //    Input         : Employee name
    //    Output        : Updates table and prints success/error
    //
    ////////////////////////////////////////////////////////////////////////////

    public void DeleteSpecificname(String name)
    {
        boolean Found = false;
        int index = 0;

        for(Employee eref : Table)
        {
            if(eref.EmpName == name)
            {
                Found = true;
                break;
            }
            index++;
        }

        if(!Found)
        {
            System.out.println("There is no such record");
        }
        else
        {
            Table.remove(index);                  // Remove employee
            System.out.println("Record Successfully deleted");
        }

   
    }
         ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : UpdateNameByID
    //    Description   : Search employee by ID and update the name
    //    Input         : Employee ID , Name 
    //    Output        : updates the name of employee
    //
    ////////////////////////////////////////////////////////////////////////////
    public void UpdateNameByID(int id,String name)
    {
        boolean Found = false;

        for(Employee eref : Table)
        {
            if(eref.EmpID == id)
            {
                Found = true;
                eref.EmpName = name;
                break;
            }
        }

        if(!Found)
        {
            System.out.println("There is no such record");
        }

        System.out.println("Successfully Updated the name....");
    }


          ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : UpdateAgeByID
    //    Description   : Search employee by ID and update the Age
    //    Input         : Employee ID , Age 
    //    Output        : updates the name of employee
    //
    ////////////////////////////////////////////////////////////////////////////
    public void UpdateAgeByID(int id,int age)
    {
        boolean Found = false;

        for(Employee eref : Table)
        {
            if(eref.EmpID == id)
            {
                Found = true;
                eref.EmpAge = age;
                break;
            }
        }

        if(!Found)
        {
            System.out.println("There is no such record");
        }

        System.out.println("Successfully Updated the age....");
    }

          ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : UpdateAddressByID
    //    Description   : Search employee by ID and update the Address
    //    Input         : Employee ID , Address 
    //    Output        : updates the Address of employee
    //
    ////////////////////////////////////////////////////////////////////////////
    public void UpdateAddressByID(int id,String address)
    {
        boolean Found = false;

        for(Employee eref : Table)
        {
            if(eref.EmpID == id)
            {
                Found = true;
                eref.EmpAdress = address;
                break;
            }
        }

        if(!Found)
        {
            System.out.println("There is no such record");
        }

        System.out.println("Successfully Updated the Address....");
    }

          ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : UpdateSalaryByID
    //    Description   : Search employee by ID and update the Salary
    //    Input         : Employee ID , Salary 
    //    Output        : updates the name of employee
    //
    ////////////////////////////////////////////////////////////////////////////
    public void UpdateSalaryByID(int id,int salary)
    {
        boolean Found = false;

        for(Employee eref : Table)
        {
            if(eref.EmpID == id)
            {
                Found = true;
                eref.EmpSalary = salary;
                break;
            }
        }

        if(!Found)
        {
            System.out.println("There is no such record");
        }

        System.out.println("Successfully Updated the salary....");
    }

          ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : AverageOfSalary
    //    Description   : Gives Average of salary
    //    Input         : LinkedList of table employee
    //    Output        : return the average of salary
    //
    ////////////////////////////////////////////////////////////////////////////
    public void AverageOfSalary()
    {
       
        int Count = 0;
        float Average  = 0.0f;

        for(Employee eref : Table)
        {
            Average = Average + eref.EmpSalary;
            Count++;
            
        }
        Average = Average / Count;
        System.out.println("Average of salary Of Employees is : " + Average);


        

        System.out.println("Successfully Shown the Average of salary....");
    }

          ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : SummationOfSalary
    //    Description   : Gives Summation of salary
    //    Input         : LinkedList of table employee
    //    Output        : return the Summation of salary
    //
    ////////////////////////////////////////////////////////////////////////////
    public void SummationOfSalary()
    {
       
        int Total = 0;

        for(Employee eref : Table)
        {
            Total = Total + eref.EmpSalary;
            
        }
        System.out.println("Average of salary Of Employees is : " + Total);


        

        System.out.println("Successfully Shown the Summation of salary....");
    }

         ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : HighestSalary
    //    Description   : It iterates through linked list of employee tables and finds 
    //                    highest salary of employee 
    //    Input         : LinkedList of table employee
    //    Output        : return the Highest salary record
    //
    ////////////////////////////////////////////////////////////////////////////
    public void HighestSalary()
    {
       
        int Max = Integer.MIN_VALUE;

        for(Employee eref : Table)
        {
            if(Max < eref.EmpSalary)
            {
                Max = eref.EmpSalary;
            }
            
        }


        for(Employee eref : Table)
        {
            if(Max == eref.EmpSalary)
            {
                System.out.println(eref);      // Print matching record
            }
        }

    }

         ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : LowestSalary
    //    Description   : It iterates through linked list of employee tables and finds 
    //                    Lowest Salary of employee 
    //    Input         : LinkedList of table employee
    //    Output        : return the Lowest Salary record
    //
    ////////////////////////////////////////////////////////////////////////////
    public void LowestSalary()
    {
       
        int Min = Integer.MAX_VALUE;

        for(Employee eref : Table)
        {
            if(Min > eref.EmpSalary)
            {
                Min = eref.EmpSalary;
            }
            
        }


        for(Employee eref : Table)
        {
            if(Min == eref.EmpSalary)
            {
                System.out.println(eref);      // Print matching record
            }
        }

    }

              ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : Count
    //    Description   : Iterates in LinkedList of tables and find of Number of records
    //    Input         : LinkedList of employee tables
    //    Output        : count of records
    //
    ////////////////////////////////////////////////////////////////////////////
    public void Count()
    {
        int Count = 0;

        for(Employee eref : Table)
        {
            Count++;
        }

        

        System.out.println("Count of records in Linkedlist is : " + Count);
    }

              ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : Round
    //    Description   : Add bonus in salary records
    //    Input         : id,Bonus
    //    Output        : Updated salary with added bonus
    //
    ////////////////////////////////////////////////////////////////////////////
    public void Round(int id, float bonus)
    {
        int Count = 0;
        int Total = 0; 
        

        for(Employee eref : Table)
        {
            if(eref.EmpID == id)
            {
                eref.EmpSalary = eref.EmpSalary + (int)(eref.EmpSalary*(bonus/100));
                
                break;
            }
        }

        
        System.out.println("Total salary with added bonus is : " + Total);
    }

              ////////////////////////////////////////////////////////////////////////////
    //
    //    Function Name : StdDev
    //    Description   : Add bonus in salary records
    //    Input         : id,Bonus
    //    Output        : Updated salary with added bonus
    //
    ////////////////////////////////////////////////////////////////////////////
    public void StdDev()
    {
        double Total = 0;
        double Avg = 0.0;
        double Std = 0.0;
        int Count = Table.size();

        for(Employee eref : Table) {
            Total += eref.EmpSalary;
        }
        Avg = Total / Count;

        double SumSquaredDiff = 0;
        for(Employee eref : Table) {
            SumSquaredDiff += Math.pow(eref.EmpSalary - Avg, 2);
        }

        Std = Math.sqrt(SumSquaredDiff / Count);

        System.out.println("Standard deviation of salary is: " + Std);

    }
}  //End of MarvellousDBMS

////////////////////////////////////////////////////////////////////////////
//
//     Class Name : Custom_DBMS
//     Description: Main program to interact with Marvellous DBMS
//
///////////////////////////////////////////////////////////////////////////
class Custom_DBMS
{
    public static void main(String A[]) throws Exception
    {
        String name = "\0";
        String Address = "\0";
        int Age = 0;
        int Salary = 0;
        float Bonus = 0.0f;
        int id = 0;
    

        // Try to restore DBMS from backup
        MarvellousDBMS mobj = MarvellousDBMS.RestoreBackUp("MarvellousDBMS.ser");

        if(mobj == null)                           // Backup not found
        {
            System.out.println("Unable to restore backup");
            mobj = new MarvellousDBMS();           // Create new DBMS
        }

        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("------------------------------------------ Marvellous DBMS ----------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------");

        Scanner sobj = new Scanner(System.in);       // Scanner for user input
        int iOption = 0;

        while(iOption != 20)                         // Main loop for DBMS operations
        {
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.println("1. insert into employee ");
            System.out.println("2. select * from employee ");
            System.out.println("3. Take a Backup of table");
            System.out.println("4. select *from employee where EmpID = ");
            System.out.println("5. select *from employee where EmpName = ");
            System.out.println("6. select *from employee where EmpAddress = ");
            System.out.println("7. delete from employee where EmpID = ");
            System.out.println("8. delete from employee where EmpName = ");
            System.out.println("9 .update employee set name = ' ' where id = ");
            System.out.println("10.update employee set Age =  where id = ");
            System.out.println("11.update employee set Adress =  where id = ");
            System.out.println("12.update employee set Salary =  where id = ");
            System.out.println("13.SELECT Average(Salary) AS AverageSalary FROM Employee;");
            System.out.println("14.SELECT SUM(Salary) AS TotalSalary FROM Employee;");
            System.out.println("15.SELECT MAX(Salary) AS HighestSalary FROM Employee;");
            System.out.println("16.SELECT MIN(Salary) AS LowestSalary FROM Employee;");
            System.out.println("17.SELECT COUNT(EmpID) AS TotalEmployees FROM Employee;");
            System.out.println("18.SELECT EmpID, Name, Salary, ROUND(Salary * 0.125) AS Bonus FROM Employee;");
            System.out.println("19.SELECT STDDEV(Salary) AS SalaryStdDev FROM Employee;");
            System.out.println("20. Terminate the DBMS ");
            System.out.println("---------------------------------------------------------------------------------------------------");

            System.out.println("Please select the desired operation on the database : ");
            iOption = sobj.nextInt();

            if(iOption == 1) // Insert new employee
            {
                System.out.println("Please enter the data that you want to insert : ");
                
                sobj.nextLine(); // consume newline
                System.out.println("Enter the name of employee : ");
                name = sobj.nextLine();

                System.out.println("Enter the Age of employee : ");
                Age = sobj.nextInt();

                sobj.nextLine(); // consume newline
                System.out.println("Enter the Address of employee : ");
                Address = sobj.nextLine();

                System.out.println("Enter the Salary of employee : ");
                Salary = sobj.nextInt();

                mobj.InsertIntoTable(name, Age, Address, Salary);
            }
            else if (iOption == 2) // Display all employees
            {
                mobj.SelectStarFrom();
            }
            else if(iOption == 3) // Backup DBMS
            {
                mobj.TakeBackup();
                System.out.println("Database gets successfully stored into secondary storage");
            }
            else if(iOption == 4) // Select by ID
            {
                System.out.println("Enter the employee id : ");
                id = sobj.nextInt();
                mobj.SelectSpecificID(id);
            }
            else if(iOption == 5) // Select by Name
            {
                sobj.nextLine(); // consume newline
                System.out.println("Enter the employee Name : ");
                name = sobj.nextLine();
                mobj.SelectSpecificName(name);
            }
            else if(iOption == 6) // Select by Address
            {
                sobj.nextLine(); // consume newline
                System.out.println("Enter the employee Address : ");
                Address = sobj.nextLine();
                mobj.SelectSpecificAddress(Address);
            }
            else if(iOption == 7) // Delete by ID
            {
                System.out.println("Enter the employee id : ");
                id = sobj.nextInt();
                mobj.DeleteSpecificID(id);
            }
            else if(iOption == 8) // Delete by Name
            {
                sobj.nextLine(); // consume newline
                System.out.println("Enter the employee Name : ");
                name = sobj.nextLine();
                mobj.DeleteSpecificname(name);
            }

            else if(iOption == 9) // Update name by ID
            {
                System.out.println("Enter the employee id that you want to update: ");
                id = sobj.nextInt();

                sobj.nextLine(); // consume newline
                System.out.println("Enter the updated name of employee : ");
                name = sobj.nextLine();

                mobj.UpdateNameByID(id,name);
            }

            else if(iOption == 10) // Update Age by ID
            {
                System.out.println("Enter the employee id that you want to update: ");
                id = sobj.nextInt();

                System.out.println("Enter the updated Age of employee : ");
                Age = sobj.nextInt();

                mobj.UpdateAgeByID(id,Age);
            }

            else if(iOption == 11) // Update Address by ID
            {
                System.out.println("Enter the employee id that you want to update: ");
                id = sobj.nextInt();

                sobj.nextLine(); // consume newline
                System.out.println("Enter the updated Address of employee : ");
                Address = sobj.nextLine();

                mobj.UpdateAddressByID(id,Address);
            }

            else if(iOption == 12) // Update Salary by ID
            {
                System.out.println("Enter the employee id that you want to update: ");
                id = sobj.nextInt();

                System.out.println("Enter the updated Salary of employee : ");
                Salary = sobj.nextInt();

                mobj.UpdateSalaryByID(id,Salary);
            }

            else if(iOption == 13) //Average of salary
            {
                mobj.AverageOfSalary();
            }

            else if(iOption == 14) //Summation of salary
            {
                mobj.SummationOfSalary();
            }

            else if(iOption == 15) //Highest salary
            {
                mobj.HighestSalary();
            }

            else if(iOption == 16) //Lowest salary
            {
                mobj.LowestSalary();
            }
            else if(iOption == 17) //Count Of Employees
            {
                mobj.Count();
            }

            else if(iOption == 18) //Add Bonus into salary
            {
                System.out.println("Enter the employee id that you want to update the bonus: ");
                id = sobj.nextInt();

                System.out.println("Enter the Bonus(in %) of Salary of employee : ");
                Bonus = sobj.nextFloat();

                mobj.Round(id,Bonus);
            }
            else if(iOption == 19) //Count Of Employees
            {
                mobj.StdDev();
            }
            else if(iOption == 20) // Terminate DBMS
            {
                System.out.println("Thank you for using Marvellous DBMS");
                mobj = null;
                System.gc(); // Garbage collection
                break;
            }
            else
            {
                System.out.println("Invalid Input");
            }
        } // End of while
    } // End of main method
} 
