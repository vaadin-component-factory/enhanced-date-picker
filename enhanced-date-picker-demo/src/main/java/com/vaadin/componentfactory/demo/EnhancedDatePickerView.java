/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.componentfactory.demo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Locale;

import com.vaadin.componentfactory.EnhancedDatePicker;
import com.vaadin.componentfactory.EnhancedDatePicker.DatePickerI18n;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.demo.DemoView;
import com.vaadin.flow.router.Route;

/**
 * View for {@link EnhancedDatePicker} demo.
 *
 * @author Vaadin Ltd
 */
@Route("enhanced-date-picker")
public class EnhancedDatePickerView extends DemoView {

    @Override
    public void initView() {
        createPatternDatePicker();
        createPatternAndLocaleDatePicker();
        createSimpleDatePicker();
        createMinAndMaxDatePicker();
        createDisabledDatePicker();
        createFinnishDatePicker();
        createWithClearButton();
        createStartAndEndDatePickers();
        createLocaleChangeDatePicker();
        createParserDatePicker();

        addCard("Additional code used in the demo",
                new Label("These methods are used in the demo."));
    }

    private void createSimpleDatePicker() {
        Div message = createMessageDiv("simple-picker-message");

        // begin-source-example
        // source-example-heading: Simple date picker
        EnhancedDatePicker datePicker = new EnhancedDatePicker();

        datePicker.addValueChangeListener(
                event -> UpdateMessage(message, datePicker));
        // end-source-example

        datePicker.setId("simple-picker");

        addCard("Simple date picker", datePicker, message);
    }

    private void createPatternDatePicker() {
        Div message = createMessageDiv("simple-picker-message");

        // begin-source-example
        // source-example-heading: Date picker with pattern
        EnhancedDatePicker datePicker = new EnhancedDatePicker(LocalDate.now(), "dd-MMM-yyyy");
        UpdateMessage(message, datePicker);

        datePicker.addValueChangeListener(
                event -> UpdateMessage(message, datePicker));

        TextField patten = new TextField();
        patten.setLabel("Define a pattern");
        patten.setValue("dd-MMM-yyyy");

        Button setPatternBtn = new Button("Set pattern from text field");
        setPatternBtn.addClickListener(e -> {
            datePicker.setPattern(patten.getValue());
            UpdateMessage(message, datePicker);
        });

        Button dropPatternBtn = new Button("Drop pattern");
        dropPatternBtn.addClickListener(e -> {
            datePicker.setPattern(null);
            UpdateMessage(message, datePicker);
        });

        // end-source-example

        datePicker.setId("Pattern-picker");

        addCard("Date picker with pattern", datePicker, message, patten, setPatternBtn, dropPatternBtn);
    }

    private void createPatternAndLocaleDatePicker() {
        Div message = createMessageDiv("simple-picker-message");

        // begin-source-example
        // source-example-heading: Date picker with pattern and locale
        EnhancedDatePicker datePicker = new EnhancedDatePicker(LocalDate.now(), "dd-MMM-yyyy");
        UpdateMessage(message, datePicker);

        datePicker.addValueChangeListener(
                event -> UpdateMessage(message, datePicker));

        TextField patten = new TextField();
        patten.setLabel("Define a pattern");
        patten.setValue("dd-MMM-yyyy");

        Button setPatternBtn = new Button("Set pattern from text field");
        setPatternBtn.addClickListener(e -> {
            datePicker.setPattern(patten.getValue());
            UpdateMessage(message, datePicker);
        });

        Button dropPatternBtn = new Button("Drop pattern");
        dropPatternBtn.addClickListener(e -> {
            datePicker.setPattern(null);
            UpdateMessage(message, datePicker);
        });
        HorizontalLayout patterns = new HorizontalLayout();
        patterns.add(setPatternBtn, dropPatternBtn);


        Button setlocaleBtn = new Button("Set locale to German");
        setlocaleBtn.addClickListener(e -> {
            datePicker.setLocale(Locale.GERMAN);
            UpdateMessage(message, datePicker);
        });

        Button setlocaleEsBtn = new Button("Set locale to Spanish");
        setlocaleEsBtn.addClickListener(e -> {
            datePicker.setLocale(new Locale("es"));
            UpdateMessage(message, datePicker);
        });

        Button setLocaleEnBtn = new Button("Set locale to English");
        setLocaleEnBtn.addClickListener(e -> {
            datePicker.setLocale(Locale.ENGLISH);
            UpdateMessage(message, datePicker);
        });

        HorizontalLayout locales = new HorizontalLayout();
        locales.add(setlocaleBtn, setlocaleEsBtn, setLocaleEnBtn);
        // end-source-example

        datePicker.setId("Pattern-picker");

        addCard("Date picker with pattern and locale", datePicker, message, patten, patterns, locales);
    }

    private void createMinAndMaxDatePicker() {
        Div message = createMessageDiv("min-and-max-picker-message");

        // begin-source-example
        // source-example-heading: Date picker with min and max
        EnhancedDatePicker datePicker = new EnhancedDatePicker();
        datePicker.setLabel("Select a day within this month");
        datePicker.setPlaceholder("Date within this month");

        LocalDate now = LocalDate.now();

        datePicker.setMin(now.withDayOfMonth(1));
        datePicker.setMax(now.withDayOfMonth(now.lengthOfMonth()));

        datePicker.addValueChangeListener(
                event -> UpdateMessage(message, datePicker));
        // end-source-example

        datePicker.setId("min-and-max-picker");
        addCard("Date picker with min and max", datePicker, message);
    }

    private void createDisabledDatePicker() {
        Div message = createMessageDiv("disabled-picker-message");

        // begin-source-example
        // source-example-heading: Disabled date picker
        EnhancedDatePicker datePicker = new EnhancedDatePicker();
        datePicker.setEnabled(false);
        // end-source-example

        datePicker.addValueChangeListener(event -> {
            message.setText("This event should not have happened");
        });

        datePicker.setId("disabled-picker");
        addCard("Disabled date picker", datePicker, message);
    }

    private void createWithClearButton() {
        // begin-source-example
        // source-example-heading: Clear button
        EnhancedDatePicker datePicker = new EnhancedDatePicker();
        datePicker.setValue(LocalDate.now());

        // Display an icon which can be clicked to clear the value:
        datePicker.setClearButtonVisible(true);
        // end-source-example

        addCard("Clear button", datePicker);
    }

    private void createFinnishDatePicker() {
        Div message = createMessageDiv("finnish-picker-message");

        // begin-source-example
        // source-example-heading: Internationalized date picker
        EnhancedDatePicker datePicker = new EnhancedDatePicker();
        datePicker.setLabel("Finnish date picker");
        datePicker.setPlaceholder("Syntymäpäivä");
        datePicker.setLocale(new Locale("fi"));

        datePicker.setI18n(
                new DatePickerI18n().setWeek("viikko").setCalendar("kalenteri")
                        .setClear("tyhjennä").setToday("tänään")
                        .setCancel("peruuta").setFirstDayOfWeek(1)
                        .setMonthNames(Arrays.asList("tammiku", "helmikuu",
                                "maaliskuu", "huhtikuu", "toukokuu", "kesäkuu",
                                "heinäkuu", "elokuu", "syyskuu", "lokakuu",
                                "marraskuu", "joulukuu")).setWeekdays(
                        Arrays.asList("sunnuntai", "maanantai", "tiistai",
                                "keskiviikko", "torstai", "perjantai",
                                "lauantai")).setWeekdaysShort(
                        Arrays.asList("su", "ma", "ti", "ke", "to", "pe",
                                "la")));

        datePicker.addValueChangeListener(event -> {
            LocalDate selectedDate = event.getValue();
            if (selectedDate != null) {
                int weekday = selectedDate.getDayOfWeek().getValue() % 7;
                String weekdayName = datePicker.getI18n().getWeekdays()
                        .get(weekday);

                int month = selectedDate.getMonthValue() - 1;
                String monthName = datePicker.getI18n().getMonthNames()
                        .get(month);

                message.setText("Day of week: " + weekdayName + "\nMonth: "
                        + monthName);
            } else {
                message.setText("No date is selected");
            }
        });
        // end-source-example

        datePicker.setId("finnish-picker");
        addCard("Internationalized date picker", datePicker, message);
    }

    private void createStartAndEndDatePickers() {
        Div message = createMessageDiv("start-and-end-message");

        // begin-source-example
        // source-example-heading: Two linked date pickers
        EnhancedDatePicker startDatePicker = new EnhancedDatePicker();
        startDatePicker.setLabel("Start");
        EnhancedDatePicker endDatePicker = new EnhancedDatePicker();
        endDatePicker.setLabel("End");

        startDatePicker.addValueChangeListener(event -> {
            LocalDate selectedDate = event.getValue();
            LocalDate endDate = endDatePicker.getValue();
            if (selectedDate != null) {
                endDatePicker.setMin(selectedDate.plusDays(1));
                if (endDate == null) {
                    endDatePicker.setOpened(true);
                    message.setText("Select the ending date");
                } else {
                    message.setText(
                            "Selected period:\nFrom " + selectedDate.toString()
                                    + " to " + endDate.toString());
                }
            } else {
                endDatePicker.setMin(null);
                message.setText("Select the starting date");
            }
        });

        endDatePicker.addValueChangeListener(event -> {
            LocalDate selectedDate = event.getValue();
            LocalDate startDate = startDatePicker.getValue();
            if (selectedDate != null) {
                startDatePicker.setMax(selectedDate.minusDays(1));
                if (startDate != null) {
                    message.setText(
                            "Selected period:\nFrom " + startDate.toString()
                                    + " to " + selectedDate.toString());
                } else {
                    message.setText("Select the starting date");
                }
            } else {
                startDatePicker.setMax(null);
                if (startDate != null) {
                    message.setText("Select the ending date");
                } else {
                    message.setText("No date is selected");
                }
            }
        });
        // end-source-example

        startDatePicker.setId("start-picker");
        endDatePicker.setId("end-picker");
        addCard("Two linked date pickers", startDatePicker, endDatePicker,
                message);

    }

    private void createLocaleChangeDatePicker() {
        Div message = createMessageDiv("Customize-locale-picker-message");
        // begin-source-example
        // source-example-heading: Date picker with customize locales
        // By default, the datePicker uses the current UI locale
        EnhancedDatePicker datePicker = new EnhancedDatePicker();
        NativeButton locale1 = new NativeButton("Locale: US");
        NativeButton locale2 = new NativeButton("Locale: UK");
        NativeButton locale3 = new NativeButton("Locale: CHINA");

        locale1.addClickListener(e -> {
            datePicker.setLocale(Locale.US);
            UpdateMessage(message, datePicker);
        });
        locale2.addClickListener(e -> {
            datePicker.setLocale(Locale.UK);
            UpdateMessage(message, datePicker);
        });
        locale3.addClickListener(e -> {
            datePicker.setLocale(Locale.CHINA);
            UpdateMessage(message, datePicker);
        });

        datePicker.addValueChangeListener(
                event -> UpdateMessage(message, datePicker));
        // end-source-example
        locale1.setId("Locale-US");
        locale2.setId("Locale-UK");
        datePicker.setId("locale-change-picker");
        addCard("Date picker with customize locales", datePicker, locale1,
                locale2, locale3, message);
    }
    
    private void createParserDatePicker() {
        Div message = createMessageDiv("Parser-date-picker");

        EnhancedDatePicker datePicker = new EnhancedDatePicker(LocalDate.now(), "dd-MMM-yyyy");
        datePicker.setAutoOpen(false);
        UpdateMessage(message, datePicker);

        datePicker.addValueChangeListener(
                event -> UpdateMessage(message, datePicker));
        
        TextField parsingPattenOne = new TextField();
        parsingPattenOne.setLabel("Define parsing pattern A");
        parsingPattenOne.setValue("dd-MM-yyyy");
        
        TextField parsingPattenTwo = new TextField();
        parsingPattenTwo.setLabel("Define a parsing pattern B");
        parsingPattenTwo.setValue("dd/MM/yy");
        
        Button setParsingPatternBtn = new Button("Set parsing pattern from text fields A & B");
        setParsingPatternBtn.addClickListener(e -> {
            datePicker.setParsers(parsingPattenOne.getValue(), parsingPattenTwo.getValue());
            UpdateMessage(message, datePicker);
        });

        TextField formattingPatten = new TextField();
        formattingPatten.setLabel("Define a formatting pattern");
        formattingPatten.setValue("dd-MMM-yyyy");

        Button setPatternBtn = new Button("Set formatting pattern from text field");
        setPatternBtn.addClickListener(e -> {
            datePicker.setPattern(formattingPatten.getValue());
            UpdateMessage(message, datePicker);
        });

        Button dropPatternBtn = new Button("Drop formatting pattern");
        dropPatternBtn.addClickListener(e -> {
            datePicker.setPattern(null);
            UpdateMessage(message, datePicker);
        });

        // end-source-example

        datePicker.setId("Pattern-picker");

        addCard("Date picker with pattern", datePicker, message, parsingPattenOne, parsingPattenTwo,
        		setParsingPatternBtn, formattingPatten, setPatternBtn, dropPatternBtn);
    }

    // begin-source-example
    // source-example-heading: Additional code used in the demo
    /**
     * Additional code used in the demo
     */
    private void UpdateMessage(Div message, EnhancedDatePicker datePicker) {
        LocalDate selectedDate = datePicker.getValue();
        if (selectedDate != null) {
        	String parsers = null;
        	if (datePicker.getParsers() != null)
        		parsers = Arrays.toString(datePicker.getParsers());
            message.setText(
                    "Day: " + selectedDate.getDayOfMonth()
                            + "\nMonth: " + selectedDate.getMonthValue()
                            + "\nYear: " + selectedDate.getYear()
                            + "\nLocale: " + datePicker.getLocale()
                            + "\nFormatting pattern: " + datePicker.getPattern()
                            + "\nParsing pattern: " + parsers);
        } else {
            message.setText("No date is selected");
        }
    }

    private Div createMessageDiv(String id) {
        Div message = new Div();
        message.setId(id);
        message.getStyle().set("whiteSpace", "pre");
        return message;
    }
    // end-source-example
}
