package by.kudman.old;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Locale;

public class BillOut {
    User user;
    LocalDateTime ldt = LocalDateTime.now();
    DateTimeFormatter formDate1 = DateTimeFormatter.ofPattern("MM-dd-yyyy,HH-mm-ss", Locale.US);
    String dateTime1 = ldt.format(formDate1);
    DateTimeFormatter formDate2 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss", Locale.US);
    String dateTime2 = ldt.format(formDate2);
    Locale locale = new Locale("US");
    NumberFormat currency = NumberFormat.getCurrencyInstance(locale);

    public BillOut(User user) {
        this.user = user;
    }

    public void printBill() throws IOException {
        File file = new File("bill" + dateTime1 + ".txt");
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bfWriter = new BufferedWriter(fileWriter)) {
            Formatter form = new Formatter();
            purchaseInfo(form);
            bfWriter.write(stringCart(form));
            form.close();
        }
    }
    private void purchaseInfo(Formatter form){
        form.format("%s%n%s%n%s%n%-6s%s%n%70s%20s%n", "Telemag Company", "Victor Babariko <3", "by BZP", "user:",
                LoggingIn.getLoggedUser().getLogin(), "Purchase date:", dateTime2);
    }


    public String stringCart(Formatter form){
        String cute = "_".repeat(90);
        form.format("%s%n%s%s%9s%6s%10s%7s%15s%4s%8s%5s%11s%3s%7s%2s%n%s%s%n",cute, "Num", "|", "Type", "|", "Name",
                "|", "Article", "|", "Size", "|", "Price, p.", "|", "Amount", "|",cute,"|");
        int k = 1;
        for (Product product : user.getCart()) {
            int amount =0;
            for (int i =0;i<user.getThings().length;i++){
                if(Shopping.getShopList().get(i).equals(product)){
                    amount = user.getThings()[i];
                }
            }
            form.format("%-7s%-15s%-20s%-15s%-15s%-13s%-5d%s%n", k++ + ".", product.getType(), product.getName(),
                    product.getArticle(), product.getSize(), currency.format(product.getPrice()), amount, "|");
        }
        form.format("%s%s%n%72s%s%n",cute,"|", "Total: ", currency.format(user.fullPrice()));
        return form.toString();
    }
}
