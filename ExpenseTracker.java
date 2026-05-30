import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class Expense {
    private int id;
    private String title;
    private double amount;
    private String category;
    private String date;

    Expense(int id, String title, double amount, String category, String date) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getID() {
        return id;
    }

    public String getTitle() {
        return title;

    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "\nID: " + id + "\nTitle: " + title
                + "\nAmount: " + amount + "\nCategory: " + category
                + "\nDate: " + date;

    }
}

public class ExpenseTracker {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Search Expense");
            System.out.println("4. Delete Expense");
            System.out.println("5. Total Spending");
            System.out.println("6.Update Expense");
            System.out.println("7. Search by Category");
            System.out.println("8.Category Wise Spending");
            System.out.println("9.Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Title:");
                    String title = sc.nextLine();

                    System.out.println("Enter Amount:");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    System.out.println("Enter Category");
                    String category = sc.nextLine();

                    System.out.println("Entre Date:");
                    String date = sc.nextLine();

                    Expense esExpense = new Expense(0, title, amount, category, date);
                    System.out.println(esExpense);

                    try {
                        Connection con = DBconnection.getConnection();
                        String sql = "INSERT INTO expenses(title,amount,category,expense_date) VALUES(?,?,?,?)";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, esExpense.getTitle());
                        ps.setDouble(2, esExpense.getAmount());
                        ps.setString(3, esExpense.getCategory());
                        ps.setString(4, esExpense.getDate());
                        int rows = ps.executeUpdate();
                        if (rows > 0) {
                            System.out.println("Expense Added Successfully!");

                        }
                        con.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        Connection con = DBconnection.getConnection();
                        String sql = "SELECT * FROM expenses";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery();

                        while (rs.next()) {
                            System.out.println("\nID: " + rs.getInt("id") +
                                    "\nTitle: " + rs.getString("title") +
                                    "\nAmount: " + rs.getDouble("amount") +
                                    "\nCategory: " + rs.getString("category") +
                                    "\nDate: " + rs.getDate("expense_date"));
                        }
                        con.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    try {
                        System.out.println("Enter ID:");
                        int searchId = sc.nextInt();
                        sc.nextLine();
                        Connection con = DBconnection.getConnection();
                        String sql = "SELECT * FROM expenses WHERE id = ?";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setInt(1, searchId);
                        ResultSet rs = ps.executeQuery();

                        if (rs.next()) {
                            System.out.println(
                                    "\nId: " + rs.getInt("id") +
                                            "\nTitle: " + rs.getString("title") +
                                            "\nAmount: " + rs.getDouble("amount") +
                                            "\nCategory " + rs.getString("category") +
                                            "\nDate " + rs.getString("expense_date"));
                        } else {
                            System.out.println("Expense Not Found!");
                        }
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    try {
                        System.out.println("Enter ID:");
                        int deleteID = sc.nextInt();
                        Connection con = DBconnection.getConnection();
                        String sql = "DELETE FROM expenses WHERE id = ?";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setInt(1, deleteID);
                        // ResultSet rs = ps.executeQuery();
                        int rows = ps.executeUpdate();
                        if (rows > 0) {
                            System.out.println("Expense Deleted Successfully!");
                        } else {
                            System.out.println("Expense Not Found!");
                        }
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    try {
                        Connection con = DBconnection.getConnection();
                        String sql = "SELECT SUM(amount) AS total FROM expenses";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next()) {
                            System.out.println("Total Spending " + rs.getDouble("total"));

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    System.out.println("Enter ID:");
                    int updateID = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter New Title:");
                    String newTitle = sc.nextLine();

                    System.out.println("Enter New Amount:");
                    double newAmount = sc.nextDouble();
                    sc.nextLine();

                    System.out.println("Enter New Category:");
                    String newCategory = sc.nextLine();

                    System.out.println("Enter New Date:");
                    String newDate = sc.nextLine();

                    try {
                        Connection con = DBconnection.getConnection();
                        String sql = "UPDATE expenses SET title = ?,amount=?, category=?, expense_date = ? WHERE id =?";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, newTitle);
                        ps.setDouble(2, newAmount);
                        ps.setString(3, newCategory);
                        ps.setString(4, newDate);
                        ps.setInt(5, updateID);

                        int rows = ps.executeUpdate();
                        if (rows > 0) {
                            System.out.println("Expense Updated Successfully!");

                        } else {
                            System.out.println("Expense Not Found!");
                        }
                        con.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
                case 7:
                    System.out.println("Enter Category:");
                    String searchCategory = sc.nextLine();
                    try {
                        Connection con = DBconnection.getConnection();
                        String sql = "SELECT * FROM expenses WHERE category = ?";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, searchCategory);
                        ResultSet rs = ps.executeQuery();
                        boolean found = false;
                        while (rs.next()) {
                            found = true;
                            System.out.println(
                                    "\nID: " + rs.getInt("id") +
                                            "\nTitle: " + rs.getString("title") +
                                            "\nAmount: " + rs.getDouble("amount") +
                                            "\nCategory: " + rs.getString("category") +
                                            "\nDate: " + rs.getDate("expense_date"));
                        }
                        if (!found) {
                            System.out.println("No Expense Found!");
                        }
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    try{
                        Connection con = DBconnection.getConnection();
                        String sql = "SELECT category, SUM(amount) AS total FROM expenses GROUP BY category";
                        PreparedStatement ps = con.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery();
                        while(rs.next()) {
                            System.out.println("Categoryes " + rs.getString("category") + 
                        "\nTotal: " + rs.getDouble("total"));
                        }
                        con.close();
                    }
                    catch(Exception e){
                   e.printStackTrace();
                    }
                    break;    
                case 9:
                    System.out.println("------Exiting--------");
                    sc.close();
                    return;
                default:
                    System.out.println("Wrong Choice");
                    break;
            }
        }
    }
}
