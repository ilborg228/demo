package com.example.demo.view;

import com.example.demo.entity.ClientEntity;
import com.example.demo.service.ClientService;
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
@Route(value="", layout = MainLayout.class)
@PageTitle("Contacts | Vaadin CRM")
@PermitAll
public class ClientListView extends VerticalLayout {
    Grid<ClientEntity> grid = new Grid<>(ClientEntity.class);
    TextField filterText = new TextField();
    ClientForm form;
    ClientService service;


    public ClientListView(ClientService service) {
        this.service=service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new ClientForm(service.findAllBanks());
        form.setWidth("25em");
        form.addListener(ClientForm.SaveEvent.class, this::saveClient);
        form.addListener(ClientForm.DeleteEvent.class, this::deleteClient);
        form.addListener(ClientForm.CloseEvent.class, e -> closeEditor());

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
        grid.setColumns("name", "phone", "email","passport");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactButton = new Button("Add contact");
        addContactButton.addClickListener(click -> addContact());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void saveClient(ClientForm.SaveEvent event) {
        service.saveClient(event.getClient());
        updateList();
        closeEditor();
    }

    private void deleteClient(ClientForm.DeleteEvent event) {
        service.deleteClient(event.getClient());
        updateList();
        closeEditor();
    }

    public void editClient(ClientEntity client) {
        if (client == null) {
            closeEditor();
        } else {
            form.setClient(client);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    void addContact() {
        grid.asSingleSelect().clear();
        editClient(new ClientEntity());
    }

    private void closeEditor() {
        form.setClient(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAllClients(filterText.getValue()));
    }
}