package Model;

public abstract class Part {
    private int partId;
    private String partName;
    private double partPrice;
    private int partStock;
    private int partMin;
    private int partMax;

    public Part(int id, String name, double price, int stock, int min, int max){
        this.partId=id;
        this.partName=name;
        this.partPrice=price;
        this.partStock=stock;
        this.partMin=min;
        this.partMax=max;


    }
    public int getPartId(){return partId;}

    private void setPartId(int id){this.partId= id;}

    public String getPartName(){return partName;}

    public void setPartName(String name){this.partName=name;}

    public double getPartPrice(){return partPrice;}

    public void setPartPrice(double price){this.partPrice=price;}

    public int getPartStock(){return partStock;}

    public void setPartStock(int stock){this.partStock=stock;}

    public int getPartMin(){return partMin;}

    public void setPartMin(int min){this.partMin=min;}

    public int getPartMax(){return partMax;}

    public void setPartMax(int max){this.partMax=max;}



}
