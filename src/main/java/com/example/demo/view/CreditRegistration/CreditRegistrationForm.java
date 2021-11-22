package com.example.demo.view.CreditRegistration;

import com.example.demo.entity.CalendarEntity;
import com.example.demo.entity.CreditEntity;
import com.example.demo.entity.OfferEntity;
import com.example.demo.repository.CalendarRepository;
import com.example.demo.repository.OfferRepository;
import com.example.demo.service.CalendarService;
import com.example.demo.service.ClientService;
import com.example.demo.service.CreditService;
import com.example.demo.service.OfferService;
import com.example.demo.view.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.security.PermitAll;
import java.sql.Date;

@Scope("prototype")
@Route(value="creditregistration", layout = MainLayout.class)
@PageTitle("CRM")
@PermitAll
@SpringComponent
public class CreditRegistrationForm extends VerticalLayout {
    
    private final CalendarRepository calendarRepository;
    private CalendarEntity calendar;
    private final OfferRepository offerRepository;
    private OfferEntity offer;
    private CreditRegistrationEntity creditRegistration = new CreditRegistrationEntity();
    private final OfferService offerService;
    private final CalendarService calendarService;
    private final ClientService clientService;
    private final CreditService creditService;

    TextField clientId = new TextField("Client ID");
    TextField creditId = new TextField("Credit ID");
    TextField amount = new TextField("Amount");
    TextField duration = new TextField("Duration(years)");
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    HorizontalLayout actions = new HorizontalLayout(save);

    Binder<CreditRegistrationEntity> binder = new Binder<>(CreditRegistrationEntity.class);

    @Autowired
    public CreditRegistrationForm(OfferRepository offerRepository, CalendarRepository calendarRepository,
                                  OfferService offerService, CalendarService calendarService,
                                  CreditService creditService, ClientService clientService) {
        this.calendarRepository = calendarRepository;
        this.offerRepository = offerRepository;
        this.calendarService = calendarService;
        this.offerService = offerService;
        this.creditService = creditService;
        this.clientService = clientService;

        add(clientId, creditId, amount, duration, actions);
        binder.bindInstanceFields(this);
        actions.setSpacing(true);

        save.getElement().getThemeList().add("primary");
        save.addClickListener(e -> save());
        setVisible(true);
    }

    private void save() {
        try {
            binder.writeBean(creditRegistration);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        offer = new OfferEntity();
        CreditEntity credit = creditService.findById(creditRegistration.getCreditId()).get();
        double loanAmount = Double.parseDouble(creditRegistration.getAmount());
        double duration = Double.parseDouble(creditRegistration.getDuration());
        offer.setCredit(credit);
        offer.setClient(clientService.findById(creditRegistration.getClientId()).get());
        if(credit.getLimit()<loanAmount)
            loanAmount=credit.getLimit();
        offer.setLoanAmount(loanAmount);
        offerService.save(offer);
        double bodyAmount = loanAmount/duration/12;
        for (int i = 0;i<duration*12;i++){
            calendar = new CalendarEntity();
            calendar.setDate(new Date(System.currentTimeMillis()+(2592000000L * i)));
            calendar.setOffer(offer);
            calendar.setBodyAmount(bodyAmount);
            double percentAmount = loanAmount * credit.getPercent() / 1200;
            calendar.setPercentAmount(percentAmount);
            loanAmount *= 1+(credit.getPercent()/1200);
            loanAmount -= bodyAmount;
            double paymentAmount = bodyAmount + percentAmount;
            calendar.setPaymentAmount(paymentAmount);
            calendarService.save(calendar);
        }
        Notification.show("Successful");
    }
}
