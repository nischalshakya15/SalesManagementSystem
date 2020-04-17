package org.personal.salesmanagement.main.dao.impl;

import org.personal.salesmanagement.main.constants.Constants;
import org.personal.salesmanagement.main.dao.SalesDao;
import org.personal.salesmanagement.main.model.Sales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SalesDaoImpl implements SalesDao {

    private static final List<Sales> sales = new LinkedList<>();

    static {
        sales.add(new Sales("G001", "Note book", 2.30, 100, 23.00));
        sales.add(new Sales("G002", "Pencils", 0.50, 1000, 500.00));
        sales.add(new Sales("G005", "Marker", 1.80, 200, 360.00));
        writeToCsv();
    }

    @Override
    public void save(Sales sale) {
        sales.add(sale);
        writeToCsv();
    }

    @Override
    public Double getTotalSales() {
        return sales.stream()
                .filter(s -> s.getTotalSales() != null)
                .mapToDouble(Sales::getTotalSales).sum();
    }

    @Override
    public List<Sales> readFromCsv() {
        final List<Sales> sales = new LinkedList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("sales.csv"));
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] sale = line.split(Constants.DELIMITER);
                Sales s = new Sales(sale[0], sale[1], Double.valueOf(sale[2]), Integer.valueOf(sale[3]), Double.valueOf(sale[4]));
                sales.add(s);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
        return sales;
    }

    private static void writeToCsv() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("sales.csv"));
            bufferedWriter.write(Sales.getHeaders());
            for (Sales sale : sales) {
                bufferedWriter.write(sale.toString());
            }
            bufferedWriter.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
