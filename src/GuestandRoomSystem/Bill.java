package GuestandRoomSystem;

import exceptions.InvalidPaymentException;
import exceptions.InvalidReservationException;

import java.time.LocalDate;

public class Bill{
    private Reservation reservation;
    private double FinalAmount;
    private PaymentMethod paymentMethod;
    private LocalDate  paymentDate;

    public Bill (Reservation MYreservation,PaymentMethod MYpaymentMethod){
        setReservation(MYreservation);
        setFinalAmount();
        setPaymentMethod(MYpaymentMethod);
        setPaymentDate();
    }

    public void setReservation(Reservation reservation) {
        if (reservation==null){
            throw new InvalidReservationException("Reservation not found");
        }
        this.reservation = reservation;
    }

    public void setFinalAmount() {
        double money = reservation.CalculateTotalPrice();
        if (money<=0){
            throw new IllegalArgumentException("Invalid Amount");
        }
        this.FinalAmount=money;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod==null){
            throw new InvalidPaymentException("Invalid payment method");
        }
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentDate() {
        this.paymentDate = LocalDate.now();
    }

    public Reservation getReservation() {
        return reservation;
    }

    public double getTotalAmount() {
        return FinalAmount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void PrintBill() {
        System.out.println("---------Bill---------");
        System.out.println("Reservation details:"+reservation);
        System.out.println("Total Amount:"+FinalAmount);
        System.out.println("Payment Method:"+paymentMethod);
        System.out.println("Payment Date:"+paymentDate);
        System.out.println("---Thanks for choosing us, we hope to see you soon:)---");
    }
    @Override
    public String toString(){
        return "Reservation details: "+reservation+
                " Total Amount: "+FinalAmount+
                " Payment Method: "+paymentMethod+
                " Payment Date: "+paymentDate;
    }
}
