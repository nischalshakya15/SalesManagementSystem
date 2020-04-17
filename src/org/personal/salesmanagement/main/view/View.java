package org.personal.salesmanagement.main.view;

import org.personal.salesmanagement.main.dao.SalesDao;
import org.personal.salesmanagement.main.dao.impl.SalesDaoImpl;
import org.personal.salesmanagement.main.model.Sales;
import org.personal.salesmanagement.main.table.CommandLineTable;

import java.util.Scanner;

public class View {

    private final static Scanner scan = new Scanner(System.in);
    private final SalesDao salesDao = new SalesDaoImpl();

    public View() {
        displayMenu();
    }

    private void displayMenu() {
        char cont;

        System.out.println("MAIN MENU FOR SUTERA EMAS SALES MANAGEMENT SYSTEM");
        System.out.println("1- Record Sales");
        System.out.println("2- View total sales");
        System.out.println("3- View sales details");
        System.out.println("4- Exit");

        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                do {
                    scan.nextLine();
                    System.out.println("Enter goods Id ");
                    String goodsId = scan.nextLine();

                    System.out.println("Enter description");
                    String description = scan.nextLine();


                    System.out.println("Enter price per unit");
                    Double pricePerUnit = scan.nextDouble();

                    System.out.println("Enter quantity");
                    Integer quantity = scan.nextInt();

                    Double totalSales = pricePerUnit * quantity;

                    Sales sale = new Sales(goodsId, description, pricePerUnit, quantity, totalSales);
                    salesDao.save(sale);

                    System.out.println("Press \"C\" to Continue to add sales and \"X\" to back to main menu");
                    cont = scan.next().charAt(0);

                    if (Character.toLowerCase(cont) == 'x') {
                        displayMenu();
                    }

                } while (cont == 'c' || cont == 'C');
                break;

            case 2:
                System.out.println("Total sales for today is RM " + salesDao.getTotalSales());
                System.out.println("Press \"X\" to back to main menu");
                cont = scan.next().charAt(0);
                if (Character.toLowerCase(cont) == 'x') {
                    displayMenu();
                }
                break;

            case 3:
                CommandLineTable commandLineTable = new CommandLineTable();
                commandLineTable.setHeaders("Goods ID", "Goods Description", "Price per unit", "Quantity", "Total");
                commandLineTable.setShowVerticalLines(true);

                for (Sales s : salesDao.readFromCsv()) {
                    commandLineTable.addRow(String.valueOf(s.getGoodsId()),
                            String.valueOf(s.getDescription()), String.valueOf(s.getPricePerUnit()),
                            String.valueOf(s.getQuantity()), String.valueOf(s.getTotalSales()));
                }

                commandLineTable.print();

                System.out.println("Press \"X\" to back to main menu");
                cont = scan.next().charAt(0);
                if (Character.toLowerCase(cont) == 'x') {
                    displayMenu();
                }
                break;

            case 4:
                System.exit(0);

            default:
                System.out.println("Invalid choice");
        }
    }
}
