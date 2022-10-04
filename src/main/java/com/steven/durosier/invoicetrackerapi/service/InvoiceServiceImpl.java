package com.steven.durosier.invoicetrackerapi.service;

import com.steven.durosier.invoicetrackerapi.entity.Invoice;
import com.steven.durosier.invoicetrackerapi.entity.User;
import com.steven.durosier.invoicetrackerapi.exception.ResourceNotFoundException;
import com.steven.durosier.invoicetrackerapi.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final UserService userService;

    @Autowired
    public InvoiceServiceImpl(final InvoiceRepository invoiceRepository, final UserService userService) {
        this.invoiceRepository = invoiceRepository;
        this.userService = userService;
    }

    @Override
    public Page<Invoice> getAllInvoices(Pageable page) {
        return invoiceRepository.findAll(page);
    }

    @Override
    public Page<Invoice> getInvoicesForCurrentUser(Pageable page) {
        final User user = userService.getLoggedInUser();

        return invoiceRepository.findInvoiceByUserId(user.getId(), page);
    }

    @Override
    public Invoice getInvoiceById(final Long id) {
        final Optional<Invoice> invoice = invoiceRepository.findById(id);

        if (invoice.isPresent()) {
            return invoice.get();
        }

        throw new ResourceNotFoundException("Invoice not found");
    }

    @Override
    public Invoice deleteInvoiceById(Long id) {
        final Invoice invoice = getInvoiceById(id);
        invoiceRepository.delete(invoice);

        return invoice;
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        final User user = userService.getLoggedInUser();
        invoice.setUser(user);

        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(final Long id, final Invoice invoice) {
        final Invoice found = getInvoiceById(id);
        return invoiceRepository.save(invoice);
    }

    @Override
    public Page<Invoice> getInvoiceByCategory(final String category, final Pageable page) {
        final User user = userService.getLoggedInUser();
        return invoiceRepository.findInvoiceByUserIdAndCategory(user.getId(), category, page);
    }

    @Override
    public Page<Invoice> getInvoiceByPartialName(final String name, final Pageable page) {
        final User user = userService.getLoggedInUser();

        return invoiceRepository.findInvoiceByUserIdAndNameContaining(user.getId(), name, page);
    }

    @Override
    public Page<Invoice> getInvoiceBetweenDates(Date startDate, Date endDate, Pageable page) {
        if (startDate == null) {
            startDate = new Date(0);
        }

        if (endDate == null) {
            endDate = new Date(System.currentTimeMillis());
        }

        final User user = userService.getLoggedInUser();

        return invoiceRepository.findInvoiceByUserIdAndDateBetween(user.getId(), startDate, endDate, page);
    }
}
