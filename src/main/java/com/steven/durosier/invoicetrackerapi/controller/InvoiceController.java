package com.steven.durosier.invoicetrackerapi.controller;

import com.steven.durosier.invoicetrackerapi.entity.Invoice;
import com.steven.durosier.invoicetrackerapi.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;

@RestController
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    public Page<Invoice> getAllInvoices(final Pageable page) {
        //return invoiceService.getAllInvoices(page);
        return invoiceService.getInvoicesForCurrentUser(page);
    }

    @GetMapping("/invoices/{id}")
    public Invoice getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @GetMapping("/invoices/category")
    public Page<Invoice> getInvoicesByCategory(@RequestParam final String category, final Pageable page) {
        return invoiceService.getInvoiceByCategory(category, page);
    }

    @GetMapping("/invoices/name")
    public Page<Invoice> getInvoicesByPartialName(@RequestParam final String name, final Pageable page) {
        return invoiceService.getInvoiceByPartialName(name, page);
    }

    @GetMapping("/invoices/dates")
    public Page<Invoice> getInvoicesBetweenDates(@RequestParam(required = false) final Date startDate,
                                                 @RequestParam(required = false) final Date endDate,
                                                 final Pageable page) {
        return invoiceService.getInvoiceBetweenDates(startDate, endDate, page);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/invoices/{id}")
    public Invoice deleteInvoiceById(@PathVariable Long id) {
        return invoiceService.deleteInvoiceById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/invoice")
    public Invoice addInvoice(@RequestBody @Valid Invoice invoice) {
        return invoiceService.addInvoice(invoice);
    }

    @PutMapping("/invoice/{id}")
    public Invoice updatenvoice(@RequestBody @Valid Invoice invoice, @PathVariable final Long id) {
        return invoiceService.updateInvoice(id, invoice);
    }
}
