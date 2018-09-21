public class Order {
    private String detail;
    private int computerNumber,amount,totalPrice;

    public Order(int computerNumber, String detail, int totalPrice) {
        this.computerNumber = computerNumber;
        this.detail = detail;
        this.totalPrice = totalPrice;
    }

    public Order() {
    }

    public int getComputerNumber() {
        return computerNumber;
    }

    public void setComputerNumber(int computerNumber) {
        this.computerNumber = computerNumber;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}