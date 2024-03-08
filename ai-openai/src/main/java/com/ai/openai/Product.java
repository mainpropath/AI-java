package com.ai.openai;

public class Product {

    private String str1;
    private String str2;

    public Product(Builder builder) {
        this.str1 = builder.str1;
        this.str2 = builder.str2;
    }

    public static void main(String[] args) {
        Product build = new Builder().setStr1("str1").setStr2("str2").build();
    }

    public static class Builder {
        private static String str1;
        private static String str2;

        public Builder() {
        }

        public Builder setStr1(String str1) {
            this.str1 = str1;
            return this;
        }

        public Builder setStr2(String str2) {
            this.str2 = str2;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }

}




