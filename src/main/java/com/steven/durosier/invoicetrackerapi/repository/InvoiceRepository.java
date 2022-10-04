package com.steven.durosier.invoicetrackerapi.repository;

import com.steven.durosier.invoicetrackerapi.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    //SELECT * FROM invoices WHERE category = ?
    Page<Invoice> findInvoiceByUserIdAndCategory(final long userId, final String category, final Pageable page);

    Page<Invoice> findInvoiceByUserId(final long userId, final Pageable page);


    //SELECT * FROM invoices WHERE name like ?
    Page<Invoice> findInvoiceByUserIdAndNameContaining(final long userId, final String name, final Pageable page);

    Page<Invoice> findInvoiceByUserIdAndDateBetween(final long userId, final Date startDate, final Date endDate, final Pageable page);
}
