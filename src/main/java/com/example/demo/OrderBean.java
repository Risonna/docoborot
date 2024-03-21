package com.example.demo;


import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

@Named
@SessionScoped
public class OrderBean implements Serializable {
    private String surname;
    private String initials;
    private String password;
    private String recipientAddress;
    private int quantity;
    private String type1;
    private String type2;
    private String delivery;
    private boolean invoice;
    private String additionalInfo;

    // Getter and setter methods for all the properties

    public String submit() {
        try {
            String fileName = "orders.xlsx";
            String directoryPath = "C:\\temp";

            // Check if the directory exists, create it if not
            File directory = new File(directoryPath);
            directory.mkdirs();

            // Create or open the workbook
            String filePath = directoryPath + File.separator + fileName;
            File file = new File(filePath);
            Workbook workbook;
            Sheet sheet;
            if (file.exists()) {
                // If the file exists, open it and get the existing sheet
                FileInputStream fis = new FileInputStream(file);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheet("Orders");
            } else {
                // If the file doesn't exist, create a new workbook and sheet
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Orders");

                // Create header row if the file is new
                Row headerRow = sheet.createRow(0);
                String[] headers = {"Surname", "Initials", "Password", "Recipient Address", "Quantity",
                        "Type 1", "Type 2", "Delivery", "Invoice", "Additional Info"};
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                }
            }

            // Get the last row number
            int lastRowNum = sheet.getLastRowNum();

            // Create data row below the last row
            Row dataRow = sheet.createRow(lastRowNum + 1);
            dataRow.createCell(0).setCellValue(surname);
            dataRow.createCell(1).setCellValue(initials);
            dataRow.createCell(2).setCellValue(password);
            dataRow.createCell(3).setCellValue(recipientAddress);
            dataRow.createCell(4).setCellValue(quantity);
            dataRow.createCell(5).setCellValue(type1);
            dataRow.createCell(6).setCellValue(type2);
            dataRow.createCell(7).setCellValue(delivery);
            dataRow.createCell(8).setCellValue(invoice);
            dataRow.createCell(9).setCellValue(additionalInfo);

            // Write to file
            FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            // Provide the user with the option to download the file
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Form data saved to Excel file."));
        } catch (IOException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to save form data."));
        }

        return "success";
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getSurname(){
        return this.surname;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }
    public String getInitials(){
        return this.initials;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }
    public String getRecipientAddress(){
        return this.recipientAddress;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getQuantity(){
        return this.quantity;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }
    public String getType1(){
        return this.type1;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }
    public String getType2(){
        return this.type2;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
    public String getDelivery(){
        return this.delivery;
    }

    public void setInvoice(boolean invoice) {
        this.invoice = invoice;
    }
    public boolean getInvoice(){
        return this.invoice;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    public String getAdditionalInfo(){
        return this.additionalInfo;
    }
}
