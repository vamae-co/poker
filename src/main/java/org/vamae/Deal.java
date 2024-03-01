package org.vamae;

public class Deal {
    private Long opponentId;
    private double sum;
    private boolean result;

    public Deal(Long opponentId, double dealSum){
        this.opponentId = opponentId;
        sum = dealSum;
    }

    public void win(){
        result = true;
    }

    public void lose(){
        result = false;
    }
}
