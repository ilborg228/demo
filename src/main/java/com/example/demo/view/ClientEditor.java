package com.example.demo.view;

import com.example.demo.entity.ClientEntity;
import com.example.demo.repository.ClientRepository;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A simple example to introduce building forms. As your real application is probably much
 * more complicated than this example, you could re-use this form in multiple places. This
 * example component is only used in MainView.
 * <p>
 * In a real world application you'll most likely using a common super class for all your
 * forms - less code, better UX.
 */
@SpringComponent
@UIScope
public class ClientEditor extends VerticalLayout implements KeyNotifier {

    private final ClientRepository repository;

    /**
     * The currently edited customer
     */
    private ClientEntity entity;

    TextField email = new TextField("Email");
    TextField name = new TextField("Name");
    TextField passport = new TextField("Passport");
    TextField phone = new TextField("Phone");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<ClientEntity> binder = new Binder<>(ClientEntity.class);
    private ChangeHandler changeHandler;

    @Autowired
    public ClientEditor(ClientRepository repository) {
        this.repository = repository;

        add(email, name, passport, phone, actions);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editCustomer(entity));
        setVisible(false);
    }

    void delete() {
        repository.delete(entity);
        changeHandler.onChange();
    }

    void save() {
        repository.save(entity);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editCustomer(ClientEntity c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = c.getId() != null;
        if (persisted) {
            entity = repository.findById(c.getId()).get();
        }
        else {
            entity = c;
        }
        cancel.setVisible(persisted);

        binder.setBean(entity);

        setVisible(true);

        email.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

}