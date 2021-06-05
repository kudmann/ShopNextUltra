package by.kudman.old;

import java.text.NumberFormat;
import java.util.Formatter;
import java.util.Locale;

public class Product {
    private String name;
    private String type;
    private String article;
    private String size;
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Product() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = (double)price/100;
    }

    @Override
    public String toString() {
      //  return name + "|" + type + "|" + article + "|" + size + "|" + price + "p.";
        Formatter form = new Formatter();
        Locale locale = new Locale("US");
        NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
        form.format("%-15s%-20s%-15s%9s%13s%7s%n", this.getType(), this.getName(),
                this.getArticle(), this.getSize(), currency.format(this.getPrice()), "|");
        return form.toString();
    }
}
