package ru.javarush.partslist.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "part")
@Proxy(lazy = false)
public class Part implements Cloneable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "required")
    private boolean required;
    @Column(name = "amount")
    private int amount;

    public Part() {
    }

    public Part(int id, String name, boolean required, int amount) {
        this.id = id;
        this.name = name;
        this.required = required;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        super.clone();
        return new Part(id, new String(name), required, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        if (id != part.id) return false;
        if (required != part.required) return false;
        if (amount != part.amount) return false;
        return name.equals(part.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (required ? 1 : 0);
        result = 31 * result + amount;
        return result;
    }

}
