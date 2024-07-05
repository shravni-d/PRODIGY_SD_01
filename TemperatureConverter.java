import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {
    private JTextField inputField;
    private JComboBox<String> unitSelector;
    private JLabel resultLabel;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JLabel instructionLabel = new JLabel("Enter temperature value and select the unit:");
        add(instructionLabel);

        inputField = new JTextField();
        add(inputField);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        unitSelector = new JComboBox<>(units);
        add(unitSelector);

        JButton convertButton = new JButton("Convert");
        add(convertButton);

        resultLabel = new JLabel("Result will be displayed here");
        add(resultLabel);

        convertButton.addActionListener(new ConvertButtonListener());

        setVisible(true);
    }

    private class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double inputValue = Double.parseDouble(inputField.getText());
                String selectedUnit = (String) unitSelector.getSelectedItem();
                String resultText = "";

                if (selectedUnit.equals("Celsius")) {
                    double fahrenheit = celsiusToFahrenheit(inputValue);
                    double kelvin = celsiusToKelvin(inputValue);
                    resultText = String.format("Fahrenheit: %.2f, Kelvin: %.2f", fahrenheit, kelvin);
                } else if (selectedUnit.equals("Fahrenheit")) {
                    double celsius = fahrenheitToCelsius(inputValue);
                    double kelvin = fahrenheitToKelvin(inputValue);
                    resultText = String.format("Celsius: %.2f, Kelvin: %.2f", celsius, kelvin);
                } else if (selectedUnit.equals("Kelvin")) {
                    double celsius = kelvinToCelsius(inputValue);
                    double fahrenheit = kelvinToFahrenheit(inputValue);
                    resultText = String.format("Celsius: %.2f, Fahrenheit: %.2f", celsius, fahrenheit);
                }

                resultLabel.setText(resultText);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter a valid number.");
            }
        }
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }

    private double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }

    private double fahrenheitToKelvin(double fahrenheit) {
        return (fahrenheit - 32) * 5/9 + 273.15;
    }

    private double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9/5 + 32;
    }

    public static void main(String[] args) {
        new TemperatureConverter();
    }
}

