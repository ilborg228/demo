package com.example.demo.view.credit;

import com.example.demo.entity.CreditEntity;
import com.example.demo.service.BankService;
import com.example.demo.service.CreditService;
import com.example.demo.view.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.security.PermitAll;

@Component
@Scope("prototype")
@Route(value="credits", layout = MainLayout.class)
@PageTitle("CRM")
@PermitAll
public class CreditListView extends VerticalLayout {

    private Grid<CreditEntity> grid = new Grid<>(CreditEntity.class);
    private TextField filterText = new TextField();
    private CreditForm form;
    private final CreditService creditService;
    private final BankService bankService;

    public CreditListView(CreditService creditService, BankService bankService) {
        this.creditService = creditService;
        this.bankService = bankService;

        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new CreditForm(bankService.findAllBanks());
        form.setWidth("25em");
        form.addListener(CreditForm.SaveEvent.class, this::saveCredit);
        form.addListener(CreditForm.DeleteEvent.class, this::deleteCredit);
        form.addListener(CreditForm.CloseEvent.class, e -> closeEditor());

        FlexLayout content = new FlexLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setFlexShrink(0, form);
        content.addClassNames("content", "gap-m");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
        closeEditor();
        grid.asSingleSelect().addValueChangeListener(event ->
                editCredit(event.getValue()));
    }

    private void configureGrid() {
        grid.addClassNames("credit-grid");
        grid.setSizeFull();
        grid.setColumns("id", "percent", "limit");
        grid.addColumn(credit -> credit.getBank().getName()).setHeader("Bank");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by id...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addCreditButton = new Button("Add credit");
        addCreditButton.addClickListener(click -> addCredit());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addCreditButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void saveCredit(CreditForm.SaveEvent event) {
        creditService.save(event.getCredit());
        updateList();
        closeEditor();
    }

    private void deleteCredit(CreditForm.DeleteEvent event) {
        creditService.delete(event.getCredit());
        updateList();
        closeEditor();
    }

    public void editCredit(CreditEntity credit) {
        if (credit == null) {
            closeEditor();
        } else {
            form.setCredit(credit);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    void addCredit() {
        grid.asSingleSelect().clear();
        editCredit(new CreditEntity());
    }

    private void closeEditor() {
        form.setCredit(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(creditService.findAllCreditsById(filterText.getValue()));
    }
}
