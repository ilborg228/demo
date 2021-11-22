package com.example.demo.view.bank;

import com.example.demo.entity.BankEntity;
import com.example.demo.entity.ClientEntity;
import com.example.demo.view.client.ClientForm;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

public class BankForm extends FormLayout {
    private BankEntity bank;

    TextField name = new TextField("Name");
    Binder<BankEntity> binder = new BeanValidationBinder<>(BankEntity.class);

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    public BankForm() {
        addClassName("contact-form");
        binder.bindInstanceFields(this);
        add(name,createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new BankForm.DeleteEvent(this, bank)));
        close.addClickListener(event -> fireEvent(new BankForm.CloseEvent(this)));


        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    public void setBank(BankEntity bank) {
        this.bank = bank;
        binder.readBean(bank);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(bank);
            fireEvent(new BankForm.SaveEvent(this, bank));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class BankFormEvent extends ComponentEvent<BankForm> {
        private BankEntity bank;

        protected BankFormEvent(BankForm source, BankEntity bank) {
            super(source, false);
            this.bank = bank;
        }

        public BankEntity getBank() {
            return bank;
        }
    }

    public static class SaveEvent extends BankForm.BankFormEvent {
        SaveEvent(BankForm source, BankEntity bank) {
            super(source, bank);
        }
    }

    public static class DeleteEvent extends BankForm.BankFormEvent {
        DeleteEvent(BankForm source, BankEntity bank) {
            super(source, bank);
        }
    }

    public static class CloseEvent extends BankForm.BankFormEvent {
        CloseEvent(BankForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
