package org.personal.salesmanagement.main.dao;

import org.personal.salesmanagement.main.model.Sales;

import java.util.List;

public interface SalesDao {

    void save(Sales sale);

    Double getTotalSales();

    List<Sales> readFromCsv();
}
