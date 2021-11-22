package com.example.demo.view.bank;

import com.example.demo.entity.BankEntity;
import com.example.demo.entity.ClientEntity;
import com.example.demo.service.BankService;
import com.example.demo.view.MainLayout;
import com.example.demo.view.client.ClientForm;
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
@Route(value="banks", layout = MainLayout.class)
@PageTitle("CRM")
@PermitAll
public class BankListView extends VerticalLayout {

    private Grid<BankEntity> grid = new Grid<>(BankEntity.class);
    private TextField filterText = new TextField();
    private BankForm form;
    private final BankService bankService;

    public BankListView(BankService bankService) {
        this.bankService = bankService;

        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new BankForm();
        form.setWidth("25em");
        form.addListener(BankForm.SaveEvent.class, this::saveBank);
        form.addListener(BankForm.DeleteEvent.class, this::deleteBank);
        form.addListener(BankForm.CloseEvent.class, e -> closeEditor());

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
                editClient(event.getValue()));
    }

    private void configureGrid() {
        grid.addClassNames("client-grid");
        grid.setSizeFull();
        grid.setColumns("id","name");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add bank");
        addContactButton.addClickListener(click -> addBank());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void saveBank(BankForm.SaveEvent event) {
        bankService.save(event.getBank());
        updateList();
        closeEditor();
    }

    private void deleteBank(BankForm.DeleteEvent event) {
        bankService.delete(event.getBank());
        updateList();
        closeEditor();
    }

    public void editClient(BankEntity bank) {
        if (bank == null) {
            closeEditor();
        } else {
            form.setBank(bank);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    void addBank() {
        grid.asSingleSelect().clear();
        editClient(new BankEntity());
    }

    private void closeEditor() {
        form.setBank(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(bankService.findAllBanksByName(filterText.getValue()));
    }
}
