package com.steven.durosier.invoicetrackerapi.service;

import com.steven.durosier.invoicetrackerapi.entity.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Date;

public interface InvoiceService {

    Page<Invoice> getAllInvoices(Pageable page);

    Page<Invoice> getInvoicesForCurrentUser(final Pageable page);

    Invoice getInvoiceById(final Long id);

    Invoice deleteInvoiceById(final Long id);

    Invoice addInvoice(final Invoice invoice);

    Invoice updateInvoice(final Long id, final Invoice invoice);

    Page<Invoice> getInvoiceByCategory(final String category, final Pageable page);

    Page<Invoice> getInvoiceByPartialName(final String name, final Pageable page);

    Page<Invoice> getInvoiceBetweenDates(final Date startDate, final Date endDate, final Pageable page);
}
