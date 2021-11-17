package com.example.demo.view;

import com.example.demo.entity.ClientEntity;
import com.example.demo.repository.ClientRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.util.StringUtils;

import java.util.List;

@Route
public class MainView extends VerticalLayout {

    private final ClientRepository repo;

    private final ClientEditor editor;

    final Grid<ClientEntity> grid;

    final TextField filter;

    private final Button addNewBtn;

    public MainView(ClientRepository repo, ClientEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(ClientEntity.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New customer", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("id", "firstName", "lastName");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by last name");

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editCustomer(e.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editCustomer(new ClientEntity("", "")));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listCustomers(filter.getValue());
        });

        listCustomers(null);
    }

    void listCustomers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems((List<ClientEntity>) repo.findAll());
        }
        else {
            grid.setItems(repo.findByNameStartsWithIgnoreCase(filterText));
        }
    }
}